package br.ufop.wagner.mybabywagner.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import br.ufop.wagner.mybabywagner.AtividadesSingleton;
import br.ufop.wagner.mybabywagner.BabyEdit;
import br.ufop.wagner.mybabywagner.MyBaby;
import br.ufop.wagner.mybabywagner.R;
import br.ufop.wagner.mybabywagner.SharedResources;


public class DialogDelete extends Dialog {
    private ImageButton buttonSim;
    private ImageButton buttonNao;


    public DialogDelete(@NonNull Context context, int position, int cod) {
        super(context);
        initComponentes(position, cod);

    }


    private void initComponentes(final int position , int cod) {
    if (cod == 1) {
        this.setContentView(R.layout.dialog_delete);

        buttonSim = findViewById(R.id.sim);
        buttonSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtividadesSingleton.getInstance().getAtividades().remove(position);
                AtividadesSingleton.getInstance().saveAtividades(getContext());
                Toast.makeText(getContext(),
                        "Atividade " + "removida com sucesso.", Toast.LENGTH_SHORT).show();
                MyBaby.atualizaLista();
                MyBaby.mAdapter.getFilter().filter("");
                MyBaby.editText.setText("");
                dismiss();
            }
        });
        buttonNao = findViewById(R.id.nao);
        buttonNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    else {
        this.setContentView(R.layout.dialog_delete);

        buttonSim = findViewById(R.id.sim);
        buttonSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedResources.getInstance().getBaby().getPeso().remove(position);
               SharedResources.getInstance().saveBabies(getContext());
                Toast.makeText(getContext(),
                        "Peso " + "removido com sucesso.", Toast.LENGTH_SHORT).show();
                BabyEdit.mAdapterPeso.notifyDataSetChanged();
                dismiss();
            }
        });
        buttonNao = findViewById(R.id.nao);
        buttonNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    }

}
