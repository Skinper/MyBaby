package br.ufop.wagner.mybabywagner;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SharedResources {

    private static SharedResources shared = null;

    //Singleton elements
    private static Baby baby;

    public static SharedResources getInstance() {
        if (shared == null) {
            shared = new SharedResources();
        }
        return shared;
    }

    private SharedResources() {

        baby = new Baby();
    }

    public Baby getBaby() {
        return baby;
    }

    public void saveBabies(Context context) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput("baby.tmp",
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(baby);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBabies(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("baby.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            baby = (Baby) ois.readObject();
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