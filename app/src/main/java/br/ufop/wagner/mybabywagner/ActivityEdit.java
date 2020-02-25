package br.ufop.wagner.mybabywagner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static java.lang.String.valueOf;

public class ActivityEdit extends AppCompatActivity {



    private EditText dataIni;
    private EditText dataFim;
    private EditText horaIni;
    private EditText horaFim;
    private EditText anotacao;
    private int position;
    private TextView tv2;
    private int iv;
    private int image;
    private TextView tipo;
    private EditText infoAdd;

    private Amamentacao amamentacao;
    private  TrocarFralda atv;
    private  Mamadeira mamadeira;
    private Alimentacao alimentacao;
    private Bebidas bebida;
    private Banho banho;
    private Medicamento med;
    private Sono Tsono;
    private Outros outro;

    private Boolean am = false, fr = false, mama = false, ali = false, beb = false, medic = false, sono = false, ba = false, outros = false;



    private Atividades atividade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        infoAdd = findViewById(R.id.infoAdd);
        Intent it = getIntent();
        atividade = it.getParcelableExtra("atividade");
        position = it.getIntExtra("position", 0);
        if (atividade instanceof TrocarFralda){
            fr = true;
            Toast.makeText(this,"Fralda", Toast.LENGTH_SHORT).show();
           atv = (TrocarFralda) atividade;
            dataIni = findViewById(R.id.dataIni);
            dataIni.setText(atv.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            dataFim = findViewById(R.id.dataFim);
            dataFim.setText( atv.getDataFinal());
            dataFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioFim(v);
                }
            });

            horaIni = findViewById(R.id.horaIni);
            horaIni.setText(atv.getHoraInicial());



            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });



            anotacao = findViewById(R.id.anotacoes);
            anotacao.setText( atv.getAnotacao());

            tipo = findViewById(R.id.tipo);
            tipo.setText((atv.getTipo()));

            iv  = (atv.getImageView());

            infoAdd.setText(atv.getFralda());
        }
         else if (atividade instanceof Amamentacao){
            am = true;
            amamentacao = (Amamentacao) atividade;
            dataIni = findViewById(R.id.dataIni);
            dataIni.setText(amamentacao.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            dataFim = findViewById(R.id.dataFim);
            dataFim.setText( amamentacao.getDataFinal());
            dataFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioFim(v);
                }
            });

            horaIni = findViewById(R.id.horaIni);
            horaIni.setText(amamentacao.getHoraInicial());



            anotacao = findViewById(R.id.anotacoes);
            anotacao.setText( amamentacao.getAnotacao());

            tipo = findViewById(R.id.tipo);
            tipo.setText((amamentacao.getTipo()));
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });




            iv  = (amamentacao.getImageView());
        }
        else if (atividade instanceof Mamadeira){
            mama = true;
            mamadeira = (Mamadeira) atividade;
            dataIni = findViewById(R.id.dataIni);
            dataIni.setText(mamadeira.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            dataFim = findViewById(R.id.dataFim);
            dataFim.setText( mamadeira.getDataFinal());
            dataFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioFim(v);
                }
            });

            horaIni = findViewById(R.id.horaIni);
            horaIni.setText(mamadeira.getHoraInicial());


            anotacao = findViewById(R.id.anotacoes);
            anotacao.setText( mamadeira.getAnotacao());

            tipo = findViewById(R.id.tipo);
            tipo.setText((mamadeira.getTipo()));

            infoAdd.setText(mamadeira.getTipoDeLeite());
            iv  = (mamadeira.getImageView());
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });


        }

        else if (atividade instanceof Alimentacao){
            ali = true;
            alimentacao = (Alimentacao) atividade;
            dataIni = findViewById(R.id.dataIni);
            dataIni.setText(alimentacao.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            dataFim = findViewById(R.id.dataFim);
            dataFim.setText( alimentacao.getDataFinal());
            dataFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioFim(v);
                }
            });

            horaIni = findViewById(R.id.horaIni);
            horaIni.setText(alimentacao.getHoraInicial());



            anotacao = findViewById(R.id.anotacoes);
            anotacao.setText( alimentacao.getAnotacao());

            tipo = findViewById(R.id.tipo);
            tipo.setText((alimentacao.getTipo()));


            infoAdd.setText(alimentacao.getTipoAlimento());
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

/*            horaFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaFim);
                }
            });*/


            iv  = (new Integer(alimentacao.getImageView()));
        }
        else if (atividade instanceof Bebidas){
            beb = true;
            bebida = (Bebidas) atividade;
            dataIni = findViewById(R.id.dataIni);
            dataIni.setText(bebida.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            dataFim = findViewById(R.id.dataFim);
            dataFim.setText( bebida.getDataFinal());
            dataFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioFim(v);
                }
            });

            horaIni = findViewById(R.id.horaIni);
            horaIni.setText(bebida.getHoraInicial());


            anotacao = findViewById(R.id.anotacoes);
            anotacao.setText( bebida.getAnotacao());

            tipo = findViewById(R.id.tipo);
            tipo.setText((bebida.getTipo()));

            infoAdd.setText(bebida.getTipoDeBebida());

            iv  = (new Integer(bebida.getImageView()));
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

   /*         horaFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaFim);
                }
            });*/
        }
        else if (atividade instanceof Banho){
            ba = true;
            banho = (Banho) atividade;
            dataIni = findViewById(R.id.dataIni);
            dataIni.setText(banho.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            dataFim = findViewById(R.id.dataFim);
            dataFim.setText( banho.getDataFinal());
            dataFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioFim(v);
                }
            });

            horaIni = findViewById(R.id.horaIni);
            horaIni.setText(banho.getHoraInicial());

            anotacao = findViewById(R.id.anotacoes);
            anotacao.setText( banho.getAnotacao());

            tipo = findViewById(R.id.tipo);
            tipo.setText((banho.getTipo()));

            infoAdd = findViewById(R.id.infoAdd);
            infoAdd.setText(banho.getProdutosUsados());
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

         /*   horaFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaFim);
                }
            });
*/

            iv  = (banho.getImageView());
        }
        else if (atividade instanceof Medicamento){
            medic = true;
            med = (Medicamento) atividade;
            dataIni = findViewById(R.id.dataIni);
            dataIni.setText(med.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            dataFim = findViewById(R.id.dataFim);
            dataFim.setText( med.getDataFinal());
            dataFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioFim(v);
                }
            });

            horaIni = findViewById(R.id.horaIni);
            horaIni.setText(med.getHoraInicial());



            anotacao = findViewById(R.id.anotacoes);
            anotacao.setText( med.getAnotacao());

            tipo = findViewById(R.id.tipo);
            tipo.setText((med.getTipo()));

            infoAdd.setText(med.getTipoDeMedicamento());
            iv  = (med.getImageView());
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

       /*     horaFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaFim);
                }
            });*/
        }
        else if (atividade instanceof Sono){
            sono = true;

            Tsono = (Sono) atividade;
            dataIni = findViewById(R.id.dataIni);
            dataIni.setText(Tsono.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            dataFim = findViewById(R.id.dataFim);
            dataFim.setText( Tsono.getDataFinal());
            dataFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioFim(v);
                }
            });

            horaIni = findViewById(R.id.horaIni);
            horaIni.setText(Tsono.getHoraInicial());


            anotacao = findViewById(R.id.anotacoes);
            anotacao.setText( Tsono.getAnotacao());

            tipo = findViewById(R.id.tipo);
            tipo.setText((Tsono.getTipo()));
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

  /*          horaFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaFim);
                }
            });*/
            infoAdd.setFocusable(false);

            infoAdd.setText("Dormiu " + Tsono.getDuracao() + " horas");


            iv  = (new Integer(Tsono.getImageView()));
        }
        else if (atividade instanceof Outros){
            outros = true;

            outro = (Outros) atividade;
            dataIni = findViewById(R.id.dataIni);
            dataIni.setText(outro.getDataDeInicio());
            dataIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendario(v);
                }
            });

            dataFim = findViewById(R.id.dataFim);
            dataFim.setText( outro.getDataFinal());
            dataFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendarioFim(v);
                }
            });

            horaIni = findViewById(R.id.horaIni);
            horaIni.setText(outro.getHoraInicial());



            anotacao = findViewById(R.id.anotacoes);
            anotacao.setText( outro.getAnotacao());

            tipo = findViewById(R.id.tipo);
            tipo.setText((outro.getTipo()));
            horaIni.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaIni);
                }
            });

/*
            horaFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obterHoras(v, horaFim);
                }
            });
*/


            iv  = (new Integer(outro.getImageView()));
        }


        //Fill text fields with student's info


/*        infoAdicionais = findViewById(R.id.infoAdd);

            infoAdicionais.setText(atividade.getInfoAdicionais());*/






    }

    public void confirm(View view) {
        if (fr) {
            atv.setDataDeInicio(String.valueOf(dataIni.getText().toString()));
            atv.setDataFinal(String.valueOf(dataFim.getText().toString()));
            atv.setHoraInicial(String.valueOf(horaIni.getText().toString()));

            atv.setAnotacao(String.valueOf(anotacao.getText().toString()));
            atv.setTipo(String.valueOf(tipo.getText().toString()));
            atv.setImageView(iv);
            atv.setFralda(String.valueOf(infoAdd.getText().toString()));
            AtividadesSingleton.getInstance().getAtividades().set(position,atv);
            AtividadesSingleton.getInstance().saveAtividades(this);
        } else if (am){
            amamentacao.setDataDeInicio(String.valueOf(dataIni.getText().toString()));
            amamentacao.setDataFinal(String.valueOf(dataFim.getText().toString()));
            amamentacao.setHoraInicial(String.valueOf(horaIni.getText().toString()));

            amamentacao.setAnotacao(String.valueOf(anotacao.getText().toString()));
            amamentacao.setTipo(String.valueOf(tipo.getText().toString()));
            amamentacao.setImageView(iv);
            AtividadesSingleton.getInstance().getAtividades().set(position,amamentacao);
            AtividadesSingleton.getInstance().saveAtividades(this);

        }
        else if (mama){
            mamadeira.setDataDeInicio(String.valueOf(dataIni.getText().toString()));
            mamadeira.setDataFinal(String.valueOf(dataFim.getText().toString()));
            mamadeira.setHoraInicial(String.valueOf(horaIni.getText().toString()));

            mamadeira.setAnotacao(String.valueOf(anotacao.getText().toString()));
            mamadeira.setTipo(String.valueOf(tipo.getText().toString()));
            mamadeira.setImageView(iv);
            mamadeira.setTipoDeLeite(String.valueOf(infoAdd.getText().toString()));
            AtividadesSingleton.getInstance().getAtividades().set(position,mamadeira);
            AtividadesSingleton.getInstance().saveAtividades(this);
        }
        else if (ali){
            alimentacao.setDataDeInicio(String.valueOf(dataIni.getText().toString()));
            alimentacao.setDataFinal(String.valueOf(dataFim.getText().toString()));
            alimentacao.setHoraInicial(String.valueOf(horaIni.getText().toString()));

            alimentacao.setAnotacao(String.valueOf(anotacao.getText().toString()));
            alimentacao.setTipo(String.valueOf(tipo.getText().toString()));
            alimentacao.setImageView(iv);
            alimentacao.setTipoAlimento(String.valueOf(infoAdd.getText().toString()));
            AtividadesSingleton.getInstance().getAtividades().set(position,alimentacao);
            AtividadesSingleton.getInstance().saveAtividades(this);
        } else if (beb){
            bebida.setDataDeInicio(String.valueOf(dataIni.getText().toString()));
            bebida.setDataFinal(String.valueOf(dataFim.getText().toString()));
            bebida.setHoraInicial(String.valueOf(horaIni.getText().toString()));

            bebida.setAnotacao(String.valueOf(anotacao.getText().toString()));
            bebida.setTipo(String.valueOf(tipo.getText().toString()));
            bebida.setImageView(iv);
            bebida.setTipoDeBebida(String.valueOf(infoAdd.getText().toString()));
            AtividadesSingleton.getInstance().getAtividades().set(position,bebida);
            AtividadesSingleton.getInstance().saveAtividades(this);
        }
        else if (ba){
            banho.setDataDeInicio(String.valueOf(dataIni.getText().toString()));
            banho.setDataFinal(String.valueOf(dataFim.getText().toString()));
            banho.setHoraInicial(String.valueOf(horaIni.getText().toString()));

            banho.setAnotacao(String.valueOf(anotacao.getText().toString()));
            banho.setTipo(String.valueOf(tipo.getText().toString()));
            banho.setImageView(iv);
            banho.setProdutosUsados((String.valueOf(infoAdd.getText().toString())));
            AtividadesSingleton.getInstance().getAtividades().set(position,banho);
            AtividadesSingleton.getInstance().saveAtividades(this);
        }
        else if (medic){
            med.setDataDeInicio(String.valueOf(dataIni.getText().toString()));
            med.setDataFinal(String.valueOf(dataFim.getText().toString()));
            med.setHoraInicial(String.valueOf(horaIni.getText().toString()));

            med.setAnotacao(String.valueOf(anotacao.getText().toString()));
            med.setTipo(String.valueOf(tipo.getText().toString()));
            med.setImageView(iv);
            med.setTipoDeMedicamento((String.valueOf(infoAdd.getText().toString())));
            AtividadesSingleton.getInstance().getAtividades().set(position,med);
            AtividadesSingleton.getInstance().saveAtividades(this);
        }
        else if (sono){
            Tsono.setDataDeInicio(String.valueOf(dataIni.getText().toString()));
            Tsono.setDataFinal(String.valueOf(dataFim.getText().toString()));
            Tsono.setHoraInicial(String.valueOf(horaIni.getText().toString()));

            Tsono.setAnotacao(String.valueOf(anotacao.getText().toString()));
            Tsono.setTipo(String.valueOf(tipo.getText().toString()));
            Tsono.setImageView(iv);
            AtividadesSingleton.getInstance().getAtividades().set(position,Tsono);
            AtividadesSingleton.getInstance().saveAtividades(this);
        }
        else if (outros){
            outro.setDataDeInicio(String.valueOf(dataIni.getText().toString()));
            outro.setDataFinal(String.valueOf(dataFim.getText().toString()));
            outro.setHoraInicial(String.valueOf(horaIni.getText().toString()));

            outro.setAnotacao(String.valueOf(anotacao.getText().toString()));
            outro.setTipo(String.valueOf(tipo.getText().toString()));
            outro.setImageView(iv);
            AtividadesSingleton.getInstance().getAtividades().set(position,outro);
            AtividadesSingleton.getInstance().saveAtividades(this);
        }


        Toast.makeText(this,
                "Atividade "  + "editada com sucesso.", Toast.LENGTH_SHORT).show();
        MyBaby.atualizaLista();
        MyBaby.mAdapter.getFilter().filter("");
        MyBaby.editText.setText("");
        finish();
    }
    public void delete(View view) {
        //Return student
       AtividadesSingleton.getInstance().getAtividades().remove(position);
        AtividadesSingleton.getInstance().saveAtividades(this);
        Toast.makeText(this,
                "Atividade "  + "removida com sucesso.", Toast.LENGTH_SHORT).show();
        MyBaby.atualizaLista();

       MyBaby.mAdapter.getFilter().filter("");
        MyBaby.editText.setText("");
        finish();
    }

    private void calendario(View view) {
        final Calendar calendario = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendario.set(Calendar.YEAR, year);
                calendario.set(Calendar.MONTH, month);
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                dataIni.setText(dataFormat.format(calendario.getTime()));
            }
        }, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void calendarioFim(View view) {
        final Calendar calendario = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendario.set(Calendar.YEAR, year);
                calendario.set(Calendar.MONTH, month);
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                dataFim.setText(dataFormat.format(calendario.getTime()));
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
        TimePickerDialog dialog = new TimePickerDialog(this,
                mTimeSetListener,
                calNow.getTime().getHours(),
                calNow.getTime().getMinutes(),
                true);

        dialog.show();
    }
}
