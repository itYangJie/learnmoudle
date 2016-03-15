package com.ityang.learnmoudle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class ColorMatrixActivity extends AppCompatActivity {

    private static final int SIZE = 20;
    private ImageView img;
    private GridLayout gridLayout;
    private float[] texts = new float[SIZE];
    private EditText[] editTexts = new EditText[SIZE];
    private Bitmap sourceBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * 初始化
     */
    private void initViews() {
        img = (ImageView) findViewById(R.id.img);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        //当gridview绘制完毕后回调该方法可以测出实际宽度
        gridLayout.post(new Runnable() {
            @Override
            public void run() {
                int gridWidth = gridLayout.getWidth() / 5;
                int gridHeight = gridLayout.getHeight() / 4;
                for (int i = 0; i < SIZE; i++) {
                    EditText editText = new EditText(ColorMatrixActivity.this);
                    editTexts[i] = editText;
                    gridLayout.addView(editText, gridWidth, gridHeight);
                }
                initTexts();
            }
        });
        sourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo);
        img.setImageBitmap(sourceBitmap);
    }

    /**
     * 初始化输入框数值
     */
    private void initTexts() {
        for (int i = 0; i < SIZE; i++) {
            if (i % 6 == 0) {
                editTexts[i].setText("1.0");
            } else {
                editTexts[i].setText("0.0");
            }
        }
    }

    /**
     * 还原
     *
     * @param view
     */
    public void backUp(View view) {
        initTexts();
        img.setImageBitmap(sourceBitmap);
    }

    public void handleImg(View view) {
        Bitmap resultBitmap = handle();
        if (resultBitmap != null) {
            img.setImageBitmap(resultBitmap);
        }
    }

    /**
     * 楚翔处理关键类
     *
     * @return
     */
    public Bitmap handle() {
        //获取输入框内容保存到数组中
        try {
            for (int i = 0; i < SIZE; i++) {
                texts[i] = Float.valueOf(editTexts[i].getText().toString());
            }
            Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap.getWidth(), sourceBitmap.getHeight(), Bitmap.Config.ARGB_8888);
            //准备画布
            Canvas canvas = new Canvas(resultBitmap);
            //抗锯齿画笔
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            //颜色矩阵设置
            paint.setColorFilter(new ColorMatrixColorFilter(texts));
            canvas.drawBitmap(sourceBitmap, 0, 0, paint);
            return resultBitmap;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
