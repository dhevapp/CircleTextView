package com.dhevapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleTextView extends View {
    private Paint bgPaint;
    private Paint textPaint;
    private Paint borderPaint;
    private Rect textBounds = new Rect();

    private String name = "";
    private String initial = "";
    private int bgColor = Color.GRAY;
    private int textColor = Color.WHITE;
    private float textSizePx;
    private float borderWidthPx = 0f;
    private int borderColor = Color.WHITE;

    public CircleTextView(Context context) {
        super(context);
        init(null);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);

        // Default sizes (in px)
        float density = getResources().getDisplayMetrics().density;
        textSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        borderWidthPx = 0f;

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircleTextView);
            bgColor = a.getColor(R.styleable.CircleTextView_bgColor, bgColor);
            textColor = a.getColor(R.styleable.CircleTextView_textColor, textColor);
            name = a.getString(R.styleable.CircleTextView_name);
            float textSizeDp = a.getDimension(R.styleable.CircleTextView_textSizePx, textSizePx);
            textSizePx = textSizeDp;
            float borderDp = a.getDimension(R.styleable.CircleTextView_borderWidthDp, borderWidthPx);
            borderWidthPx = borderDp;
            borderColor = a.getColor(R.styleable.CircleTextView_borderColor, borderColor);
            a.recycle();
        }

        bgPaint.setColor(bgColor);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSizePx);
        borderPaint.setStrokeWidth(borderWidthPx);
        borderPaint.setColor(borderColor);

        setName(name);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defaultSize = (int) (48 * getResources().getDisplayMetrics().density);
        int w = resolveSize(defaultSize, widthMeasureSpec);
        int h = resolveSize(defaultSize, heightMeasureSpec);
        int size = Math.min(w, h);
        setMeasuredDimension(size, size);

        // scale text size relative to view size if textSize not explicitly large
        textPaint.setTextSize(size * 0.45f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        float radius = Math.min(w, h) / 2f;

        // background circle
        canvas.drawCircle(w / 2f, h / 2f, radius - borderWidthPx, bgPaint);

        // text
        if (initial != null && !initial.isEmpty()) {
            textPaint.getTextBounds(initial, 0, initial.length(), textBounds);
            float x = w / 2f;
            float y = h / 2f - textBounds.exactCenterY();
            canvas.drawText(initial, x, y, textPaint);
        }

        // border
        if (borderWidthPx > 0) {
            canvas.drawCircle(w / 2f, h / 2f, radius - borderWidthPx / 2f, borderPaint);
        }
    }

    // Public setters
    public void setName(String name) {
        this.name = name != null ? name.trim() : "";
        if (!this.name.isEmpty()) {
            initial = this.name.substring(0, 1).toUpperCase();
        } else {
            initial = "";
        }
        invalidate();
    }

    public void setBgColor(int color) {
        bgColor = color;
        bgPaint.setColor(bgColor);
        invalidate();
    }

    public void setTextColor(int color) {
        textColor = color;
        textPaint.setColor(textColor);
        invalidate();
    }

    public void setTextSizePx(float px) {
        textSizePx = px;
        textPaint.setTextSize(textSizePx);
        invalidate();
    }

    public void setBorderWidthPx(float px) {
        borderWidthPx = px;
        borderPaint.setStrokeWidth(borderWidthPx);
        invalidate();
    }

    public void setBorderColor(int color) {
        borderColor = color;
        borderPaint.setColor(borderColor);
        invalidate();
    }

    // getters
    public String getName() { return name; }
    public String getInitial() { return initial; }
                                   }
