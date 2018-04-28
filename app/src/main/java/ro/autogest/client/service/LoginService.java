package ro.autogest.client.service;

import android.app.ProgressDialog;

import ro.autogest.client.activity.LoginActivity;
import ro.autogest.client.repository.Callback;
import ro.autogest.client.repository.BackendRepository;
import ro.autogest.client.repository.model.BooleanResponse;
import ro.autogest.client.repository.model.CredentialeLogin;
import ro.autogest.client.util.Utils;

public class LoginService extends BaseService {

    private static LoginService instance;

    public void login(final LoginActivity activity, CredentialeLogin credentialeLogin) {
        final BackendRepository backendRepository = BackendRepository.getInstance();
        final String url = Utils.SERVER_ADDRESS + "user/login";
        final ProgressDialog progressDialog = Utils.triggerProgressDialog(activity);

        backendRepository.write(url, BooleanResponse.class, credentialeLogin, new Callback<BooleanResponse>() {
            @Override
            public void callbackException(final Exception e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        activity.showError(e.getMessage());
                        activity.onLoginFailed();
                    }
                });

            }

            @Override
            public void callback(final BooleanResponse response) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        activity.handleServiceResponse(response.getResponse());
                    }
                    });
            }
        });
    }

    public static LoginService getInstance() {
        if(instance == null) {
            instance = new LoginService();
        }
        return instance;
    }
}
