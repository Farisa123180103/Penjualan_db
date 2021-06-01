package com.example.penjualan.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.penjualan.entity.AppDatabase;
import com.example.penjualan.entity.DataPenjualan;

import java.util.List;

public class MainPresenter implements MainContact.presenter{
    private MainContact.view view;

    public MainPresenter(MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long>{

        private AppDatabase appDatabase;
        private DataPenjualan dataPenjualan;

        public InsertData(AppDatabase appDatabase, DataPenjualan dataPenjualan) {
            this.appDatabase = appDatabase;
            this.dataPenjualan = dataPenjualan;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataPenjualan);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }

    @Override
    public void insertData(String tgl, int kotor, int keluar, AppDatabase database) {
        final DataPenjualan dataPenjualan = new DataPenjualan();
        dataPenjualan.setTgl(tgl);
        dataPenjualan.setKotor(kotor);
        dataPenjualan.setKeluar(keluar);
        new InsertData(database, dataPenjualan).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataPenjualan> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void, Void, Integer> {

        private AppDatabase appDatabase;
        private DataPenjualan dataPenjualan;

        public EditData(AppDatabase appDatabase, DataPenjualan dataPenjualan) {
            this.appDatabase = appDatabase;
            this.dataPenjualan = dataPenjualan;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.dao().updateData(dataPenjualan);
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db","onPostExecute : "+integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String tgl, int kotor, int keluar, int id, AppDatabase database) {
        final DataPenjualan dataPenjualan = new DataPenjualan();
        dataPenjualan.setTgl(tgl);
        dataPenjualan.setKotor(kotor);
        dataPenjualan.setKeluar(keluar);
        dataPenjualan.setId(id);
        new EditData(database, dataPenjualan).execute();
    }

    class DeleteData extends AsyncTask<Void,Void,Long>{

        private AppDatabase appDatabase;
        private DataPenjualan dataPenjualan;

        public DeleteData(AppDatabase appDatabase, DataPenjualan dataPenjualan) {
            this.appDatabase = appDatabase;
            this.dataPenjualan = dataPenjualan;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataPenjualan);
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }

    @Override
    public void deleteData(DataPenjualan dataPenjualan, AppDatabase database) {
        new DeleteData(database,dataPenjualan).execute();
    }
}
