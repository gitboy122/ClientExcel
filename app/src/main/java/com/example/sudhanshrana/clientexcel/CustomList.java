package com.example.sudhanshrana.clientexcel;

/**
 * Created by HARJOT on 23-07-2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final ArrayList<String> lec;
    private final ArrayList<lecture> l;
    private final ArrayList<String> time;

    public CustomList(Activity context,
                      ArrayList<String> lec, ArrayList<lecture> l,ArrayList<String> time) {
        super(context, R.layout.list_row, lec);
        this.context = context;
        this.lec = lec;
        this.l = l;
        this.time=time;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.exp, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.lecNo1);
        txtTitle.setText(lec.get(position)+".");
        TextView code = (TextView) rowView.findViewById(R.id.ccode1);
        code.setText(l.get(position).getCCode());
        TextView room = (TextView) rowView.findViewById(R.id.room1);
        room.setText("Room: "+l.get(position).getRoom());
        TextView teacher = (TextView) rowView.findViewById(R.id.teacher1);
        teacher.setText("Teacher: "+l.get(position).getTeacher());
        TextView t = (TextView) rowView.findViewById(R.id.time1);
        t.setText("Start At : "+time.get(position)+":00");
        TextView t1 = (TextView) rowView.findViewById(R.id.time2);

        try {
            t1.setText("End At : " + time.get(position + 1) + ":00");
        }
        catch (Exception e)
        {
            t1.setText("End At : 7:00");
        }
        return rowView;
    }
}
