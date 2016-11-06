package com.pinterest.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by harsh on 05/11/16.
 */
public class HttpManager {

    private static OkHttpClient normalClient;


    public static void initialize(){
        OkHttpClient.Builder normalBuilder = new OkHttpClient.Builder();
        try {
            normalBuilder.connectTimeout(2, TimeUnit.MINUTES);
            normalBuilder.readTimeout(2, TimeUnit.MINUTES);
            normalBuilder.writeTimeout(2, TimeUnit.MINUTES);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        normalClient = normalBuilder.build();
    }

    private static OkHttpClient.Builder setSSLTrustStore(OkHttpClient.Builder builder) {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
//            context.init(null, new TrustManager[]{new PubKeyManager()}, null);
            SSLSocketFactory sslSocketFactory = context.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory);
            return builder;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return builder;
    }

    @Nullable
    public static Response execute(final Request request) throws IOException {

        try {

            return normalClient.newCall(request).execute();

        } catch (SSLHandshakeException sslHandshakeException) {   // pin the server and get new keys

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * set cookie
     * @param builder
     */
    @NonNull
    private static OkHttpClient.Builder setCookie(OkHttpClient.Builder builder) {

        return builder;
    }

    /**
     * add headers
     * @param ctx
     * @param builder
     */
    public static Request.Builder setHTTPHeaders(@NonNull final Context ctx, Request.Builder builder) {

        builder.header("Accept-Encoding", "gzip");
        return builder;
    }

}