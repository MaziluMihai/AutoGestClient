package ro.autogest.client.service;

import android.app.ProgressDialog;

import ro.autogest.client.repository.BackendRepository;
import ro.autogest.client.repository.Callback;
import ro.autogest.client.repository.model.BooleanResponse;
import ro.autogest.client.activity.BaseActivity;
import ro.autogest.client.repository.model.Utilizator;
import ro.autogest.client.util.SessionData;
import ro.autogest.client.util.Utils;

public class UserService extends BaseService {

    private static UserService instance;

    public void getUserInfo(final BaseActivity activity) {
        final BackendRepository backendRepository = BackendRepository.getInstance();
        final String url = Utils.SERVER_ADDRESS + "user/email/" + SessionData.loggedInUserEmail;
        final ProgressDialog progressDialog = Utils.triggerProgressDialog(activity);

        backendRepository.read(url, Utilizator.class, new Callback<Utilizator>() {
            @Override
            public void callbackException(final Exception e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        activity.showError(e.getMessage());
                    }
                });

            }

            @Override
            public void callback(final Utilizator response) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        activity.handleServiceResponse(response);
                    }
                    });
            }
        });
    }

    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }
        return instance;
    }
}
