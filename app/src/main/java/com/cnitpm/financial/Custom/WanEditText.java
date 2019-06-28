package com.cnitpm.financial.Custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class WanEditText extends AppCompatEditText {
    public WanEditText(Context context) {
        super(context);
        init();
    }

    public WanEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WanEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    /**
     * EditText右侧的图标
     */
    protected Drawable mRightDrawable;

    private void init() {
        mRightDrawable = getCompoundDrawables()[2];
        if (mRightDrawable == null) {
            //这里当没有设置右侧图标时你可以给它设置个默认的右侧图标，当然根据你的项目需求来
            return;
        }
        //这里当设置了右侧图标时，我们对图标做了一些自定义设置，你也可以做其他设置
        mRightDrawable.setBounds(0, 0, mRightDrawable.getIntrinsicWidth(), mRightDrawable.getIntrinsicHeight());
    }
    public void setRightPicOnclickListener(RightPicOnclickListener rightPicOnclickListener) {
        this.rightPicOnclickListener = rightPicOnclickListener;
    }

    public interface RightPicOnclickListener {
        void rightPicClick(EditText editText);
    }

    private RightPicOnclickListener rightPicOnclickListener;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mRightDrawable != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {

                    //设置点击EditText右侧图标EditText失去焦点，
                    // 防止点击EditText右侧图标EditText获得焦点，软键盘弹出
                    setFocusableInTouchMode(true);
                    setFocusable(true);
                    //点击EditText右侧图标事件接口回调
                    if (rightPicOnclickListener != null) {
                        rightPicOnclickListener.rightPicClick(this);
                    }
                } else {
                    //设置点击EditText输入区域，EditText请求焦点，软键盘弹出，EditText可编辑
                    setFocusableInTouchMode(true);
                    setFocusable(true);
                    //设置点击EditText输入区域，EditText不请求焦点，软键盘不弹出，EditText不可编辑
//                    setFocusableInTouchMode(false);
//                    setFocusable(false);
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
