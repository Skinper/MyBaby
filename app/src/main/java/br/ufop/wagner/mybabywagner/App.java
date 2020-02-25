package br.ufop.wagner.mybabywagner;
import android.app.Application;
import android.content.Context;

public class App {
    private static Context context;

    public static void setContext(Context cntxt) {
        context = cntxt;
    }

    public static Context getContext() {
        return context;
    }
}