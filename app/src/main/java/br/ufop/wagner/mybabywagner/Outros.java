package br.ufop.wagner.mybabywagner;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Outros extends Atividades implements Serializable, Parcelable {
    public Outros(String dataDeInicio, String dataFinal, String horaInicial,   String anotacao, String tipo) {
        super(dataDeInicio, dataFinal, horaInicial,  anotacao, tipo, R.drawable.outros32);
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


    }


    protected Outros(Parcel in) {
        super(in.readString(), in.readString(),  in.readString(), in.readString(), in.readString(), in.readInt());

    }


    public static final Parcelable.Creator<Outros> CREATOR = new Parcelable.Creator<Outros>() {
        @Override
        public Outros createFromParcel(Parcel in) {
            return new Outros(in);
        }

        @Override
        public Outros[] newArray(int size) {
            return new Outros[size];
        }
    };

}