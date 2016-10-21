package org.telegram;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.methods.Constants;
import org.telegram.api.methods.SetWebhook;
import org.telegram.services.BotLogger;
import org.telegram.updateshandlers.SentCallback;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Send Helper
 * @date 20 of June of 2015
 */
public class SenderHelper {
    private static volatile BotLogger log = BotLogger.getLogger(SenderHelper.class.getName());
    private static final ExecutorService exe = Executors.newSingleThreadExecutor();



    public static void SendWebhook(String webHookURL, String botToken) {
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            String url = Constants.BASEURL + botToken + "/" + SetWebhook.PATH;

            HttpPost httppost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody(SetWebhook.URL_FIELD, webHookURL);
            if (Constants.pathToCertificatePublicKey != null) {
                builder.addBinaryBody(SetWebhook.CERTIFICATE_FIELD, new File(Constants.pathToCertificatePublicKey), ContentType.APPLICATION_OCTET_STREAM, Constants.certificatePublicKeyFileName);
            }
            HttpEntity multipart = builder.build();
            httppost.setEntity(multipart);
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity ht = response.getEntity();
            BufferedHttpEntity buf = new BufferedHttpEntity(ht);
            String responseContent = EntityUtils.toString(buf, "UTF-8");
            log.debug(responseContent);
        } catch (IOException e) {
            log.error(e);
        }

    }

    public static void SendApiMethod(BotApiMethod method, String botToken) {
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            String url = Constants.BASEURL + botToken + "/" + method.getPath();
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader("charset", "UTF-8");
            httppost.setEntity(new StringEntity(method.toJson().toString(), ContentType.APPLICATION_JSON));
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity ht = response.getEntity();
            BufferedHttpEntity buf = new BufferedHttpEntity(ht);
            String responseContent = EntityUtils.toString(buf, "UTF-8");

            JSONObject jsonObject = new JSONObject(responseContent);
            if (!jsonObject.getBoolean("ok")) {
                throw new InvalidObjectException(jsonObject.toString());
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    public static void SendApiMethodAsync(BotApiMethod method, String botToken, SentCallback callback) {
        exe.submit(() -> {
            try {
                CloseableHttpClient httpclient = HttpClientBuilder.create().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
                String url = Constants.BASEURL + botToken + "/" + method.getPath();
                HttpPost httppost = new HttpPost(url);
                httppost.addHeader("charset", "UTF-8");
                httppost.setEntity(new StringEntity(method.toJson().toString(), ContentType.APPLICATION_JSON));
                CloseableHttpResponse response = httpclient.execute(httppost);
                HttpEntity ht = response.getEntity();
                BufferedHttpEntity buf = new BufferedHttpEntity(ht);
                String responseContent = EntityUtils.toString(buf, "UTF-8");

                JSONObject jsonObject = new JSONObject(responseContent);
                if (!jsonObject.getBoolean("ok")) {
                    callback.onError(method, jsonObject);
                }
                callback.onResult(method, jsonObject);
            } catch (IOException e) {
                log.error(e);
            }
        });
    }
}
