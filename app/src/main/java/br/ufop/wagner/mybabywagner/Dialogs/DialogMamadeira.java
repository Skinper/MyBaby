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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.Mamadeira;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.R;

public class DialogMamadeira extends Dialog {
    private int tema;
    private EditText data;
    private Time time;
    private TextView horaIni;
    private TextView horaFim;
    private RadioGroup radioGroup;
    private String TipoDeLeite ="";
    private EditText horas;
    private ImageButton confirmar;
    private RadioButton materno;
    private RadioButton empo;
    private  SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar calendario = Calendar.getInstance();


    private EditText anotacoes;
    private EditText volume;

    public DialogMamadeira(@NonNull Context context, int themeResId) {
        super(context);
        this.tema = tema;
        initComponentes();
    }


    private void initComponentes() {

        this.setContentView(R.layout.dialog_mamadeira);


        volume = this.findViewById(R.id.volumeLeite);
        data = this.findViewById(R.id.dataMamadeira);
        radioGroup = this.findViewById(R.id.radioGroupMamadeira);
        materno = this.findViewById(R.id.radioButtonMaterno);
        empo = this.findViewById(R.id.radioButtonEmpo);
        anotacoes = this.findViewById(R.id.anotacoesMamadeira);



        data.setText(dataFormat.format(calendario.getTime()));
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario(v);
            }
        });


        horas = findViewById(R.id.horaMamadeira);
        String time = new SimpleDateFormat("HH:mm").format(calendario.getTime());
        horas.setText(time);

        horas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterHoras(v, horas);
            }
        });

        confirmar = this.findViewById(R.id.confirmarMamadeira);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMaterno) {

                    TipoDeLeite = "Leite Materno";
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonEmpo) {

                    TipoDeLeite = "Leite em pó";
                }
                if (!data.getText().toString().equals("") && !TipoDeLeite.equals("") ){

                    Mamadeira mamadeira = new Mamadeira(String.valueOf(data.getText()),String.valueOf(data.getText()),  String.valueOf(horas.getText()),  TipoDeLeite, String.valueOf(anotacoes.getText()),volume.getText().toString());
                    AtividadesSingleton.getInstance().getAtividades().add(mamadeira);
                    AtividadesSingleton.getInstance().saveAtividades(getContext());
                    Toast.makeText(getContext(),"Atividade adicionada com sucesso no dia " + volume.getText() , Toast.LENGTH_SHORT).show();
                    MyBaby.atualizaLista();
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(),"Deve escolher um tipo de leite para adicionar a atividade.", Toast.LENGTH_SHORT).show();
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
