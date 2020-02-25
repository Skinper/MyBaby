package br.ufop.wagner.mybabywagner.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import br.ufop.wagner.mybabywagner.R;

import static android.support.constraint.Constraints.TAG;

public class DialogSom extends Dialog {

    private  boolean isStopped = true;
    private boolean resume = false;
    private int currentPosition = 0;

    public DialogSom(@NonNull Context context) {
        super(context);

        initComponentes();
    }

    public void initComponentes(){

        setContentView(R.layout.dialog_som);
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.musica);

        final ImageButton play_button = (ImageButton)this.findViewById(R.id.imageButton);
        play_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              if (isStopped){
                  mp.start();
                  isStopped = false;
                  play_button.setImageResource(R.drawable.pause);
                  if (resume){
                      mp.seekTo(currentPosition);
                      resume = false;
                  }

              } else{
                  play_button.setImageResource(R.drawable.play);
                   currentPosition = mp.getCurrentPosition();
                   resume = true;
                  mp.pause();
                  isStopped = true;

              }
            }
        });

  /*      ImageButton pause_button = (ImageButton)this.findViewById(R.id.imageButton2);
        pause_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mp.reset();
            }
        });*/

        ImageButton confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                dismiss();
            }
        });
    }

}
