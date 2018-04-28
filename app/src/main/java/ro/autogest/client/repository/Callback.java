package ro.autogest.client.repository;

public interface Callback<T> {
    void callback(T t);
    void callbackException(Exception e);
}
