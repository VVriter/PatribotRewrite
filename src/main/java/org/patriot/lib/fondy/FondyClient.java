package org.patriot.lib.fondy;

import lombok.Getter;
import okhttp3.*;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import static org.patriot.lib.fondy.FondyUtil.generateSignature;

public class FondyClient {

    private final String API_ENDPOINT = "https://api.fondy.eu/api/checkout/url/";


    @Getter private String merchantId;
    @Getter private String signatureProtector;
    @Getter private String moneyAmount;
    @Getter private String currency;
    @Getter private String lang;
    @Getter private String orderDescription;

    //Generated
    @Getter private String orderId;


    public FondyClient(Builder builder) {
        this.merchantId = builder.merchantId;
        this.signatureProtector = builder.signatureProtector;
        this.moneyAmount = builder.moneyAmount;
        this.currency = builder.currency;
        this.lang = builder.lang;
        this.orderDescription = builder.orderDescription;

        this.orderId = UUID.randomUUID().toString().substring(0, 10);
    }


    public String generatePaymentUrl() throws Exception {
        final Map<String, Object> params = new TreeMap<>();
        params.put("amount", getMoneyAmount());
        params.put("currency", getCurrency());
        params.put("lang", getLang());
        params.put("merchant_id", getMerchantId());
        params.put("order_desc", getOrderDescription());
        params.put("order_id", getOrderId());
        params.put("response_url", "https://patriot-csgo.com/");
        params.put("server_callback_url", "https://patriot-csgo.com/");
        params.put("version", "1.0.1");
        params.put("signature", generateSignature(params, getSignatureProtector()));

        final OkHttpClient client = new OkHttpClient();
        final MediaType mediaType = MediaType.parse("application/json");
        final RequestBody body = RequestBody.create(mediaType, new JSONObject().put("request",  new JSONObject(params)).toString());
        final Request request = new Request.Builder()
                .url(API_ENDPOINT)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        final Response response = client.newCall(request).execute();

        final JSONObject res = new JSONObject(response.body().string());

        return res.getJSONObject("response").getString("checkout_url");
    }



    public static class Builder {

        @Getter private String merchantId;
        @Getter private String signatureProtector;
        @Getter private String moneyAmount;
        @Getter private String currency;
        @Getter private String lang;
        @Getter private String orderDescription;

        public Builder setMerchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder setSignatureProtector(String signatureProtector) {
            this.signatureProtector = signatureProtector;
            return this;
        }

        public Builder setMoneyAmount(String moneyAmount) {
            this.moneyAmount = moneyAmount + "00";
            return this;
        }

        public Builder setCurrency(Currency currency) {
            switch (currency) {
                case RUB: this.currency = "RUB"; return this;
                case UAH: this.currency = "UAH"; return this;
                case USD: this.currency = "USD"; return this;
                default: this.currency = "UAH"; return this;
            }
        }

        public Builder setLang(Lang lang) {
            switch (lang) {
                case ENGLISH: this.lang = "EN"; return this;
                case RUSSIAN: this.lang = "RU"; return this;
                case UKRAINIAN: this.lang = "UA"; return this;
                default: this.lang = "RU"; return this;
            }
        }

        public Builder setDescription(String description) {
            this.orderDescription = description;
            return this;
        }

        public FondyClient build() {
            return new FondyClient(this);
        }

    }


}
