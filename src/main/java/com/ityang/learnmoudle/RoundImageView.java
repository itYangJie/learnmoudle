package com.ityang.learnmoudle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/3/15.
 */
public class RoundImageView extends View {
    private Bitmap sourceBitmap,destBitmap;
    private Context ctx;
    private Paint paint;
    public RoundImageView(Context context) {
        super(context);
        this.ctx = context;
        init();
    }
    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        init();
    }
    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.ctx = context;
        init();
    }
    /**
     * 初始化
     */
    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        sourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setImageBitmap(Bitmap source) {
        this.sourceBitmap = source;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle((getLeft()+getRight())/2,(getTop()+getBottom())/2, (getRight()-getLeft())/2, paint);
        //canvas.drawCircle(500,500,200,paint);
        //canvas.drawRoundRect(0,0,500,500,80,80,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sourceBitmap, 0, 0, paint);
    }
}
