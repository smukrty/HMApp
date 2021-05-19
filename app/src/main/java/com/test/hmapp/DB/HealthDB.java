package com.test.hmapp.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class HealthDB {
    private static SQLiteDatabase db=null;//建立SQLiteDatabase物件

    private final static String TABLE_NAME="TableHealth"; //建立欄位名稱
    private final static String _ID="_id";//編排編號
    private final static String DATE="date";//新增日期
    private final static String HIGH="high";//收縮壓
    private final static String LOW="low";//舒張壓
    private final static String BUMP="bump";//脈搏數

    //    建立表格
    private final static String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY,"
            + DATE +" TEXT,"+HIGH+" INTEGER,"+LOW+" INTEGER ,"+BUMP+" INTEGER"+" )";
    private Context context;

    //HealthDB的建構式
    public HealthDB(Context context){
        this.context=context;
    }

    //建立open()方法，資料庫存執行開啟資料庫，尚未存在則建立資料庫
    public void open() throws SQLException {
        try {
            //建立資料庫並指定權限
            db=context.openOrCreateDatabase("HealthDB.db",Context.MODE_PRIVATE,null);
            //建立表格
            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context,"HealthDB.db 已建立", Toast.LENGTH_LONG).show();
            Log.d("Debug","HealthDB.db 已建立");
        }
    }

    //建立新增、修改(更新)、刪除，資料操作
    //execSQL完整輸入SQL語法實現，資料操作

    //建立方法append()
    public void append(String date,int high,int low,int bump){
        String insert_text="INSERT INTO "+TABLE_NAME+"( "+DATE+","+HIGH+","+LOW+","+BUMP+") " + "values ('"+date+"',"+high+","+low+","+bump+")";
        db.execSQL(insert_text);
    }

    //建立方法update()
    public void update(int name,int price,long id){
        String update_text="UPDATE "+TABLE_NAME+" SET "+HIGH+"="+name+","+LOW+"="+price+" WHERE "+_ID+"="+id;
        db.execSQL(update_text);
    }

    //建立方法delete()
    public void delete(long id){
        String delete_text="DELETE FROM "+TABLE_NAME+" WHERE "+_ID+"="+id;
        db.execSQL(delete_text);
    }

    //建立查詢方法select()，查詢單筆資料
    //rawQuery完整輸入SQL語法實現資料查詢
    public Cursor select(long id){
        String select_text="SELECT * FROM "+TABLE_NAME+" WHERE "+_ID+"="+id;
        Cursor cursor=db.rawQuery(select_text,null);
        return cursor;
    }

    //建立查詢方法select_all()，查詢所有資料
    //rawQuery完整輸入SQL語法實現資料查詢
    public Cursor select_all(){
        String select_text="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=db.rawQuery(select_text,null);
        return cursor;
    }

}
