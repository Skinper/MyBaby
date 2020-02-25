package br.ufop.wagner.mybabywagner.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.Banho;
import br.ufop.wagner.mybabywagner.Bebidas;
import br.ufop.wagner.mybabywagner.Horario;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.R;


public class DialogBanho extends Dialog {
    private int tema;
    private EditText data;
    private EditText horasIni;
    private EditText horasFim;
    private ImageButton confirmar;
    private EditText anotacoes;
    private EditText produtosUsados;
    private  SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar calendario = Calendar.getInstance();
    private EditText duracao;
    private Horario falta;
    private Horario ini;
    private Horario fim;


/*    private CheckBox saboneteLiquido;
    private CheckBox saboneteBarra;
    private CheckBox shampoo;
    private CheckBox condicionador;
    private CheckBox aguaMorna;
    private CheckBox aguaFria;*/








    public DialogBanho(@NonNull Context context, int tema) {
        super(context);
        this.tema = tema;
        initComponentes();
    }


    private void initComponentes() {

        this.setContentView(R.layout.dialog_banho);



        data = this.findViewById(R.id.dataBanho);
        anotacoes = this.findViewById(R.id.anotacoesBanho);
        confirmar = this.findViewById(R.id.confirmarBanho);
        horasIni = this.findViewById(R.id.horaBanhoIni);

        produtosUsados = this.findViewById(R.id.produtosUtilizados);


/*    // Checkboxs
        saboneteLiquido = this.findViewById(R.id.saboenteLiquido);
        saboneteBarra = this.findViewById(R.id.saboneteBarra);
        shampoo = this.findViewById(R.id.shampoo);
        condicionador = this.findViewById(R.id.condicionador);
        aguaMorna = this.findViewById(R.id.aguaMorna);
        aguaFria = this.findViewById(R.id.aguaFria);*/

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

        horasFim = this.findViewById(R.id.horaBanhoFim);

        horasFim.setText(timesNow);
        timesNow = new SimpleDateFormat("HH:mm").format(calendario.getTime());

        horasIni.setText(timesNow);


        duracao = findViewById(R.id.duracaoBanhoFim);




        horasFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterHoras(v, horasFim);


            }

        });





        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.getText().toString().equals("") ){


                    Banho banho = new Banho(String.valueOf(data.getText()),String.valueOf(data.getText()),  String.valueOf(horasIni.getText()), String.valueOf(anotacoes.getText().toString()), String.valueOf(produtosUsados.getText().toString()), duracao.getText().toString(), horasFim.getText().toString());
                    AtividadesSingleton.getInstance().getAtividades().add(banho);
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
                ini = Horario.parse(horasIni.getText().toString());
                fim = Horario.parse(horasFim.getText().toString());
                falta = fim.diferenca(ini);
                duracao.setText(falta.toString());
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
