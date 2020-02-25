package br.ufop.wagner.mybabywagner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public class Sono extends Atividades implements Serializable, Parcelable {
     String duracao;
     String horaFinal;

    public Sono(String dataDeInicio, String dataFinal, String horaInicial, String anotacao,String duracao, String horaFinal) {
        super(dataDeInicio, dataFinal, horaInicial,   anotacao,"Sono", R.drawable.dormindo);
        this.duracao=duracao;
        this.horaFinal =horaFinal;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
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
        dest.writeString(duracao);
        dest.writeString(horaFinal);


    }


    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    protected Sono(Parcel in) {
        super(in.readString(),in.readString(),in.readString(), in.readString(),in.readString() ,in.readInt());
         duracao = in.readString();
         horaFinal = in.readString();


    }


    public static final Creator<Sono> CREATOR = new Creator<Sono>() {
        @Override
        public Sono createFromParcel(Parcel in) {
            return new Sono(in);
        }

        @Override
        public Sono[] newArray(int size) {
            return new Sono [size];
        }
    };


}
