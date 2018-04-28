package ro.autogest.client.util;

import android.app.Activity;
import android.app.ProgressDialog;

public class Utils {
    public final static String SERVER_ADDRESS = "http://192.168.1.45:8080/auto-gest-server/rest/";

    public static final ProgressDialog triggerProgressDialog(Activity activity, String text){
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(text);
        progressDialog.show();
        return progressDialog;
    }

    public static final ProgressDialog triggerProgressDialog(Activity activity){
        return Utils.triggerProgressDialog(activity, "Contactare Server...");
    }
}
