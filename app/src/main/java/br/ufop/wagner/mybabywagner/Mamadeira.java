package br.ufop.wagner.mybabywagner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public class Mamadeira extends Atividades implements Serializable, Parcelable{

    private String tipoDeLeite;
    private String volume;


    public String getTipoDeLeite() {
        return tipoDeLeite;
    }

    public void setTipoDeLeite(String tipoDeLeite) {
        this.tipoDeLeite = tipoDeLeite;
    }

    public Mamadeira(String dataDeInicio, String dataFinal, String horaInicial,   String tipoDeLeite , String anotacao,String volume) {
        super(dataDeInicio, dataFinal, horaInicial,  anotacao, "Mamadeira", R.drawable.mamadeira);
        this.tipoDeLeite = tipoDeLeite;
        this.volume = volume;
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
        dest.writeString(tipoDeLeite);
        dest.writeString(volume);

    }


    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    protected Mamadeira(Parcel in) {
        super(in.readString(), in.readString(),in.readString(),in.readString(),in.readString(),in.readInt());
        tipoDeLeite = in.readString();
        volume = in.readString();

    }


    public static final Creator<Mamadeira> CREATOR = new Creator<Mamadeira>() {
        @Override
        public Mamadeira createFromParcel(Parcel in) {
            return new Mamadeira(in);
        }

        @Override
        public Mamadeira[] newArray(int size) {
            return new Mamadeira [size];
        }
    };

}
