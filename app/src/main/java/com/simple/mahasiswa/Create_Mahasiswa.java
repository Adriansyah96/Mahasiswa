package com.simple.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simple.mahasiswa.model.Mahasiswa;

public class Create_Mahasiswa extends AppCompatActivity {
    private EditText nim,nama,kelas;
    private DatabaseReference database;
    private Button btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__mahasiswa);

        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        btn_simpan = findViewById(R.id.btn_simpan);

        database = FirebaseDatabase.getInstance().getReference();

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });

    }

    private void simpan() {
        String Snim = nim.getText().toString();
        String Snama = nama.getText().toString();
        String Skelas = kelas.getText().toString();

        if (Snim.isEmpty() || Snama.isEmpty() || Skelas.isEmpty()){
            Toast.makeText(Create_Mahasiswa.this, "Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
        } else{
            submit_makanan(new Mahasiswa(nim.getText().toString(), nama.getText().toString(), kelas.getText().toString()));
        }
    }

    private void submit_makanan(Mahasiswa mahasiswa) {
        database.child("Mahasiswa").push().setValue(mahasiswa).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nim.setText("");
                nama.setText("");
                kelas.setText("");

                Snackbar.make(findViewById(R.id.btn_simpan), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, Create_Mahasiswa.class);
    }


}
