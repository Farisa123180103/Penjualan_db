package com.example.penjualan.view;

import android.view.View;

import com.example.penjualan.entity.AppDatabase;
import com.example.penjualan.entity.DataPenjualan;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataPenjualan> list);
        void editData(DataPenjualan item);
        void deleteData(DataPenjualan item);
    }

    interface presenter{
        void insertData(String tgl, int kotor, int keluar, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String tgl, int kotor, int keluar, int id, AppDatabase database);
        void deleteData(DataPenjualan dataPenjualan, AppDatabase database);
    }
}
