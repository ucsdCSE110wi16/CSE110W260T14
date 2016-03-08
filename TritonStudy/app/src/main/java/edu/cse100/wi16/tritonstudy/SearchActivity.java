package edu.cse100.wi16.tritonstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SearchActivity extends AppCompatActivity
{
    Vector searchResults = new Vector(1,1);

    /*String[] arrayCSEcourses = new String[]{"Choose Course","CSE 3", "CSE 7", "CSE 8A", "CSE 8B", "CSE 11",
            "CSE 12", "CSE 15L", "CSE 30", "CSE 20", "CSE 21", "CSE 100", "CSE 101", "CSE 105",
            "CSE 110", "CSE 120", "CSE 127", "CSE 130", "CSE 131", "CSE 136", "CSE 140",
            "CSE 140L", "CSE 141", "CSE 141L", "CSE 148", "CSE 150", "CSE 152",
            "CSE 167", "CSE 168", "CSE 169", "CSE 182"};*/

    //ArrayAdapter for the ListView
    String[]  myStringArray;
    //ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray);
    ArrayAdapter<String> myAdapter;
    ListView myList;
    Spinner spClass;
    ArrayAdapter<String> adClass1;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //spClass = (Spinner) findViewById(R.id.dropdownClasses);
        //adClass1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayCSEcourses);
        //spClass.setAdapter(adClass1);

        //DEBUG: Add test values to the search results vector
        searchResults.add("CSE 3");
        searchResults.add("CSE 7");
        searchResults.add("CSE 140");
        searchResults.add("CSE 140L");
        searchResults.add("CSE 101");
        searchResults.add("CSE 110");

        //search Button
        Button search = (Button) findViewById(R.id.buttonSearch);

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doSearch();
            }
        });

    }

    /*
    ==============================================

    Main search method

    ==============================================
    */

    public void doSearch()
    {
        //ArrayList object for dynamically changing array size
        List<String> myStringArray = new ArrayList<String>();

        //Reference to the ListView component in the activity layout
//        myList =(ListView) findViewById(R.id.lvSearchResults);
        int i = 0;

        //Iterate through the vector
        for (Object obj : searchResults) {
            if (obj instanceof String) {
                //Add each String instance of the Object to the ArrayList
                //then increment a counter variable by 1
                myStringArray.add(obj.toString());
                i++;
            }
        }
        //Convert the ArrayList of strings to a regular array for use with an ArrayAdapter
        //to be used by the ListView
        String[] arr = myStringArray.toArray(new String[myStringArray.size()]);

        //ArrayAdapter contains a custom layout XML resource to represent each list item
        //create it using the array derived from the ArrayList
        myAdapter = new ArrayAdapter<String>(this, R.layout.search_results_layout, R.id.textClass1, arr);

        //set the ListView to the ArrayAdapter created above
        myList.setAdapter(myAdapter);

        //Update search results count via TextView display, counter provided by the ArrayList
        //building FOR EACH-IN loop
//        TextView searchStatus = (TextView) findViewById(R.id.textSearchStatus);
//        searchStatus.setText("Matches found: " + Integer.toString(i), TextView.BufferType.EDITABLE);
    }
}
