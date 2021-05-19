package com.test.hmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.test.hmapp.GridViwe.GridAdapter;

public class MainActivity extends AppCompatActivity {
    private GridView gvList;
    private String [] text_item={"血壓","血糖","定時","Exit"};
    private int[] img_item={R.drawable.pressure,R.drawable.sugar,R.drawable.alarm,R.drawable.exit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridAdapter adapter = new GridAdapter (MainActivity.this, text_item, img_item);
        gvList=(GridView)findViewById(R.id.grid);
        gvList.setAdapter(adapter);
        gvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "你選取了" + text_item[+position], Toast.LENGTH_SHORT).show();
            }
        });
    }

}