package br.ufop.wagner.mybabywagner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public class TrocarFralda extends Atividades implements Serializable,Parcelable {

    public String getFralda() {
        return fralda;
    }

    public void setFralda(String fralda) {
        this.fralda = fralda;
    }

    private String fralda;


    public TrocarFralda(String dataDeInicio, String dataFinal, String horaInicial,  String anotacao, String tipo, String fralda) {
        super(dataDeInicio, dataFinal, horaInicial,  anotacao,"Fralda", R.drawable.fraldas);
        this.fralda = fralda;
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
        dest.writeString(fralda);


    }


    protected TrocarFralda(Parcel in) {
        super(in.readString(),in.readString(),in.readString(), in.readString(),in.readString(),in.readInt());
        this.fralda = in.readString();
    }


    public static final Creator<TrocarFralda> CREATOR = new Creator<TrocarFralda>() {
        @Override
        public TrocarFralda createFromParcel(Parcel in) {
            return new TrocarFralda(in);
        }

        @Override
        public TrocarFralda[] newArray(int size) {
            return new TrocarFralda[size];
        }
    };
}
