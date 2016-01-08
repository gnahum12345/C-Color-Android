package com.example.gaby.c_color;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;

import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class CoreActivity extends Activity{
    ImageView imageView;
   // Button back;
    TextView label;
    long[] rgb;
    int[] rgbInt;
    String [] colors;
    int previewWidth;
    int previewHeight;
    String color;
    Bitmap bitmap;
    ToggleButton toggleButton;
    boolean checked = false;

    // RadioGroup radioGroup;
    //RadioButton radio;

    CheckBox gray,red,green,blue, brown, yellow, purple, pink, orange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_image);
        imageView = (ImageView) findViewById(R.id.imageView);
        Log.w("activity", "im in core");
        toggleButton = (ToggleButton) findViewById(R.id.BorW);
        toggleButton.setText("Black");
        //back = (Button) findViewById(R.id.back);
        label = (TextView) findViewById(R.id.label);
        color = getIntent().getStringExtra("label");
        colors = getIntent().getStringArrayExtra("arrayOfColors");
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
        gray = (CheckBox) findViewById(R.id.gray);
        rgb = getIntent().getLongArrayExtra("rgb");
        previewHeight = getIntent().getIntExtra("Height", 375);
        previewWidth = getIntent().getIntExtra("Width", 375);
        rgbInt = new int[rgb.length];
        for(int i = 0; i<rgb.length; i++){
            rgbInt[i] =  (((int)rgb[i]) | 0xff000000);

            //rgbInt[i] = Color.BLUE;
        }


//        Log.w("Preview", "width:" + previewWidth + "\npreviewHeight: " + previewHeight + "\nrgb:" + rgb + "\nrgbInt: " + rgbInt);


        bitmap = Bitmap.createBitmap(rgbInt, previewWidth , previewHeight, Bitmap.Config.ARGB_8888);
        //should be a black photo.
        Drawable d = new BitmapDrawable(getResources(), bitmap);

        imageView.setBackground(d);
        imageView.setRotation(90);
        imageView.setVisibility(View.VISIBLE);
        imageView.invalidate();
//        Log.w("Preview: ", " " + imageView.isShown());
        label.setText("Color: " + color);
    /*    back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

*/
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   checked = true;
                   toggleButton.setText("White");
                   toggleButton.setTextOn("White");
                   Log.w("ChangeImage","will go to change image and will turn white");
                   changeImage();
               } else{
                   checked = false;
                   toggleButton.setText("Black");
                   toggleButton.setTextOff("Black");
                   Log.w("ChangeImage", "will go to change image and will turn black");
                   changeImage();
               }

                /*String colors2Change = boxesChecked();
                if (isChecked) {
                    Log.w("Toggle Button", "Im true");
                    checked = true;
                    toggleButton.setTextOn("White");
                    int[] nRGB = new int[rgb.length];
                    for (int i = 0; i < rgb.length; i++) {
                        if (colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.WHITE;
                        else
                            nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                    }
                    bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    checked = false;
                    Log.w("Toggle Button", "Im false");
                    toggleButton.setTextOff("Black");
                    int[] nRGB = new int[rgb.length];
                    for (int i = 0; i < rgb.length; i++) {
                        if (colors2Change.contains(getColor((int) rgb[i])))
                            nRGB[i] = Color.BLACK;
                        else
                            nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                    }
                    bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                    Drawable d = new BitmapDrawable(getResources(), bitmap);
                    imageView.setBackground(d);
                    imageView.setRotation(90);
                    imageView.setVisibility(View.VISIBLE);
                }*/
            }
        });

        gray.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
                /*String colors2Change = boxesChecked();
                if (checked && toggleButton.getText().equals("White")) {
                    if (isChecked) {
                        Log.w("Checked", "Gray is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }else{
                    if (isChecked) {
                        Log.w("Checked", "Gray is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.BLACK;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }

                }*/
            }
        });
        red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
                /*String colors2Change = boxesChecked();
                if(!checked && toggleButton.getText().equals("Black")) {
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
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }else{
                    if (isChecked) {
                        Log.w("Checked", "Red is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }*/
            }
        });

        blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
              /*  String colors2Change = boxesChecked();
                if(!checked && toggleButton.getText().equals("Black")) {
                    if (isChecked) {
                        Log.w("Checked", "Blue is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.BLACK;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }else{
                    if (isChecked) {
                        Log.w("Checked", "Blue is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }*/
            }
        });
        green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
                /*String colors2Change = boxesChecked();
                if (!checked && toggleButton.getText().equals("Black")) {
                    Log.w("checkedBoxes:", colors2Change);
                    if (isChecked) {
                        Log.w("Checked", "Green is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.BLACK;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    } else {
                        colors2Change = boxesChecked();
                        Log.w("checkedBoxes:", colors2Change);
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.BLACK;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }else{
                    if (isChecked) {
                        Log.w("Checked", "Green is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }

                }*/
            }
        });
        yellow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
                /*String colors2Change = boxesChecked();
                if(!checked && toggleButton.getText().equals("Black")) {
                    if (isChecked) {
                        Log.w("Checked", "Yellow is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.BLACK;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }else{
                    if (isChecked) {
                        Log.w("Checked", "Yellow is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }*/
            }
        });
        orange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
                /*String colors2Change = boxesChecked();
                if(!checked && toggleButton.getText().equals("Black")) {
                    if (isChecked) {
                        Log.w("Checked", "Orange is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.BLACK;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }else{
                    if (isChecked) {
                        Log.w("Checked", "Orange is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }*/
            }
        });
        brown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
                /*String colors2Change = boxesChecked();
                if(!checked && toggleButton.getText().equals("Black")) {
                    if (isChecked) {
                        Log.w("Checked", "Brown is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.BLACK;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }else{
                    if (isChecked) {
                        Log.w("Checked", "Brown is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }*/
            }
        });
        pink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImage();
                /*String colors2Change = boxesChecked();
                if(!checked && toggleButton.getText().equals("Black")) {
                    if (isChecked) {
                        Log.w("Checked", "Pink is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.BLACK;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }else{
                    if (isChecked) {
                        Log.w("Checked", "Pink is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }*/
            }
        });
        purple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               changeImage();
                /*String colors2Change = boxesChecked();
                if(!checked && toggleButton.getText().equals("Black")) {
                    if (isChecked) {
                        Log.w("Checked", "Purple is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.BLACK;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }else{
                    if (isChecked) {
                        Log.w("Checked", "Purple is checked");
                        Log.w("Checked", "Changing colors");
                        int[] nRGB = new int[rgb.length];
                        for (int i = 0; i < rgb.length; i++) {
                            if (colors2Change.contains(getColor((int) rgb[i])))
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                                nRGB[i] = Color.WHITE;
                            else
                                nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                        }
                        bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        imageView.setBackground(d);
                        imageView.setRotation(90);
                        imageView.setVisibility(View.VISIBLE);

                    }
                }*/
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

    private void changeImage(){
        String colors2Change = boxesChecked();
        if(!checked && (toggleButton.getText().equals("Black"))) {
            Log.w("ChangeImage","will be black");
            int[] nRGB = new int[rgb.length];
            for (int i = 0; i < rgb.length; i++) {
                if (colors2Change.contains(getColor((int) rgb[i])))
                    nRGB[i] = Color.BLACK;
                else
                    nRGB[i] = ((((int) rgb[i])) | 0xff000000);

            }
            bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
            Drawable d = new BitmapDrawable(getResources(), bitmap);
            imageView.setBackground(d);
            imageView.setRotation(90);
            imageView.setVisibility(View.VISIBLE);
        }else{
            Log.w("ChangeImage","Will be white");
            int[] nRGB = new int[rgb.length];
            for (int i = 0; i < rgb.length; i++) {
                if (colors2Change.contains(getColor((int) rgb[i])))
                    nRGB[i] = Color.WHITE;
                else
                    nRGB[i] = ((((int) rgb[i])) | 0xff000000);

            }
            bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
            Drawable d = new BitmapDrawable(getResources(), bitmap);
            imageView.setBackground(d);
            imageView.setRotation(90);
            imageView.setVisibility(View.VISIBLE);
        }
           /* if (isChecked) {
                Log.w("Checked", "Red is checked");
                Log.w("Checked", "Changing colors");
                int[] nRGB = new int[rgb.length];
                for (int i = 0; i < rgb.length; i++) {
                    if (colors2Change.contains(getColor((int) rgb[i])))
                        nRGB[i] = Color.BLACK;
                    else
                        nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                }
                bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                imageView.setBackground(d);
                imageView.setRotation(90);
                imageView.setVisibility(View.VISIBLE);

            }
        }else{
            if (isChecked) {
                Log.w("Checked", "Red is checked");
                Log.w("Checked", "Changing colors");
                int[] nRGB = new int[rgb.length];
                for (int i = 0; i < rgb.length; i++) {
                    if (colors2Change.contains(getColor((int) rgb[i])))
                        nRGB[i] = Color.WHITE;
                    else
                        nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                }
                bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
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
                        nRGB[i] = Color.WHITE;
                    else
                        nRGB[i] = ((((int) rgb[i])) | 0xff000000);

                }
                bitmap = Bitmap.createBitmap(nRGB, previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                imageView.setBackground(d);
                imageView.setRotation(90);
                imageView.setVisibility(View.VISIBLE);

            }
        }*/
    }
/*    public void changeNormal(){
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
        return boxes;
    }

    public String getColor(int midColor){
        int rRed = getRed(midColor)/64;
        int rBlue = getBlue(midColor)/64;
        int rGreen = getGreen(midColor)/64 ;
        int reducedColor = ((rRed) + (rGreen*4) + (rBlue*16));

        if((getGreen(midColor) + getBlue(midColor) + getRed(midColor) )== 765)
            return "White";
        for(int i = 0; i<colors.length; i++){
            if(reducedColor == i)
                return colors[i];
        }
        return "Color not recognized";
      /*  switch(reducedColor){
            case 0: //(0,0,0) 0=32,1=96,2=160,3=224
                return "Black";
            case 1: //(1,0,0)  (96,32,32)
                return "Brown";  // Brown
            case 2: //(2,0,0)  (160,32,32)
                return "Red"; // RED
            case 3: //(3,0,0) (224,32,32)
                return "Red"; // RED
            case 4: //(0,1,0)  (32,96,32)
                return "Green"; // GREEN
            case 5: //(1,1,0) (96,96,32)
                return "Yellow"; // YELLOW
            case 6: //(2,1,0)  (160,96,32)
                return "Brown"; // BROWN
            case 7: //(3,1,0) (224,96,32)
                return "Orange"; // ORANGE
            case 8: //(0,2,0)
                return "Green"; // GREEN
            case 9: //(1,2,0)
                return "Green"; //GREEN
            case 10: //(2,2,0)
                return "Yellow"; // YELLOW
            case 11: //(3,2,0)
                return "Brown"; // BROWN
            case 12: //(0,3,0)
                return "Green"; //GREEN
            case 13: //(1,3,0)
                return "Green"; //GREEN
            case 14: //(2,3,0)
                return "Green"; //GREEN
            case 15: //(3,3,0)
                return "Yellow"; //YELLOW
            case 16: //(0,0,1)
                // Log.w("Switch:", "Im at 16");
                return "Blue"; //BLUE
            case 17: //(1,0,1)
                return "Purple"; //PURPLE
            case 18: //(2,0,1)
                return "Purple"; //PURPLE
            case 19: //(3,0,1)
                return "Purple";  //PURPLE
            case 20: //(0,1,1)
                return "Blue"; //BLUE
            case 21: //(1,1,1)
                return "Gray"; //GRAY
            case 22: //(2,1,1)
                return "Brown"; //BROWN
            case 23: //(3,1,1)
                return "Brown"; // BROWN
            case 24: //(0,2,1)
                return "Green"; //GREEN
            case 25: //(1,2,1)
                return "Green"; //GREEN
            case 26: //(2,2,1)
                return "Green"; //Green
            case 27: //(3,2,1)
                return "Brown"; //Brown
            case 28: //(0,3,1)
                return "Green";  //GREEN
            case 29: //(1,3,1)
                return "Green"; //GREEN
            case 30: //(2,3,1)
                return "Green"; //GREEN
            case 31: //(3,3,1)
                return "Yellow"; //Yellow
            case 32: //(0,0,2)
                // Log.w("Switch:", "Im at 32");
                return "Blue"; //Blue
            case 33: //(1,0,2)
                return "Purple"; //Purple
            case 34: //(2,0,2)
                return "Purple"; //Purple
            case 35: //(3,0,2)
                return "Pink"; //Pink
            case 36: //(0,1,2)
                // Log.w("Switch:", "Im at 36");
                return "Blue"; //BLUE
            case 37: //(1,1,2)
                // Log.w("Switch:", "Im at 37");
                return "Blue"; //BLUE
            case 38: //(2,1,2)
                return "Purple"; //PURPLE
            case 39: //(3,1,2)
                return "Pink"; //PINK
            case 40: //(0,2,2)
                // Log.w("Switch:", "Im at 40");
                return "Green"; //GREEN
            case 41: //(1,2,2)
                // Log.w("Switch:", "Im at 41");
                return "Green"; //GREEN
            case 42: //(2,2,2)
                return "Gray"; //GRAY
            case 43: //(3,2,2)
                return "Pink"; //PINK
            case 44: //(0,3,2)
                return "Green"; //GREEN
            case 45: //(1,3,2)
                return "Green"; //GREEN
            case 46: //(2,3,2)
                return "Green"; //GREEN
            case 47: //(3,3,2)
                return "Yellow"; //YELLOW
            case 48: //(0,0,3)
                // Log.w("Switch:", "Im at 48");
                return "Blue"; // BLUE
            case 49: //(1,0,3)
                //Log.w("Switch:", "Im at 49");
                return "Blue"; //BLUE
            case 50: //(2,0,3)
                return "Purple"; //PURPLE
            case 51: //(3,0,3)
                return "Pink"; //PINK
            case 52: //(0,1,3)
                //Log.w("Switch:", "Im at 52");
                return "Blue"; //BLUE
            case 53: //(1,1,3)
                //Log.w("Switch:", "Im at 53");
                return "Blue"; //BLUE
            case 54: //(2,1,3)
                return "Purple"; //PURPLE
            case 55: //(3,1,3)
                return "Pink"; //PINK
            case 56: //(0,2,3)
                //Log.w("Switch:", "Im at 56");
                return "Blue"; //BLUE
            case 57: //(1,2,3)
                //Log.w("Switch:", "Im at 57");
                return "Blue"; //BLUE
            case 58: //(2,2,3)
                //Log.w("Switch:", "Im at 58");
                return "Purple"; //PURPLE
            case 59: //(3,2,3)
                return "Pink"; //PINK
            case 60: //(0,3,3)
                //Log.w("Switch:", "Im at 60");
                return "Green"; //GREEN
            case 61: //(1,3,3)
                //Log.w("Switch:", "Im at 61");
                return "Green"; //GREEN
            case 62: //(2,3,3)
                //Log.w("Switch:", "Im at 62");
                return "Gray"; // GRAY
            case 63: //(3,3,3)
                return "Gray"; //GRAY
            default:
                return "Unidentifiable color";
        }
        */
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
    //    param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);

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

    private Bitmap rotateBitmap(){
        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        return (Bitmap.createBitmap(bitmap , 0, 0, bitmap.getWidth(),  bitmap.getHeight(), matrix, true));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.flashlight).setVisible(false);

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
        }else if(id == R.id.back){
            Intent intent = new Intent(CoreActivity.this, MainActivity.class);
            startActivity(intent);
        }
        /*//noinspection SimplifiableIfStatement
        if (id == R.id.flashlight) {

        }*/

        //return super.onOptionsItemSelected(item);                   LOOK AT ME!!!!!! I SAID LOOK AT ME!!!!!!!!!!!!!!!!!!!!!
        // -------------------------------------------------I SAID LOOK AT ME-----------WHY YOU NO LOOK AT ME---------------

        return true;
    }
}
