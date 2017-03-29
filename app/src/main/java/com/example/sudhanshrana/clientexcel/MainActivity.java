package com.example.sudhanshrana.clientexcel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ChildEventListener, View.OnClickListener {
    String s,s1;
    Spinner sp,sp1;
    ArrayAdapter<String> adapt,adapt1;
    Button b1;

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference myRef=db.getReference();
    DatabaseReference myRef1=db.getReference();
    List<String> l1=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(this);
        /*
        SharedPreferences spp=getSharedPreferences("user",MODE_PRIVATE);
        String pp=spp.getString("Year","0");

        if(!pp.equals("0"))
        {
            Intent in=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(in);
        }
        else{
          //  Intent in=new Intent(MainActivity.this,branchSelect.class);
          //  startActivity(in);
        }*/
        sp=(Spinner)findViewById(R.id.spinner4);
        sp1=(Spinner)findViewById(R.id.spinner5);


        l1.add("Select Year");
        adapt=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,l1);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        adapt1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adapt1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapt);
        adapt1.add("Select Branch");
        sp1.setAdapter(adapt1);
        sp1.setVisibility(View.GONE);



        myRef.addChildEventListener(this);
        sp.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        s = l1.get(position);
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("Year", s);
                        editor.commit();
                        sp1.setVisibility(View.VISIBLE);
                        myRef1 = myRef.child(s);
                        myRef1.addChildEventListener(ch);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        sp1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        s1=adapt1.getItem(position);
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("Branch", s1);
                        editor.commit();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        showProgressDialog();


    }


    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError)
    {

    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        hideProgressDialog();
        l1.add(dataSnapshot.getKey().toString());

        adapt.notifyDataSetChanged();





    }
    ChildEventListener ch =new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            adapt1.add(dataSnapshot.getKey().toString());

            adapt.notifyDataSetChanged();


        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    @Override
    public void onClick(View v) {
        Intent in=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(in);
    }
}
