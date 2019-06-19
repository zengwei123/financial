package com.cnitpm.financial.Custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class WavesProgress extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mWaveHeight;
    private int mWaveDx;
    private int dx;

    public WavesProgress(Context context) {
        this(context, null);
    }

    public WavesProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public WavesProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#FF3891"));
        mPaint.setStyle(Paint.Style.FILL);
        //波长的长度(这里设置为屏幕的宽度)
        mWaveDx = getResources().getDisplayMetrics().widthPixels;
        startAnimation();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //控件的宽高
        mWidth =  MeasureSpec.getSize(widthMeasureSpec);
        mHeight =  MeasureSpec.getSize(heightMeasureSpec);
        //水波的高度
        mWaveHeight = 15;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWave(canvas);
    }


    private void drawWave(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#F1AAA6"));
        Path path = new Path();
        path.reset();
        path.moveTo((float) (-mWaveDx + dx), (float) (mHeight *0.9));
        for (int i = -mWaveDx; i < getWidth() + mWaveDx; i += mWaveDx) {
            path.rQuadTo(mWaveDx / 4, -mWaveHeight, mWaveDx / 2, 0);
            path.rQuadTo(mWaveDx / 4, mWaveHeight, mWaveDx / 2, 0);
        }
        canvas.drawPath(path, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mHeight/2);
        mPaint.setColor(Color.parseColor("#ffffff"));
        canvas.drawCircle(mHeight/2,mHeight/2,mHeight-45,mPaint);
    }

    public void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mWaveDx);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //水平方向的偏移量
                dx = ( int ) animation.getAnimatedValue();
                invalidate();
            }

        });
        valueAnimator.start();

    }

}
