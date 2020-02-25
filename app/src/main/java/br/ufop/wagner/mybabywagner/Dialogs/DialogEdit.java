package br.ufop.wagner.mybabywagner.Dialogs;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.ufop.wagner.mybabywagner.Alimentacao;
import br.ufop.wagner.mybabywagner.Amamentacao;
import br.ufop.wagner.mybabywagner.Atividades;
import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.Banho;
import br.ufop.wagner.mybabywagner.Bebidas;
import br.ufop.wagner.mybabywagner.Horario;
import br.ufop.wagner.mybabywagner.LaunchNotification;
import br.ufop.wagner.mybabywagner.Mamadeira;
import br.ufop.wagner.mybabywagner.Medicamento;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.Outros;
import br.ufop.wagner.mybabywagner.R;
import br.ufop.wagner.mybabywagner.Sono;
import br.ufop.wagner.mybabywagner.TrocarFralda;

public class DialogEdit extends Dialog {
    private EditText dataIni;
    private EditText dataFim;
    private EditText horaIni;
    private EditText horaFim;
    private int position;
    private TextView tv2;
    private int iv;
    private int image;

    private EditText infoAdd;

    private Amamentacao amamentacao;
    private TrocarFralda atv;
    private Mamadeira mamadeira;
    private Alimentacao alimentacao;
    private Bebidas bebida;
    private Banho banho;
    private Medicamento med;
    private Sono Tsono;
    private Outros outro;


    private ImageButton confirmar;
    private EditText anotacoes;
    private SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar calendario = Calendar.getInstance();
    private EditText duracao;
    private Horario falta;
    private Horario ini;
    private Horario horarioFim;

    // Amamentação
    private Chronometer chronometer;
    private boolean isStopped = true;
    private long millisElapsed;
    private ImageButton playPause;
    private ImageButton buttonReset;

    // Medicamentos
    private Spinner spinner1;
    private String TipoDeMedicamento;
    private static final String[] MEDICAMENTOS = {"Benadryl", "Motrin", "Mylicon", "Tynelol","Vitamin D", "Outro"};


    private EditText textDate;
    private DateFormat dateFormat;
    private EditText textTime;
    private DateFormat dateFormatTime;
    private Calendar calendar;
    private DatePickerDialog dialogDatePicker;
    private TimePickerDialog dialogTimePicker;

    private Spinner spinnerFrequency;
    private static final String[] FREQUENCY_OPTIONS = {
            "Uma vez",
            "Uma vez por dia",
            "12 em 12 horas",
            "8 em 8 horas",
            "6 em 6 horas",
            "4 em 4 horas"};
    private static int frequency = 0;
    private Switch switchActive;

    private AlarmManager alarmManager;
    private long miliSegundos;

    // Fraldas
    private RadioButton fezes;
    private RadioButton molhada;
    private RadioGroup radioGroup;
    private String TrocaFralda;

    // Mamadeira

    private String TipoDeLeite;
    private RadioButton materno;
    private RadioButton empo;

    private EditText volume;

   /// ALimentação
    private String TipoDeComida;
    private static final String[] COMIDAS = {"Carne", "Cereais", "Fruta", "Legumes","Petisco", "Outro"};

    //Bebidas
    private String TipoDeBebida;
    private static final String[] BEBIDAS = {"Chá", "Sumo", "Água", "Outro"};

    //Banho
    private EditText produtosUsados;


// Outras
    private EditText tipo;



    private Atividades atividade;
    private Boolean am = false, fr = false, mama = false, ali = false, beb = false, medic = false, sono = false, ba = false, outros = false;

    public DialogEdit(@NonNull Context context,   int position) {
        super(context);


        initComponentes(position);
    }


    public void initComponentes(final int position) {
        atividade = AtividadesSingleton.getInstance().getAtividades().get(position);
        infoAdd = findViewById(R.id.infoAdd);

        if (atividade instanceof TrocarFralda) {
           setContentView(R.layout.dialog_trocarfralda);
            Toast.makeText(getContext(), "Fralda", Toast.LENGTH_SHORT).show();
            atv = (TrocarFralda) atividade;
            dataIni = findViewById(R.id.dataFralda);
            dataIni.setText(atv.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });


            horaIni = findViewById(R.id.horaFralda);
            horaIni.setText(atv.getHoraInicial());


            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });


            anotacoes = findViewById(R.id.anotacoesFralda);
            anotacoes.setText(atv.getAnotacao());
            fezes = findViewById(R.id.radioButtonFezes);
            molhada = findViewById(R.id.radioButtonMolhada);
            radioGroup = findViewById(R.id.radioGroupFralda);
            fezes = findViewById(R.id.radioButtonFezes);
            molhada = findViewById(R.id.radioButtonMolhada);
        if(atv.getFralda() != null){
            if (atv.getFralda().equals("Fezes")){
                radioGroup.check(R.id.radioButtonFezes);
            }else {
                radioGroup.check(R.id.radioButtonMolhada);
            }
            }


            iv = (atv.getImageView());


            confirmar = this.findViewById(R.id.confirmarFralda);

            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                            if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonFezes) {

                                TrocaFralda = "Fezes";

                            } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMolhada) {

                                TrocaFralda = "Molhada";
                            }

                    if (!dataIni.getText().toString().equals("") ){
                        TrocarFralda fralda = new TrocarFralda(String.valueOf(dataIni.getText()),String.valueOf(dataIni.getText()),  String.valueOf(horaIni.getText()) ,String.valueOf(anotacoes.getText()),"Fralda",TrocaFralda);
                        AtividadesSingleton.getInstance().getAtividades().set(position, fralda);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());
                        MyBaby.atualizaLista();
                        MyBaby.mAdapter.getFilter().filter("");
                        MyBaby.editText.setText("");

                        dismiss();
                    }
                    else {
                        Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        } else if (atividade instanceof Amamentacao) {

            setContentView(R.layout.dialog_amamentacao);
            amamentacao = (Amamentacao) atividade;
            dataIni = findViewById(R.id.dataMama);
            dataIni.setText(amamentacao.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });
            horaFim = findViewById(R.id.horaMamaFim);
            horaFim.setText(amamentacao.getHoraFinal());


            horaIni = findViewById(R.id.horaMama);
            horaIni.setText(amamentacao.getHoraInicial());


            anotacoes = findViewById(R.id.anotacoesMama);
            anotacoes.setText(amamentacao.getAnotacao());


            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHorasAmamentacao(v, horaIni);
                }
            });
            horaFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHorasAmamentacao(v, horaFim);


                }

            });


            iv = (amamentacao.getImageView());
            duracao = findViewById(R.id.duracaoAmamentacao);
            duracao.setText(amamentacao.getDuracao());

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

            confirmar = this.findViewById(R.id.confirm);

            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if  (!dataIni.getText().toString().equals("")){
                        Amamentacao ama = new Amamentacao(String.valueOf(dataIni.getText()),String.valueOf(dataIni.getText()),String.valueOf(horaIni.getText()), horaFim.getText().toString(), String.valueOf(anotacoes.getText()), duracao.getText().toString());
                        AtividadesSingleton.getInstance().getAtividades().set(position,ama);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());
                        chronometer.clearAnimation();
                        Toast.makeText(getContext(),"Atividade alterada com sucesso" , Toast.LENGTH_LONG).show();
                        MyBaby.atualizaLista();
                        dismiss();
                    }
                    else {
                        Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_LONG).show();
                    }


                }
            });








        } else if (atividade instanceof Mamadeira) {
         setContentView(R.layout.dialog_mamadeira);
            mamadeira = (Mamadeira) atividade;
            dataIni = findViewById(R.id.dataMamadeira);
            dataIni.setText(mamadeira.getDataDeInicio());
            volume = this.findViewById(R.id.volumeLeite);
            radioGroup = this.findViewById(R.id.radioGroupMamadeira);
            materno = this.findViewById(R.id.radioButtonMaterno);
            empo = this.findViewById(R.id.radioButtonEmpo);
            if (mamadeira.getTipoDeLeite().equals("Leite em pó")){
                radioGroup.check(R.id.radioButtonEmpo);
            }else {
                radioGroup.check(R.id.radioButtonMaterno);
            }

            volume.setText(mamadeira.getVolume());

            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });



            horaIni = findViewById(R.id.horaMamadeira);
            horaIni.setText(mamadeira.getHoraInicial());


            anotacoes = findViewById(R.id.anotacoesMamadeira);
            anotacoes.setText(mamadeira.getAnotacao());


            iv = (mamadeira.getImageView());

            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });
            confirmar = this.findViewById(R.id.confirmarMamadeira);

            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dataIni.getText().toString().equals("") ){
                        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMaterno) {

                            TipoDeLeite = "Leite Materno";
                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonEmpo) {

                            TipoDeLeite = "Leite em pó";
                        }
                        Mamadeira mamadeira = new Mamadeira(String.valueOf(dataIni.getText()),String.valueOf(dataIni.getText()),  String.valueOf(horaIni.getText()),  TipoDeLeite, String.valueOf(anotacoes.getText()),volume.getText().toString());
                        AtividadesSingleton.getInstance().getAtividades().set(position,mamadeira);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());

                        MyBaby.atualizaLista();
                        MyBaby.mAdapter.getFilter().filter("");
                        MyBaby.editText.setText("");
                        dismiss();
                    }
                    else {
                        Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        } else if (atividade instanceof Alimentacao) {
           setContentView(R.layout.dialog_comidas);
            alimentacao = (Alimentacao) atividade;
            dataIni = findViewById(R.id.dataComida);
            dataIni.setText(alimentacao.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });



            horaIni = findViewById(R.id.horaComida);
            horaIni.setText(alimentacao.getHoraInicial());


            anotacoes = findViewById(R.id.anotacoesComida);
            anotacoes.setText(alimentacao.getAnotacao());

            TipoDeComida = alimentacao.getTipoAlimento();



            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

            iv = ( (alimentacao.getImageView()));
            spinner1 = this.findViewById(R.id.spinner1);
            initSpinner2();

            confirmar = this.findViewById(R.id.confirmarComida);
            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dataIni.getText().toString().equals("") ){

                        Alimentacao comida = new Alimentacao(String.valueOf(dataIni.getText()),String.valueOf(dataIni.getText()),  String.valueOf(horaIni.getText()), String.valueOf(anotacoes.getText()), TipoDeComida);
                        AtividadesSingleton.getInstance().getAtividades().set(position,comida);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());
                        MyBaby.atualizaLista();
                        MyBaby.mAdapter.getFilter().filter("");
                        MyBaby.editText.setText("");
                        dismiss();
                    }
                    else {
                        Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        } else if (atividade instanceof Bebidas) {
            setContentView(R.layout.dialog_bebida);
            bebida = (Bebidas) atividade;
            dataIni = findViewById(R.id.dataBebida);
            dataIni.setText(bebida.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            TipoDeBebida = bebida.getTipoDeBebida();
            horaIni = findViewById(R.id.horaBebida);
            horaIni.setText(bebida.getHoraInicial());

            volume = findViewById(R.id.volumeBebida);
            anotacoes = findViewById(R.id.anotacoesBebida);
            anotacoes.setText(bebida.getAnotacao());
            volume.setText(bebida.getVolume());
            spinner1 = this.findViewById(R.id.spinnerBebida);
            initSpinner3();

            iv = (new Integer(bebida.getImageView()));
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

            confirmar = this.findViewById(R.id.confirmarBebida);
            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dataIni.getText().toString().equals("") ){
                        Bebidas bebidaFim = new Bebidas(String.valueOf(dataIni.getText()),String.valueOf(dataIni.getText()),  String.valueOf(horaIni.getText()), String.valueOf(anotacoes.getText()), TipoDeBebida,volume.getText().toString());
                        AtividadesSingleton.getInstance().getAtividades().set(position,bebidaFim);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());

                        MyBaby.atualizaLista();
                        MyBaby.mAdapter.getFilter().filter("");
                        MyBaby.editText.setText("");
                        dismiss();
                    }
                    else {
                        Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (atividade instanceof Banho) {
           setContentView(R.layout.dialog_banho);
            banho = (Banho) atividade;
            dataIni = findViewById(R.id.dataBanho);
            dataIni.setText(banho.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            produtosUsados = findViewById(R.id.produtosUtilizados);
            produtosUsados.setText(banho.getProdutosUsados());
            horaIni = findViewById(R.id.horaBanhoIni);
            horaIni.setText(banho.getHoraInicial());
            horaFim = this.findViewById(R.id.horaBanhoFim);
            horaFim.setText(banho.getHoraFinal());

            anotacoes = findViewById(R.id.anotacoesBanho);
            anotacoes.setText(banho.getAnotacao());

            duracao = findViewById(R.id.duracaoBanhoFim);
            duracao.setText(banho.getDuracao());

            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHorasSono(v, horaIni);
                }
            });
            horaFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHorasSono(v,horaFim);
                }
            });

            iv = (banho.getImageView());
            confirmar = this.findViewById(R.id.confirmarBanho);
            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dataIni.getText().toString().equals("") ){


                        Banho banho = new Banho(String.valueOf(dataIni.getText()),String.valueOf(dataIni.getText()),  String.valueOf(horaIni.getText()), String.valueOf(anotacoes.getText().toString()), String.valueOf(produtosUsados.getText().toString()),duracao.getText().toString(),horaFim.getText().toString());
                        AtividadesSingleton.getInstance().getAtividades().set(position, banho);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());

                        MyBaby.atualizaLista();
                        MyBaby.mAdapter.getFilter().filter("");
                        MyBaby.editText.setText("");
                        dismiss();
                    }
                    else {
                        Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (atividade instanceof Medicamento) {
            setContentView(R.layout.dialog_medicamentos);
            med = (Medicamento) atividade;
            dataIni = findViewById(R.id.dataMed);
            dataIni.setText(med.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            calendario = med.getCalendario();


            horaIni = findViewById(R.id.horaMed);
            horaIni.setText(med.getHoraInicial());

            frequency = med.getFrequency();
            // aq

            //Set a DateFormat to define how the date info will be shown
            dateFormat = DateFormat.getDateInstance();
            textDate = findViewById(R.id.textDate);

            textDate.setText(med.getDataNotificacao());

            textDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioMed(v, textDate);
                }
            });
            //Set a DateFormat to define how the time info will be shown
            textTime = findViewById(R.id.textTime);
            dateFormatTime = DateFormat.getTimeInstance();
        //    String time = new SimpleDateFormat("HH:mm").format(calendario.getTime());
            textTime.setText(med.getHoraNotificacao());
    /*        SimpleDateFormat formatador = new SimpleDateFormat("mm:ss");
            Date data = null;
            try {
                data = formatador.parse(textTime.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Time tempoMed = new Time(data.getTime());
            miliSegundos = tempoMed.getTime();*/

            textTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHorasMed(v, textTime,true);
                }
            });



            spinnerFrequency = findViewById(R.id.spinnerFrequency);
            ArrayAdapter<String> apadter = new ArrayAdapter<String>(
                    getContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    FREQUENCY_OPTIONS
            );

            spinnerFrequency.setAdapter(apadter);

            if(frequency == 0)
                spinnerFrequency.setSelection(0);
            else if(frequency == 1)
                spinnerFrequency.setSelection(1);
            else if(frequency == 2)
                spinnerFrequency.setSelection(2);
            else if(frequency == 3)
                spinnerFrequency.setSelection(3);
            else if(frequency == 4)
                spinnerFrequency.setSelection(4);
            else if(frequency == 5)
                spinnerFrequency.setSelection(6);



            spinnerFrequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0)
                        frequency = 0;
                    else if(position == 1)
                        frequency = 1;
                    else if(position == 2)
                        frequency = 2;
                    else if(position == 3)
                        frequency = 3;
                    else if(position == 4)
                        frequency = 4;
                    else if(position == 5)
                        frequency = 6;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    frequency = 0;
                }
            });

            switchActive = findViewById(R.id.switchActive);
//        textTimesPerDay = findViewById(R.id.textTimesPerDay);





            // ate aq


            anotacoes = findViewById(R.id.anotacoesMed);
            anotacoes.setText(med.getAnotacao());
            TipoDeMedicamento = med.getTipoDeMedicamento();
            spinner1 = this.findViewById(R.id.spinner1);
            initSpinner1();


            iv = (med.getImageView());
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

            confirmar = this.findViewById(R.id.confirmarMed);
            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!dataIni.getText().toString().equals("") ){
                        Intent it = new Intent(getContext(),
                                LaunchNotification.class);
                        PendingIntent p = PendingIntent.
                                getActivity(getContext(), 0, it, 0);

                        long time = calendario.getTimeInMillis();
                        long timeDifference = time - System.currentTimeMillis();
                        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                        if(switchActive.isChecked()) {
                            if (timeDifference <= 0) {
                                Toast.makeText(getContext(), "Não pode setar uma notificação no passado! ", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (frequency == 0)
                                alarmManager.set(AlarmManager.RTC_WAKEUP, time, p);
                            else
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,
                                        1000 * 60 * 60 * 24 / frequency, p);
                            Toast.makeText(getContext(), "A notificação será lançada em  " +
                                    timeDifference / 1000 + " segundos", Toast.LENGTH_SHORT).show();
                        } else {
                            alarmManager.cancel(p);
                            Toast.makeText(getContext(), "A notificação está desabilitada", Toast.LENGTH_SHORT).show();
                        }
                       Medicamento medicamento = new Medicamento(String.valueOf(dataIni.getText()),String.valueOf(dataIni.getText()),  String.valueOf(horaIni.getText()), String.valueOf(anotacoes.getText()), TipoDeMedicamento, frequency, textTime.getText().toString(),textDate.getText().toString(),calendario);
                        AtividadesSingleton.getInstance().getAtividades().set(position,medicamento);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());

                        MyBaby.atualizaLista();
                        MyBaby.mAdapter.getFilter().filter("");
                        MyBaby.editText.setText("");
                        dismiss();
                    }
                    else {
                        Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (atividade instanceof Sono) {
            sono = true;
            setContentView(R.layout.dialog_sono);
            Tsono = (Sono) atividade;
            this.setContentView(R.layout.dialog_sono);

            dataIni = this.findViewById(R.id.dataSono);
            anotacoes = this.findViewById(R.id.anotacoesSono);
            confirmar = this.findViewById(R.id.confirmarSono);
            horaIni = this.findViewById(R.id.horaSonoIni);
            horaFim = this.findViewById(R.id.horaSonoFim);
            dataIni.setText(Tsono.getDataDeInicio());
            horaFim.setText(Tsono.getHoraFinal());

            horaIni.setText(Tsono.getHoraInicial());


            duracao = findViewById(R.id.duracaoSono);

            duracao.setText( Tsono.getDuracao());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });


            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHorasSono(v, horaIni);

                }
            });

            horaFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHorasSono(v, horaFim);


                }

            });


            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (!dataIni.getText().toString().equals("")) {

                        Sono sono = new Sono(String.valueOf(dataIni.getText()), String.valueOf(dataIni.getText()), String.valueOf(horaIni.getText()), String.valueOf(anotacoes.getText()), duracao.getText().toString(), horaFim.getText().toString());
                        AtividadesSingleton.getInstance().getAtividades().set(position,sono);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());
                        Toast.makeText(getContext(), "O bebê dormiu " + duracao.getText() + " horas ", Toast.LENGTH_SHORT).show();
                        MyBaby.atualizaLista();
                        MyBaby.mAdapter.getFilter().filter("");
                        MyBaby.editText.setText("");
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else if (atividade instanceof Outros) {
           setContentView(R.layout.dialog_outros);

            outro = (Outros) atividade;
            dataIni = findViewById(R.id.dataOutros);
            dataIni.setText(outro.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });



            horaIni = findViewById(R.id.horaOutrosIni);
            horaIni.setText(outro.getHoraInicial());


            anotacoes = findViewById(R.id.anotacoesOutros);
            anotacoes.setText(outro.getAnotacao());


            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

            tipo = findViewById(R.id.atv);
            tipo.setText(outro.getTipo());

            iv = (new Integer(outro.getImageView()));

            confirmar=findViewById(R.id.confirmarOutros);
            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dataIni.getText().toString().equals("") ){
                        Outros outro = new Outros(String.valueOf(dataIni.getText()),String.valueOf(dataIni.getText()),  String.valueOf(horaIni.getText()), String.valueOf(anotacoes.getText()),String.valueOf(tipo.getText().toString()));
                        AtividadesSingleton.getInstance().getAtividades().set(position,outro);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());
                     MyBaby.atualizaLista();
                        MyBaby.mAdapter.getFilter().filter("");
                        MyBaby.editText.setText("");
                        dismiss();
                    }
                    else {
                        Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void calendario(View view) {
        final  Calendar calNow = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calNow.set(Calendar.YEAR, year);
                calNow.set(Calendar.MONTH, month);
                calNow.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                dataIni.setText(dataFormat.format(calNow.getTime()));
            }
        }, calNow.get(Calendar.YEAR),
                calNow.get(Calendar.MONTH), calNow.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void obterHorasSono(View view, final TextView tvHora) {
        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {

                Calendar calNow = Calendar.getInstance();
                calNow.set(Calendar.HOUR_OF_DAY, hora);
                calNow.set(Calendar.MINUTE, minuto);
                String hours = new SimpleDateFormat("HH:mm").format(calNow.getTime());
                tvHora.setText(hours.toString());
                ini = Horario.parse(horaIni.getText().toString());
                horarioFim = Horario.parse(horaFim.getText().toString());
                falta = horarioFim.diferenca(ini);
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


    private void initSpinner1() {

        ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                MEDICAMENTOS);


        spinner1.setAdapter(adapterSpinner1);

        for (int i = 0 ; i < MEDICAMENTOS.length; i++){
            if (MEDICAMENTOS[i].equals(TipoDeMedicamento)){
                spinner1.setSelection(i);
            }
        }
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(MEDICAMENTOS[position].equals("Benadryl")) {
                    TipoDeMedicamento = "Benadryl";
                } else if(MEDICAMENTOS[position].equals("Motrin")) {
                    TipoDeMedicamento = "Motrin";
                } else if(MEDICAMENTOS[position].equals("Mylicon")) {
                    TipoDeMedicamento = "Mylicon";
                }else if(MEDICAMENTOS[position].equals("Tynelol")) {
                    TipoDeMedicamento = "Tynelol";
                }else if(MEDICAMENTOS[position].equals("Vitamin D")) {
                    TipoDeMedicamento = "Vitamin D";
                }else if(MEDICAMENTOS[position].equals("Outro")) {
                    TipoDeMedicamento = "Outro";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    private void initSpinner2() {
        ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                COMIDAS);
        spinner1.setAdapter(adapterSpinner1);
        for (int i = 0 ; i < COMIDAS.length; i++){
            if (COMIDAS[i].equals(TipoDeComida)){
                spinner1.setSelection(i);
            }
        }
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

    private void initSpinner3() {
        ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                BEBIDAS);
        spinner1.setAdapter(adapterSpinner1);
        for (int i = 0 ; i < BEBIDAS.length; i++){
            if (BEBIDAS[i].equals(TipoDeBebida)){
                spinner1.setSelection(i);
            }
        }
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(BEBIDAS[position].equals("Chá")) {
                    TipoDeBebida = "Chá";
                } else if(BEBIDAS[position].equals("Sumo")) {
                    TipoDeBebida = "Sumo";
                } else if(BEBIDAS[position].equals("Água")) {
                    TipoDeBebida = "Água";
                }else if(BEBIDAS[position].equals("Outro")) {
                    TipoDeBebida = "Outro";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void obterHora(View view, boolean fim){

        Calendar calendar = Calendar.getInstance();//cria o obj calendar e atribui a hora e data do sistema
        Date data = calendar.getTime();//transforma o obj calendar em obj Date


        SimpleDateFormat sdhora = new SimpleDateFormat("HH:mm");//cria um obj de formatação de hora
        String s = sdhora.format(data);//gera a string final formatada no estilo "HH:mm:ss"
        if (!fim){
            horaIni.setText(s);
        }
        else {
            horaFim.setText(s);
        }

    }

    public void resetarHora(View v){
        horaIni.setText("");
        horaFim.setText("");
    }

    private void obterHorasAmamentacao(View view, final TextView tvHora) {
        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {

                Calendar calNow = Calendar.getInstance();
                calNow.set(Calendar.HOUR_OF_DAY, hora);
                calNow.set(Calendar.MINUTE, minuto);
                String hours = new SimpleDateFormat("HH:mm").format(calNow.getTime());
                tvHora.setText(hours.toString());
                ini = Horario.parse(horaIni.getText().toString());
                horarioFim = Horario.parse(horaFim.getText().toString());
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


    private void calendarioMed(View view, final EditText data) {
        //    final Calendar calendario = Calendar.getInstance();
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


    private void obterHorasMed(View view, final TextView tvHora,  final boolean notification) {
        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {

               // Calendar calNow = Calendar.getInstance();
                calendario.set(Calendar.HOUR_OF_DAY, hora);
                calendario.set(Calendar.MINUTE, minuto);
                String hours = new SimpleDateFormat("HH:mm").format(calendario.getTime());
                tvHora.setText(hours.toString());

            }
        };

        //Calendar calNow = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(getContext(),
                mTimeSetListener,
                calendario.getTime().getHours(),
                calendario.getTime().getMinutes(),
                true);

        dialog.show();
    }
}
