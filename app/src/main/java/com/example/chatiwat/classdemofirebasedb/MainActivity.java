package com.example.chatiwat.classdemofirebasedb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener{


    private EditText editTextItemCreate, editTextItemLookup, editTextItemUpdate, editTextCreateQuantity, editTextUpdateQuantity;
    private TextView textViewLookupQuantity;
    private Button buttonCreate, buttonUpdate, buttonLookup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextItemCreate = (EditText) findViewById(R.id.editTextItemCreate);
        editTextItemUpdate = (EditText) findViewById(R.id.editTextItemUpdate);
        editTextItemLookup = (EditText) findViewById(R.id.editTextItemLookup);

        editTextCreateQuantity = (EditText) findViewById(R.id.editTextCreateQuantity);
        editTextUpdateQuantity = (EditText) findViewById(R.id.editTextUpdateQuantity);

        textViewLookupQuantity = (TextView) findViewById(R.id.textViewLookupQuantity);

        buttonCreate = (Button) findViewById(R.id.buttonCreate);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonLookup = (Button) findViewById(R.id.buttonLookup);

        buttonCreate.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonLookup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == buttonCreate){

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(editTextItemCreate.getText().toString());

            myRef.setValue(editTextCreateQuantity.getText().toString());


        }else if (v == buttonUpdate){

        } else if (v == buttonLookup) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(editTextItemLookup.getText().toString());

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    Log.d(TAG, "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

            //FirebaseDatabase database = FirebaseDatabase.getInstance();
            //DatabaseReference myRef = database.getReference(editTextItemLookup.getText());

        }

    }


}
