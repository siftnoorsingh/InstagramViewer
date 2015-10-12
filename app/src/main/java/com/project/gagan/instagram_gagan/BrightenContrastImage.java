package com.project.gagan.instagram_gagan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by Sift on 13/10/2015.
 */
public class BrightenContrastImage extends AppCompatActivity {

    Bitmap new_bm;
    Bitmap bitmap;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_effects);
        final SeekBar SeekBar_contrast = (SeekBar) findViewById(R.id.SeekBar_contrast);
        final SeekBar SeekBar_brightness = (SeekBar) findViewById(R.id.SeekBar_brightness);
        imageView = (ImageView) findViewById(R.id.imageView5);
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView.setImageBitmap(bmp);
        SeekBar_contrast.setMax(10);
        SeekBar_brightness.setMax(510);
        SeekBar_contrast.setProgress((1));
        SeekBar_brightness.setProgress((255));
        SeekBar_contrast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                new_bm = changeBitmapContrastBrightness(bitmap, (float) (progress - 1), (float) (SeekBar_brightness.getProgress() - 255));
                imageView.setImageBitmap(new_bm);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        SeekBar_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                new_bm = changeBitmapContrastBrightness(bitmap, (float) (SeekBar_contrast.getProgress() - 1), (float) (progress - 255));
                imageView.setImageBitmap(new_bm);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public static Bitmap changeBitmapContrastBrightness(Bitmap bmp, float contrast, float brightness)
    {
        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });

        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        Canvas canvas = new Canvas(ret);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bmp, 0, 0, paint);

        return ret;
    }

}
