package br.ufop.wagner.mybabywagner.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.ufop.wagner.mybabywagner.Amamentacao;
import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.Horario;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.R;


public class DialogAmamentacao extends Dialog {


    private Chronometer chronometer;
    private boolean isStopped = true;
    private long millisElapsed;
    private ImageButton playPause;
    private ImageButton buttonReset;
    private ImageButton confirm;


    private int tema;
    private EditText data;
    private Time time;
    private EditText horasIni;
    private EditText horasFim;
    private EditText anotacao;
    private  SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar calendario = Calendar.getInstance();
    private EditText duracao;

    private Horario falta;
    private Horario ini;
    private Horario horarioFim;


    public DialogAmamentacao(@NonNull Context context,int tema) {
        super(context);
        this.tema = tema;
        initComponentes();


    }
    private void initComponentes(){

        this.setContentView(R.layout.dialog_amamentacao);
        // Inicializa os elementos do dialog
        AtividadesSingleton.getInstance().loadAtividades(this.getContext());

        data = this.findViewById(R.id.dataMama);
        data.setText(dataFormat.format(calendario.getTime()));
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario(v);
            }
        });
        duracao = findViewById(R.id.duracaoAmamentacao);
        horasIni = this.findViewById(R.id.horaMama);
        horasFim = this.findViewById(R.id.horaMamaFim);
        anotacao = this.findViewById(R.id.anotacoesMama);
        String timesNow = new SimpleDateFormat("HH:mm").format(calendario.getTime());
        horasFim.setText(timesNow);
        timesNow = new SimpleDateFormat("HH:mm").format(calendario.getTime());
        horasIni.setText(timesNow);
        horasIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterHoras(v, horasIni);

            }
        });

        horasFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterHoras(v, horasFim);


            }

        });



        confirm = this.findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if  (!data.getText().toString().equals("")){
                    Amamentacao amamentacao = new Amamentacao(String.valueOf(data.getText()),String.valueOf(data.getText()),String.valueOf(horasIni.getText()), horasFim.getText().toString(), String.valueOf(anotacao.getText()), duracao.getText().toString());
                    AtividadesSingleton.getInstance().getAtividades().add(amamentacao);
                    AtividadesSingleton.getInstance().saveAtividades(getContext());
                    chronometer.clearAnimation();
                    Toast.makeText(getContext(),"Atividade adicionada com sucesso  " + data.getText() , Toast.LENGTH_LONG).show();
                    MyBaby.atualizaLista();
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_LONG).show();
                }


            }
        });


        chronometer = this.findViewById(R.id.chronometer);
        playPause = this.findViewById(R.id.buttonPlayPause);
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean fim = false;
                if(isStopped) {
                    //Play
                    chronometer.start();
                    chronometer.setBase(SystemClock.elapsedRealtime() - millisElapsed);
                    isStopped = false;
                    playPause.setImageResource(android.R.drawable.ic_media_pause);
                    obterHora(v,fim);


                } else {
                    // Pause
                    millisElapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
                    chronometer.stop();
                    isStopped = true;
                    playPause.setImageResource(android.R.drawable.ic_media_play);
                    fim = true;
                    obterHora(v,fim);
                  duracao.setText(chronometer.getText().toString());

                }

            }
        });

        buttonReset = this.findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                isStopped = true;
                millisElapsed = 0;
                playPause.setImageResource(android.R.drawable.ic_media_play);
                duracao.setText(chronometer.getText().toString());
                resetarHora(v);
            }
        });
    }


    private void calendario(View view) {
    //    final Calendar calendario = Calendar.getInstance();
        DatePickerDialog datePickerDialog  = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendario.set(Calendar.YEAR,year);
                calendario.set(Calendar.MONTH,month);
                calendario.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                data.setText(dataFormat.format(calendario.getTime()));
            }
        },calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
    public void obterHora(View view, boolean fim){

        Calendar calendar = Calendar.getInstance();//cria o obj calendar e atribui a hora e data do sistema
        Date data = calendar.getTime();//transforma o obj calendar em obj Date


        SimpleDateFormat sdhora = new SimpleDateFormat("HH:mm");//cria um obj de formatação de hora
        String s = sdhora.format(data);//gera a string final formatada no estilo "HH:mm:ss"
        if (!fim){
            horasIni.setText(s);
        }
        else {
            horasFim.setText(s);
        }

    }

    public void resetarHora(View v){
        horasIni.setText("");
        horasFim.setText("");
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
                horarioFim = Horario.parse(horasFim.getText().toString());
                falta = horarioFim.diferenca(ini);

                SimpleDateFormat formatador = new SimpleDateFormat("mm:ss");
                Date data = null;
                try {
                    data = formatador.parse(falta.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Time time = new Time(data.getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                String formatted = sdf.format(new Date(time.getTime()));
                duracao.setText(formatted);
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