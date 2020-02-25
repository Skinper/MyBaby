package br.ufop.wagner.mybabywagner;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressLint("ParcelCreator")
public class Banho extends Atividades  implements Serializable, Parcelable{
    public String getProdutosUsados() {
        return ProdutosUsados;
    }

    public void setProdutosUsados(String produtosUsados) {
        ProdutosUsados = produtosUsados;
    }

    private String ProdutosUsados;
    private String duracao;
    private String horaFinal;

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Banho(String dataDeInicio, String dataFinal, String horaInicial, String anotacao, String produtosUsados, String duracao, String horaFinal) {
        super(dataDeInicio, dataFinal, horaInicial,  anotacao,"Banho", R.drawable.banho);
        ProdutosUsados = produtosUsados;
        this.duracao = duracao;
        this.horaFinal = horaFinal;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getDataDeInicio());
        dest.writeString(getDataFinal());
        dest.writeString(getHoraInicial());
        dest.writeString(getAnotacao());
        dest.writeString(getTipo());
        dest.writeInt(getImageView());
        dest.writeString(getProdutosUsados());
        dest.writeString(getHoraFinal());
    }


    protected Banho(Parcel in) {
        super(in.readString(),in.readString(), in.readString(),in.readString(),in.readString(),in.readInt());
        this.ProdutosUsados = in.readString();
        this.horaFinal = in.readString();
    }


    public static final Creator<Banho> CREATOR = new Creator<Banho>() {
        @Override
        public Banho createFromParcel(Parcel in) {
            return new Banho(in);
        }

        @Override
        public Banho[] newArray(int size) {
            return new Banho [size];
        }
    };
}
