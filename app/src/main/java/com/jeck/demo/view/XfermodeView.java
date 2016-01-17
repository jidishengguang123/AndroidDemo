package com.jeck.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jeck.demo.R;

/**
 * Created by apple on 16/1/17.
 * Email:jidishengguang123@163.com
 */
public class XfermodeView extends View {

    private Bitmap mBgBitmap, mFgBitmap;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;

    private int mWidth;
    private int mHeight;

    public XfermodeView(Context context) {
        super(context);
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init();
    }

    private void init() {
        initPaint();
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mPath = new Path();
        createBgBitmap();
        createFgBitmap();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        //使笔触和连接处更加光滑
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(50);
    }

    private void createBgBitmap() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap
                .ic_launcher);
        mBgBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBgBitmap);
        Matrix matrix = new Matrix();
        //将原图缩放到控件尺寸大小
        matrix.postScale(mWidth / bmp.getWidth(), mHeight / bmp.getHeight());
        canvas.drawBitmap(bmp, matrix, new Paint());
    }

    private void createFgBitmap() {
        mFgBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap
                .Config.ARGB_8888);
        mCanvas = new Canvas(mFgBitmap);
        //mCanvas绘图的承载体是mFgBitmap
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
        }
        //实际上在改变mFgBitmap
        mCanvas.drawPath(mPath, mPaint);
        //将改变后的mFgBitmap绘制在canvas上
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBgBitmap, 0, 0, null);
        canvas.drawBitmap(mFgBitmap, 0, 0, null);
    }
}
