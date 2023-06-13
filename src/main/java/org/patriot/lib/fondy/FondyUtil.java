package org.patriot.lib.fondy;

import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

public class FondyUtil {


    public static String getPaymentStatus(String orderId, String protectionCode) throws IOException {
        Map<String, Object> map = new TreeMap<>();
        map.put("order_id", orderId);
        map.put("merchant_id", "1521980");
        map.put("signature", generateSignature(map, protectionCode));
        JSONObject object = new JSONObject()
                .put("request", new JSONObject(map));

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, object.toString());
        Request request = new Request.Builder()
                .url("https://pay.fondy.eu/api/status/order_id")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        JSONObject o = new JSONObject(response.body().string()).getJSONObject("response");
        return o.getString("order_status");
    }




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
