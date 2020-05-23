package com.hym.groupbuy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.hym.groupbuy.R;


/**
 * ViewPager指示器
 */
public class ViewPagerIndicator extends View {

    private Paint mForePaint;
    private Paint mBackground;
    /* 圆圈半径 */
    private int mradius;
    /* 前景 * 坐标 */
    private float foreOffX;
    /* ○ 与 ○ 之间的间距 */


    private int mNumbers;       /* 数量 */

    /* 间距 */
    private float mGapWidth;
    private int mForeColor;
    private int mBackgroundColor;

    public ViewPagerIndicator(Context context) {
        super(context);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mForeColor = attributes.getColor(R.styleable.ViewPagerIndicator_foreColor, Color.RED);
        mBackgroundColor = attributes.getColor(R.styleable.ViewPagerIndicator_background_color, Color.GRAY);
        mNumbers = attributes.getInt(R.styleable.ViewPagerIndicator_numbers, 4);
        mradius = attributes.getInt(R.styleable.ViewPagerIndicator_radius, 8);
        /* 初始化画笔 */
        initPaint();
    }

    /** 初始化画笔 */
    private void initPaint(){
        /* 前景○ */
        mForePaint = new Paint();
        mForePaint.setColor(mForeColor);
        mForePaint.setStyle(Paint.Style.FILL);
        /* 背景○ */
        mBackground = new Paint();
        mBackground.setColor(mBackgroundColor);
        mBackground.setStyle(Paint.Style.FILL);

        /* 间距 */
        mGapWidth = (float) (3.0 * mradius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mNumbers; i++) {
            /* 起始坐标 + i * 间隔 */
            canvas.drawCircle(10 + i * mGapWidth,10,mradius,mBackground);
        }
        canvas.drawCircle(10 + foreOffX,10,mradius,mForePaint);
    }

    public void setOffX(int position,float pec){
        /* 当前页X坐标 + 起始坐标 */
        float pagerX = (float) position * mGapWidth;
        /* 计算出偏移 */
        foreOffX = pagerX + mGapWidth * pec;
        /* 重绘 */
        invalidate();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize= MeasureSpec.getSize(widthMeasureSpec);
        int heightSpectMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);
        //这里就是对wrap_content的支持
//        if(widthSpecMode==MeasureSpec.AT_MOST && heightSpectMode==MeasureSpec.AT_MOST){
        if(widthSpecMode==MeasureSpec.AT_MOST && heightSpectMode==0){
            //这里设定的根据你自己自定义View的情况而定
            setMeasuredDimension(2 * mradius + 10 + (mNumbers-1) * 3 * mradius,20 + 2 * mradius);

        }else if(widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(2 * mradius + 10 +(mNumbers-1) * 3 * mradius,heightSpecSize);

//        }else if (heightSpectMode==MeasureSpec.AT_MOST){
        }else if (heightSpectMode==0){
            setMeasuredDimension(widthSpecSize,10 + 2 * mradius);
        }

    }
}
