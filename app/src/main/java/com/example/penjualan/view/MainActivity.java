package com.example.penjualan.view;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penjualan.R;
import com.example.penjualan.entity.AppDatabase;
import com.example.penjualan.entity.DataPenjualan;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnSub;
    private RecyclerView recyclerView;
    private EditText etKotor, etTgl, etKeluar;

    private int id = 0;
    boolean edit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSub = findViewById(R.id.bt_submit);
        etTgl = findViewById(R.id.et_tgl);
        etKotor = findViewById(R.id.et_kotor);
        etKeluar = findViewById(R.id.et_keluar);
        recyclerView = findViewById(R.id.rc_data);

        appDatabase = AppDatabase.iniDb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);

    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil menghapus data", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etTgl.setText("");
        etKeluar.setText("");
        etKotor.setText("");
        btnSub.setText("Submit");
    }

    @Override
    public void getData(List<DataPenjualan> list) {
        mainAdapter = new MainAdapter(this,list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataPenjualan item) {
        etTgl.setText(item.getTgl());
        etKotor.setText(item.getKotor());
        etKeluar.setText(item.getKeluar());
        id = item.getId();
        edit = true;
        btnSub.setText("Update");
    }

    @Override
    public void deleteData(DataPenjualan item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        resetForm();;
                        mainPresenter.deleteData(item,appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    btnSub.
    @Override
    public void onClick(View view) {
        if (view == btnSub){
            if(etTgl.getText().toString().equals("")||etKeluar.getText().toString().equals("")||etKotor.getText().toString().equals("")){
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {
                if(!edit){
                    mainPresenter.insertData(etTgl.getText().toString(),Integer.parseInt(etKotor.getText().toString())
                            ,Integer.parseInt(etKeluar.getText().toString()),appDatabase);
                } else {
                    mainPresenter.editData(etTgl.getText().toString(),Integer.parseInt(etKotor.getText().toString())
                            ,Integer.parseInt(etKeluar.getText().toString()),id,appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}