package br.ufop.wagner.mybabywagner.Dialogs;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import br.ufop.wagner.mybabywagner.Alimentacao;
import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.LaunchNotification;
import br.ufop.wagner.mybabywagner.Medicamento;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.R;

public class DialogMedicamentos extends Dialog {

    private int tema;
    private EditText data;
    private RadioGroup radioGroup;
    private EditText horas;
    private ImageButton confirmar;
    private EditText anotacoes;
    private Spinner spinner1;
    private String TipoDeMedicamento;
    private  SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar calendario = Calendar.getInstance();


    private EditText textDate;
    private DateFormat dateFormat;
    private EditText textTime;
    private DateFormat dateFormatTime;
    private Calendar calendar;
    private DatePickerDialog dialogDatePicker;
    private TimePickerDialog dialogTimePicker;
    //    private EditText textTimesPerDay;
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




    private static final String[] MEDICAMENTOS = {"Benadryl", "Motrin", "Mylicon", "Tynelol","Vitamin D", "Outro"};




    public DialogMedicamentos(@NonNull Context context, int tema) {
        super(context);
        this.tema = tema;
        initComponentes();
    }


    private void initComponentes() {

        this.setContentView(R.layout.dialog_medicamentos);
        // aq


        //Set a DateFormat to define how the date info will be shown
        dateFormat = DateFormat.getDateInstance();
        textDate = findViewById(R.id.textDate);
        textDate.setText(dataFormat.format(calendario.getTime()));

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario(v, textDate);
            }
        });
        //Set a DateFormat to define how the time info will be shown
        textTime = findViewById(R.id.textTime);
        dateFormatTime = DateFormat.getTimeInstance();
        String time = new SimpleDateFormat("HH:mm").format(calendario.getTime());
        textTime.setText(time);


        textTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterHoras(v, textTime,true);
            }
        });



        spinnerFrequency = findViewById(R.id.spinnerFrequency);
        ArrayAdapter<String> apadter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                FREQUENCY_OPTIONS
        );
        spinnerFrequency.setAdapter(apadter);
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


        spinner1 = this.findViewById(R.id.spinner1);
        initSpinner1();
        data = this.findViewById(R.id.dataMed);
        anotacoes = this.findViewById(R.id.anotacoesMed);
        data.setText(dataFormat.format(calendario.getTime()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarioAtividade(v,data);
            }
        });


        horas = findViewById(R.id.horaMed);
        time = new SimpleDateFormat("HH:mm").format(calendario.getTime());
        horas.setText(time);

        horas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterHorasAtividade(v, horas);
            }
        });

        confirmar = this.findViewById(R.id.confirmarMed);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.getText().toString().equals("") ){
                    Intent it = new Intent(getContext(),
                            LaunchNotification.class);
                    PendingIntent p = PendingIntent.
                            getActivity(getContext(), 0, it, 0);

                    long time = calendario.getTimeInMillis();
                    long timeDifference = time - System.currentTimeMillis();
                    alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                    if(switchActive.isChecked()) {
                        if (timeDifference <= 0) {
                            Toast.makeText(getContext(), "Não pode setar uma notificação no passado!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (frequency == 0)
                            alarmManager.set(AlarmManager.RTC_WAKEUP, time, p);
                        else
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,
                                    1000 * 60 * 60 * 24 / frequency, p);
                        Toast.makeText(getContext(), "A notificação será lançada em " +
                                timeDifference / 1000 + " segundos", Toast.LENGTH_SHORT).show();
                        Medicamento medicamento = new Medicamento(String.valueOf(data.getText()),String.valueOf(data.getText()),  String.valueOf(horas.getText()), String.valueOf(anotacoes.getText()), TipoDeMedicamento, frequency,textTime.getText().toString(), textDate.getText().toString(), calendario);
                        AtividadesSingleton.getInstance().getAtividades().add(medicamento);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());
                        Toast.makeText(getContext(),"Atividade adicionada com sucesso no dia " + data.getText() + " "  , Toast.LENGTH_SHORT).show();
                        MyBaby.atualizaLista();
                        dismiss();
                    } else {
                        alarmManager.cancel(p);
                        Toast.makeText(getContext(), "The notifications were disabled!", Toast.LENGTH_SHORT).show();
                        Medicamento medicamento = new Medicamento(String.valueOf(data.getText()),String.valueOf(data.getText()),  String.valueOf(horas.getText()), String.valueOf(anotacoes.getText()), TipoDeMedicamento, frequency,calendario);
                        AtividadesSingleton.getInstance().getAtividades().add(medicamento);
                        AtividadesSingleton.getInstance().saveAtividades(getContext());
                        Toast.makeText(getContext(),"Atividade adicionada com sucesso no dia " + data.getText() + " "  , Toast.LENGTH_SHORT).show();
                        MyBaby.atualizaLista();
                        dismiss();
                    }

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
                MEDICAMENTOS);
        spinner1.setAdapter(adapterSpinner1);
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

    private void calendario(View view, final EditText data) {
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


    private void obterHoras(View view, final TextView tvHora,  final boolean notification) {
        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {

            //    Calendar calNow = Calendar.getInstance();
                calendario.set(Calendar.HOUR_OF_DAY, hora);
                calendario.set(Calendar.MINUTE, minuto);
                String hours = new SimpleDateFormat("HH:mm").format(calendario.getTime());
                tvHora.setText(hours.toString());

            }
        };

      //  calendario = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(getContext(),
                mTimeSetListener,
                calendario.getTime().getHours(),
                calendario.getTime().getMinutes(),
                true);


        dialog.show();
    }



    private void calendarioAtividade(View view, final EditText data) {
        //  final Calendar calendario = Calendar.getInstance();
       final  Calendar calNow = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calNow.set(Calendar.YEAR, year);
                calNow.set(Calendar.MONTH, month);
                calNow.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                data.setText(dataFormat.format(calNow.getTime()));
            }
        }, calNow.get(Calendar.YEAR),
                calNow.get(Calendar.MONTH), calNow.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void obterHorasAtividade(View view, final TextView tvHora) {
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

/*
    protected void onResume() {

        if(LaunchNotification.mNotificationManager
                != null) {
            LaunchNotification.mNotificationManager
                    .cancel(0);
        }
    }
*/

/*    public void ok(View view) {
        Intent it = new Intent(getContext(),
                LaunchNotification.class);
        PendingIntent p = PendingIntent.
                getActivity(getContext(), 0, it, 0);

        long time = calendar.getTimeInMillis();
        long timeDifference = time - System.currentTimeMillis();
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        if(switchActive.isChecked()) {
            if (timeDifference <= 0) {
                Toast.makeText(getContext(), "You can not set an alarm to a past time!", Toast.LENGTH_SHORT).show();
                return;
            }
//        int timesPerDay = 1;
//        if(! textTimesPerDay.getText().toString().equals(""))
//            timesPerDay = Integer.parseInt(textTimesPerDay.getText().toString());
            if (frequency == 0)
                alarmManager.set(AlarmManager.RTC_WAKEUP, time, p);
            else
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,
                        1000 * 60 * 60 * 24 / frequency, p);
            Toast.makeText(getContext(), "Notification will be launched within " +
                    timeDifference / 1000 + " seconds", Toast.LENGTH_SHORT).show();
        } else {
            alarmManager.cancel(p);
            Toast.makeText(getContext(), "The notifications were disabled!", Toast.LENGTH_SHORT).show();
        }
        dismiss();
    }*/

    public void showDatePicker(View view) {
        dialogDatePicker.show();
    }

    public void showTimePicker(View view) {
        dialogTimePicker.show();
    }
}
