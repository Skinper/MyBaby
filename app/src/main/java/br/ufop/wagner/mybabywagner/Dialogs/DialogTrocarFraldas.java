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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.ufop.wagner.mybabywagner.AtividadesAdapter;
import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.R;
import br.ufop.wagner.mybabywagner.TrocarFralda;

public class DialogTrocarFraldas extends Dialog {
    private int tema;
    private EditText data;
    private Time time;
    private TextView horaIni;
    private TextView horaFim;
    private RadioGroup radioGroup;
    private String TrocaFralda ="";
    private EditText horas;
    private ImageButton confirmar;
    private RadioButton fezes;
    private RadioButton molhada;
    private EditText anotacoes;
    private ListView listView;
    private  SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar calendario = Calendar.getInstance();

    public DialogTrocarFraldas(@NonNull Context context, int themeResId) {
        super(context);
        this.tema = tema;
        initComponentes();
    }


    private void initComponentes() {

        this.setContentView(R.layout.dialog_trocarfralda);



        data = this.findViewById(R.id.dataFralda);
        anotacoes = this.findViewById(R.id.anotacoesFralda);
        radioGroup = this.findViewById(R.id.radioGroupFralda);
        fezes = this.findViewById(R.id.radioButtonFezes);
        molhada = this.findViewById(R.id.radioButtonMolhada);
        data.setText(dataFormat.format(calendario.getTime()));
        fezes = findViewById(R.id.radioButtonFezes);
        molhada = findViewById(R.id.radioButtonMolhada);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonFezes) {

                    TrocaFralda = "Fezes";
                    Toast.makeText(getContext(),TrocaFralda, Toast.LENGTH_SHORT).show();
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMolhada) {

                    TrocaFralda = "Molhada";
                }
            }
        });
        data.setText(dataFormat.format(calendario.getTime()));
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario(v);
            }
        });


        horas = findViewById(R.id.horaFralda);
        String time = new SimpleDateFormat("HH:mm").format(calendario.getTime());
        horas.setText(time);
        horas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterHoras(v, horas);
            }
        });

        confirmar = this.findViewById(R.id.confirmarFralda);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonFezes) {

                    TrocaFralda = "Fezes";

                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMolhada) {

                    TrocaFralda = "Molhada";
                }

            if (!data.getText().toString().equals("") && !TrocaFralda.equals("") ){

                TrocarFralda fralda = new TrocarFralda(String.valueOf(data.getText()),String.valueOf(data.getText()),  String.valueOf(horas.getText()) ,String.valueOf(anotacoes.getText()),"Fralda",TrocaFralda);
                AtividadesSingleton.getInstance().getAtividades().add(fralda);
                AtividadesSingleton.getInstance().saveAtividades(getContext());
                Toast.makeText(getContext(),"Atividade adicionada com sucesso no dia " + data.getText() + " " + TrocaFralda , Toast.LENGTH_SHORT).show();
                MyBaby.atualizaLista();

                dismiss();
            }
            else {
                Toast.makeText(getContext(),"Deve escolher um tipo de fralda para adicionar atividade!", Toast.LENGTH_SHORT).show();
            }
            }
        });

    }


    private void calendario(View view) {
        final Calendar calendario = Calendar.getInstance();
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


