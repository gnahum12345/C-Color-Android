package com.example.gaby.c_color;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;




public class MainActivity extends Activity implements SurfaceHolder.Callback {
    Camera camera;
    Camera.Parameters param;
    TextView label;
    String color;

    final String [] colors = { "Black", "Black", "Black", "Blue", "Blue", "Blue", "Blue","Blue",
                        "Black","Black","Black", "Blue","Blue","Blue","Blue","Blue",
                        "Green","Green","Green","Blue","Blue","Blue","Blue","Blue",
                        "Green","Green","Green","Green","Blue","Blue","Blue","Blue",
                        "Green","Green","Green","Green","Green","Blue","Blue","Blue",
                        "Green","Green","Green","Green","Green","Blue","Blue","Blue",
                        "Green","Green","Green","Green","Green","Green","Blue","Blue",
                        "Green","Green","Green","Green","Green","Green","Green","Blue",

                        "Black","Black","Black","Black","Purple","Purple","Blue","Blue",
                        "Black","Black","Black","Black","Blue","Blue","Blue","Blue",
                        "Green","Green","Green","Blue","Blue","Blue","Blue","Blue",
                        "Green","Green","Green","Green","Blue","Blue","Blue","Blue",
                        "Green","Green","Green","Green","Green","Blue","Blue","Blue",
                        "Green","Green","Green","Green","Green","Green","Blue","Blue",
                        "Green","Green","Green","Green","Green","Green","Blue","Blue",
                        "Green","Green","Green","Green","Green","Green","Green","Blue",

                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Green","Green","Gray","Gray","Purple","Purple","Purple","Purple",
                        "Green","Green","Green","Gray","Gray","Purple","Blue","Blue",
                        "Green","Green","Green","Green","Green","Blue","Blue","Blue",
                        "Green","Green","Green","Green","Green","Green","Blue","Blue",
                        "Green","Green","Green","Green","Green","Green","Blue","Blue",
                        "Green","Green","Green","Green","Green","Green","Green","Blue",

                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Gray","Purple","Purple","Purple","Purple",
                        "Green","Green","Gray","Gray","Gray","Gray","Purple","Purple",
                        "Green","Green","Green","Gray","Gray","Gray","Blue","Blue",
                        "Green","Green","Green","Green","Gray","Gray","Blue","Blue",
                        "Green","Green","Green","Green","Green","Green","Green","Blue",
                        "Green","Green","Green","Green","Green","Green","Green","Blue",

                        "Red","Brown","Purple","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Gray","Gray","Purple","Purple","Purple",
                        "Green","Green","Green","Gray","Gray","Gray","Gray","Purple",
                        "Green","Green","Green","Green","Gray","Gray","Gray","Gray",
                        "Green","Green","Green","Green","Green","Gray","Gray","Purple",
                        "Green","Green","Green","Green","Green","Green","Green","Blue",

                        "Red","Red","Red","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Gray","Gray","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Brown","Gray","Gray","Purple","Purple",
                        "Green","Green","Green","Gray","Gray","Gray","Gray","Purple",
                        "Green","Green","Green","Green","Gray","Gray","Gray","Gray",
                        "Green","Green","Green","Green","Green","Green","Green","Blue",

                        "Red","Red","Red","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Purple","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown", "Brown","Purple","Purple","Purple","Purple",
                        "Brown","Brown","Brown","Brown","Gray","Purple","Purple","Purple",
                        "Yellow","Yellow","Yellow","Yellow","Gray","Gray","Gray","Purple",
                        "Yellow","Yellow","Yellow","Yellow","Gray","Gray","Gray","Gray",
                        "Green","Green","Green","Green","Gray","Gray","Gray","Gray",

                        "Red","Red","Red","Red","Pink","Pink","Pink","Pink",
                        "Red","Red","Red","Red","Pink","Pink","Pink","Pink",
                        "Orange","Orange","Red","Red","Pink","Pink","Pink","Pink",
                        "Orange","Orange","Orange","Red","Pink","Pink","Pink","Pink",
                        "Orange","Orange","Orange","Orange","Pink","Pink","Pink","Pink",
                        "Orange","Orange","Orange","Orange","Orange","Pink","Pink","Pink",
                        "Orange","Orange","Orange","Orange","Orange","Gray","Pink","Pink",
                        "Yellow","Yellow","Yellow","Yellow","Yellow","Gray","Gray","Gray"};

// number of pixels//transforms NV21 pixel data into RGB pixels

    long [] rgb;
    long midColor;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Button sliders;
    Button image;
    boolean flashlight = false;
    boolean landscape = false;
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
                color = getColor();
                label.setText(color);
                label.setTextSize(20);
                switchActivity();

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
        intent.putExtra("arrayOfColors", colors);
        startActivity(intent);

    }

    public String getColor() {
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

                    midColor = rgb[rgb.length / 2];
                }
            });
            if(param.isZoomSupported()){
                final int maxZoom = param.getMaxZoom();
                zoomControls.setIsZoomOutEnabled(true);
                zoomControls.setIsZoomInEnabled(true);

                zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (param.getZoom() < maxZoom) {
                            int currZoom = param.getZoom();
                            currZoom +=2;
                            if(currZoom > maxZoom)
                                currZoom = maxZoom;
                            param.setZoom(currZoom);
                            camera.setParameters(param);

                        }
                    }
                });
                zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (param.getZoom() > 0) {
                            int currZoom = param.getZoom();
                            currZoom -=2;
                            if(currZoom < 0)
                                currZoom = 0;
                            param.setZoom(currZoom);
                            camera.setParameters(param);

                        }
                    }
                });

            }else{
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
        if(landscape)
            camera.setDisplayOrientation(0);
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        landscape = true;
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            param.setRotation(0);
            camera.setParameters(param);
            camera.setDisplayOrientation(0);
            if(flashlight)
                flashLightOn();
            else
                flashLightOff();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            param.setRotation(90);
            camera.setParameters(param);
            camera.setDisplayOrientation(90);
            if(flashlight)
                flashLightOn();
            else
                flashLightOff();
        }
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
