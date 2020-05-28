package com.wuchao.ui;

import android.content.Context;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wuchao.core_ui.InputView;

/**
 * Description:<br>
 * Author:wuchao
 * Date:2020/5/28 15:06
 */
public class PasswordInputView extends InputView {

    private ImageView mLeftImageView;
    private ImageView mDeleteImageView;
    private ImageView mEyeImageView;
    private boolean mIsHidePasswordMode=true;

    public PasswordInputView(@NonNull Context context) {
        super(context);
    }

    public PasswordInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(AttributeSet attrs) {
        super.initView(attrs);
        getEditText().setHint("请输入密码");
        changeFocusMode(false);
        changeHideMode(true);
    }

    private void changeFocusMode(boolean focus) {
        if(focus){
            mLeftImageView.setColorFilter(mViewFocusColor);
            mDeleteImageView.setVisibility(isEmpty()?INVISIBLE:VISIBLE);
        }else{
            mLeftImageView.setColorFilter(mViewNormalColor);
            mDeleteImageView.setVisibility(INVISIBLE);
        }
    }


    @Override
    protected ImageView[] getLeftIcons() {
        mLeftImageView = new ImageView(getContext());
        mLeftImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mLeftImageView.setImageResource(R.drawable.ic_password_normal);
        return new ImageView[]{mLeftImageView};
    }

    @Override
    protected ImageView[] getRightIcons() {
        mDeleteImageView = new ImageView(getContext());
        mDeleteImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mDeleteImageView.setImageResource(R.drawable.ic_delete);
        mDeleteImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditText().setText("");
            }
        });

        mEyeImageView = new ImageView(getContext());
        mEyeImageView.setVisibility(INVISIBLE);
        mEyeImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mEyeImageView.setImageResource(R.drawable.ic_eye_normal);
        mEyeImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeHideMode(!mIsHidePasswordMode);
            }
        });
        return new ImageView[]{mDeleteImageView, mEyeImageView};
    }

    private void changeHideMode(boolean isHidePasswordMode) {
        this.mIsHidePasswordMode=isHidePasswordMode;
        if(mIsHidePasswordMode){
            //隐藏密码
            getEditText().setTransformationMethod(PasswordTransformationMethod.getInstance());
            mEyeImageView.setColorFilter(mViewNormalColor);
        }else{
            //显示密码
            getEditText().setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mEyeImageView.setColorFilter(mViewFocusColor);
        }

        setTextWithSelection(getEditText(),getEditText().getText().toString());
    }

    private void setTextWithSelection(EditText editText,CharSequence content){
        editText.setText(content);
        editText.setSelection(editText.getText().toString().length());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        changeFocusMode(hasFocus);
    }

    @Override
    public void afterTextChanged(Editable s) {
        super.afterTextChanged(s);
        mDeleteImageView.setVisibility(isEmpty()?INVISIBLE:VISIBLE);
    }
}
