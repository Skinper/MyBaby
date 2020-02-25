package br.ufop.wagner.mybabywagner.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.Outros;
import br.ufop.wagner.mybabywagner.Peso;
import br.ufop.wagner.mybabywagner.R;
import br.ufop.wagner.mybabywagner.SharedResources;

public class DialogPeso extends Dialog {
    private int tema;
    private EditText data;
    private EditText peso;

    private Button confirmar;


    private  SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private Calendar calendario = Calendar.getInstance();


    public DialogPeso(@NonNull Context context, int tema) {
        super(context);
        this.tema = tema;
        initComponentes();
    }


    private void initComponentes() {

        this.setContentView(R.layout.dialog_peso);



        data = this.findViewById(R.id.dataPeso);

        confirmar = this.findViewById(R.id.confirmarPeso);
        peso = this.findViewById(R.id.pesoBebe);




        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario(v);
            }
        });



      /*  peso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/





        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!data.getText().toString().equals("")  && !peso.getText().toString().equals(""))){

                    Peso p = new Peso(Float.valueOf(peso.getText().toString()),  (data.getText().toString()));

                    SharedResources.getInstance().getBaby().getPeso().add(p);

                    SharedResources.getInstance().saveBabies(getContext());


                    Toast.makeText(getContext(),"Peso adicionada com sucesso no dia " + data.getText() + " "  , Toast.LENGTH_SHORT).show();

                    dismiss();
                }
                else {
                    Toast.makeText(getContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
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
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM", Locale.US);
                data.setText(dataFormat.format(calendario.getTime()));
            }
        }, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }



}


