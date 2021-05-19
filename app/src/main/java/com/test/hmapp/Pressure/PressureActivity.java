package com.test.hmapp.Pressure;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.hmapp.DB.HealthDB;
import com.test.hmapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PressureActivity extends AppCompatActivity {
    private HealthDB db;
    private Cursor cursor;
    private ListView pressureList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pressure_list);
        setTitle("血壓管理");
        //尋找各個元件的ID
        findViews();
        
        //        建立HealthDB物件db
        db=new HealthDB(PressureActivity.this);
        //        查詢目前資料庫擁有的資料
        db.open();
        cursor=db.select_all();
        //        更新ListView重新顯示資料
        UpdateListView(cursor);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //尋找各個元件的ID
        findViews();
        //建立FruitDB物件db
        db=new HealthDB(PressureActivity.this);
        //查詢目前資料庫擁有的資料
        db.open();

        //開啟app時查詢資料庫所有資料並更新至ListView
        cursor=db.select_all();
        //更新ListView重新顯示資料
        UpdateListView(cursor);
    }



    private void findViews() {
        pressureList=(ListView)findViewById(R.id.pressureList);
        pressureList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            //長按選項後，詢問是否刪除此選項
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                try {
                    cursor.moveToPosition(position);
                    final int _id=Integer.parseInt(cursor.getString(0));
                    String date=cursor.getString(1);
                    String high=cursor.getString(2);
                    String low=cursor.getString(3);
                    //AlertDialog 跳出訊息與選項
                    new AlertDialog.Builder(PressureActivity.this)
                            .setMessage("確定刪除\nID="+id+"\n日期"+date+"\n收縮壓=" +high+"\n舒張壓="+low)
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //執行刪除動作
                                    db.delete(cursor.getInt(0));
                                    //更新顯示畫面
                                    UpdateListView(cursor=db.select_all());
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }catch (Exception e){
                    Toast.makeText(PressureActivity.this,"刪除失敗", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        //偵聽使用者是否觸發FloatActionBar
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PressureActivity.this,PressureCreateActivity.class);
                startActivity(intent);
            }
        });
    }

    //UpdateListView()方法更新選單
    private void UpdateListView(Cursor pressurecursor) {
        MyAdapter adapter =new MyAdapter(pressurecursor);
        pressureList.setAdapter(adapter);
        cursor=pressurecursor;
    }

    //實作MyAdapter繼承BaseAdapter類別
    public class MyAdapter extends BaseAdapter {
        private Cursor cursor;

        public MyAdapter(Cursor cursor){
            this.cursor=cursor;
        }

        @Override
        public int getCount() {
            return cursor.getCount();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            cursor.moveToPosition(position);
            return cursor.getInt(0);
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View getview=view;
            cursor.moveToPosition(position);
            getview=getLayoutInflater().inflate(R.layout.pressure_view,null);
            TextView txtDate=(TextView)getview.findViewById(R.id.txtDate);
            txtDate.setText(cursor.getString(1));
            TextView txtHigh=(TextView)getview.findViewById(R.id.txtHigh);
            txtHigh.setText("收縮壓"+cursor.getString(2));
            TextView txtLow=(TextView)getview.findViewById(R.id.txtLow);
            txtLow.setText("舒張壓"+String.valueOf(cursor.getString(3)));
            return getview;
        }
    }

}

