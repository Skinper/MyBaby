package br.ufop.wagner.mybabywagner;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;


public class Baby implements Parcelable, Serializable {

    private String name = "";
    private String sex = "";
    private String date = "";
    private ArrayList<Peso> peso = new ArrayList<>();


    public ArrayList<Peso> getPeso() {
        return peso;
    }

    public void setPeso(ArrayList<Peso> peso) {
        this.peso = peso;
    }

    public Baby(String name, String sex, String date) {

        this.name = name;
        this.sex = sex;
        this.date = date;

    }
    public Baby (){

    }

    protected Baby(Parcel in) {

        name = in.readString();
        sex = in.readString();
        date =   in.readString();
        peso = in.readArrayList(null);

    }


    public static final Creator<Baby> CREATOR = new Creator<Baby>() {
        @Override
        public Baby createFromParcel(Parcel in) {
            return new Baby(in);
        }

        @Override
        public Baby[] newArray(int size) {
            return new Baby[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(sex);
        dest.writeSerializable(date);
        dest.writeList(peso);

    }
}


