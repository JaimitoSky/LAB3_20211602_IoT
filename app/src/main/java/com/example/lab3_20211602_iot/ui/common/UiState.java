package com.example.lab3_20211602_iot.ui.common;

public class UiState<T> {
    public enum Status { LOADING, SUCCESS, EMPTY, ERROR }
    public final Status status;
    public final T data;
    public final String message;

    private UiState(Status s, T d, String m) {
        status = s; data = d; message = m;
    }
    public static <T> UiState<T> loading() { return new UiState<>(Status.LOADING, null, null); }
    public static <T> UiState<T> success(T d) { return d == null ? empty() : new UiState<>(Status.SUCCESS, d, null); }
    public static <T> UiState<T> empty() { return new UiState<>(Status.EMPTY, null, null); }
    public static <T> UiState<T> error(String m) { return new UiState<>(Status.ERROR, null, m); }
}
