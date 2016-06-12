package com.br.zeroaedes.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Victor on 10/04/2016.
 */

public class Focus {

    @SerializedName("Version")
    public String version;

    @SerializedName("Content")
    public String content;

    @SerializedName("StatusCode")
    public String statusCode;

    @SerializedName("ReasonPhrase")
    public String reasonPhrase;

    @SerializedName("Headers")
    public String headers;

    @SerializedName("RequestMessage")
    public String requestMessage;

    @SerializedName("IsSuccessStatusCode")
    public String isSuccessStatusCode;

}

/*
* Version
* Content
* StatusCode
* ReasonPhrase
* Headers
* RequestMessage
* IsSuccessStatusCode
* */