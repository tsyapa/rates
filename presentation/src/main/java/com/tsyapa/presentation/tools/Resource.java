package com.tsyapa.presentation.tools;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final Throwable error;
    public final boolean isLoading;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable Throwable error, boolean isLoading) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.isLoading = isLoading;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null, false);
    }

    public static <T> Resource<T> error(Throwable error) {
        return new Resource<>(Status.ERROR, null, error, false);
    }

    public static <T> Resource<T> loading(boolean isLoading) {
        return new Resource<>(Status.LOADING, null, null, isLoading);
    }

    public enum Status {SUCCESS, ERROR, LOADING}
}