package com.jeck.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-07
 * Time: 15:45
 * Description:
 */
public class ArcDiagramView extends View{
    private int mCircleXY;//圆心坐标
    private float mRadius;//内圆半径
    private RectF mArcRectF;//弧线外接矩形

    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;

    private float mSweepAngle = 270;
    private String mShowText = "ArcDiagram";
    private int mTextWidth;

    public ArcDiagramView(Context context) {
        super(context);
        initPaint();
    }

    public ArcDiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ArcDiagramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.GRAY);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.GRAY);
        mCirclePaint.setAntiAlias(true);
        mArcPaint.setStrokeWidth(30);
        mArcPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mCirclePaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int length = getMeasuredHeight();
        if (length > 0){
            mCircleXY = length/2;
            mRadius = (float) (length*0.5/2);
            mArcRectF = new RectF((float)(length*0.1),(float)(length*0.1),(float)(length*0.9),(float)(length*0.9));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,mCirclePaint);
        //绘制弧线
        if (mSweepAngle > 0){
            canvas.drawArc(mArcRectF,270,mSweepAngle,false,mArcPaint);
        }
        //绘制文字
        canvas.drawText(mShowText,mCircleXY,mCircleXY,mTextPaint);
    }

    public void setSweepValue(float sweepValue){
        if (sweepValue != 0){
            mSweepAngle = sweepValue;
        }else {
            mSweepAngle = 25;
        }
        invalidate();
    }
}
