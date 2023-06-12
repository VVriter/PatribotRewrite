package org.patriot.lib.fondy;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.StringJoiner;

public class FondyUtil {

    public static String generateSignature(Map<String, Object> parameters, String protectionCode) {
        final StringJoiner signature = new StringJoiner("|");
        signature.add(protectionCode);
        parameters.forEach((key, value) -> {
            if (value.toString().isEmpty() || "signature".equals(key) || "response_signature_string".equals(key)) {
                return;
            }
            signature.add(value.toString());
        });
        return DigestUtils.sha1Hex(signature.toString());
    }

}
