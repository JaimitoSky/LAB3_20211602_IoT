package com.example.lab3_20211602_iot.core;

public class Result<T> {
    public final boolean isSuccess;
    public final T data;
    public final Throwable error;

    private Result(boolean ok, T d, Throwable e) {
        this.isSuccess = ok;
        this.data = d;
        this.error = e;
    }

    public static <T> Result<T> success(T d) {
        return new Result<>(true, d, null);
    }

    public static <T> Result<T> failure(Throwable e) {
        return new Result<>(false, null, e);
    }
}
