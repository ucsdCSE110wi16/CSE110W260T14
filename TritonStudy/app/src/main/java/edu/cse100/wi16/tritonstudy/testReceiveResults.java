package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class testReceiveResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_receive_results);

//        Bundle bundel = getIntent().getExtras();

//        Serializable data = getIntent().getSerializableExtra("results");
//
//        Intent intent = getIntent();
//        HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("hashMap");

        HashMap<String,String> hm = (HashMap<String,String>) getIntent().getExtras().get("hashMapKey");

        Log.d("DEBUG", "This is the map on the other side " + hm.get("Antelope"));

        for (Map.Entry<String,String> entry : hm.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Log.d("DEBUG", "The key is (" + key + ") " + ", The value is ("+value+")");
        }




    }

}
