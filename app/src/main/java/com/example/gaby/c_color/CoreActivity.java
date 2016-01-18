package com.example.gaby.c_color;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileInputStream;


public class CoreActivity extends Activity{

    ImageView imageView;
    TextView label;
    int[] rgbInt;
    String [] colors;
    int previewWidth;
    int previewHeight;
    String color;
    Bitmap bitmap;
    ToggleButton toggleButton;
    boolean checked = false;

    CheckBox gray,red,green,blue,brown,yellow,purple,pink,orange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("Picture", "at core");
        setContentView(R.layout.core_image);
        Log.w("Picture","finished setting content view");
        ActionBar actionBar = getActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        imageView = (ImageView) findViewById(R.id.imageView);
        toggleButton = (ToggleButton) findViewById(R.id.BorW);
        toggleButton.setText("White");
        toggleButton.setTextOff("White");
        toggleButton.setTextOn("Black");
        label = (TextView) findViewById(R.id.label);
        color = getIntent().getStringExtra("label");
        colors = getIntent().getStringArrayExtra("arrayOfColors");
        boolean fileExists = getIntent().hasExtra("Picture");
        Log.w("Picture", "fileName exists: " + fileExists);
        String filename = getIntent().getStringExtra("Picture");
        Log.w("Picture", "fileName" + filename);
        try {
            FileInputStream is = this.openFileInput(filename);
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
            /*if(filename.equals("bitmap.png")) {
                FileInputStream is = this.openFileInput(filename);
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            }else{
                File image = new File(filename);
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.w("Picture","bitmap:" + bitmap);

        red = (CheckBox) findViewById(R.id.Red);
        brown = (CheckBox) findViewById(R.id.Brown);
        blue = (CheckBox) findViewById(R.id.Blue);
        yellow = (CheckBox) findViewById(R.id.Yellow);
        purple = (CheckBox) findViewById(R.id.Purple);
        pink = (CheckBox) findViewById(R.id.Pink);
        orange = (CheckBox) findViewById(R.id.Orange);
        green = (CheckBox) findViewById(R.id.Green);
        gray = (CheckBox) findViewById(R.id.gray);
        previewHeight = getIntent().getIntExtra("Height", 375);
        previewWidth = getIntent().getIntExtra("Width", 375);


        rgbInt = new int[previewHeight*previewWidth];
        Log.w("Picture", "PreviewWidth:" + previewWidth + "\npreviewHeight:" + previewHeight);

        bitmap = Bitmap.createScaledBitmap(bitmap,previewWidth,previewHeight,true);
        bitmap.getPixels(rgbInt, 0, previewWidth, 0, 0, previewWidth, previewHeight);


        for (int y = 0; y < previewHeight; y++){
            for (int x = 0; x < previewWidth; x++)
            {
                int index = y * previewWidth + x;
                int R = (rgbInt[index] >> 16) & 0xff;     //bitwise shifting
                int G = (rgbInt[index] >> 8) & 0xff;
                int B = rgbInt[index] & 0xff;

                //R,G.B - Red, Green, Blue
                //to restore the values after RGB modification, use
                //next statement
                rgbInt[index] = 0xff000000 | (R << 16) | (G << 8) | B;
            }}

        //bitmap = Bitmap.createBitmap(rgbInt, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);

        Drawable d = new BitmapDrawable(getResources(), bitmap);

        imageView.setBackground(d);
        imageView.setRotation(90);
        imageView.setVisibility(View.VISIBLE);
        imageView.invalidate();
        if(color == null)
            label.setText("Color: ");
        else
            label.setText("Color: " + color);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y =   event.getY();
            //    Log.w("ImageView","X:" + x + "\nY:" + y + "\nWidth:" + previewWidth + "\nHeight:" + previewHeight);
                int col = getColorBitmap(x,y,v);

                label.setText("Color: " + getColor(col));
                return true;
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   checked = true;
                   toggleButton.setText("Black");
                   toggleButton.setTextOn("Black");
                   changeImage();
               } else{
                   checked = false;
                   toggleButton.setText("White");
                   toggleButton.setTextOff("White");
                   changeImage();
               }
            }
        });

        gray.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
            }
        });
        red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
            }
        });

        blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
            }
        });
        green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
            }
        });
        yellow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
            }
        });
        orange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
            }
        });
        brown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
            }
        });
        pink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
            }
        });
        purple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               changeImage();
            }
        });
    }

    private int getColorBitmap(float x, float y, View v) {

        if (x < 0 || y < 0 || x > (float)v.getWidth() || y > (float) v.getHeight()) {
            return 0; //Invalid, return 0
        } else {
            //Convert touched x, y on View to on Bitmap
            int xBm = (int) (x * (previewWidth / (double) v.getWidth()));
            int yBm = (int) (y * (previewHeight / (double) v.getHeight()));

            return bitmap.getPixel(xBm, yBm);
        }
    }




    private void changeImage(){
        String colors2Change = boxesChecked();
        if(!checked && (toggleButton.getText().equals("White"))) {
            int[] nRGB = new int[rgbInt.length];
            for (int i = 0; i < rgbInt.length; i++) {
                if (colors2Change.contains(getColor((int) rgbInt[i])))
                    nRGB[i] = Color.BLACK;
                else
                    nRGB[i] = ((((int) rgbInt[i])) | 0xff000000);

            }
            bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
            Drawable d = new BitmapDrawable(getResources(), bitmap);
            imageView.setBackground(d);
            imageView.setRotation(90);
            imageView.setVisibility(View.VISIBLE);
        }else {
            int[] nRGB = new int[rgbInt.length];
            for (int i = 0; i < rgbInt.length; i++) {
                if (colors2Change.contains(getColor((int) rgbInt[i])))
                    nRGB[i] = Color.WHITE;
                else
                    nRGB[i] = ((((int) rgbInt[i])) | 0xff000000);

            }
            bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
            Drawable d = new BitmapDrawable(getResources(), bitmap);
            imageView.setBackground(d);
            imageView.setRotation(90);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private String boxesChecked(){
        String boxes = "";
        if(red.isChecked())
            boxes += "Red ";
        if(blue.isChecked())
            boxes += "Blue ";
        if(brown.isChecked())
            boxes += "Brown ";
        if(yellow.isChecked())
            boxes += "Yellow ";
        if(purple.isChecked())
            boxes += "Purple ";
        if(pink.isChecked())
            boxes += "Pink ";
        if(orange.isChecked())
            boxes += "Orange ";
        if(green.isChecked())
            boxes += "Green ";
        if(gray.isChecked())
            boxes += "Gray ";

        return boxes;
    }

    public String getColor(int midColor) {
        int rRed = getRed(midColor) / 32;
        int rBlue = getBlue(midColor) / 32;
        int rGreen = getGreen(midColor) / 32;
        int reducedColor = ((rRed*64) + (rGreen * 8) + (rBlue));
        if ((getRed(midColor) + getBlue(midColor) + getGreen(midColor)) == 765)
            return "White";
        for (int i = 0; i < colors.length; i++) {
            if (i == reducedColor)
                return colors[i];
        }
        return "Not recognizable";
    }

    public int getRed(long color){
        return ((int) (((color & 0xFF0000) >>16) & 0xFF));
    }
    //return 0-255
    public int getGreen(long color){
        return  ((int) (((color & 0xFF00) >> 8) & 0xFF));
    }
    //return 0-255
    public int getBlue(long color){
        return ((int) (color & 0xFF));
    }



    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private Bitmap rotateBitmap(){
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        return (Bitmap.createBitmap(bitmap , 0, 0, bitmap.getWidth(),  bitmap.getHeight(), matrix, true));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.setGroupVisible(R.id.main,false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Context context = getApplicationContext();
        CharSequence text = "The image was saved in your photos";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        if(id == R.id.save){
            bitmap = rotateBitmap();
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "C-Color image" , "Saved image");
            toast.show();
        }else if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
