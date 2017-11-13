package com.example.ashwin.sqlitetry3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

/**
 * Created by Ashwin on 11/9/2017.
 */

public class MyView {

    Helper help;

    public MyView(Context context) {
        help = new Helper(context);
    }

    public long add(String name, String password){
        SQLiteDatabase db = help.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(help.NAME, name);
        cv.put(help.PASSWORD, password);
        long id =db.insert(help.TABLENAME, null, cv);
        return id;
    }

    public String view(){
        SQLiteDatabase db = help.getWritableDatabase();
        String[] columns = {help.UID, help.NAME, help.PASSWORD};
        Cursor cursor =db.query(help.TABLENAME, columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        { int cid =cursor.getInt(cursor.getColumnIndex(help.UID));
            String name =cursor.getString(cursor.getColumnIndex(help.NAME));
            String  password =cursor.getString(cursor.getColumnIndex(help.PASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }
}

class Helper extends SQLiteOpenHelper
{
    public final static String DBNAME = "LavDb";
    public static final String TABLENAME = "LavTable";
    public static final int DBversion = 1;
    public static final String UID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    private static final String CREATETABLE = "CREATE TABLE "+TABLENAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ PASSWORD+" VARCHAR(225));";
    private static final String DROPTABLE ="DROP TABLE IF EXISTS "+TABLENAME;
    private Context context;

    public Helper(Context context) {
        super(context, DBNAME, null, DBversion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATETABLE);
        } catch (Exception e) {
            Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Message.message(context,"OnUpgrade");
            db.execSQL(DROPTABLE);
            onCreate(db);
        }catch (Exception e) {
            Message.message(context,""+e);
        }
    }
}

class Message{
    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
