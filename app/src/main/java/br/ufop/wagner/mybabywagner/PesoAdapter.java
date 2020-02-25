package br.ufop.wagner.mybabywagner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PesoAdapter extends BaseAdapter  {

    public static ArrayList<Peso> list;
    private Context context;
    private LayoutInflater layoutInflater;


    public PesoAdapter(ArrayList<Peso> list,
                             Context context) {

        this.list = list;
        this.context = context;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Recovers the Student in the current position
        Peso peso = list.get(position);

        //Creates a View object from the given XML layout file
        layoutInflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.peso_adapter, null);

        //Update TextView's text
        TextView tv1 = v.findViewById(R.id.textData);
        tv1.setText(String.valueOf(peso.getData()));

        TextView tv2 = v.findViewById(R.id.textPeso);
        ImageView iv = v.findViewById(R.id.img);


        /* iv.setImageResource(atividade.getImageView());*/
        tv2.setText(String.valueOf(peso.getPeso()));


        return v;

    }








}
