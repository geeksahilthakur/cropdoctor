package com.iamsahil.apple;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.iamsahil.apple.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import soup.neumorphism.NeumorphCardView;

public class MainActivity<scab> extends AppCompatActivity {


    TextView result, confidence;
    ImageView imageView;
    ImageView vdo;
    Button picture;
    int imageSize = 224;
    NeumorphCardView crd;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseMessaging.getInstance().subscribeToTopic("notifications");
        FirebaseMessaging.getInstance().subscribeToTopic("wheat");


        result = findViewById(R.id.result);
        confidence = findViewById(R.id.confidence);
        imageView = findViewById(R.id.imageView);
       vdo = findViewById(R.id.vdo);
        picture = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        crd = findViewById(R.id.crd);


        picture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    //Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });
    }

    @SuppressLint("DefaultLocale")
    public void classifyImage(Bitmap image){

        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int [] intValues = new int [imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            for(int i=0; i < imageSize; i++){
                for(int j = 0; j < imageSize; j++){
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f /255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f /255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));

                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            int maxPos = 0;
            float maxConfidences = 0;
            for(int i =0; i < confidences.length; i ++){
                if(confidences[i] > maxConfidences){
                    maxConfidences = confidences[i];
                    maxPos = i;
                }
            }
             String[] classes = {"Apple Scab ", "\n Apple leaf scab", "\n Apple Canker"};

            result.setText(classes[maxPos]);

            String s = "";
            for(int i = 0; i < classes.length; i++){
                s += String.format("%s: %1f%%\n", classes[i], confidences[i] * 100);
            }
            confidence.setText(s);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);
            image = Bitmap.createScaledBitmap(image, imageSize , imageSize,false);
            classifyImage(image);
        }
        super.onActivityResult(requestCode, resultCode, data);

        //error while passing string using put extra


       // result.setOnClickListener(new View.OnClickListener(){
         //   @Override
           // public void onClick(View v) {

             //   String as = "Apple scab";
               // String ls = "leaf scab";

                //Intent i = new Intent(getApplicationContext(), trt.class);
                //if (result.equals("apple scab")) {
                  //  i.putExtra("A", as);
                //}
                //else {
                  //  i.putExtra("L", ls);
               // }
                //startActivity(i);

          //  }
        //});

        result.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), trt.class);
            startActivity(i);

             }
        });



    }
}