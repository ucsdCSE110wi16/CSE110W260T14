package edu.cse100.wi16.tritonstudy;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import java.util.HashMap;
import java.util.Map;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class edit_user_profile_info extends AppCompatActivity {

    // TODO: Change bio text orientation, should be top left


    int CROP_IMAGE = 1;
    int SET_BACKGROUND = 2;
    Uri uri;
    ImageView viewImage;
    Drawable myDrawable;
    Button b;
    private final Firebase rootRef = new Firebase("https://sweltering-inferno-5625.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile_info);

        Firebase.setAndroidContext(this);

        Log.d("STATE", "check if user is authenticated");
        rootRef.addAuthStateListener(new Firebase.AuthStateListener() {

            // @Override
            public void onAuthStateChanged(final AuthData authData) {
                if (authData != null) {
                    Log.d("STATE", "User is authenticated");

                    populateFields(authData);

                } else {
                    Log.d("DEBUG", "user is not logged in");
                    startActivity(new Intent(edit_user_profile_info.this, login.class));
                }
            }
        });

//        //Submit Button
//        Button submitButton = (Button) findViewById(R.id.buttonSubmit);
//
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                EditText eName = (EditText) findViewById(R.id.dynamicProfile_editText_name);
//                final EditText eEmail = (EditText) findViewById(R.id.dynamicProfile_editText_email);
//                EditText eMajor = (EditText) findViewById(R.id.dynamicProfile_editText_major);
//                EditText eBio = (EditText) findViewById(R.id.dynamicProfile_editText_bio);
//
//                final String mName = eName.getText().toString();
//                final String mEmail = eEmail.getText().toString();
//                final String mMajor = eMajor.getText().toString();
//                final String mBio = eBio.getText().toString();
//
//                String uid = mRef.getAuth().getUid().toString();
//                Log.d("Variable", "UID is: " + uid);
//
//                Firebase userRef = mRef.child("users/"+uid);
////                Log.d("Variable", userRef.toString());
//
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("name", mName);
//
//                Log.d("Email", mRef.getAuth().getProviderData().get("email").toString());
//                Log.d("Password", mRef.getAuth().getProvider().toString());
//
//                mRef.changeEmail(mRef.getAuth().getProviderData().get("email").toString(),
//                        mRef.getAuth().getProvider().toString(), mEmail, new Firebase.ResultHandler() {
//                    @Override
//                    public void onSuccess() {
//                        Log.d("STATE", "Email changed to" + eEmail);
//                    }
//                    @Override
//                    public void onError(FirebaseError firebaseError) {
//                        Log.d("ERROR", "Email unchanged! ");
//                    }
//                });
//
//                map.put("major",mMajor);
//                map.put("bio", mBio);
//
//                Log.d("Auth State", String.valueOf((mRef.getAuth())));
//
//                userRef.updateChildren(map);
//
//
//
//            }
//        });
//
//        viewImage = (ImageView)findViewById(R.id.profilePicture);
//        b = (Button)findViewById(R.id.buttonChangePic);
//
//        //Change Pic Button
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectPicture();
//            }
//        });
//

        //Cancel Button - returns to the Main Screen
//        Button cancelButton = (Button) findViewById(R.id.buttonCancel);
//
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(ProfileActivity.this, ProfileActivityStatic.class));
//            }
//        });
//
//        //Edit Times Button
//        Button editTimesButton = (Button) findViewById(R.id.buttonEditStudy);
//
//        editTimesButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                startActivity(new Intent(ProfileActivity.this, add_studytime.class));
//            }
//        });
    }

    public void populateFields(AuthData authData){
        Log.d("DEBUG", "populateFields()");

        Log.d("DEBUG", "set firebase reference to logged in users folder");
        Firebase userRef = rootRef.child("users/" + authData.getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                
                    Student student = dataSnapshot.getValue(Student.class);

                    Log.d("DEBUG", "populate with users name");
                    EditText etName = (EditText) findViewById(R.id.edit_user_profile_info_etName);
                    etName.setText(student.getName() , EditText.BufferType.EDITABLE);

                    Log.d("DEBUG", "populate with users email");
                    EditText etEmail = (EditText) findViewById(R.id.edit_user_profile_info_etEmail);
                    etEmail.setText(student.getEmail(), TextView.BufferType.EDITABLE);

                    Log.d("DEBUG", "populate with users phone number");
                    EditText etPhone = (EditText) findViewById(R.id.edit_user_profile_info_etPhone);
                    etPhone.setText(student.getPhoneNumber(), TextView.BufferType.EDITABLE);

                    Log.d("DEBUG", "populate with users bio");
                    EditText etBio = (EditText) findViewById(R.id.edit_user_profile_info_etBio);
                    etBio.setText(student.getBio(), TextView.BufferType.EDITABLE);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("UpdateLesson error: " + firebaseError.getMessage());
            }
        });
    }

//    public void selectPicture() {
//        final CharSequence[] options = { "Take Photo", "Choose from Gallery", "Cancel" };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
//        builder.setTitle("Add Photo!");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (options[item].equals("Choose from Gallery")) {
//                    choosePhoto();
//                } else if (options[item].equals("Take Photo")) {
//                    takePhoto();
//                }
//            }
//
//        });
//
//        AlertDialog alert11 = builder.create();
//        alert11.show();
//    }

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



























      