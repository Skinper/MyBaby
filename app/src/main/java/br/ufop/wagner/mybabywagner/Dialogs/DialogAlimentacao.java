package br.ufop.wagner.mybabywagner.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.ufop.wagner.mybabywagner.Alimentacao;
import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.R;

public class DialogAlimentacao extends Dialog {

    private int tema;
    private EditText data;
    private EditText horas;
    private ImageButton confirmar;
    private EditText anotacoes;
    private Spinner spinner1;
    private String TipoDeComida;
    private  SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar calendario = Calendar.getInstance();

    private static final String[] COMIDAS = {"Carne", "Cereais", "Fruta", "Legumes","Petisco", "Outro"};




    public DialogAlimentacao(@NonNull Context context, int tema) {
        super(context);
        this.tema = tema;
        initComponentes();
    }


    private void initComponentes() {

        this.setContentView(R.layout.dialog_comidas);


        spinner1 = this.findViewById(R.id.spinner1);
        initSpinner1();
        data = this.findViewById(R.id.dataComida);
        data.setText(dataFormat.format(calendario.getTime()));
        anotacoes = this.findViewById(R.id.anotacoesComida);


        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario(v);
            }
        });


        horas = findViewById(R.id.horaComida);
        String time = new SimpleDateFormat("HH:mm").format(calendario.getTime());
        horas.setText(time);


        horas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterHoras(v, horas);
            }
        });




        confirmar = this.findViewById(R.id.confirmarComida);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.getText().toString().equals("") ){

                    Alimentacao comida = new Alimentacao(String.valueOf(data.getText()),String.valueOf(data.getText()),  String.valueOf(horas.getText()), String.valueOf(anotacoes.getText()), TipoDeComida);
                    AtividadesSingleton.getInstance().getAtividades().add(comida);
                    AtividadesSingleton.getInstance().saveAtividades(getContext());
                    Toast.makeText(getContext(),"Atividade adicionada com sucesso no dia " + data.getText() + " "  , Toast.LENGTH_SHORT).show();
                    MyBaby.atualizaLista();
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void initSpinner1() {
        ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                COMIDAS);
        spinner1.setAdapter(adapterSpinner1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(COMIDAS[position].equals("Carne")) {
                    TipoDeComida = "Carne";
                } else if(COMIDAS[position].equals("Cereais")) {
                    TipoDeComida = "Cereais";
                } else if(COMIDAS[position].equals("Fruta")) {
                    TipoDeComida = "Fruta";
                }else if(COMIDAS[position].equals("Legumes")) {
                    TipoDeComida = "Legumes";
                }else if(COMIDAS[position].equals("Petisco")) {
                    TipoDeComida = "Petisco";
                }else if(COMIDAS[position].equals("Outro")) {
                    TipoDeComida = "Outro";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
