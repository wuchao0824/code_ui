package com.wuchao.core_ui;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * Description:<br>
 * Author:wuchao
 * Date:2020/5/28 10:06
 */
public class InputView extends FrameLayout implements View.OnFocusChangeListener, TextWatcher {

    private EditText mEditText;
    private int mLineFocusHeight;
    protected int mViewFocusColor;
    private View mBottomLine;
    private int mLineNormalHeight;
    protected int mViewNormalColor;
    private boolean mIsEmpty=true;

    public InputView(@NonNull Context context) {
        this(context,null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    protected void initView(AttributeSet attrs) {
        int icIconSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getContext().getResources().getDisplayMetrics());
        int icIconMargin= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getContext().getResources().getDisplayMetrics());
        ImageView[] leftIcons = getLeftIcons();
        int leftIconsCount=leftIcons==null?0:leftIcons.length;
        for(int i=0;i<leftIconsCount;i++){
            ImageView leftImageView=leftIcons[i];
            LayoutParams leftLayoutParams=new LayoutParams(icIconSize,icIconSize);
            leftLayoutParams.leftMargin=(icIconSize+icIconMargin)*i;
            leftLayoutParams.gravity= Gravity.LEFT|Gravity.CENTER_VERTICAL;
            addView(leftImageView,leftLayoutParams);
        }
        ImageView[] rightIcons = getRightIcons();
        int rightIconsCount=rightIcons==null?0:rightIcons.length;
        for(int i=0;i<rightIconsCount;i++){
            ImageView rightIcon=rightIcons[i];
            LayoutParams rightLayoutParams=new LayoutParams(icIconSize,icIconSize);
            rightLayoutParams.rightMargin=(icIconSize+icIconMargin)*i;
            rightLayoutParams.gravity=Gravity.RIGHT|Gravity.CENTER_VERTICAL;
            addView(rightIcon,rightLayoutParams);
        }

        int etMargin= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics());
        mEditText = new EditText(getContext());
        LayoutParams etParams=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        etParams.leftMargin=etMargin+icIconSize*leftIconsCount+(leftIconsCount-1)*icIconMargin;
        etParams.rightMargin=etMargin+icIconSize*rightIconsCount+(rightIconsCount-1)*icIconMargin;
        mEditText.setLayoutParams(etParams);
        mEditText.setBackgroundColor(Color.TRANSPARENT);
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
        mEditText.setTextColor(Color.parseColor("#333333"));
        mEditText.setHintTextColor(Color.parseColor("#666666"));
        mEditText.setSingleLine();
        mEditText.setOnFocusChangeListener(this);
        mEditText.addTextChangedListener(this);
        addView(mEditText);


        mLineNormalHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getContext().getResources().getDisplayMetrics());
        mViewNormalColor = ContextCompat.getColor(getContext(),R.color.line);
        mLineFocusHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getContext().getResources().getDisplayMetrics());
        mViewFocusColor = ContextCompat.getColor(getContext(), R.color.main);
        mBottomLine = new View(getContext());
        LayoutParams lineParams=new LayoutParams(LayoutParams.MATCH_PARENT, mLineNormalHeight);
        lineParams.gravity=Gravity.BOTTOM;
        mBottomLine.setLayoutParams(lineParams);
        mBottomLine.setBackgroundColor(mViewNormalColor);
        addView(mBottomLine);
    }



    /**
     *  des: 获取右边图标
     */
    protected ImageView[] getRightIcons() {
        return null;
    }

    /**
     *  des: 左边图标
     */
    protected ImageView[] getLeftIcons() {
        return null;
    }

    /**
     *  des: 底部线条风格
     */
    private void changeBottomLineStyle(boolean focus){
        int color;
        int height;
        if(focus){
            color= mViewFocusColor;
            height=mLineFocusHeight;
        }else{
            color= mViewNormalColor;
            height=mLineNormalHeight;
        }
        mBottomLine.setBackgroundColor(color);
        mBottomLine.getLayoutParams().height=height;
        mBottomLine.requestLayout();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mIsEmpty=s.toString().length()==0;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        changeBottomLineStyle(hasFocus);
    }

    public EditText getEditText(){
        return mEditText;
    }

    public boolean isEmpty(){
        return mIsEmpty;
    }

}
