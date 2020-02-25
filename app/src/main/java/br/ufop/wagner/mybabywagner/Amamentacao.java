package br.ufop.wagner.mybabywagner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Chronometer;

import java.io.Serializable;
import java.sql.Time;

@SuppressLint("ParcelCreator")
public class Amamentacao extends Atividades implements Parcelable, Serializable {

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;

    }

    private String horaFinal;
    private String duracao;

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Amamentacao(String dataDeInicio, String dataFinal, String horaInicial, String horaFinal, String anotacao, String duracao) {
        super(dataDeInicio, dataFinal, horaInicial,  anotacao,"Amamentação", R.drawable.breastfeeding);
        this.horaFinal = horaFinal;
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
        dest.writeString(getHoraFinal());
        dest.writeString(getDuracao());

    }


    protected Amamentacao(Parcel in) {
        super(in.readString(),in.readString(), in.readString(),in.readString(),in.readString(),in.readInt());
        this.horaFinal = in.readString();
        this.duracao = in.readString();

    }


    public static final Creator<Amamentacao> CREATOR = new Creator<Amamentacao>() {
        @Override
        public Amamentacao createFromParcel(Parcel in) {
            return new Amamentacao(in);
        }

        @Override
        public Amamentacao[] newArray(int size) {
            return new Amamentacao [size];
        }
    };

}
