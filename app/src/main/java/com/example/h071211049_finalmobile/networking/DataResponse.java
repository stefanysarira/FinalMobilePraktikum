package com.example.h071211049_finalmobile.networking;

import com.google.gson.annotations.SerializedName;

public class DataResponse<T> {
    @SerializedName("results")
    private T data;
    public T getData() {
        return data;
    }
}
