package com.giu.converter;

import com.giu.domain.Policy;
import com.giu.representation.PolicyRepresentation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.join;
import static java.lang.System.in;

/**
 * Created by martin on 09/08/16.
 */
@Component
public class PolicyConverter {

    public List<PolicyRepresentation> convert(Iterable<Policy> policies) {
        List<PolicyRepresentation> result = new ArrayList<>();
        policies.forEach(it -> result.add((convert(it))));
        return result;
    }

    private PolicyRepresentation convert(Policy policy) {
        PolicyRepresentation result = new PolicyRepresentation();
        //TODO: Cambiar a string como vimos con marto
        result.setRegex(convertToBindingString(policy.getCn()));
        return result;
    }

    public static String convertToBindingString(String objectGUID) {
        StringBuilder displayStr = new StringBuilder();

        displayStr.append("");
        displayStr.append(convertToDashedString(objectGUID.getBytes()));
        displayStr.append("");

        return displayStr.toString();
    }

    public static String convertToDashedString(byte[] objectGUID) {
        StringBuilder displayStr = new StringBuilder();

        displayStr.append(prefixZeros((int) objectGUID[3] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[2] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[1] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[0] & 0xFF));
        displayStr.append("-");
        displayStr.append(prefixZeros((int) objectGUID[5] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[4] & 0xFF));
        displayStr.append("-");
        displayStr.append(prefixZeros((int) objectGUID[7] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[6] & 0xFF));
        displayStr.append("-");
        displayStr.append(prefixZeros((int) objectGUID[8] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[9] & 0xFF));
        displayStr.append("-");
        displayStr.append(prefixZeros((int) objectGUID[10] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[11] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[12] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[13] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[14] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[15] & 0xFF));

        return displayStr.toString();
    }

    private static String prefixZeros(int value) {
        if (value <= 0xF) {
            StringBuilder sb = new StringBuilder("0");
            sb.append(Integer.toHexString(value));

            return sb.toString();

        } else {
            return Integer.toHexString(value);
        }
    }






}
