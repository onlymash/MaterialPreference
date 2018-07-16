package moe.shizuku.preference;

import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

public class PreferenceDialogCompatBuilder {

    private AlertDialog.Builder mBuilder;

    public PreferenceDialogCompatBuilder(Context context) {
        mBuilder = new AlertDialog.Builder(context);
    }

    public PreferenceDialogCompatBuilder(Context context, int themeResId) {
        mBuilder = new AlertDialog.Builder(context, themeResId);
    }

    public PreferenceDialogCompatBuilder setTitle(int titleId) {
        mBuilder.setTitle(titleId);
        return this;
    }

    public PreferenceDialogCompatBuilder setTitle(CharSequence title) {
        mBuilder.setTitle(title);
        return this;
    }

    public PreferenceDialogCompatBuilder setCustomTitle(View customTitleView) {
        mBuilder.setCustomTitle(customTitleView);
        return this;
    }

    public PreferenceDialogCompatBuilder setMessage(int messageId) {
        mBuilder.setMessage(messageId);
        return this;
    }

    public PreferenceDialogCompatBuilder setMessage(CharSequence message) {
        mBuilder.setMessage(message);
        return this;
    }

    public PreferenceDialogCompatBuilder setIcon(int iconId) {
        mBuilder.setIcon(iconId);
        return this;
    }

    public PreferenceDialogCompatBuilder setIcon(Drawable icon) {
        mBuilder.setIcon(icon);
        return this;
    }

    public PreferenceDialogCompatBuilder setIconAttribute(int attrId) {
        mBuilder.setIconAttribute(attrId);
        return this;
    }

    public PreferenceDialogCompatBuilder setPositiveButton(int textId, OnClickListener listener) {
        mBuilder.setPositiveButton(textId, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setPositiveButton(CharSequence text, OnClickListener listener) {
        mBuilder.setPositiveButton(text, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setNegativeButton(int textId, OnClickListener listener) {
        mBuilder.setNegativeButton(textId, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setNegativeButton(CharSequence text, OnClickListener listener) {
        mBuilder.setNegativeButton(text, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setNeutralButton(int textId, OnClickListener listener) {
        mBuilder.setNeutralButton(textId, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setNeutralButton(CharSequence text, OnClickListener listener) {
        mBuilder.setNeutralButton(text, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setCancelable(boolean cancelable) {
        mBuilder.setCancelable(cancelable);
        return this;
    }

    public PreferenceDialogCompatBuilder setOnCancelListener(OnCancelListener onCancelListener) {
        mBuilder.setOnCancelListener(onCancelListener);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public PreferenceDialogCompatBuilder setOnDismissListener(OnDismissListener onDismissListener) {
        mBuilder.setOnDismissListener(onDismissListener);
        return this;
    }

    public PreferenceDialogCompatBuilder setOnKeyListener(OnKeyListener onKeyListener) {
        mBuilder.setOnKeyListener(onKeyListener);
        return this;
    }

    public PreferenceDialogCompatBuilder setItems(int itemsId, OnClickListener listener) {
        mBuilder.setItems(itemsId, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setItems(CharSequence[] items, OnClickListener listener) {
        mBuilder.setItems(items, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setAdapter(ListAdapter adapter, OnClickListener listener) {
        mBuilder.setAdapter(adapter, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setCursor(Cursor cursor, OnClickListener listener, String labelColumn) {
        mBuilder.setCursor(cursor, listener, labelColumn);
        return this;
    }

    public PreferenceDialogCompatBuilder setMultiChoiceItems(int itemsId, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
        mBuilder.setMultiChoiceItems(itemsId, checkedItems, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
        mBuilder.setMultiChoiceItems(items, checkedItems, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, OnMultiChoiceClickListener listener) {
        mBuilder.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setSingleChoiceItems(int itemsId, int checkedItem, OnClickListener listener) {
        mBuilder.setSingleChoiceItems(itemsId, checkedItem, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, OnClickListener listener) {
        mBuilder.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setSingleChoiceItems(CharSequence[] items, int checkedItem, OnClickListener listener) {
        mBuilder.setSingleChoiceItems(items, checkedItem, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setSingleChoiceItems(ListAdapter adapter, int checkedItem, OnClickListener listener) {
        mBuilder.setSingleChoiceItems(adapter, checkedItem, listener);
        return this;
    }

    public PreferenceDialogCompatBuilder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        mBuilder.setOnItemSelectedListener(listener);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PreferenceDialogCompatBuilder setView(int layoutResId) {
        mBuilder.setView(layoutResId);
        return this;
    }

    public PreferenceDialogCompatBuilder setView(View view) {
        mBuilder.setView(view);
        return this;
    }

    public Dialog create() {
        return mBuilder.create();
    }

    public Dialog show() {
        return mBuilder.show();
    }
}
