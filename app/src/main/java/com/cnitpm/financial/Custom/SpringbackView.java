package com.cnitpm.financial.Custom;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

public class SpringbackView extends ScrollView {

    private static final float MOVE_DELAY = 0.3f;//当拉出屏幕时的拖拽系数
    private static final int ANIM_TIME = 300;//回弹耗时
    private static final int FLING = 2;//fling 系数

    private View childView;
    private boolean havaMoved;

    private Rect originalRect = new Rect();  //代表上下左右的一个区域

    private float startY;
    private SpringBackViewEvent springBackViewEvent;
    /**这个方法的作用就是在完成View的布局实例化后的回调**/
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //在这里已经可以获取 到子控件
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }

    //重写滑动速度
    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 2);
    }


    //设置布局的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (childView == null)  //这里是上面获取到的子控件
            return;

        originalRect.set(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
    }


    public SpringbackView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SpringbackView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpringbackView(Context context) {
        super(context);
    }


    /**
     * 在触摸事件中, 处理上拉和下拉的逻辑
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //为空不管他
        if (childView == null) {
            return super.dispatchTouchEvent(ev);
        }
        //获取事件
        int action = ev.getAction();
        //
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();  //按下的坐标
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                if (!havaMoved)
                    break;

                //位移的动画
                TranslateAnimation anim = new TranslateAnimation(0, 0, childView.getTop(), originalRect.top);
                if (springBackViewEvent!=null){
                    if(childView.getTop()>0){
                        springBackViewEvent.Translate(true);
                    }else {
                        springBackViewEvent.Translate(false);
                    }
                }
                anim.setDuration(ANIM_TIME);  //动画时间
                childView.startAnimation(anim);  //给控件设置动画时间
                // 将标志位设回false
                havaMoved = false;  //没有移动

                resetViewLayout();

                break;
            case MotionEvent.ACTION_MOVE:
                //移动事件
                float nowY = ev.getY();  //获取坐标
                int deltaY = (int) (nowY - startY);  //移动的距离 实际长度
                int offset = (int) (deltaY * MOVE_DELAY);  //移动的距离
                childView.layout(originalRect.left, originalRect.top + offset, originalRect.right, originalRect.bottom + offset);

                havaMoved = true;

                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    public void resetViewLayout() {
        //设置控件的位置
        childView.layout(originalRect.left, originalRect.top, originalRect.right, originalRect.bottom);
    }
    public interface SpringBackViewEvent{
        void Translate(boolean b);
    }

    public SpringBackViewEvent getSpringBackViewEvent() {
        return springBackViewEvent;
    }

    public void setSpringBackViewEvent(SpringBackViewEvent springBackViewEvent) {
        this.springBackViewEvent = springBackViewEvent;
    }
}