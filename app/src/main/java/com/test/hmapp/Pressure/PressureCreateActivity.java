package com.test.hmapp.Pressure;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.test.hmapp.DB.HealthDB;
import com.test.hmapp.R;

public class PressureCreateActivity extends AppCompatActivity {
    private HealthDB db =new HealthDB(PressureCreateActivity.this);
    private TextInputEditText edtHigh,edtLow,edtBump;
    private Button btnAppend;
    private int month;
    private int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pressure_create);
        findViews();
        //  獲得目前時間
        gettme();
    }

    private void findViews() {
        edtHigh=(TextInputEditText)findViewById(R.id.edtHigh);
        edtLow=(TextInputEditText)findViewById(R.id.edtLow);
        edtBump=(TextInputEditText)findViewById(R.id.edtBump);
        btnAppend=(Button)findViewById(R.id.btnAppend);

        btnAppend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用HealthDB類別的方法，將資料新增至資料庫
                db.append(num_chg_ten(month+1)+"/"+num_chg_ten(date),
                        Integer.parseInt(edtHigh.getText().toString()),
                        Integer.parseInt(edtLow.getText().toString()),
                        Integer.parseInt(edtBump.getText().toString()));
                finish();//新增後，結束此頁面
            }
        });
    }

    private void gettme() {
        Calendar calendar=Calendar.getInstance();//得到Calendar物件
        month = calendar.get(Calendar.MONTH);//透過指定的參數獲得系統當下的時間
        date = calendar.get(Calendar.DATE);
        int hour=calendar.get(Calendar.HOUR);
        int min=calendar.get(Calendar.MINUTE);
        //   標題上顯示時間
        setTitle(getTitle().toString()+"-"
                +(month +1)+"/"+ date +"  "+num_chg_ten(hour)+":"+num_chg_ten(min));
    }

    //此方法使尚未有十位數的數字，前面加入"0"的符號
    public String num_chg_ten(int num){
        String text = null;
        //   個位數字前面增加"0"
        if(num<10){
            text="0"+num;
        }
        else{
            text=String.valueOf(num);
        }
        return text;
    }



}