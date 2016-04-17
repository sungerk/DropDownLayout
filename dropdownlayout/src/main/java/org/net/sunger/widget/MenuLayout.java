package org.net.sunger.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import net.org.sunger.dropdownlayout.R;

import java.util.List;

/**
 * Created by sunger on 16/4/16.
 */
public class MenuLayout extends RelativeLayout {
    private Animation animationIn;
    private Animation animationOut;
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

    private int getMenuId() {
        if (getId() == NO_ID) {
            setId(R.id.menu_id);
        }
        return getId();
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


    public void bindFragments(List<Fragment> fragmentList) {
        this.fragments = fragmentList;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (Fragment frg : fragments) {
            transaction.add(getMenuId(), frg);
        }
        transaction.commit();
    }



    public void show() {
        if (animationIn == null) {
            animationIn = AnimationUtils.loadAnimation(getContext(), R.anim.menu_in);
        }
        setVisibility(VISIBLE);
        setAnimation(animationIn);
        animationIn.start();
    }

    public void dissMiss() {
        if (animationOut == null) {
            animationOut = AnimationUtils.loadAnimation(getContext(), R.anim.menu_out);
        }
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


    public boolean isShow() {
        return getVisibility() == VISIBLE;
    }


}
