package ro.autogest.client.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import ro.autogest.client.R;

public abstract class BaseActivity extends AppCompatActivity {
    public void showError(String message) {
        //Snackbar.make(getWindow().getCurrentFocus(), message, Snackbar.LENGTH_LONG).show();
        Snackbar.make(getViewElementForError(), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public abstract View getViewElementForError();
    public abstract void handleServiceResponse(Object response);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}
