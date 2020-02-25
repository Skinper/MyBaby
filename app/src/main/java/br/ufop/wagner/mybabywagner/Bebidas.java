package br.ufop.wagner.mybabywagner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public class Bebidas extends Atividades implements Serializable, Parcelable {
        String TipoDeBebida;
        String volume;

    public Bebidas(String dataDeInicio, String dataFinal, String horaInicial,   String anotacao, String tipoDeBebida,String volume) {
        super(dataDeInicio, dataFinal, horaInicial,  anotacao,"Bebidas", R.drawable.bebida);
        TipoDeBebida = tipoDeBebida;
        this.volume = volume;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTipoDeBebida() {
        return TipoDeBebida;
    }

    public void setTipoDeBebida(String tipoDeBebida) {
        TipoDeBebida = tipoDeBebida;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getDataDeInicio());
        dest.writeString(getDataFinal());
        dest.writeString(getHoraInicial());

        dest.writeString(getAnotacao());
        dest.writeString(getTipo());
        dest.writeInt(getImageView());
        dest.writeString(getTipoDeBebida());
        dest.writeString(volume);

    }


    protected Bebidas(Parcel in) {
        super(in.readString(),in.readString(), in.readString(),in.readString(),in.readString(),in.readInt());
        this.TipoDeBebida = in.readString();
        this.volume = in.readString();
    }


    public static final Creator<Bebidas> CREATOR = new Creator<Bebidas>() {
        @Override
        public Bebidas createFromParcel(Parcel in) {
            return new Bebidas(in);
        }

        @Override
        public Bebidas[] newArray(int size) {
            return new Bebidas [size];
        }
    };
}
