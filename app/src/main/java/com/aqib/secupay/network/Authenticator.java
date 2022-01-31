package com.aqib.secupay.network;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class Authenticator implements Interceptor {

    private String credentials;
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        request = request.newBuilder().header("Authorization", this.credentials).build();
        Response re = chain.proceed(request);
        Intrinsics.checkNotNullExpressionValue(re, "chain.proceed(request)");
        return re;

    }

    public Authenticator(@NotNull String username, @NotNull String password) {
        super();
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        String s = Credentials.basic(username, password);
        Intrinsics.checkNotNullExpressionValue(s, "Credentials.basic(username, password)");
        this.credentials = s;
    }
}
