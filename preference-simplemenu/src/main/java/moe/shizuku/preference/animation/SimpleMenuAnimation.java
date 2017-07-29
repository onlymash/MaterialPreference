package moe.shizuku.preference.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.Rect;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import moe.shizuku.preference.drawable.FixedBoundsDrawable;

/**
 * Helper class to create and start animation of Simple Menu.
 *
 * TODO let params styleable
 */

public class SimpleMenuAnimation {

    public static void postStartEnterAnimation(final ViewGroup list, final FixedBoundsDrawable background,
                                               final int width, final int height,
                                               final int startX, final int startY, final Rect start,
                                               final int itemHeight, final int elevation, final int selectedIndex) {
        list.post(new Runnable() {
            @Override
            public void run() {
                // return if already dismissed
                if (list.getParent() == null) {
                    return;
                }
                startEnterAnimation(list, background, width, height, startX, startY, start, itemHeight, elevation, selectedIndex);
            }
        });
    }

    public static void startEnterAnimation(final ViewGroup list, final FixedBoundsDrawable background,
                                           int width, int height,
                                           int centerX, int centerY, Rect start,
                                           int itemHeight, int elevation, int selectedIndex) {
        PropertyHolder holder = new PropertyHolder(background, list);
        Animator backgroundAnimator = createBoundsAnimator(
                holder, width, height, centerX, centerY, start);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                backgroundAnimator,
                createElevationAnimator((View) list.getParent(), elevation));
        animatorSet.setDuration(backgroundAnimator.getDuration());
        animatorSet.start();

        long delay = 0;

        for (int i = 0; i < list.getChildCount(); i++) {
            int offset = selectedIndex - i;
            startChild(list.getChildAt(i), delay + 20 * Math.abs(offset),
                    offset == 0 ? 0 : (int) (itemHeight * 0.2) * (offset < 0 ? -1 : 1));
        }
    }

    private static void startChild(View child, long delay, int translationY) {
        child.setAlpha(0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(child, "alpha", 0.0f, 1.0f),
                ObjectAnimator.ofFloat(child, "translationY", translationY, 0));
        animatorSet.setDuration(200);
        animatorSet.setStartDelay(delay);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }

    private static Rect[] getBounds(
            int width, int height, int centerX, int centerY) {
        int endWidth = Math.max(centerX, width - centerX);
        int endHeight = Math.max(centerY, height - centerY);

        int endLeft = centerX - endWidth;
        int endRight = centerX + endWidth;
        int endTop = centerY - endHeight;
        int endBottom = centerY + endHeight;

        Rect end = new Rect(endLeft, endTop, endRight, endBottom);
        Rect max = new Rect(0, 0, width, height);

        return new Rect[]{end, max};
    }

    private static Animator createBoundsAnimator(PropertyHolder holder,
                                                 int width, int height, int centerX, int centerY, Rect start) {
        int speed = 4096;

        int endWidth = Math.max(centerX, width - centerX);
        int endHeight = Math.max(centerY, height - centerY);

        Rect[] rect = getBounds(width, height, centerX, centerY);
        Rect end = rect[0];
        Rect max = rect[1];

        long duration = (long) ((float) Math.max(endWidth, endHeight) / speed * 1000);
        duration = Math.min(duration, 300);

        Animator animator = ObjectAnimator
                .ofObject(holder, SimpleMenuBoundsProperty.BOUNDS, new RectEvaluator(max), start, end);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(duration);
        return animator;
    }

    @SuppressWarnings("unchecked")
    private static Animator createElevationAnimator(View view, float elevation) {
        Animator animator = ObjectAnimator.ofObject(view, View.TRANSLATION_Z, (TypeEvaluator) new FloatEvaluator(), -elevation, 0f);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        return animator;
    }
}
