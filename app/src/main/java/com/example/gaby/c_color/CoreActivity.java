package com.example.gaby.c_color;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.Image;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.ArrayList;



public class CoreActivity extends Activity{
    ImageView imageView;
    Button back;
    TextView label;
    long[] rgb;
    int[] rgbInt;
    int previewWidth;
    int previewHeight;
    String color;
   // RadioGroup radioGroup;
    //RadioButton radio;

    CheckBox red,green,blue, brown, yellow, purple, pink, orange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_image);
        imageView = (ImageView) findViewById(R.id.imageView);
        back = (Button) findViewById(R.id.back);
        label = (TextView) findViewById(R.id.label);
        color = getIntent().getStringExtra("label");
     //   radioGroup = (RadioGroup) findViewById(R.id.colorRadio);

       // String txt = getIntent().getStringExtra("label");
     //   label.setText(txt);
        red = (CheckBox) findViewById(R.id.Red);
        brown = (CheckBox) findViewById(R.id.Brown);
        blue = (CheckBox) findViewById(R.id.Blue);
        yellow = (CheckBox) findViewById(R.id.Yellow);
        purple = (CheckBox) findViewById(R.id.Purple);
        pink = (CheckBox) findViewById(R.id.Pink);
        orange = (CheckBox) findViewById(R.id.Orange);
        green = (CheckBox) findViewById(R.id.Green);


        rgb = getIntent().getLongArrayExtra("rgb");
        previewHeight = getIntent().getIntExtra("Height", 375);
        previewWidth = getIntent().getIntExtra("Width", 375);
        rgbInt = new int[rgb.length];
        for(int i = 0; i<rgb.length; i++){
            rgbInt[i] =  (((int)rgb[i]) | 0xff000000);

            //rgbInt[i] = Color.BLUE;
        }


//        Log.w("Preview", "width:" + previewWidth + "\npreviewHeight: " + previewHeight + "\nrgb:" + rgb + "\nrgbInt: " + rgbInt);


        Bitmap bitmap = Bitmap.createBitmap(rgbInt, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
        //should be a black photo.
        Drawable d = new BitmapDrawable(getResources(), bitmap);

        imageView.setBackground(d);
        imageView.setRotation(90);
        imageView.setVisibility(View.VISIBLE);
        imageView.invalidate();
//        Log.w("Preview: ", " " + imageView.isShown());
        label.setText("Color: " + color);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

        red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String colors2Change = boxesChecked();
                if (isChecked) {
                    Log.w("Checked", "Red is checked");
                    Log.w("Checked", "Changing colors");
                    int[] nRGB = new int[rgb.length];
                    for (int i = 0; i < rgb.length; i++) {
                        if (colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    colors2Change = boxesChecked();
                    Log.w("checkedBoxes", colors2Change);
                    int[] nRGB = new int[rgb.length];
                    for (int i = 0; i < rgb.length; i++) {
                        if (colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                    // TODO: Revert Image or notify the user they need to click all their boxes again

                }
            }
        });

        blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String colors2Change = boxesChecked();
                Log.w("checkedBoxes:", colors2Change);
                if (isChecked) {
                    Log.w("Checked", "Blue is checked");
                    Log.w("Checked", "Changing colors");
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int)rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    colors2Change = boxesChecked();
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int)rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                    //TODO: Revert Image or notify the user they need to click all their boxes again

                }
            }
        });
        green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String colors2Change = boxesChecked();
                Log.w("checkedBoxes:", colors2Change);
                if (isChecked) {
                    Log.w("Checked", "Green is checked");
                    Log.w("Checked", "Changing colors");
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    colors2Change = boxesChecked();
                    Log.w("checkedBoxes:", colors2Change);
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                    // TODO: Revert Image or notify the user they need to click all their boxes again

                }
            }
        });
        yellow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String colors2Change = boxesChecked();
                Log.w("checkedBoxes:", colors2Change);
                if (isChecked) {
                    Log.w("Checked", "Yellow is checked");
                    Log.w("Checked", "Changing colors");
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    colors2Change = boxesChecked();
                    Log.w("checkedBoxes:", colors2Change);
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                    // TODO: Revert Image or notify the user they need to click all their boxes again

                }
            }
        });
        orange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String colors2Change = boxesChecked();
                Log.w("checkedBoxes:", colors2Change);
                if (isChecked) {
                    Log.w("Checked", "Orange is checked");
                    Log.w("Checked", "Changing colors");
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++) {
                        if (colors2Change.contains(getColor((int) rgb[i]))){
                            nRGB[i] = Color.BLACK;
                        }else{
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);
                        }

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    colors2Change = boxesChecked();
                    Log.w("checkedBoxes:", colors2Change);
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int)rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);


                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                    // TODO: Revert Image or notify the user they need to click all their boxes again

                }
            }
        });
        brown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String colors2Change = boxesChecked();
                Log.w("checkedBoxes:", colors2Change);
                if (isChecked) {
                    Log.w("Checked", "Brown is checked");
                    Log.w("Checked", "Changing colors");
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    colors2Change = boxesChecked();
                    Log.w("checkedBoxes:", colors2Change);
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                    // TODO: Revert Image or notify the user they need to click all their boxes again

                }
            }
        });
        pink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String colors2Change = boxesChecked();
                Log.w("checkedBoxes:", colors2Change);
                if (isChecked) {
                    Log.w("Checked", "Pink is checked");
                    Log.w("Checked", "Changing colors");
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    colors2Change = boxesChecked();
                    Log.w("checkedBoxes:", colors2Change);
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                    // TODO: Revert Image or notify the user they need to click all their boxes again

                }
            }
        });
        purple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String colors2Change = boxesChecked();
                Log.w("checkedBoxes:", colors2Change);
                if (isChecked) {
                    Log.w("Checked", "Purple is checked");
                    Log.w("Checked", "Changing colors");
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);

                } else {
                    colors2Change = boxesChecked();
                    Log.w("checkedBoxes:", colors2Change);
                    int [] nRGB = new int[rgb.length];
                    for(int i=0; i<rgb.length; i++){
                        if(colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int)rgb[i]))| 0xff000000);

                    }
                    Bitmap bitmap = Bitmap.createBitmap(nRGB, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                    // TODO: Revert Image or notify the user they need to click all their boxes again

                }
            }
        });
        
/*
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedID = group.getCheckedRadioButtonId();
                switch(selectedID){
                    case R.id.Yellow:
                        Log.w("Preview", "Yellow");
                        changeYellow();
                        break;
                    case R.id.Red:
                        Log.w("Preview", "Red");
                        changeRed();
                        break;
                    case R.id.Blue:
                        Log.w("Preview", "Blue");
                        changeBlue();
                        break;
                    case R.id.Green:
                        Log.w("Preview", "Green");
                        changeGreen();
                        break;
                    case R.id.Brown:
                        Log.w("Preview" , "Brown");
                        changeImage("Brown");
                    case R.id.none:
                        Log.w("Preview", "none");
                        changeNormal();
                        break;

                }
            }
        });*/



    }
/*
    private void changeImage(String color){
        Log.w("Preview", "Im at change" + color);
        int [] nRgb = new int[rgb.length];
        for(int i = 0; i <rgb.length; i++){
            if(getColor((int)rgb[i]).equals(color))
                nRgb[i] = Color.BLACK;
            else
                nRgb[i] = ((((int)rgb[i]))| 0xff000000);

        }
        Bitmap bitmap = Bitmap.createBitmap(nRgb, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        imageView.setBackground(d);
        imageView.setRotation(90);
        imageView.setVisibility(View.VISIBLE);
        imageView.invalidate();
    }
    public void changeNormal(){
        Log.w("Preview","Im at changeYellow");
        int [] rgbNormal = new int[rgb.length];
        for(int i = 0; i<rgb.length; i++){
            rgbNormal[i] =  ((((int)rgb[i])) | 0xff000000);
            //rgbInt[i] = Color.BLUE;
        }
        Bitmap bitmap = Bitmap.createBitmap(rgbNormal, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        imageView.setBackground(d);
        imageView.setRotation(90);
        imageView.setVisibility(View.VISIBLE);
        imageView.invalidate();
    }
    public void changeYellow(){
        Log.w("Preview","Im at changeYellow");
        int [] rgbYellow = new int[rgb.length];
        for(int i = 0; i<rgb.length; i++){
            if(getColor((int)rgb[i]).equals("Yellow"))
                rgbYellow[i] =  Color.BLACK;
            else
                rgbYellow[i] = ((((int)rgb[i]))| 0xff000000);
            //rgbInt[i] = Color.BLUE;
        }
        Bitmap bitmap = Bitmap.createBitmap(rgbYellow, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        imageView.setBackground(d);
        imageView.setRotation(90);
        imageView.setVisibility(View.VISIBLE);
        imageView.invalidate();
    }
    public void changeBlue(){
        Log.w("Preview","Im at changeBlue");
        int [] rgbBlue = new int[rgb.length];
        for(int i = 0; i<rgb.length; i++){
            if(getColor((int)rgb[i]).equals("Blue"))
                rgbBlue[i] =  Color.BLACK;
            else
                rgbBlue[i] = ((((int)rgb[i]))| 0xff000000);
            //rgbInt[i] = Color.BLUE;
        }
        Bitmap bitmap = Bitmap.createBitmap(rgbBlue, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        imageView.setBackground(d);
        imageView.setRotation(90);
        imageView.setVisibility(View.VISIBLE);
        imageView.invalidate();
    }

    public void changeRed(){
        Log.w("Preview","Im at changeRed");
        int [] rgbRed = new int[rgb.length];
        for(int i = 0; i<rgb.length; i++){
            if(getColor((int)rgb[i]).equals("Red"))
                rgbRed[i] =  Color.BLACK;
            else
                rgbRed[i] = ((((int)rgb[i]))| 0xff000000);
            //rgbInt[i] = Color.BLUE;
        }
        Bitmap bitmap = Bitmap.createBitmap(rgbRed, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        imageView.setBackground(d);
        imageView.setRotation(90);
        imageView.setVisibility(View.VISIBLE);
        imageView.invalidate();

    }
    public void changeGreen(){
        Log.w("Preview","Im at changeGreen");
        int [] rgbGreen = new int[rgb.length];
        for(int i = 0; i<rgb.length; i++){
            if(getColor((int)rgb[i]).equals("Green"))
                rgbGreen[i] =  Color.BLACK;
            else
                rgbGreen[i] = ((((int)rgb[i]))| 0xff000000);
            //rgbInt[i] = Color.BLUE;
        }
        Bitmap bitmap = Bitmap.createBitmap(rgbGreen, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        imageView.setBackground(d);
        imageView.setRotation(90);
        imageView.setVisibility(View.VISIBLE);
        imageView.invalidate();

    }*/


    private String boxesChecked(){
        String boxes = "";
        if(red.isChecked())
            boxes += "Red";
        if(blue.isChecked())
            boxes += "Blue";
        if(brown.isChecked())
            boxes += "Brown";
        if(yellow.isChecked())
            boxes += "Yellow";
        if(purple.isChecked())
            boxes += "Purple";
        if(pink.isChecked())
            boxes += "Pink";
        if(orange.isChecked())
            boxes += "Orange";
        if(green.isChecked())
            boxes += "Green";
        return boxes;
    }

    public String getColor(int midColor){
        int rRed = getRed(midColor)/64;
        int rBlue = getBlue(midColor)/64;
        int rGreen = getGreen(midColor)/64 ;
        int reducedColor = ((rRed) + (rGreen*4) + (rBlue*16));
        switch(reducedColor){                                        // 19 BLUES------5 REDS--------13 GREENS-----7 YELLOWS----4 ORANGES
            case 0: //(0,0,0) 0=32,1=96,2=160,3=224
                return "Black";
            case 1: //(1,0,0)
                return "Brown";
            case 2: //(2,0,0)
                return "Red";
            case 3: //(3,0,0)
                return "Red";
            case 4: //(0,1,0)
                return "Green";
            case 5: //(1,1,0)
                return "Yellow";
            case 6: //(2,1,0)
                return "Orange";
            case 7: //(3,1,0)
                return "Orange";
            case 8: //(0,2,0)
                return "Green";
            case 9: //(1,2,0)
                return "Green";
            case 10: //(2,2,0)
                return "Yellow";
            case 11: //(3,2,0)
                return "Orange";
            case 12: //(0,3,0)
                return "Green";
            case 13: //(1,3,0)
                return "Green";
            case 14: //(2,3,0)
                return "Green";
            case 15: //(3,3,0)
                return "Yellow";
            case 16: //(0,0,1)
                Log.w("Switch:", "Im at 16");
                return "Blue";
            case 17: //(1,0,1)
                return "Purple";
            case 18: //(2,0,1)
                return "Purple";
            case 19: //(3,0,1)
                return "Red";
            case 20: //(0,1,1)
                Log.w("Switch:", "Im at 20");
                return "Blue";
            case 21: //(1,1,1)
                return "Gray";
            case 22: //(2,1,1)
                return "Red";
            case 23: //(3,1,1)
                return "Red";
            case 24: //(0,2,1)
                return "Green";
            case 25: //(1,2,1)
                return "Green";
            case 26: //(2,2,1)
                return "Yellow";
            case 27: //(3,2,1)
                return "Orange";
            case 28: //(0,3,1)
                return "Green";
            case 29: //(1,3,1)
                return "Green";
            case 30: //(2,3,1)
                return "Yellow";
            case 31: //(3,3,1)
                return "Yellow";
            case 32: //(0,0,2)
                Log.w("Switch:", "Im at 32");
                return "Blue";
            case 33: //(1,0,2)
                return "Purple";
            case 34: //(2,0,2)
                return "Purple";
            case 35: //(3,0,2)
                return "Pink";
            case 36: //(0,1,2)
                Log.w("Switch:", "Im at 36");
                return "Blue";
            case 37: //(1,1,2)
                Log.w("Switch:", "Im at 37");
                return "Blue";
            case 38: //(2,1,2)
                return "Purple";
            case 39: //(3,1,2)
                return "Pink";
            case 40: //(0,2,2)
                Log.w("Switch:", "Im at 40");
                return "Blue";
            case 41: //(1,2,2)
                Log.w("Switch:", "Im at 41");
                return "Blue";
            case 42: //(2,2,2)
                return "Gray";
            case 43: //(3,2,2)
                return "Pink";
            case 44: //(0,3,2)
                return "Green";
            case 45: //(1,3,2)
                return "Green";
            case 46: //(2,3,2)
                return "Green";
            case 47: //(3,3,2)
                return "Yellow";
            case 48: //(0,0,3)
                Log.w("Switch:", "Im at 48");
                return "Blue";
            case 49: //(1,0,3)
                Log.w("Switch:", "Im at 49");
                return "Blue";
            case 50: //(2,0,3)
                return "Purple";
            case 51: //(3,0,3)
                return "Pink";
            case 52: //(0,1,3)
                Log.w("Switch:", "Im at 52");
                return "Blue";
            case 53: //(1,1,3)
                Log.w("Switch:", "Im at 53");
                return "Blue";
            case 54: //(2,1,3)
                return "Purple";
            case 55: //(3,1,3)
                return "Pink";
            case 56: //(0,2,3)
                Log.w("Switch:", "Im at 56");
                return "Blue";
            case 57: //(1,2,3)
                Log.w("Switch:", "Im at 57");
                return "Blue";
            case 58: //(2,2,3)
                Log.w("Switch:", "Im at 58");
                return "Blue";
            case 59: //(3,2,3)
                return "Pink";
            case 60: //(0,3,3)
                Log.w("Switch:", "Im at 60");
                return "Blue";
            case 61: //(1,3,3)
                Log.w("Switch:", "Im at 61");
                return "Blue";
            case 62: //(2,3,3)
                Log.w("Switch:", "Im at 62");
                return "Blue";
            case 63: //(3,3,3)
                return "White";
            default:
                return "Unidentifiable color";
        }
    }


    public int getRed(long color){
        int red = (int) (((color & 0xFF0000) >>16) & 0xFF);
        return red;
    }
    //return 0-255
    public int getGreen(long color){
        int green = (int) (((color & 0xFF00) >> 8) & 0xFF);
        return green;
    }
    //return 0-255
    public int getBlue(long color){
        int blue = (int) (color & 0xFF);
        return blue;
    }


    private void backToMain(){
        Intent intent = new Intent(CoreActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    //    param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        try
        {
            // release the camera immediately on pause event
            //releaseCamera();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        //   param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        //  camera.setParameters(param);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*//noinspection SimplifiableIfStatement
        if (id == R.id.flashlight) {

        }*/

        //return super.onOptionsItemSelected(item);                   LOOK AT ME!!!!!! I SAID LOOK AT ME!!!!!!!!!!!!!!!!!!!!!
        // -------------------------------------------------I SAID LOOK AT ME-----------WHY YOU NO LOOK AT ME---------------

        return true;
    }
}
