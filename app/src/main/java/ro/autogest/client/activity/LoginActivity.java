package ro.autogest.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ro.autogest.client.R;
import ro.autogest.client.repository.model.CredentialeLogin;
import ro.autogest.client.service.LoginService;
import ro.autogest.client.util.SessionData;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
 //       setSupportActionBar(toolbar);


        loginButton = findViewById(R.id.btn_login);
        emailEditText = findViewById(R.id.input_email);
        passwordEditText = findViewById(R.id.input_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

//        _signupLink.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // Start the Signup activity
//                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
//                startActivityForResult(intent, REQUEST_SIGNUP);
//            }
//        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        loginService = LoginService.getInstance();
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            return;
        }

        loginButton.setEnabled(false);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // TODO: Implement your own authentication logic here.
        LoginService.getInstance().login(this, new CredentialeLogin(email,password));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }



    public void onLoginFailed() {
        //Snackbar.make(findViewById(R.id.fab), "Login failed", Snackbar.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("enter a valid email address");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordEditText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        return valid;
    }

    @Override
    public void handleServiceResponse(Object response) {
        if(((Boolean)response) == true) {
            SessionData.loggedInUserEmail = emailEditText.getText().toString();

            Intent intent = new Intent(getApplicationContext(), DasboardActivity.class);
            startActivityForResult(intent, REQUEST_SIGNUP);
            finish();
        } else {
            Snackbar.make(getViewElementForError(), "Emailul sau parola introduse sunt incorecte", Snackbar.LENGTH_LONG).show();
            onLoginFailed();
        }
    }


    @Override
    public View getViewElementForError() {
        return findViewById(R.id.fab);
    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }
}
