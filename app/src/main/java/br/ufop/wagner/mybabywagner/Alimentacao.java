package br.ufop.wagner.mybabywagner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public class Alimentacao extends Atividades implements Parcelable, Serializable {
    String TipoAlimento;


    public Alimentacao(String dataDeInicio, String dataFinal, String horaInicial,   String anotacao, String TipoAlimento) {
        super(dataDeInicio, dataFinal, horaInicial,  anotacao,"Alimentação", R.drawable.comida);
        this.TipoAlimento = TipoAlimento;
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
        dest.writeString(TipoAlimento);
    }

    public String getTipoAlimento() {
        return TipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        TipoAlimento = tipoAlimento;
    }

    protected Alimentacao(Parcel in) {
        super(in.readString(),in.readString(),in.readString(),in.readString(),in.readString(),in.readInt());
        TipoAlimento = in.readString();
    }


    public static final Creator<Alimentacao> CREATOR = new Creator<Alimentacao>() {
        @Override
        public Alimentacao createFromParcel(Parcel in) {
            return new Alimentacao(in);
        }

        @Override
        public Alimentacao[] newArray(int size) {
            return new Alimentacao[size];
        }
    };

}
