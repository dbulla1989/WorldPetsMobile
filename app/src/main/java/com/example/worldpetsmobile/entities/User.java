package com.example.worldpetsmobile.entities;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("userId")
    private int userId;

    @SerializedName("identificationType")
    private String tipoIdentificacion;

    @SerializedName("identification")
    private String identificacion;

    @SerializedName("names")
    private String nombres;

    @SerializedName("surnames")
    private String apellidos;

    @SerializedName("email")
    private String email;

    @SerializedName("cellPhone")
    private String telefono;

    public User() {}

    protected User(Parcel in) {
        userId = in.readInt();
        tipoIdentificacion = in.readString();
        identificacion = in.readString();
        nombres = in.readString();
        apellidos = in.readString();
        email = in.readString();
        telefono = in.readString();
    }

    public int getId() { return userId; }
    public void setId(int userId) { this.userId = userId; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public void setTipoIdentificacion(String identificationType) { this.tipoIdentificacion = identificationType ;}
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identification) { this.identificacion = identification ;}
    public String getNombre() { return nombres; }
    public void setNombre(String nombre) { this.nombres = nombre; }
    public String getApellido() { return apellidos; }
    public void setApellido(String apellido) { this.apellidos = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String cellPhone) { this.telefono = cellPhone; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(tipoIdentificacion);
        dest.writeString(identificacion);
        dest.writeString(nombres);
        dest.writeString(apellidos);
        dest.writeString(email);
        dest.writeString(telefono);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

