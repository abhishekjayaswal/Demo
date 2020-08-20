package com.anjali.flightbooking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import com.anjali.flightbooking.model.User;

public class Database extends SQLiteOpenHelper {

    private static Database database;
    private static final String DATABASE_NAME = "flightBooking.db";
    private static final int DATABASE_VERSION= 1;
    private final Context context;
    private static  final String TABLE_USER ="user_tbl";
    private  static  final String COL_FIRST_NAME = "first_name";
    private static  final String COL_MIDDLE_NAME = "middle_name";
    private static  final String COL_LAST_NAME= "last_name";
    private static  final String COL_EMAIL_ID= "email_id";
    private static  final String COL_USER_NAME = "user_name";
    private static  final String COL_PASSWORD ="password";
    private static  final String COL_MOBILE_NUMBER = "mobile_number";
    private SQLiteDatabase sqLiteDatabase;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.sqLiteDatabase=getWritableDatabase();
    }

    public static synchronized Database getInstance(Context context)
    {
         if(database == null)
         {
             database=new Database(context.getApplicationContext());
         }
         return  database;
    }

    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQueryUser = "CREATE TABLE "+ TABLE_USER  + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
               COL_FIRST_NAME +" TEXT, " +
                COL_MIDDLE_NAME +" TEXT," +
                COL_LAST_NAME+" TEXT," +
                COL_EMAIL_ID+" TEXT," +
                COL_USER_NAME+" TEXT," +
                COL_PASSWORD+" TEXT," +
                COL_MOBILE_NUMBER+" TEXT)" ;
        sqLiteDatabase.execSQL(sqlQueryUser);

    }

    public boolean addUser(User user)
    {
        if(user != null)
        {
            ContentValues values = new ContentValues();
            values.put(COL_FIRST_NAME, user.getFirstName());
            values.put(COL_MIDDLE_NAME,user.getMiddleName());
            values.put(COL_LAST_NAME, user.getLastName());
            values.put(COL_USER_NAME,user.getUserName());
            values.put(COL_EMAIL_ID, user.getEmailId());
            values.put(COL_PASSWORD, user.getPassword());
            values.put(COL_MOBILE_NUMBER, user.getNumber());
            long result = sqLiteDatabase .insert( TABLE_USER ,null, values);
            if(result != -1)
            {
                return true;
            }else {
                return false;
            }

        }else {
            return false;
        }
    }

    public User getUserDetails(String emailid, String password)
    {
        User user=null;
      String[] colomuns= {COL_EMAIL_ID , COL_PASSWORD};
      String query= "select * from " + TABLE_USER + " Where " + COL_EMAIL_ID + "=? and " + COL_PASSWORD + "=?";
      Cursor cr= sqLiteDatabase.rawQuery(query , new String[]{emailid,password});
      if(cr != null)
      {
          if(cr.moveToFirst()){
              do{

                  user = new User();
                  user.setFirstName(cr.getString(cr.getColumnIndexOrThrow(COL_FIRST_NAME)));
                  user.setMiddleName(cr.getString(cr.getColumnIndexOrThrow(COL_MIDDLE_NAME)));
                  user.setLastName(cr.getString(cr.getColumnIndexOrThrow(COL_LAST_NAME)));
                  user.setUserName(cr.getString(cr.getColumnIndexOrThrow(COL_USER_NAME)));
                  user.setPassword(cr.getString(cr.getColumnIndexOrThrow(COL_PASSWORD)));
                  user.setEmailId(cr.getString(cr.getColumnIndexOrThrow(COL_EMAIL_ID)));
                  user.setNumber(cr.getString(cr.getColumnIndexOrThrow(COL_MOBILE_NUMBER)));

              }while (cr.moveToNext());
          }

      }
      return user;
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}
