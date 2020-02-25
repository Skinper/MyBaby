package br.ufop.wagner.mybabywagner;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BabyRegister extends AppCompatActivity {

    private Baby baby;
    private RadioGroup radioGroup;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private AlertDialog alerta;
    private EditText etName;
    private EditText etDate;
    private EditText date;
    private String sex;
    private int dia, mes, ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_register);
        etName = findViewById(R.id.textName);

        radioGroup = findViewById(R.id.radioGroup);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);


         etDate = findViewById(R.id.textDate);
        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();


                dia= c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BabyRegister.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker,
                                          int selectedyear, int selectedmonth,
                                          int selectedday) {

                        c.set(Calendar.YEAR, selectedyear);
                        c.set(Calendar.MONTH, selectedmonth);
                        c.set(Calendar.DAY_OF_MONTH, selectedday);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                        etDate.setText(sdf.format(c.getTime()));


                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });
       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {

           }
       });




    }

    public void confirm(View view) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMale){

            sex = "Menino";
            Toast.makeText(this,"RadioButton selecionado", Toast.LENGTH_LONG).show();
        }
        else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonFemale){
            Toast.makeText(this,"RadioButton selecionado Menina", Toast.LENGTH_LONG).show();
            sex = "Menina";
        }

        SharedResources.getInstance().getBaby().setSex(sex);
        SharedResources.getInstance().getBaby().setDate(etDate.getText().toString());
        SharedResources.getInstance().getBaby().setName(etName.getText().toString());
        System.out.print(" Nome " + SharedResources.getInstance().getBaby().getName());
        SharedResources.getInstance().saveBabies(this);

        Intent intent3 = new Intent(this, MyBaby.class);

        if (!SharedResources.getInstance().getBaby().getName().equals("") && !SharedResources.getInstance().getBaby().getSex().equals("") && !SharedResources.getInstance().getBaby().getDate().equals("")){
            Toast.makeText(this,
                    "Baby " + SharedResources.getInstance().getBaby().getSex() + " added successfully.", Toast.LENGTH_SHORT).show();

            startActivity(intent3);

            finish();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
            builder.setTitle("Alerta");
            //define a mensagem
            builder.setMessage("Você deve preencher todos os campos");
            //define um botão como positivo
            builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(BabyRegister.this, "Confirmado", Toast.LENGTH_SHORT).show();
                }
            });

            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();
        }

    }

    public void babybirth(View view) {

/*
    final Calendar c = Calendar.getInstance();
    dia= c.get(Calendar.DAY_OF_MONTH);
    mes = c.get(Calendar.MONTH);
    ano = c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker,
                                  int selectedyear, int selectedmonth,
                                  int selectedday) {
                selectedyear=selectedyear;
                c.set(Calendar.YEAR, selectedyear);
                c.set(Calendar.MONTH, selectedmonth);
                c.set(Calendar.DAY_OF_MONTH, selectedday);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                etDate.setText(sdf.format(c.getTime()));


            }
        },ano,mes,dia);
        datePickerDialog.show();*/

    }
}
