package br.ufop.wagner.mybabywagner;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AtividadesSingleton {

    private static AtividadesSingleton shared = null;

    //Singleton elements
    private static ArrayList<Atividades> atividades;

    public static AtividadesSingleton getInstance() {
        if (shared == null) {
            shared = new AtividadesSingleton();
        }
        return shared;
    }

    private AtividadesSingleton() {

        atividades = new ArrayList<Atividades>();
    }

    public ArrayList<Atividades> getAtividades() {
        return atividades;
    }

    public void saveAtividades(Context context) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput("atividades.tmp",
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(atividades);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAtividades(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("atividades.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            atividades = (ArrayList<Atividades>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
