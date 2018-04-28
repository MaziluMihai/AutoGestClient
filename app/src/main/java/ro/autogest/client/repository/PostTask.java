package ro.autogest.client.repository;

import android.os.AsyncTask;
import android.util.Log;


import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

public class PostTask<T> extends AsyncTask<String, String, T> {

    private final String url;
    private final Object postObject;
    private final Callback<T> callback;
    private boolean exceptionOccured;
    private Class returnType;


    public PostTask(String url, Class returnType, Object postObject, Callback<T> callback) {
        this.url = url;
        this.postObject = postObject;
        this.callback = callback;
        exceptionOccured = false;
        this.returnType = returnType;
    }


    @Override
    protected T doInBackground(String...strings) {
        exceptionOccured = false;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            return (T)restTemplate.postForEntity(url, postObject,returnType).getBody();
        } catch (Exception e) {
            exceptionOccured = true;
            Log.e("PostTask","Rest Exception", e);
            callback.callbackException(e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(T result) {
        if(!exceptionOccured) {
            callback.callback(result);
        }
        super.onPostExecute(result);
    }
}