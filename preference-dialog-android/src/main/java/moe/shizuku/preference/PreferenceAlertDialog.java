package moe.shizuku.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StyleRes;

public class PreferenceAlertDialog extends AlertDialog {

    protected PreferenceAlertDialog(Context context) {
        super(context);
    }

    protected PreferenceAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected PreferenceAlertDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
}
