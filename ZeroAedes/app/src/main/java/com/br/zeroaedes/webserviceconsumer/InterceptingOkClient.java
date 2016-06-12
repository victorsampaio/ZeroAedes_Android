package com.br.zeroaedes.webserviceconsumer;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

public class InterceptingOkClient extends OkClient {
    public InterceptingOkClient(OkHttpClient client) {
        super(client);
        client.setConnectTimeout(1, TimeUnit.MINUTES);
        client.setReadTimeout(1, TimeUnit.MINUTES);
    }

    @Override
    public Response execute(Request request) throws IOException {
        Response response = super.execute(request);
        return response;
    }


}
