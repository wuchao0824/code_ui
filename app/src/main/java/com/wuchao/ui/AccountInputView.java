package com.wuchao.ui;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wuchao.core_ui.InputView;

/**
 * Description:<br>
 * Author:wuchao
 * Date:2020/5/28 14:05
 */
public class AccountInputView extends InputView {

    private ImageView mUserAccountView;
    private ImageView mDeleteView;

    public AccountInputView(@NonNull Context context) {
        super(context);
    }

    public AccountInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AccountInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(AttributeSet attrs) {
        super.initView(attrs);
        getEditText().setHint("请输入用户名");
        changeFocusMode(false);
    }

    private void changeFocusMode(boolean focus) {
        if(focus){
            mDeleteView.setVisibility(isEmpty()?INVISIBLE:VISIBLE);
            mUserAccountView.setColorFilter(mViewFocusColor);
        }else{
            mDeleteView.setVisibility(INVISIBLE);
            mUserAccountView.setColorFilter(mViewNormalColor);
        }
    }


    @Override
    protected ImageView[] getLeftIcons() {
        mUserAccountView = new ImageView(getContext());
        mUserAccountView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mUserAccountView.setImageResource(R.drawable.ic_account_normal);
        return new ImageView[]{mUserAccountView};
    }

    @Override
    protected ImageView[] getRightIcons() {
        int padding= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
        mDeleteView = new ImageView(getContext());
        mDeleteView.setPadding(padding, padding, padding, padding);
        mDeleteView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mDeleteView.setImageResource(R.drawable.ic_delete);
        mDeleteView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditText().setText("");
            }
        });
        return new ImageView[]{mDeleteView};
    }

    @Override
    public void afterTextChanged(Editable s) {
        super.afterTextChanged(s);
        mDeleteView.setVisibility(isEmpty()?INVISIBLE:VISIBLE);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        changeFocusMode(hasFocus);
    }
}
