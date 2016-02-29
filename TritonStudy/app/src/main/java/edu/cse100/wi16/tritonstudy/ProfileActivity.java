package edu.cse100.wi16.tritonstudy;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    //Firebase reference
    Firebase ref = new Firebase("https://sweltering-inferno-5625.firebaseio.com/users");

    EditText firstName, lastName, emailAddress, major, bio;
    TextView nameStatic, emailStatic, majorStatic, bioStatic;

    int CROP_IMAGE = 1;
    int SET_BACKGROUND = 2;
    Uri uri;
    ImageView viewImage;
    Drawable myDrawable;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        Student student = new Student();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Submit Button
        Button submitButton = (Button) findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                firstName = (EditText) findViewById(R.id.textNameFirst);
                lastName = (EditText) findViewById(R.id.textNameLast);
                emailAddress = (EditText) findViewById(R.id.textEmail);
                major = (EditText) findViewById(R.id.majorText);
                bio = (EditText) findViewById(R.id.textBio);



            }
        });

        viewImage = (ImageView)findViewById(R.id.profilePicture);
        b = (Button)findViewById(R.id.buttonChangePic);

        //Change Pic Button
         b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture();
            }
        });

        //=============================================
        //Drop Down Box (spinner) for Enrolled Colleges
        //=============================================
        Spinner dropdown = (Spinner) findViewById(R.id.spinnerColleges);
        String[] items = new String[]{"Select your college:", "Revelle", "John Muir", "Thurgood Marshall", "Earl Warren", "Eleanor Roosevelt", "Sixth"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        //Cancel Button - returns to the Main Screen
        Button cancelButton = (Button) findViewById(R.id.buttonCancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ProfileActivityStatic.class));
            }
        });

        //Edit Times Button
        Button editTimesButton = (Button) findViewById(R.id.buttonEditStudy);

        editTimesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, edit_times.class));
            }
        });

    }

    public void selectPicture() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Choose from Gallery")) {
                    choosePhoto();
                } else if (options[item].equals("Take Photo")) {
                    takePhoto();
                }
            }

        });

        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CROP_IMAGE);
    }

    public void choosePhoto() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), CROP_IMAGE);
    }

    public void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == RESULT_OK && data != null){
            if (reqCode == CROP_IMAGE){
                Uri imageUri = data.getData();
                try {
                    Intent cropIntent = new Intent("com.android.camera.action.CROP");
                    // indicate image type and Uri
                    cropIntent.setDataAndType(imageUri, "image/*");
                    // set crop properties
                    cropIntent.putExtra("crop", "true");
                    // indicate aspect of desired crop
                    cropIntent.putExtra("aspectX", 3);
                    cropIntent.putExtra("aspectY", 4);
                    // indicate output X and Y
                    cropIntent.putExtra("outputX", 300);
                    cropIntent.putExtra("outputY", 300);
                    // retrieve data on return
                    cropIntent.putExtra("scaleUpIfNeeded", true);

                    cropIntent.putExtra("return-data", true);
                    // start the activity - we handle returning in onActivityResult
                    startActivityForResult(cropIntent, SET_BACKGROUND);
                }
                // respond to users whose devices do not support the crop action
                catch (ActivityNotFoundException anfe) {
                    // display an error message
                    String errorMessage = "Whoops - your device doesn't support the crop action!";
                    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
            else if (reqCode == SET_BACKGROUND) {
                uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    //myDrawable = Drawable.createFromStream(inputStream, uri.toString());
                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream );
                    viewImage.setImageBitmap(bitmap);
                } catch (IOException e) {}
            }
        }
        else if (resCode == RESULT_CANCELED) {
            Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
        }
    }
    /*
    //Compresses an image to a smaller size
    Bitmap compressImage (Bitmap bmp)
    {
        //Get dimensions of original image
        width = bmp.getWidth();
        height = bmp.getHeight();

        //Get new scale dimensions
        matrix = new Matrix();
        scaleWidth = ((float) newWidth) / width;
        scaleHeight = ((float) newHeight) / height;
        matrix.postScale(scaleWidth, scaleHeight);
        matrix.postRotate(45);

        //Write new outputstream containing the compressed image
        Bitmap finalBmp = Bitmap.createBitmap(originalImage, 0, 0, width, height, matrix, true);
        outputStream = new ByteArrayOutputStream();
        finalBmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        return finalBmp;
    }*/
}
