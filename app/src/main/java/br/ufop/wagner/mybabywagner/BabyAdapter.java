package br.ufop.wagner.mybabywagner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class BabyAdapter extends BaseAdapter {

    private Baby baby;
    private Context context;

    public BabyAdapter(Baby baby,
                       Context context) {
        this.baby = baby;
        this.context = context;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Recovers the Student in the current position


        //Creates a View object from the given XML layout file
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_baby_adapter, null);

        //Update TextView's text

        TextView tv2 = v.findViewById(R.id.textName);
        tv2.setText( baby.getName().toString());

       /* TextView tv3 = v.findViewById(R.id.textDate);
        tv3.setText((CharSequence) baby.getDate());


        TextView tv5 = v.findViewById(R.id.textSex);
        tv5.setText( baby.getSex().toString()); */


        return v;

    }
}

