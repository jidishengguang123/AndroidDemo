package com.jeck.demo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeck.demo.R;

public class PaletteActivtiy extends AppCompatActivity {
    private TextView mPaletteImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_activtiy);
        mPaletteImage = (TextView) findViewById(R.id.palette_text);
        
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        Palette.Builder builder = new Palette.Builder(bitmap);
        //创建Palette对象
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //通过Palette来获取对应的色调
                Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
                //将颜色设置给相应的控件
                mPaletteImage.setBackgroundColor(vibrant.getRgb());
            }
        });
    }
}
