package br.ufop.wagner.mybabywagner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AtividadesAdapter extends BaseAdapter implements Filterable {

    public static ArrayList<Atividades> list;
    public static ArrayList<Atividades> list_exib;
    private Context context;
    private LayoutInflater layoutInflater;
    public  ArrayList<Atividades> itens_filtrados;

    public AtividadesAdapter(ArrayList<Atividades> list,
                          Context context) {

        this.list = list;
        this.context = context;
        list_exib = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Recovers the Student in the current position
        Atividades atividade = list.get(position);

        //Creates a View object from the given XML layout file
        layoutInflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.atividades_adapter, null);

        //Update TextView's text
        TextView tv1 = v.findViewById(R.id.textData);
        tv1.setText(String.valueOf(atividade.getDataFinal()));

        TextView tv2 = v.findViewById(R.id.textAtividade);
        ImageView iv = v.findViewById(R.id.img);

        if (atividade instanceof TrocarFralda){
            iv.setImageResource(R.drawable.fraldas);
        }
        if (atividade instanceof Amamentacao){
            iv.setImageResource(R.drawable.breastfeeding);
        }
        if (atividade instanceof Mamadeira){
            iv.setImageResource(R.drawable.mamadeira);
        }
        if (atividade instanceof Alimentacao){
            iv.setImageResource(R.drawable.comida);
        }
        if (atividade instanceof Bebidas){
            iv.setImageResource(R.drawable.bebida);
        }
        if (atividade instanceof Banho){
            iv.setImageResource(R.drawable.banho);
        }
        if (atividade instanceof Medicamento){
            iv.setImageResource(R.drawable.medicamentos);
        }
        if (atividade instanceof Sono){
            iv.setImageResource(R.drawable.dormindo);
        }
        if (atividade instanceof Outros){
            iv.setImageResource(R.drawable.outros32);
        }
       /* iv.setImageResource(atividade.getImageView());*/
        tv2.setText(atividade.getTipo());
        TextView tv3 = v.findViewById(R.id.Hora);
        tv3.setText(atividade.getHoraInicial());

        //Update ImageView's image



        return v;

    }

    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence filtro) {
                FilterResults results = new FilterResults();
                String filtroString = filtro.toString().toLowerCase();
                MyBaby.filter = true;
                //se não foi realizado nenhum filtro insere todos os itens.
                if (filtroString.equals("")) {
                    MyBaby.filter = false;
                    results.count = list_exib.size();
                    results.values = list_exib;
                } else {
                    //cria um array para armazenar os objetos filtrados.
                     itens_filtrados = new ArrayList<Atividades>();

                    //percorre toda lista verificando se contem a palavra do filtro na descricao do objeto.
                    for (int i = 0; i < list.size(); i++) {
                        Atividades data = list.get(i);
                    //    filtro = filtro.toString().toLowerCase();
                        String condicao;
                        if (filtroString.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")){
                            condicao = data.getTipo().toLowerCase();

                        }
                        else {
                            condicao = data.getDataFinal().toLowerCase();
                        }

                        if (condicao.contains(filtroString)) {
                            //se conter adiciona na lista de itens filtrados.
                            itens_filtrados.add(data);
                        }
                    }
                    // Define o resultado do filtro na variavel FilterResults
                    results.count = itens_filtrados.size();
                    results.values = itens_filtrados;
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

                list =  (ArrayList<Atividades>) results.values; // Valores filtrados.
                notifyDataSetChanged();  // Notifica a lista de alteração
            }

        };
        return filter;
    }

    public static boolean isValidaData(String dataStr){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); //Formate a data do jeito que for necessário, outro exemplo: ("dd/MM/yyyy")
        try {
            java.sql.Date data = new java.sql.Date(format.parse(dataStr).getTime());
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public static void atualizaFiltro(){
       MyBaby.atualizaLista();
    }






}
