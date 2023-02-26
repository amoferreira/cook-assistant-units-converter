package com.example.converter;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PopActivity extends Activity {

    Button info_back;
    private ImageView imageView_ingredient;
    private TextView text_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        String ingredient= getIntent().getStringExtra("ingredient");
        imageView_ingredient=findViewById(R.id.imageView_ingredient);
        text_info=findViewById(R.id.text_info);
        switch(ingredient){
            case "1":
                imageView_ingredient.setImageResource(R.drawable.water);
                text_info.setText(R.string.Water_info);
                break;
            case "2":
                imageView_ingredient.setImageResource(R.drawable.olive_oil);
                text_info.setText(R.string.Oil_info);
                break;
            case "3":
                imageView_ingredient.setImageResource(R.drawable.white_wheat_flour);
                text_info.setText(R.string.Flour_info);
                break;
            case "4":
                imageView_ingredient.setImageResource(R.drawable.sugar);
                text_info.setText(R.string.Sugar_info);
                break;
            case "5":
                imageView_ingredient.setImageResource(R.drawable.honey);
                text_info.setText(R.string.Honey_info);
                break;
            case "6":
                imageView_ingredient.setImageResource(R.drawable.wine_glass);
                text_info.setText(R.string.Wine_info);
                break;
            default:
                imageView_ingredient.setImageResource(R.drawable.bake);
                text_info.setText(R.string.Nothing_info);

        }

        info_back = (Button) findViewById(R.id.info_back);
        info_back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view){
            finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.75));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);


    }
}