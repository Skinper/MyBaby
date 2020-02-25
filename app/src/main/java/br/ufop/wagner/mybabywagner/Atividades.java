package br.ufop.wagner.mybabywagner;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Chronometer;
import android.widget.ImageView;

import java.io.Serializable;
import java.sql.Time;

public class Atividades implements Serializable{

private String dataDeInicio = null;
private String dataFinal = null;
private String horaInicial;
private String horaFinal;
private String anotacao;
private String tipo;
private int imageView;
/*private String infoAdicionais;*/

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Atividades(String dataDeInicio, String horaInicial) {
        this.dataDeInicio = dataDeInicio;
        this.horaInicial = horaInicial;
    }
/*
    protected Atividades(Parcel in) {
        dataDeInicio = in.readString();
        dataFinal = in.readString();
        horaInicial = in.readString();
        horaFinal = in.readString();
        anotacao = in.readString();
        tipo = in.readString();
        imageView = in.readInt();

    }*/

/*
    public static final Creator<Atividades> CREATOR = new Creator<Atividades>() {
        @Override
        public Atividades createFromParcel(Parcel in) {
            return new Atividades(in);
        }

        @Override
        public Atividades[] newArray(int size) {
            return new Atividades[size];
        }
    };
*/

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public Atividades(String dataDeInicio, String dataFinal, String horaInicial,   String anotacao, String tipo, int iv) {
        this.dataDeInicio = dataDeInicio;
        this.dataFinal = dataFinal;
        this.horaInicial = horaInicial;
        this.anotacao = anotacao;
        this.tipo = tipo;
        this.imageView = iv;

    }

    public String getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(String dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }




/*    @Override
    public int describeContents() {
        return 0;
    }

 */

/*    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dataDeInicio);
        dest.writeString(dataFinal);
        dest.writeString(horaInicial);
        dest.writeString(horaFinal);
        dest.writeString(anotacao);
        dest.writeString(tipo);
        dest.writeInt(imageView);

    }*/
}
