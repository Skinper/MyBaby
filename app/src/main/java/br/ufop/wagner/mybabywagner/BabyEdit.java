package br.ufop.wagner.mybabywagner;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.ufop.wagner.mybabywagner.Dialogs.DialogDelete;
import br.ufop.wagner.mybabywagner.Dialogs.DialogPeso;

public class BabyEdit extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private AlertDialog alerta;
    private EditText etName;
    private EditText etDate;
    private EditText date;
    private String sex;
    private int dia, mes, ano;
    ImageButton peso;
    public static ListView listView;
    public static PesoAdapter mAdapterPeso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_edit);
        etName = findViewById(R.id.textName);
        etName.setText(SharedResources.getInstance().getBaby().getName());
        radioGroup = findViewById(R.id.radioGroupBabyEdit);
        radioButtonMale = findViewById(R.id.radioButtonMaleBabyEdit);
        radioButtonFemale = findViewById(R.id.radioButtonFemaleBabyEdit);
        sex = SharedResources.getInstance().getBaby().getSex();
        Toast.makeText(this," Nome " + SharedResources.getInstance().getBaby().getSex(),Toast.LENGTH_SHORT).show();
        if (sex.equals("Menino")) {

            radioGroup.check(R.id.radioButtonMaleBabyEdit);
        } else if ( sex.equals("Menina")){
            radioGroup.check(R.id.radioButtonFemaleBabyEdit);
        }

        etDate = findViewById(R.id.textDate);
        etDate.setText(SharedResources.getInstance().getBaby().getDate());
        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();


                dia= c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BabyEdit.this, new DatePickerDialog.OnDateSetListener() {
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

        peso = findViewById(R.id.addPeso);
        peso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogPeso(v.getContext(), R.layout.dialog_peso).show();
            }
        });
        mAdapterPeso = new PesoAdapter(SharedResources.getInstance().getBaby().getPeso(),this);
        listView = this.findViewById(R.id.listPeso);
        listView.setAdapter(mAdapterPeso);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    new DialogDelete(view.getContext(),position, 2).show();



                return true;
            }
        });



    }

    public void confirm(View view) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMaleBabyEdit){

            sex = "Menino";

        }
        else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonFemaleBabyEdit){

            sex = "Menina";
        }

        SharedResources.getInstance().getBaby().setSex(sex);
        SharedResources.getInstance().getBaby().setDate(etDate.getText().toString());
        SharedResources.getInstance().getBaby().setName(etName.getText().toString());
        /*Toast.makeText(this," Nome " + SharedResources.getInstance().getBaby().getName(),Toast.LENGTH_SHORT).show();*/
        SharedResources.getInstance().saveBabies(this);
        MyBaby.text.setText(SharedResources.getInstance().getBaby().getName());

        if (!SharedResources.getInstance().getBaby().getName().equals("") && !SharedResources.getInstance().getBaby().getSex().equals("") && !SharedResources.getInstance().getBaby().getDate().equals("")){

            finish();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
            builder.setTitle("Atenção");
            //define a mensagem
            builder.setMessage("Você deve preencher todos os campos");
            //define um botão como positivo
            builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(BabyEdit.this, "Confirmado", Toast.LENGTH_SHORT).show();
                }
            });

            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();
        }

    }


}
