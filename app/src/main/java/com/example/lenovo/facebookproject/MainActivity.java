package com.example.lenovo.facebookproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

            public static int IMG_RESULT;
            String ImageDecode;
            public EditText postText;
            public String postString;
            ImageView imageViewLoad;
            String postImage;
            Button galleryButton;
            Button submit;
            Intent intent;
            String[] FILE;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                imageViewLoad = (ImageView) findViewById(R.id.previewImage);
                galleryButton = (Button)findViewById(R.id.galleryButton);
                submit =(Button)findViewById(R.id.submitButton) ;
                postText = (EditText)findViewById(R.id.postText);

                galleryButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        intent = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        startActivityForResult(intent, IMG_RESULT);

                    }

                });

            }

            @Override

            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                try {

                    if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                            && null != data) {


                        Uri URI = data.getData();
                        String[] FILE = { MediaStore.Images.Media.DATA };


                        Cursor cursor = getContentResolver().query(URI,
                                FILE, null, null, null);

                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(FILE[0]);
                        ImageDecode = cursor.getString(columnIndex);
                        cursor.close();

                        imageViewLoad.setImageBitmap(BitmapFactory
                                .decodeFile(ImageDecode));

                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                            .show();
                }
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        postString= postText.getText().toString();
                        Intent intentSend = new Intent(Createpost.this,MainActivity.class);
                        intentSend.putExtra("string2",postString);
                        intentSend.putExtra("imgnum",IMG_RESULT);


                        startActivity(intentSend);

                    }



                });


            }

    }

