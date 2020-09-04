package com.knighenko.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.knighenko.myapplication.adapter.AdvAdapter;
import com.knighenko.myapplication.entity.Advertisement;
import com.knighenko.myapplication.model.ConnectServer;
import com.knighenko.myapplication.model.JsonToObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Advertisement> advertisements;
    private RecyclerView titleList;
    private int listIndex;
    private RecyclerView listAdvRecyclerView;
    private AdvAdapter advAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void initRecyclerView() {
        listAdvRecyclerView = findViewById(R.id.listAdvRecyclerView);
        listAdvRecyclerView.setHasFixedSize(true);
        listAdvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        advAdapter = new AdvAdapter();
        advAdapter.setListAdv(advertisements);
        listAdvRecyclerView.setAdapter(advAdapter);

    }

    public void onClickBtnSend(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectServer connectServer = null;
                try {
                    connectServer = new ConnectServer("91.235.129.33", 8080);
                    advertisements = new JsonToObject(connectServer.readJsonStrig()).getAdvertisements();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        TextView textView = findViewById(R.id.textView);
                        textView.setText(advertisements.get(0).getTitle());
                        initRecyclerView();

                    }
                });


            }
        }).start();
    }


    public void onClickBtnNext(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (advertisements.size() - 1 != listIndex) listIndex++;
                        TextView textView = findViewById(R.id.textView);
                        textView.setText(advertisements.get(listIndex).getTitle());


                    }
                });
            }
        }).start();
    }

    public void onClickBtnPrev(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (listIndex != 0) listIndex--;
                        TextView textView = findViewById(R.id.textView);
                        textView.setText(advertisements.get(listIndex).getTitle());


                    }
                });
            }
        }).start();
    }


}