package br.ufop.wagner.mybabywagner;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> titles;
    private ArrayList<Integer> images;
    private Context context;


    public RecyclerAdapter(ArrayList<String> titles, ArrayList<Integer> images, Context context) {
        this.titles = titles;
        this.images = images;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
    holder.AlbumTitle.setText(titles.get(position));
    int image_id = images.get(position);
    holder.Album.setImageResource(image_id);
/*    holder.Album.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Toast.makeText(context,"Clicou em " + titles.get(position), Toast.LENGTH_SHORT).show();
           if (titles.get(position).equals("Amamentação")){

               dialog = new Dialog(v.getContext());
               if(dialog != null) {
                   dialog.setContentView(R.layout.dialog_amamentacao);
                   dialog.show();
               }


           }
        }
    });*/
    }


    @NonNull

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView Album;
        TextView AlbumTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            Album = itemView.findViewById(R.id.amamentacao);
            AlbumTitle = itemView.findViewById(R.id.amamentacaoText);
        }
    }

}

