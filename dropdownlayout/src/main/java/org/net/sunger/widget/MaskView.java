package org.net.sunger.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by sunger on 16/4/16.
 */
public class MaskView extends View {
    private static final Animation DEFAULT_ANIM_IN = getDefalutAlphaAnimationIn();
    private static final Animation DEFAULT_ANIM_OUT = getDefalutAlphaAnimationOut();

    private Animation animationIn = DEFAULT_ANIM_IN;
    private Animation animationOut = DEFAULT_ANIM_OUT;


    public MaskView(Context context) {
        this(context,null);
    }

    public MaskView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MaskView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void show() {
        setVisibility(VISIBLE);
        setAnimation(animationIn);
     }

    public void dissMiss() {
        setVisibility(GONE);
        setAnimation(animationOut);
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

    private static AlphaAnimation getDefalutAlphaAnimationOut() {
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(250);
        return animation;
    }

    public  void setFragment(){

    }


}
