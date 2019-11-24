package com.example.kuy.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kuy.R;
import com.example.kuy.ui.home.CustomAdapterFilm;
import com.example.kuy.ui.home.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaftarFilm extends AppCompatActivity {

    private String[] films = {"Avenger","Spiderman","Joker","Danur"};
    private ArrayList<String> data;

    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private AdapterFilm adapter;
    private ArrayList<Film> heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_film);
        adapter = new AdapterFilm(this);
        ListView listView = findViewById(R.id.list_film);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, dataName);
        listView.setAdapter(adapter);
        prepare();
        addItem();

        data = new ArrayList<>() ;
        getData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String getName = data.get(position);
                Intent sendData = new Intent(DaftarFilm.this, Deskripsi.class);
                sendData.putExtra("MyName",getName);
                startActivity(sendData);
            }
        });
    }
    private void getData() {
        Collections.addAll(data, films);
    }

    private void addItem() {
        heroes = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Film hero = new Film();
            hero.setPhoto(dataPhoto.getResourceId(i, -1));
            hero.setName(dataName[i]);
            hero.setDescription(dataDescription[i]);
            heroes.add(hero);
        }
        adapter.setHeroes(heroes);
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.desc_film);
        dataPhoto = getResources().obtainTypedArray(R.array.gambar_film);
    }
}
