package com.example.mizuno.prog_imageselect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageSelectActivity extends AppCompatActivity {

    public TextView text;
    public EditText editText;
    public String filepath;
    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select);

        text = (TextView) findViewById(R.id.textView);
        editText=(EditText) findViewById(R.id.editText);
        Button button=(Button) findViewById(R.id.button);
        imageView=(ImageView) findViewById(R.id.imageView);

        //初期ディレクトリの指定と表示
        filepath= Environment.getExternalStorageDirectory().toString() +"/"+ Environment.DIRECTORY_PICTURES+"/" ;
        text.setText(filepath);

        //表示ボタンをクリックしたあとの挙動
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = editText.getText().toString();
                text.setText(filepath + filename);
                File file = new File(filepath + filename);

                try {
                    InputStream is = new FileInputStream(file);
                    Bitmap bm = BitmapFactory.decodeStream(is);
                    imageView.setImageBitmap(bm);

                } catch (FileNotFoundException e) {
                    text.setText("画像がありません");
                    e.printStackTrace();
                }
                editText.setText(null);
            }
        });

    }
}
