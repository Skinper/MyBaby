package br.ufop.wagner.mybabywagner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Calendar;

@SuppressLint("ParcelCreator")
public class Medicamento extends Atividades implements Serializable, Parcelable {
    String tipoDeMedicamento;
    int frequency;
    String horaNotificacao;
    String dataNotificacao;
    private Calendar calendario;


    public Medicamento(String dataDeInicio, String dataFinal, String horaInicial,  String anotacao, String tipoDeMedicamento, int frequency, Calendar calendario) {
        super(dataDeInicio, dataFinal, horaInicial,  anotacao,"Medicamento", R.drawable.medicamentos);
        this.tipoDeMedicamento = tipoDeMedicamento;
        this.frequency = frequency;
        this.calendario = calendario;
    }

    public Calendar getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendar calendario) {
        this.calendario = calendario;
    }

    public String getHoraNotificacao() {
        return horaNotificacao;
    }

    public void setHoraNotificacao(String horaNotificacao) {
        this.horaNotificacao = horaNotificacao;
    }

    public String getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(String dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Medicamento(String dataDeInicio, String dataFinal, String horaInicial, String anotacao, String tipoDeMedicamento, int frequency, String horaNotificacao, String dataNotificacao, Calendar calendario) {
        super(dataDeInicio, dataFinal, horaInicial,  anotacao,"Medicamento", R.drawable.medicamentos);
        this.tipoDeMedicamento = tipoDeMedicamento;
        this.frequency = frequency;
        this.horaNotificacao = horaNotificacao;
        this.dataNotificacao = dataNotificacao;
        this.calendario = calendario;

    }

    public String getTipoDeMedicamento() {
        return tipoDeMedicamento;
    }

    public void setTipoDeMedicamento(String tipoDeMedicamento) {
        this.tipoDeMedicamento = tipoDeMedicamento;
    }

    @Override

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getDataDeInicio());
        dest.writeString(getDataFinal());
        dest.writeString(getHoraInicial());
        dest.writeString(getAnotacao());
        dest.writeString(getTipo());
        dest.writeInt(getImageView());
        dest.writeString(tipoDeMedicamento);
        dest.writeInt(frequency);


    }


    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    protected Medicamento(Parcel in) {
        super(in.readString(),in.readString(), in.readString(),in.readString(),in.readString(),in.readInt());
        tipoDeMedicamento = in.readString();
        frequency = in.readInt ();
    }


    public static final Creator<Medicamento> CREATOR = new Creator<Medicamento>() {
        @Override
        public Medicamento createFromParcel(Parcel in) {
            return new Medicamento(in);
        }

        @Override
        public Medicamento[] newArray(int size) {
            return new Medicamento [size];
        }
    };
}
