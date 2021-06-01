package com.example.penjualan.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "penjualan_db")
public class DataPenjualan {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id ;

    @ColumnInfo(name = "tgl")
    private String tgl ;

    @ColumnInfo(name = "kotor")
    private int kotor ;

    @ColumnInfo(name = "keluar")
    private int keluar ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getKotor() {
        return kotor;
    }

    public void setKotor(int kotor) {
        this.kotor = kotor;
    }

    public int getKeluar() {
        return keluar;
    }

    public void setKeluar(int keluar) {
        this.keluar = keluar;
    }
}
