package rikka.materialpreference;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rikka on 2015/12/1.
 */
public class ListPreference extends android.support.v7.preference.ListPreference {
    private boolean showValueSummary;

    public ListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyPreference);
        showValueSummary = a.getBoolean(R.styleable.MyPreference_showValueSummary, true);
        a.recycle();
    }

    public ListPreference(Context context) {
        super(context, null);
    }

    @Override
    public void setValue(String index) {
        super.setValue(index);
        if (showValueSummary) {
            setSummary(getEntries()[findIndexOfValue(index)]);
        }
    }

    @Override
    protected void onClick() {
        super.onClick();

        /*new AlertDialog.Builder(getContext(), R.style.PreferenceTheme_Dialog)
                .setTitle(getDialogTitle())
                .setMessage(getDialogMessage())
                .setIcon(getDialogIcon())
                .setNegativeButton(getNegativeButtonText(), null)
                .setSingleChoiceItems(getEntries(), findIndexOfValue(getValue()), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setValueIndex(which);
                        dialog.dismiss();
                    }
                })
                .show();*/
    }
}

