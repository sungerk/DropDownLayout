package org.net.sunger.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by sunger on 16/4/16.
 */
public class DropDownLayout extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener{

    private ContentLayout mContentLayout;
    private MaskView mMaskView;
    private MenuLayout mMenuLayout;

    public DropDownLayout(Context context) {
        this(context, null);
    }

    public DropDownLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DropDownLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private void findChildView() {
        for (int i = 0; i < getChildCount(); i++) {
            View childItem = getChildAt(i);
            if (childItem instanceof ContentLayout) {
                mContentLayout = (ContentLayout) childItem;
            } else if (childItem instanceof MaskView) {
                mMaskView = (MaskView) childItem;
            } else if (childItem instanceof MenuLayout) {
                mMenuLayout = (MenuLayout) childItem;
            }
        }
    }


    @Override
    public void onGlobalLayout() {
        findChildView();
        if (mContentLayout == null || mMaskView == null || mMenuLayout == null) {
            throw new IllegalArgumentException("you layout must be contain ContentLayout MaskView MenuLayout");
        }

        if (Build.VERSION.SDK_INT < 16) {
            removeLayoutListenerPre16();
        } else {
            removeLayoutListenerPost16();
        }
        mMenuLayout.setVisibility(GONE);
        mMaskView.setVisibility(GONE);
        mMaskView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });

    }

    @SuppressWarnings("deprecation")
    private void removeLayoutListenerPre16() {
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @TargetApi(16)
    private void removeLayoutListenerPost16() {
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }


    public void showMenuAt(int position) {
        if (!mMenuLayout.isShow()) {
            mMaskView.show();
            mMenuLayout.show();
        }
        mMenuLayout.setCurrentItem(position);
    }

    public void closeMenu() {
        mMaskView.dissMiss();
        mMenuLayout.dissMiss();
    }
}
