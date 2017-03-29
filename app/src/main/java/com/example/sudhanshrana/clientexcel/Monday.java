package com.example.sudhanshrana.clientexcel;

/**
 * Created by HARJOT on 22-07-2016.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Jagdeep on 05/07/16.
 */
public class Monday extends Fragment implements ChildEventListener, AdapterView.OnItemLongClickListener {

    String year,branch;
FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference myref=db.getReference();
    private final ArrayList<String> lec=new ArrayList<String>();
    private final ArrayList<lecture> l=new ArrayList<lecture>();
    private final ArrayList<String> time=new ArrayList<String>();
    lecture temp=new lecture();
    lecture temp1=new lecture();

    String tempt,templ;

    public static Monday newInstance(int tabpos) {
        Monday fragment = new Monday();
        Bundle args = new Bundle();
        args.putInt("day", tabpos);
        fragment.setArguments(args);
        return fragment;
    }

        @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(getContext());
        year=sp.getString("Year","fsf");
        branch=sp.getString("Branch","fsdf");



            Bundle args = getArguments();
            int tabPosition = args.getInt("day");
            myref=myref.child(year).child(branch).child(getday(tabPosition));
            myref.addChildEventListener(this);

            CustomList adapter = new
            CustomList(getActivity(),lec,l ,time);
            ListView  li =new ListView(getContext());
            li.setAdapter(adapter);
            li.setOnItemLongClickListener(this);
            return li;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        temp=dataSnapshot.getValue(lecture.class);
        templ=dataSnapshot.getKey();
        tempt=gettime(templ);
        lec.add(templ);
        l.add(temp);
        time.add(tempt);


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



    public String getday(int pos)
    {
        switch(pos)
        {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";

        };
        return "Monday";
    }

    public String gettime(String t)
    {
        switch(t)
        {
            case "1":
                return "8";
            case "2":
                return "9";
            case "3":
                return "10";
            case "4":
                return "11";
            case "5":
                return "12";
            case "6":
                return "1";
            case "7":
                return "2";
            case "8":
                return "3";
            case "9":
                return "4";
            case "10":
                return "5";
        };
        return  "5";
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        return false;
    }
}

