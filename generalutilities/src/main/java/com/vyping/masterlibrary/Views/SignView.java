package com.vyping.masterlibrary.Views;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SignView extends View {

    private OnSignedListener interfase;

    private static Bitmap bitmap;
    private static Path path;
    private static Rect boundary;
    private static Canvas canvas1;
    private static boolean isdraw;
    private static int width, height;

    public SignView(Context context) {

        super(context);

        init();
    }

    public SignView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        init();
    }

    public SignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        init();
    }

    public SignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init() {

        path = new Path();
        isdraw = false;
        bitmap = null;

        setOnTouchListener(onTouchListener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override @SuppressLint("DrawAllocation")
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        super.onLayout(changed, left, top, right, bottom);

        width = getWidth();
        height = getHeight();
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas1 = new Canvas(bitmap);
        boundary = new Rect(0,0, width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        @SuppressLint("DrawAllocation")
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);

        canvas.drawPath(path, paint);
        canvas1.drawPath(path, paint);
        canvas.drawRect(boundary, paint);
    }

    private final OnTouchListener onTouchListener = new OnTouchListener() {

        @Override @SuppressLint("ClickableViewAccessibility")
        public boolean onTouch(View v, @NonNull MotionEvent event) {

            isdraw = true;

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:// se activa cuando se presiona el dedo

                    path.moveTo(event.getX(),event.getY());
                    invalidate();

                    break;

                case MotionEvent.ACTION_MOVE:// se activa cuando el dedo se mueve

                    path.lineTo(event.getX(),event.getY());
                    invalidate();

                    break;

                case MotionEvent.ACTION_UP:// se activa cuando el dedo se mueve

                    boolean signed = requestIfIsSigned();

                    interfase.StatusSign(signed);

                    break;
            }

            return true;
        }

        private void DummyVoid() { }
    };

    public void setOnSignedListener(OnSignedListener listener) {

        interfase = listener;
    }

    public boolean requestIfIsSigned() {

        Bitmap emptyBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        if (bitmap.sameAs(emptyBitmap) || path.isEmpty()) {

            return FALSE;

        } else {

            return TRUE;
        }
    }

    public Bitmap getBitmap() {

        if (!isdraw) {

            return null;
        }

        return bitmap;
    }

    public void ClearSignBitmap() {

        isdraw = FALSE;

        path.reset();
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas1 = new Canvas(bitmap);
        invalidate();
    }

    public interface OnSignedListener {

        void StatusSign(boolean signed);
    }
}
