package ro.autogest.client.repository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ro.autogest.client.util.Utils;

public class BackendRepository {

    private static BackendRepository instance;

    public static synchronized BackendRepository getInstance() {
        if (instance == null) {
            instance = new BackendRepository();
        }
        return instance;
    }


    public <T> void read(final String url, final Class returnType, final Callback<T> callback) {
        new GetTask(url, returnType, new Callback<T>() {

            @Override
            public void callback(T result) {
                callback.callback(result);
            }

            @Override
            public void callbackException(Exception e) {
                callback.callbackException(e);
            }
        }).execute(new String[]{});
    }

//    public <T> void readList(final String url, final Class<T[]> clazz, final Callback<List<T>> callback) {
//        new GetTask(url, new Callback<String>() {
//
//            @Override
//            public void callback(String result) {
//                final T[] array = new GsonBuilder().create().fromJson(result, clazz);
//                callback.callback(new ArrayList<T>(Arrays.asList(array)));
//            }
//        }).execute();
//    }

    public <T> void write(final String url, final Class returnType, final Object postObject, final Callback<T> callback) {


        new PostTask(url, returnType, postObject, new Callback<T>() {

            @Override
            public void callback(T result) {
                callback.callback(result);
            }

            @Override
            public void callbackException(Exception e) {
                callback.callbackException(e);
            }
        }).execute(new String[]{});
    }
}