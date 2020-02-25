package br.ufop.wagner.mybabywagner.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.Outros;
import br.ufop.wagner.mybabywagner.R;
import br.ufop.wagner.mybabywagner.Sono;

public class DialogOutros extends Dialog {
    private int tema;
    private EditText data;
    private EditText horasIni;
    private EditText horasFim;
    private ImageButton confirmar;
    private EditText anotacoes;
    private EditText tipo;

    private  SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar calendario = Calendar.getInstance();


    public DialogOutros(@NonNull Context context, int tema) {
        super(context);
        this.tema = tema;
        initComponentes();
    }


    private void initComponentes() {

        this.setContentView(R.layout.dialog_outros);



        data = this.findViewById(R.id.dataOutros);
        anotacoes = this.findViewById(R.id.anotacoesOutros);
        confirmar = this.findViewById(R.id.confirmarOutros);
        horasIni = this.findViewById(R.id.horaOutrosIni);

        tipo = this.findViewById(R.id.atv);

        data.setText(dataFormat.format(calendario.getTime()));
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario(v);
            }
        });


        String timesNow = new SimpleDateFormat("HH:mm").format(calendario.getTime());

        timesNow = new SimpleDateFormat("HH:mm").format(calendario.getTime());
        horasIni.setText(timesNow);

        horasIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterHoras(v, horasIni);
            }
        });





        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.getText().toString().equals("") && !tipo.getText().toString().equals("")  ){


                    Outros outro = new Outros(String.valueOf(data.getText()),String.valueOf(data.getText()),  String.valueOf(horasIni.getText()), String.valueOf(anotacoes.getText()),String.valueOf(tipo.getText().toString()));
                    AtividadesSingleton.getInstance().getAtividades().add(outro);
                    AtividadesSingleton.getInstance().saveAtividades(getContext());

                    Toast.makeText(getContext(),"Atividade adicionada com sucesso no dia " + data.getText() + " "  , Toast.LENGTH_SHORT).show();
                    MyBaby.atualizaLista();
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(),"Não pode adicionar uma atividade sem título.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void calendario(View view) {
      //  final Calendar calendario = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendario.set(Calendar.YEAR, year);
                calendario.set(Calendar.MONTH, month);
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                data.setText(dataFormat.format(calendario.getTime()));
            }
        }, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void obterHoras(View view, final TextView tvHora) {
        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {

                Calendar calNow = Calendar.getInstance();
                calNow.set(Calendar.HOUR_OF_DAY, hora);
                calNow.set(Calendar.MINUTE, minuto);
                String hours = new SimpleDateFormat("HH:mm").format(calNow.getTime());
                tvHora.setText(hours.toString());
            }
        };

        Calendar calNow = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(getContext(),
                mTimeSetListener,
                calNow.getTime().getHours(),
                calNow.getTime().getMinutes(),
                true);

        dialog.show();
    }

}


