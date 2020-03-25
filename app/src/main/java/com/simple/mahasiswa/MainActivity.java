package com.simple.mahasiswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simple.mahasiswa.adapter.Adapter_Mahasiswa;
import com.simple.mahasiswa.model.Mahasiswa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference database;
    private RecyclerView recycleview;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Mahasiswa> arrayList_mahasiswa;


    private Button btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleview = findViewById(R.id.recycleview_mahasiswa);
        recycleview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManager);
        btn_create = findViewById(R.id.btn_create);

        database = FirebaseDatabase.getInstance().getReference();

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Create_Mahasiswa.class);
                startActivity(intent);
            }
        });

        database.child("Mahasiswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList_mahasiswa = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Mahasiswa mahasiswa = noteDataSnapshot.getValue(Mahasiswa.class);
                    mahasiswa.setKey(noteDataSnapshot.getKey());
                    arrayList_mahasiswa.add(mahasiswa);
                }
                adapter = new Adapter_Mahasiswa(arrayList_mahasiswa, MainActivity.this);
                recycleview.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, MainActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
