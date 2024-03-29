package org.torusresearch.fetchnodedetails.types;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.Header;

public class APIUtils {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient().newBuilder().writeTimeout(10, TimeUnit.SECONDS).build();

    private APIUtils() {
    }

    public static Callback toCallback(CompletableFuture<String> future) {
        return new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try {
                    future.complete(Objects.requireNonNull(response.body()).string());
                } catch (IOException e) {
                    e.printStackTrace();
                    future.completeExceptionally(e);
                }
            }
        };
    }

    public static CompletableFuture<String> get(String url) {
        return _get(url, new Header[0]);
    }

    public static CompletableFuture<String> get(String url, Header[] headers) {
        return _get(url, headers);
    }

    private static CompletableFuture<String> _get(String url, Header[] headers) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        for (Header header : headers) {
            requestBuilder.addHeader(header.name.utf8(), header.value.utf8());
        }
        Request request = requestBuilder.build();
        CompletableFuture<String> future = new CompletableFuture<>();
        client.newCall(request).enqueue(toCallback(future));
        return future;
    }
}
