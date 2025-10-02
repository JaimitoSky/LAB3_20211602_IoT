package com.example.lab3_20211602_iot.core;

public interface SimpleCallback<T> {
    void onResult(Result<T> result);
}
