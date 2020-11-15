package com.stmikbanisaleh.latihanrecyclerview;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Dosen implements Cloneable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nama")
    private String nama;

    @ColumnInfo(name = "kompetensi")
    private String kompetensi;

    @ColumnInfo(name = "status")
    private boolean status;

    public Dosen() {
    }

    public Dosen(int id, String nama, String kompetensi, boolean status) {
        this.id = id;
        this.nama = nama;
        this.kompetensi = kompetensi;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(String kompetensi) {
        this.kompetensi = kompetensi;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Dosen clone() throws CloneNotSupportedException {
        return (Dosen) super.clone();
    }
}
