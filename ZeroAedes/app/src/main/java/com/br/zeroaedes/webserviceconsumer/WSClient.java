package com.br.zeroaedes.webserviceconsumer;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Tiago Alencar on 29/12/15.
 */
public class WSClient {
    public static final String PREFERENCIAS = "br.com.prolins.niceCook.wsconsumer.PREFERENCIAS";
    public static final String LOGIN_TOKEN = "br.com.prolins.niceCook.wsconsumer.LOGIN_TOKEN";
    public static final String LOGIN_TOKEN_FACE = "br.com.prolins.niceCook.wsconsumer.LOGIN_TOKEN_FACE";
    public static final String LOGIN_IDUSUARIO = "br.com.prolins.niceCook.wsconsumer.LOGIN_IDUsuario";
    public static final String USER_FACEBOOK = "br.com.prolins.niceCook.wsconsumer.LOGIN_IDUSER_FACEBOOK";
    public static final String RESPONSE_FACEBOOK = "br.com.prolins.niceCook.wsconsumer.LOGIN_IDRESPONSE_FACEBOOK";

    private static final String BASE_URL = "http://suporte.prolins.com.br/nicecook/api";
    private static final String TAG = "WSClientService";
    public static String BASE_URL_IMAGES = "http://suporte.prolins.com.br/nicecook/images/";//tem que colocar recipe para receitas e profile para perfil

    private ApiService apiService;

    public WSClient(final Context context) {
        InterceptingOkClient interceptingOkClient = new InterceptingOkClient(new OkHttpClient());

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                request.addHeader("Device", android_id);
                SharedPreferences pref = context.getApplicationContext().getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE);
                String token = pref.getString(LOGIN_TOKEN, "");
                request.addHeader("Token", token);
                request.addHeader("Cache-Control", "no-cache");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
//                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(requestInterceptor)
//                .setLog(new AndroidLog(TAG))
                .setClient(interceptingOkClient)
                .build();

        apiService = restAdapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}


