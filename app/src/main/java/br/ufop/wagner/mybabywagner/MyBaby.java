package br.ufop.wagner.mybabywagner;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import br.ufop.wagner.mybabywagner.Dialogs.DialogAlimentacao;
import br.ufop.wagner.mybabywagner.Dialogs.DialogAmamentacao;
import br.ufop.wagner.mybabywagner.Dialogs.DialogBanho;
import br.ufop.wagner.mybabywagner.Dialogs.DialogBebidas;
import br.ufop.wagner.mybabywagner.Dialogs.DialogDelete;
import br.ufop.wagner.mybabywagner.Dialogs.DialogEdit;
import br.ufop.wagner.mybabywagner.Dialogs.DialogMamadeira;
import br.ufop.wagner.mybabywagner.Dialogs.DialogMedicamentos;
import br.ufop.wagner.mybabywagner.Dialogs.DialogOutros;
import br.ufop.wagner.mybabywagner.Dialogs.DialogSom;
import br.ufop.wagner.mybabywagner.Dialogs.DialogTempoSono;
import br.ufop.wagner.mybabywagner.Dialogs.DialogTrocarFraldas;

public class MyBaby extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener {

    public static TextView text;
    private TextView textView;
    private NavigationView mNavigation;
    private View mHeaderView;
    Dialog dialog;
    public static ListView listView;
    private RecyclerView recyclerView;
    public static AtividadesAdapter mAdapter;
    private static final int ATIVIDADES_EDIT = 1;
    public static boolean filter = false;
    public static EditText editText;
    private Button data;
    private Button atv;


    float historicX = Float.NaN, historicY = Float.NaN;
    static final int DELTA = 50;
    enum Direction {LEFT, RIGHT;}



    private ArrayList<Integer> images = new ArrayList<Integer>(Arrays.asList(R.drawable.breastfeeding64, R.drawable.fraldas64,R.drawable.mamadeira64,R.drawable.comida64,R.drawable.bebida64,R.drawable.banho64,
            R.drawable.medicamentos64,R.drawable.dormindo64,R.drawable.outros, R.drawable.som64));
    private ArrayList<String> names = new ArrayList<String>(Arrays.asList("Amamentação","Troca de Fraldas","Mamadeira","Alimentação","Bebida","Banho","Medicamentos","Tempo de sono","Outros","Som"));

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        App.setContext(this);
        setContentView(R.layout.activity_my_baby);
        SharedResources.getInstance().loadBabies(this);
        AtividadesSingleton.getInstance().loadAtividades(this);

       recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(names,images,this);
        recyclerView.setAdapter(adapter);





        recyclerView.addOnItemTouchListener( new ItemListeners(getBaseContext(),
                new ItemListeners.OnItemClickListeners() {
                    @Override
                    public void onClick(View view, int position) {
                              //  dialog = new Dialog(view.getContext());


                                if (images.get(position) == R.drawable.breastfeeding64){
                                    Toast.makeText(getBaseContext(),"Clicou em amamentação", Toast.LENGTH_SHORT).show();
                                        new DialogAmamentacao(view.getContext(),R.layout.dialog_amamentacao).show();
                                       updateData();
                                       mAdapter.notifyDataSetChanged();


                        }
                        if (images.get(position) == R.drawable.fraldas64){
                            Toast.makeText(getBaseContext(),"Clicou em fraldas", Toast.LENGTH_SHORT).show();

                                new DialogTrocarFraldas(view.getContext(),R.layout.dialog_trocarfralda).show();
                                mAdapter.notifyDataSetChanged();


                        }
                        if (images.get(position) == R.drawable.mamadeira64){
                            Toast.makeText(getBaseContext(),"Clicou em mamadeira", Toast.LENGTH_SHORT).show();
                            new DialogMamadeira(view.getContext(),R.layout.dialog_mamadeira).show();
                            mAdapter.notifyDataSetChanged();
                  ;

                        }
                        if (images.get(position) == R.drawable.comida64){
                            Toast.makeText(getBaseContext(),"Clicou em comida", Toast.LENGTH_SHORT).show();

                            new DialogAlimentacao(view.getContext(),R.layout.dialog_comidas).show();
                            mAdapter.notifyDataSetChanged();

                        }
                        if (images.get(position) == R.drawable.medicamentos64){
                            Toast.makeText(getBaseContext(),"Clicou em medicamentos", Toast.LENGTH_SHORT).show();
                            new DialogMedicamentos(view.getContext(),R.layout.dialog_medicamentos).show();
                            mAdapter.notifyDataSetChanged();


                        }
                        if (images.get(position) == R.drawable.dormindo64){
                            Toast.makeText(getBaseContext(),"Clicou em sono", Toast.LENGTH_SHORT).show();
                           new DialogTempoSono(view.getContext(),R.layout.dialog_sono).show();
                            mAdapter.notifyDataSetChanged();


                        }
                        if (images.get(position) == R.drawable.bebida64){
                            Toast.makeText(getBaseContext(),"Clicou em bebida", Toast.LENGTH_SHORT).show();
                              new DialogBebidas(view.getContext(),R.layout.dialog_bebida).show();
                            mAdapter.notifyDataSetChanged();


                        }
                        if (images.get(position) == R.drawable.banho64){
                            Toast.makeText(getBaseContext(),"Clicou em banho", Toast.LENGTH_SHORT).show();
                            new DialogBanho(view.getContext(),R.layout.dialog_banho).show();
                            mAdapter.notifyDataSetChanged();


                        }
                        if (images.get(position) == R.drawable.outros){
                            Toast.makeText(getBaseContext(),"Clicou em Outros", Toast.LENGTH_SHORT).show();
                            new DialogOutros(view.getContext(),R.layout.dialog_outros).show();
                            mAdapter.notifyDataSetChanged();


                        }
                        if (images.get(position) == R.drawable.som64){
                            Toast.makeText(getBaseContext(),"Clicou em Som", Toast.LENGTH_SHORT).show();
                            new DialogSom(view.getContext()).show();
                            mAdapter.notifyDataSetChanged();


                        }

                    }
                }));





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mNavigation = (NavigationView) findViewById(R.id.nav_view);
        mHeaderView = mNavigation.getHeaderView(0);

        text = (TextView) mHeaderView.findViewById(R.id.textNameHeader);
        text.setText(SharedResources.getInstance().getBaby().getName());


        // Lista de Atividades
        mAdapter = new AtividadesAdapter(AtividadesSingleton.getInstance().getAtividades(),this);
        listView = this.findViewById(R.id.listAtividades);
        listView.setAdapter(mAdapter);
        atualizaLista();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 //  Intent it = new Intent(MyBaby.this,ActivityEdit.class);

                    if (filter){

                        int i = AtividadesSingleton.getInstance().getAtividades().indexOf(mAdapter.itens_filtrados.get(position));

                         /*               it.putExtra("atividade",(Parcelable) AtividadesSingleton.getInstance().getAtividades().get(i));
                                        it.putExtra("position",i);*/
                                        position = i;
                        new DialogEdit(view.getContext(), position).show();

                    } else {
                        filter = false;

                      /*  it.putExtra("atividade",(Parcelable) AtividadesSingleton.getInstance().getAtividades().get(position));
                        it.putExtra("position", position);*/
                        new DialogEdit(view.getContext(), position).show();
                    }

              //  startActivityForResult(it, ATIVIDADES_EDIT);

                atualizaLista();
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (filter){

                    int i = AtividadesSingleton.getInstance().getAtividades().indexOf(mAdapter.itens_filtrados.get(position));

                         /*               it.putExtra("atividade",(Parcelable) AtividadesSingleton.getInstance().getAtividades().get(i));
                                        it.putExtra("position",i);*/
                    position = i;
                    new DialogDelete(view.getContext(),position,1).show();

                } else {
                    filter = false;

                      /*  it.putExtra("atividade",(Parcelable) AtividadesSingleton.getInstance().getAtividades().get(position));
                        it.putExtra("position", position);*/
                    new DialogDelete(view.getContext(),position,1).show();
                }



                return true;
            }
        });

        editText =  this.findViewById(R.id.editText1);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //quando o texto é alterado chamamos o filtro.
                filter = false;
                mAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        data= findViewById(R.id.dataList);
        atv = findViewById(R.id.atvList);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(AtividadesSingleton.getInstance().getAtividades(), new Comparator<Atividades>() {

                    @Override
                    public int compare(Atividades o1, Atividades o2) {
                        Date data1 = stringToDate(o1.getDataFinal());
                        Date data2 = stringToDate(o2.getDataFinal());
                        long miliData1 = data1.getTime();
                        long miliData2 = data2.getTime();
                        int retorno = 0;
                        if( miliData1 > miliData2 ){
                            retorno = -1;
                        }
                        else if (miliData2 == miliData1){
                        retorno = o2.getHoraInicial().compareTo(o1.getHoraInicial());

                        }
                        else if (miliData1 < miliData2){
                            retorno =1;
                        }

                        return retorno;

                    }
                });
                mAdapter.notifyDataSetChanged();
            }
        });

        atv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(AtividadesSingleton.getInstance().getAtividades(), new Comparator<Atividades>() {
                    @Override
                    public int compare(Atividades o1, Atividades o2) {
                        return o1.getTipo().compareToIgnoreCase(o2.getTipo());

                    }
                });
                mAdapter.notifyDataSetChanged();
            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_baby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

/*
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else */
        if (id == R.id.editBaby) {
            Intent it = new Intent(MyBaby.this,BabyEdit.class);

            startActivity(it);

            text.setText(SharedResources.getInstance().getBaby().getName());


        } else if (id == R.id.peso) {
            if (SharedResources.getInstance().getBaby().getPeso().size() > 0    ){
                Intent it = new Intent(MyBaby.this,GraficoPeso.class);
                startActivity(it);
            } else {
                Toast.makeText(this,"Cadastre algum peso ao bebê", Toast.LENGTH_SHORT).show();
            }

        } /*else if (id == R.id.nav_manage) {

        }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void atualizaLista(){
        Collections.sort(AtividadesSingleton.getInstance().getAtividades(), new Comparator<Atividades>() {
            @Override
            public int compare(Atividades o1, Atividades o2) {
                Date data1 = stringToDate(o1.getDataFinal());
                Date data2 = stringToDate(o2.getDataFinal());
                long miliData1 = data1.getTime();
                long miliData2 = data2.getTime();
                int retorno = 0;
                if( miliData1 > miliData2 ){
                    retorno = -1;
                }
                else if (miliData2 == miliData1){
                    retorno = o2.getHoraInicial().compareTo(o1.getHoraInicial());

                }
                else if (miliData1 < miliData2){
                    retorno =1;
                }

                return retorno;

            }
        });


        mAdapter.notifyDataSetChanged();



    }

    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

     mAdapter.notifyDataSetChanged();
    }


    private   void updateData() {
        mAdapter = new AtividadesAdapter(AtividadesSingleton.getInstance().getAtividades(),this);
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        atualizaLista();
    }

    public static Date stringToDate(String data1) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        f.setLenient(false);
        java.util.Date d1 = null;
        try {
            d1 = f.parse(data1);
        } catch (ParseException e) {}
        return d1;
    }




}
