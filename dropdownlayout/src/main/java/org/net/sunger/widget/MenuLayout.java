package org.net.sunger.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by sunger on 16/4/16.
 */
public class MenuLayout extends RelativeLayout {
    private static final Animation DEFAULT_ANIM_IN = getDefalutAlphaAnimationIn();
    private static final Animation DEFAULT_ANIM_OUT = getDefalutAlphaAnimationOut();
    private Animation animationIn = DEFAULT_ANIM_IN;
    private Animation animationOut = DEFAULT_ANIM_OUT;
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private int currentItem = 0;

    public MenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuLayout(Context context) {
        this(context, null);
    }

    public MenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MenuLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setClickable(true);
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private void hideLastFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.size(); i++) {
            if (i != currentItem || !fragments.get(i).isHidden()) {
                transaction.hide(fragments.get(i));
            }
        }
    }
    public void setCurrentItem(int position) {
        currentItem = position;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideLastFragment(transaction);
        transaction.show(fragments.get(position));
        transaction.commit();
    }


    public void setFragment(List<Fragment> fragmentList, int id) {
        this.fragments = fragmentList;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (Fragment frg : fragments) {
            transaction.add(id, frg);
        }
        transaction.commit();
    }

    public void show() {
        setVisibility(VISIBLE);
        setAnimation(animationIn);
        animationIn.start();
    }

    public void dissMiss() {
        setVisibility(GONE);
        setAnimation(animationOut);
        animationOut.start();

    }


    public Animation getAnimationIn() {
        return animationIn;
    }

    public void setAnimationIn(Animation animationIn) {
        this.animationIn = animationIn;
    }

    public void setAnimationIn(int animationInResid) {
        this.animationIn = AnimationUtils.loadAnimation(getContext(), animationInResid);
    }

    public Animation getAnimationOut() {
        return animationOut;
    }

    public void setAnimationOut(Animation animationOut) {
        this.animationOut = animationOut;
    }

    public void setAnimationOut(int animationOutResId) {
        this.animationOut = AnimationUtils.loadAnimation(getContext(), animationOutResId);
    }

    private static AlphaAnimation getDefalutAlphaAnimationIn() {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(250);
        return animation;
    }


    public boolean isShow() {
        return getVisibility() == VISIBLE;
    }


    private static AlphaAnimation getDefalutAlphaAnimationOut() {
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(250);
        return animation;
    }

}
