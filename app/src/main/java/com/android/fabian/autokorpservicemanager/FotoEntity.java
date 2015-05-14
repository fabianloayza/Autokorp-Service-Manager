package com.android.fabian.autokorpservicemanager;

/**
 * Created by Faby on 29/12/14.
 */
public class FotoEntity {
    private int id_foto;
    private int id_automovil;
    private byte[] foto;

    public int getId_foto() {
        return id_foto;
    }

    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }

    public int getId_automovil() {
        return id_automovil;
    }

    public void setId_automovil(int id_automovil) {
        this.id_automovil = id_automovil;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
