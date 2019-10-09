package com.youdao.test.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.youdao.test.R;

public class CircleProgressBar extends View {

    private static final int DEFAULT_MAX = 100;
    private static final float MAX_DEGREE = 360.0f;

    private static final String COLOR_FFF2A670 = "#fff2a670";
    private static final String COLOR_FFD3D3D5 = "#ffe3e3e5";

    private final RectF mProgressRectF = new RectF();

    private final Paint mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mProgressBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float mRadius;
    private float mCenterX;
    private float mCenterY;

    private int mProgress;
    private int mMax = DEFAULT_MAX;

    //Stroke width of the progress of the progress bar
    private float mProgressStrokeWidth;

    //color of the progress of the progress bar
    private int mProgressColor;

    //Background color of the progress of the progress bar
    private int mProgressBackgroundColor;

    //the rotate degree of the canvas, default is -90.
    private int mStartDegree = -90;

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFromAttributes(context, attrs);
        initPaint();
    }

    /**
     * Basic data initialization
     */
    @SuppressWarnings("ResourceType")
    private void initFromAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);

        mProgressStrokeWidth = a.getDimensionPixelSize(R.styleable.CircleProgressBar_progress_stroke_width, 0);

        mProgressColor = a.getColor(R.styleable.CircleProgressBar_progress_color, Color.parseColor(COLOR_FFF2A670));
        mProgressBackgroundColor = a.getColor(R.styleable.CircleProgressBar_progress_background_color, Color.parseColor(COLOR_FFD3D3D5));

        a.recycle();
    }

    /**
     * Paint initialization
     */
    private void initPaint() {

        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(mProgressStrokeWidth);
        mProgressPaint.setColor(mProgressColor);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);

        mProgressBackgroundPaint.setStyle(Paint.Style.STROKE);
        mProgressBackgroundPaint.setStrokeWidth(mProgressStrokeWidth);
        mProgressBackgroundPaint.setColor(mProgressBackgroundColor);
        mProgressBackgroundPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    /**
     * The progress bar color gradient,
     * need to be invoked in the {@link #onSizeChanged(int, int, int, int)}
     */
    private void updateProgressShader() {
        mProgressPaint.setShader(null);
        mProgressPaint.setColor(mProgressColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.rotate(mStartDegree, mCenterX, mCenterY);
        drawSolidLineProgress(canvas);
        canvas.restore();
    }

    /**
     * Just draw arc
     */
    private void drawSolidLineProgress(Canvas canvas) {
        canvas.drawArc(mProgressRectF, 0.0f, MAX_DEGREE, false, mProgressBackgroundPaint);
        canvas.drawArc(mProgressRectF, 0.0f, MAX_DEGREE * mProgress / mMax, false, mProgressPaint);
    }

    /**
     * When the size of CircleProgressBar changed, need to re-adjust the drawing area
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
        mRadius = Math.min(mCenterX, mCenterY);
        mProgressRectF.top = mCenterY - mRadius;
        mProgressRectF.bottom = mCenterY + mRadius;
        mProgressRectF.left = mCenterX - mRadius;
        mProgressRectF.right = mCenterX + mRadius;

        updateProgressShader();

        //Prevent the progress from clipping
        mProgressRectF.inset(mProgressStrokeWidth / 2, mProgressStrokeWidth / 2);
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        invalidate();
    }

    private static final class SavedState extends BaseSavedState {
        int progress;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            progress = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(progress);
        }

        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    public Parcelable onSaveInstanceState() {
        // Force our ancestor class to save its state
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);

        ss.progress = mProgress;

        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        setProgress(ss.progress);
    }

}
