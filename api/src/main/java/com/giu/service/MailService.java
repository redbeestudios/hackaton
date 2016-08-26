package com.giu.service;

import com.giu.domain.User;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by martin on 26/07/16.
 */
@Service
public class MailService implements RegistrationService{

    private static final String CHARSET_UTF8 = "UTF-8";



    private JavaMailSender mailSender;
    private RedisService redisService;
    private VelocityEngine velocityEngine;

    @Autowired
    public void setMailSender(JavaMailSender mailSender, RedisService redisService) {
        this.mailSender = mailSender;
        this.redisService = redisService;
    }

    @Autowired
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Override
    public void register(User user) {
        sendConfirmationEmail(user);
    }

    @Override
    public void sendMeNewPassword(User user, String password) {
        sendNewPassword(user, password);
    }

    private void sendNewPassword(final User user, final String password) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getAlternativeMail());
                message.setSubject("Nueva Cuenta");
                Map model = new HashMap();
                model.put("name",user.getCn());
                model.put("user", user);
                model.put("password",password);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "/templates/forgetPass.vm",CHARSET_UTF8, model);
                // guardar el has en redis
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }

    private void sendConfirmationEmail(final User user) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getAlternativeMail());
                message.setFrom("julian.avaca44@gmail.com");
                message.setSubject("Nuevo Usuario");
                Map model = new HashMap();
                String hash = generateHash();
                model.put("name",user.getCn());
                model.put("user", user);
                model.put("link", "http://localhost:18093/#/password/" + hash );
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "/templates/registration.vm",CHARSET_UTF8, model);
                // guardar el has en redis
                redisService.setValue(hash,user.getEmployeeID());
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }

    private String generateHash(){
        return UUID.randomUUID().toString();
    }

}
