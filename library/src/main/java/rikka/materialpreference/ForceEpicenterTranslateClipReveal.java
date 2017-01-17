package rikka.materialpreference;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Created by Rikka on 2017/1/17.
 */

public class ForceEpicenterTranslateClipReveal extends EpicenterTranslateClipReveal {

    private Rect mEpicenterBounds = null;

    public ForceEpicenterTranslateClipReveal() {
    }

    public ForceEpicenterTranslateClipReveal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEpicenterBounds(Rect epicenterBounds) {
        mEpicenterBounds = epicenterBounds;
    }

    @Override
    public Rect getEpicenter() {
        return mEpicenterBounds;
    }
}
