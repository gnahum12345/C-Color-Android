package com.example.gaby.c_color;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import java.io.IOException;
import java.text.DecimalFormat;



public class MainActivity extends Activity implements SurfaceHolder.Callback {
    Camera camera;
    Camera.Parameters param;
    TextView label;
    String color;
    final int MAX_AVERAGE = 10;
    final String LABEL_TXT = "Color: ";
    final int MIN_YELLOW = 10;
    long [] average = new long[MAX_AVERAGE];
    int currIndex = 0;
    int count= 1;
    // number of pixels//transforms NV21 pixel data into RGB pixels
    long [] rgb;
    long midColor;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Button sliders;
    Button image;
    boolean recording = false;
    boolean flashlight = false;
    Camera.PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    Camera.PictureCallback jpegCallback;
    ZoomControls zoomControls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        surfaceHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        label = (TextView) findViewById(R.id.label);
        sliders = (Button)findViewById(R.id.sliders);
        image = (Button)findViewById(R.id.image);
        sliders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                label.setText("Color: " + getColor());

            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // recording = true;
                color = getColor();
                label.setText(color);
                label.setTextSize(20);
                //    label.setBackgroundColor((int)midColor);
            //    Log.w("Color", "midColor" + (int) midColor);
                switchActivity();

                //label.setTextColor((int)(midColor));
            }
        });
    }


    private void switchActivity(){
        Log.w("SwitchActivity", "Im here");
        if(flashlight){
            param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(param);
        }
        Intent intent = new Intent(MainActivity.this, CoreActivity.class);
        intent.putExtra("rgb", rgb);
        intent.putExtra("Width",camera.getParameters().getPreviewSize().width);
        intent.putExtra("Height",camera.getParameters().getPreviewSize().height);
        intent.putExtra("label", color);
        startActivity(intent);

    }

    public String getColor(){
        int rRed = getRed(midColor)/64;
        int rBlue = getBlue(midColor)/64;
        int rGreen = getGreen(midColor)/64 ;
        int reducedColor = ((rRed) + (rGreen*4) + (rBlue*16));
        switch(reducedColor){
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
      //          Log.w("Switch:", "Im at 16");
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
       //       Log.w("Switch:", "Im at 32");
                return "Blue"; //Blue
            case 33: //(1,0,2)
                return "Purple"; //Purple
            case 34: //(2,0,2)
                return "Purple"; //Purple
            case 35: //(3,0,2)
                return "Pink"; //Pink
            case 36: //(0,1,2)
       //         Log.w("Switch:", "Im at 36");
                return "Blue"; //BLUE
            case 37: //(1,1,2)
       //         Log.w("Switch:", "Im at 37");
                return "Blue"; //BLUE
            case 38: //(2,1,2)
                return "Purple"; //PURPLE
            case 39: //(3,1,2)
                return "Pink"; //PINK
            case 40: //(0,2,2)
       //         Log.w("Switch:", "Im at 40");
                return "Green"; //GREEN
            case 41: //(1,2,2)
        //        Log.w("Switch:", "Im at 41");
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
        //        Log.w("Switch:", "Im at 48");
                return "Blue"; // BLUE
            case 49: //(1,0,3)
         //       Log.w("Switch:", "Im at 49");
                return "Blue"; //BLUE
            case 50: //(2,0,3)
                return "Purple"; //PURPLE
            case 51: //(3,0,3)
                return "Pink"; //PINK
            case 52: //(0,1,3)
         //       Log.w("Switch:", "Im at 52");
                return "Blue"; //BLUE
            case 53: //(1,1,3)
         //       Log.w("Switch:", "Im at 53");
                return "Blue"; //BLUE
            case 54: //(2,1,3)
                return "Purple"; //PURPLE
            case 55: //(3,1,3)
                return "Pink"; //PINK
            case 56: //(0,2,3)
         //       Log.w("Switch:", "Im at 56");
                return "Blue"; //BLUE
            case 57: //(1,2,3)
        //        Log.w("Switch:", "Im at 57");
                return "Blue"; //BLUE
            case 58: //(2,2,3)
        //        Log.w("Switch:", "Im at 58");
                return "Purple"; //PURPLE
            case 59: //(3,2,3)
                return "Pink"; //PINK
            case 60: //(0,3,3)
         //       Log.w("Switch:", "Im at 60");
                return "Green"; //GREEN
            case 61: //(1,3,3)
         //       Log.w("Switch:", "Im at 61");
                return "Green"; //GREEN
            case 62: //(2,3,3)
          //      Log.w("Switch:", "Im at 62");
                return "Gray"; // GRAY
            case 63: //(3,3,3)
                return "Gray"; //GRAY
            default:
                return "Unidentifiable color";
        }
    }
    /*switch(reducedColor){
                case 0:
                    color = "Black";
                    break;
                case 1:
                    color = "Blue";
                    break;
                case 2:
                    color = "Green";
                    break;
                case 3:
                    color = "Cyan";
                    break;
                case 4:
                    color = "Red";
                    break;
                case 5:
                    color = "Purple";
                    break;
                case 6:
                    color = "Yellow";
                    break;
                case 7:
                    color = "White";
                    break;
                default:
                    color = "Undetermined";

            }*/
/*
    public double getClosest(){
        double closest = distanceFromPerfect(average[0]);
        for(int i = 1; i < MAX_AVERAGE; i++){
            if(closest < distanceFromPerfect(average[i])){
                closest = distanceFromPerfect(average[i]);
            }
        }
        return closest;

    }

    public void addAverage(long aver){
        if(recording){
            this.average[currIndex] = aver;
            currIndex++;
            //       Log.w("worked", "adding the average worked");
            //circular array for measurements.
            if(currIndex == MAX_AVERAGE){
                Log.w("going to calc", "maxAverage was reached");
                currIndex = 0;
                recording = false;
                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                //           Log.w("Average", "getting average was successful");
           //     double percentage = getClosest()
                String color = getColor();
                label.setText("Color: " + color);
                switch(color.toUpperCase()){
                    case "RED":
                        label.setHighlightColor(Color.RED);
                        break;
                    case "BLUE":
                        label.setHighlightColor(Color.BLUE);
                        break;


                }

                //         Log.w("Percentage", "percentage was successful");
     //           num.setText(decimalFormat.format(percentage) + "%");
     //           mProgress.setProgress((int) percentage);

                //   param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                //   camera.setParameters(param);
            }
        }
    }
    public double distanceFromPerfect(long color){
        long red = getRed(color);
        long blue = getBlue(color);
        long green = getGreen(color);

   //     final double maxDistance = Math.sqrt( Math.pow((IDEAL_RED),2) + Math.pow((IDEAL_GREEN),2) + Math.pow((IDEAL_BLUE), 2));
        //    Log.w("distance", "calculating perfect distance was successful");
  //      double distance = Math.sqrt(Math.pow((IDEAL_RED-red),2)+ Math.pow((IDEAL_GREEN-green),2) + Math.pow((IDEAL_BLUE-blue),2));
  //      Log.w("color", "Red: " + red + " blue" + blue + " green " + green);
   //     return ((100)- ((distance /maxDistance) *100));
        return 0;
    }


*/
    //return 0-255
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


    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        try {
            camera.setPreviewCallback(new Camera.PreviewCallback() {
                @Override
                public void onPreviewFrame(byte[] data, Camera camera) {
                    int frameHeight = camera.getParameters().getPreviewSize().height;
                    int frameWidth = camera.getParameters().getPreviewSize().width;
                    // number of pixels//transforms NV21 pixel data into RGB pixels
                    // convert
                    rgb = decodeYUV420SP(rgb, data, frameWidth, frameHeight);
                    //  Log.w("decoding", "decoding was successful");
            /*        long num;
                    long avg =0;
                    for(int  i = 0; i< rgb.length; i++){
                        num = rgb[i];
                        //num = checkColor(rgb[i]);
                        //     Log.w("color", "checkYellow was successful");
                        //if it is within range, than num = rgb[i] else it = 0;
                        avg += num;
                        count++;

                    }
                    Log.w("count", " " + count + " length: " + rgb.length);
                    avg = avg/count;
                    int accounted = ((count *100)/rgb.length);
                    count = 1;
                    //  Log.w("accounted", "accounted was successful");
                    if(accounted >= MIN_YELLOW){
                        addAverage(avg);
                        //     Log.w("addAverage", "was successful");
                    }
               */
                    //   if(recording){
                    midColor = rgb[rgb.length / 2];
                    //      recording = false;
                    //     }
                }
            });
            if(param.isZoomSupported()){
                final int maxZoom = param.getMaxZoom();
                zoomControls.setIsZoomOutEnabled(true);
                zoomControls.setIsZoomInEnabled(true);

                zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
            //            Log.w("Zoom", "I, in ZoomIn");
                        if (param.getZoom() < maxZoom) {
                            int currZoom = param.getZoom();
              //              Log.w("Zoom", "CurrZoom:" + currZoom);
                            currZoom +=2;
                            if(currZoom > maxZoom)
                                currZoom = maxZoom;
                //            Log.w("Zoom", "CurrZoom after:" + currZoom);
                            param.setZoom(currZoom);
                            camera.setParameters(param);

                        }
                    }
                });
                zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                 //       Log.w("Zoom", "I, in ZoomOut");
                        if (param.getZoom() > 0) {
                            int currZoom = param.getZoom();
                   //         Log.w("Zoom", "CurrZoom:" + currZoom);
                            currZoom -=2;
                            if(currZoom < 0)
                                currZoom = 0;
                    //        Log.w("Zoom", "CurrZoom after:" + currZoom);
                            param.setZoom(currZoom);
                            camera.setParameters(param);

                        }
                    }
                });

            }else{
            //    Log.w("Zoom", "zoom does not work");
                zoomControls.setVisibility(View.GONE);

            }
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {

        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        refreshCamera();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // open the camera
            camera = Camera.open();
            zoomControls = (ZoomControls) findViewById(R.id.zoomControls);
        } catch (RuntimeException e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
        param = camera.getParameters();
        param.setPreviewFormat(ImageFormat.NV21);
        camera.setDisplayOrientation(90);
        // Log.i("Camera", "camera is displayed properly.");
        // modify parameter
        param.setPreviewSize(352, 288);
        //param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        int maxZoom = param.getMaxZoom();
        param.setZoom(maxZoom / 2);
        param.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        camera.setParameters(param);
        try {
            // The Surface has been created, now tell the camera where to draw
            // the preview.
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            int frameHeight = camera.getParameters().getPreviewSize().height;
            int frameWidth = camera.getParameters().getPreviewSize().width;
            // number of pixels//transforms NV21 pixel data into RGB pixels
            rgb = new long[frameWidth * frameHeight];
        } catch (Exception e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // stop preview and release camera
        if(flashlight){
            flashLightOff();
        }
        camera.stopPreview();
        camera.release();
        camera = null;
    }



    //  Byte decoder : ---------------------------------------------------------------------
    public long[] decodeYUV420SP(long[] rgb, byte[] yuv420sp, int width, int height) {
        // Pulled directly from:
        // http://ketai.googlecode.com/svn/trunk/ketai/src/edu/uic/ketai/inputService/KetaiCamera.java
        final int frameSize = width * height;

        for (int j = 0, yp = 0; j < height; j++) { int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0)
                    y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }

                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0)
                    r = 0;
                else if (r > 262143)
                    r = 262143;
                if (g < 0)
                    g = 0;
                else if (g > 262143)
                    g = 262143;
                if (b < 0)
                    b = 0;
                else if (b > 262143)
                    b = 262143;

                rgb[yp] =  0x00ffffff & (((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff));
            }
        }
        return rgb;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(flashlight){
            flashLightOff();
        }
        camera.setParameters(param);
        try
        {
            // release the camera immediately on pause event
            //releaseCamera();
            camera.stopPreview();
            camera.setPreviewCallback(null);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(flashlight)
            flashLightOn();
        refreshCamera();
    }

    private void flashLightOff(){

        param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(param);
    }

    private void flashLightOn(){
        param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(param);
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.save).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();
        Context context = getApplicationContext();
        CharSequence text = "Flashlight on";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        //noinspection SimplifiableIfStatement
        if (id == R.id.flashlight) {
            if(flashlight == false) {
                flashlight = true;
                toast.show();
                flashLightOn();
            } else {
                flashlight = false;
                toast.setText("Flashlight off");
                toast.show();
                flashLightOff();
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
