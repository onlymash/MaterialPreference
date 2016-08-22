package rikka.materialpreference;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A version of {@link ListPreference} that presents the options in a drop down menu rather than a dialog.
 */
public class SimpleMenuPreference extends ListPreference {

    private final Context mContext;

    private final Adapter mAdapter;

    private PreferenceViewHolder mViewHolder;
    private PopupWindow mPopupWindow;
    private AlertDialog mAlertDialog;

    private int mLeftPadding;
    private int mPopupPadding;
    private int mListPadding;
    private int mListItemHeight;
    private RecyclerView mRecyclerView;

    private boolean mShowPopup;
    private boolean mShowingDialog;

    private boolean mShouldCalcUseDialog;
    private boolean mUseDialog;

    public SimpleMenuPreference(Context context) {
        this(context, null);
    }

    public SimpleMenuPreference(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.simpleMenuPreferenceStyle);
    }

    public SimpleMenuPreference(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs, defStyle, 0);
    }

    public SimpleMenuPreference(Context context, AttributeSet attrs, int defStyleAttr,
                              int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.SimpleMenuPreference, defStyleAttr, defStyleRes);

        mContext = context;
        mAdapter = createAdapter();
        mPopupWindow = createPopupMenu(a);
        mAlertDialog = createDialog();
        a.recycle();

        updateEntries();
    }

    protected Adapter createAdapter() {
        return new Adapter();
    }

    private AlertDialog createDialog() {
        return new AlertDialog.Builder(mContext)
                .setView(mRecyclerView)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mShowingDialog = false;
                    }
                })
                .create();
    }

    private PopupWindow createPopupMenu(TypedArray a) {
        mListPadding = (int) a.getDimension(R.styleable.SimpleMenuPreference_listPadding, 0);
        mListItemHeight = (int) a.getDimension(R.styleable.SimpleMenuPreference_listItemHeight, 0);
        mPopupPadding = (int) a.getDimension(R.styleable.SimpleMenuPreference_popupPadding, 0);

        //mRecyclerView = new RecyclerView(mContext);
        mRecyclerView = (RecyclerView) LayoutInflater.from(mContext).inflate(R.layout.simple_menu_recycler_view, null);
        mRecyclerView.setPadding(0, mListPadding, 0, mListPadding);
        mRecyclerView.setFocusable(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        float elevation = a.getDimension(R.styleable.SimpleMenuPreference_popupElevation, 0);
        Drawable background = a.getDrawable(R.styleable.SimpleMenuPreference_popupBackground);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            LayerDrawable layerDrawable = (LayerDrawable) background;

            Drawable in = ContextCompat.getDrawable(mContext, R.drawable.simple_menu_background_pre_lolipop);
            TypedValue typedValue = new TypedValue();
            if (background != null &&
                    getContext().getTheme().resolveAttribute(android.R.attr.windowBackground, typedValue, true)) {

                in = DrawableCompat.wrap(in);
                DrawableCompat.setTint(in, ContextCompat.getColor(getContext(), typedValue.resourceId));

                layerDrawable.setDrawableByLayerId(R.id.simple_menu_popup_background, in);
            }
        }

        PopupWindow popupWindow = new PopupWindow(mContext);
        popupWindow.setContentView(mRecyclerView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(background);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(elevation);
        }

        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.listPreferredItemPaddingLeft, typedValue, true);
        mLeftPadding = (int) mContext.getResources().getDimension(typedValue.resourceId);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // 2px for drawable
            mLeftPadding -= 2;
        }

        return popupWindow;
    }

    @Override
    protected void onClick() {
        if (getEntries() == null || getEntries().length == 0) {
            return;
        }

        if (mPopupWindow == null) {
            return;
        }

        if (mUseDialog) {
            showDialog();
        } else {
            showPopupMenu();
        }
    }

    private void resetUseDialog() {
        if (!mShouldCalcUseDialog || mViewHolder == null) {
            return;
        }

        mShouldCalcUseDialog = false;

        boolean useDialog = shouldUseDialog();
        if (mUseDialog != useDialog) {
            mUseDialog = useDialog;

            if (mUseDialog && mPopupWindow.getContentView() != null) {
                mPopupWindow.setContentView(null);
                mAlertDialog.setView(mRecyclerView);
            }

            if (!mUseDialog) {
                mAlertDialog.setView(null);
                mPopupWindow.setContentView(mRecyclerView);
            }

            mAdapter.notifyDataSetChanged();

            /*if (mRecyclerView.getParent() != null
                && mRecyclerView.getParent() instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) mRecyclerView.getParent();
                    vg.removeView(mRecyclerView);
            }*/
        }
    }

    private void showDialog() {
        if (!mAlertDialog.isShowing()) {
            mAlertDialog.show();
            mShowingDialog = true;
        }
    }
    /**
     * Show PopupMenu and calculate to align selected menu item over list item vertically
     */
    private void showPopupMenu() {
        int index = getValueIndex();
        index = index < 0 ? 0 : index;

        int y_off = -mViewHolder.itemView.getHeight() - index * mListItemHeight;

        int[] rect = new int[2];
        mViewHolder.itemView.getLocationInWindow(rect);

        int anchor_y = rect[1];

        int maxHeight = mPopupWindow.getMaxAvailableHeight(mViewHolder.itemView, -anchor_y);
        final int height = mListItemHeight * getEntries().length + mListPadding * 2;

        if (height > maxHeight) {
            y_off = -anchor_y + mPopupPadding;

            // scroll to select item
            final int scroll = (int) ((index + 1) * mListItemHeight + y_off + mPopupPadding * 1.5);
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.scrollBy(0, -height);
                    mRecyclerView.scrollBy(0, scroll);
                }
            });

            mRecyclerView.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);

            mPopupWindow.setHeight((int) (maxHeight - mPopupPadding * 1.5));
        } else {
            // make sure PopupWindow in window
            int y = (int) (height + anchor_y + y_off + mPopupPadding * 0.5);
            if (y > maxHeight) {
                y_off -= y - maxHeight;
            } else if (y + y_off < mPopupPadding) {
                y_off = -y + mPopupPadding;
            }

            mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        mPopupWindow.showAsDropDown(mViewHolder.itemView, mLeftPadding, y_off);

        /*Log.d("SimpleMenuPreference", "index " + index
                + " height " + height
                + " windowHeight " + windowHeight
                + " anchor_y " + anchor_y
                + " " + (height + anchor_y - mViewHolder.itemView.getHeight()));*/
    }

    @Override
    public void setEntries(@NonNull CharSequence[] entries) {
        super.setEntries(entries);
        updateEntries();
    }

    private void updateEntries() {
        mAdapter.notifyDataSetChanged();

        mShouldCalcUseDialog = true;
        resetUseDialog();
    }

    /**
     * Guess whether need use dialog
     *
     * @return use dialog
     */
    private boolean shouldUseDialog() {
        if (mViewHolder == null || getEntries() == null) {
            return false;
        }

        List<CharSequence> list = new ArrayList<>();
        list.addAll(Arrays.asList(getEntries()));
        Collections.sort(list, new Comparator<CharSequence>() {
            @Override
            public int compare(CharSequence o1, CharSequence o2) {
                return o2.length() - o1.length();
            }
        });

        int maxWidth = mViewHolder.itemView.getWidth() - mLeftPadding * 3;

        if (maxWidth <= 0) {
            return false;
        }

        Rect bounds = new Rect();
        Paint textPaint = new TextPaint();
        textPaint.setTextSize(16 * mContext.getResources().getDisplayMetrics().scaledDensity);

        for (CharSequence chs : list) {
            textPaint.getTextBounds(chs.toString(), 0, chs.length(), bounds);
            int width = bounds.width();
            Log.d("QAQ", "" + width + " " + maxWidth);

            if (width > maxWidth) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setValueIndex(int index) {
        setValue(getEntryValues()[index].toString());
    }

    private int getValueIndex() {
        return findIndexOfValue(getValue());
    }

    @Override
    protected void notifyChanged() {
        super.notifyChanged();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder view) {
        super.onBindViewHolder(view);

        mViewHolder = view;

        if (mShowPopup) {
            mViewHolder.itemView.post(new Runnable() {
                @Override
                public void run() {
                    onClick();
                    mShowPopup = false;
                }
            });
        }

        mViewHolder.itemView.post(new Runnable() {
            @Override
            public void run() {
                resetUseDialog();
            }
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();

        final SavedState myState = new SavedState(superState);
        myState.value =
                (mPopupWindow != null && mPopupWindow.isShowing()) || mShowingDialog ? 1 : 0;

        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state == null || !state.getClass().equals(SavedState.class)) {
            // Didn't save state for us in onSaveInstanceState
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());
        if (myState.value == 1) {
            mShowPopup = true;
        }
    }

    private static class SavedState extends BaseSavedState {
        int value;

        public SavedState(Parcel source) {
            super(source);
            value = source.readInt();
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(value);
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_menu_item, parent, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mCheckedTextView.setText(getEntries()[position]);
            holder.mCheckedTextView.setChecked(position == findIndexOfValue(getValue()));
            holder.mCheckedTextView.setMaxLines(mUseDialog ? 99 : 1);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setValueIndex(holder.getAdapterPosition());

                    if (!mUseDialog) {
                        if (mPopupWindow != null && mPopupWindow.isShowing()) {
                            mPopupWindow.dismiss();
                        }
                    } else {
                        if (mAlertDialog != null && mAlertDialog.isShowing()) {
                            mAlertDialog.dismiss();
                        }
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return getEntries() == null ? 0 : getEntries().length;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        public CheckedTextView mCheckedTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mCheckedTextView = (CheckedTextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
