package br.ufop.wagner.mybabywagner;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Horario {
    private final int horas;
    private final int minutos;

    public Horario(int horas, int minutos) {
        if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59) {
            throw new IllegalArgumentException();
        }
        this.horas = horas;
        this.minutos = minutos;
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public static Horario parse(String input) {
        char[] cs = input.toCharArray();
        if (cs.length != 5) throw new IllegalArgumentException();
        for (int i = 0; i < 5; i++) {
            if (i == 2) continue;
            if (cs[i] < '0' || cs[i] > '9') throw new IllegalArgumentException();
        }
        if (cs[2] != ':') throw new IllegalArgumentException();

        int h = (cs[0] - '0') * 10 + cs[1] - '0';
        int m = (cs[3] - '0') * 10 + cs[4] - '0';
        return new Horario(h, m);
    }

    public static Horario agora() {
        GregorianCalendar gc = new GregorianCalendar();
        return new Horario(gc.get(Calendar.HOUR_OF_DAY), gc.get(Calendar.MINUTE));
    }

    public Horario diferenca(Horario outro) {
        int difHoras = this.horas - outro.horas;
        int difMinutos = this.minutos - outro.minutos;
        while (difMinutos < 0) {
            difMinutos += 60;
            difHoras--;
        }
        while (difHoras < 0) {
            difHoras += 24;
        }
        return new Horario(difHoras, difMinutos);
    }

    @Override
    public String toString() {
        return ((horas < 10) ? "0" : "") + horas + ":" + ((minutos < 10) ? "0" : "") + minutos;
    }
}
