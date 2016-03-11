package rikka.materialpreference;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Rikka on 2015/12/1.
 */
public class EditTextPreference extends android.support.v7.preference.EditTextPreference {
    private boolean showValueSummary;
    private int inputType;

    public EditTextPreference(Context context) {
        super(context);
    }

    public EditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyPreference);
        showValueSummary = a.getBoolean(R.styleable.MyPreference_showValueSummary, true);
        a.recycle();

        a = context.obtainStyledAttributes(attrs, R.styleable.EditTextPreference);
        inputType = a.getInt(R.styleable.EditTextPreference_textInputType, InputType.TYPE_NULL);
        a.recycle();
    }

    public EditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        if (showValueSummary) {
            setSummary(text);
        }
    }

    public int getInputType() {
        return inputType;
    }

    @Override
    protected void onClick() {
        super.onClick();
    }
}

