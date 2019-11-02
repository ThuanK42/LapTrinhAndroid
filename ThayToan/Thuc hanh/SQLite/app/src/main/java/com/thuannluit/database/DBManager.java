package com.thuannluit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.thuannluit.model.Classroom;
import com.thuannluit.model.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static SQLiteDatabase mysql;

    public static final String DATABASE_NAME = "QLSINHVIEN.sqlite";
    private static final String DATABASE_PATH = "/data/data/com.thuannluit.learnsqlite/databases/";


    private static final String tbllop = "tbllop";
    private static final String malop = "malop";
    private static final String tenlop = "tenlop";
    private static final String siso = "siso";

    private static final String tblsinhvien = "tblsinhvien";
    private static final String masv = "masv";
    private static final String tensv = "tensv";


    public SQLiteDatabase openDatabase() {
        mysql = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        return mysql;
    }

    public boolean checkForTableExists(String table) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = openDatabase();
        String sql = "SELECT name FROM sqlite_master WHERE type='table'";
        Cursor mCursor = sqLiteDatabase.rawQuery(sql, null);
        mCursor.moveToFirst();

        while (!mCursor.isAfterLast()) {
            list.add(mCursor.getString(0));
            Log.e("tables", mCursor.getString(0));
            mCursor.moveToNext();
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).trim().equals(table.trim())) {
                Log.e("TAG", "TABLE is exist");
                return true;
            }
        }
        return false;
    }

    // tao database
    public SQLiteDatabase createDatabase(Context activity) {
        try {

            File f = new File(DATABASE_PATH + DATABASE_NAME);
            if (!f.exists()) {
                InputStream e = activity.getAssets().open(DATABASE_NAME);
                File folder = new File(DATABASE_PATH);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                FileOutputStream myOutput = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    // xoa database
    public void deleteDataBase(Context context) throws IOException {
        String msg = "";
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (tmpFile.exists()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                SQLiteDatabase.deleteDatabase(tmpFile);
                msg = "DB is deleted";
            } else {
                msg = "DB deleted failed";
            }
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "First, create database", Toast.LENGTH_SHORT).show();
        }
    }

    // Tao bang
    public void createTable(Context context) throws SQLException {
        mysql = openDatabase();

        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);

        if (tmpFile.exists()) {

            if (checkForTableExists(tblsinhvien) == false && checkForTableExists(tblsinhvien) == false) {
                String sqlClass = "create table if not exists "
                        + tbllop + " ( "
                        + malop + " TEXT PRIMARY KEY unique, "
                        + tenlop + " TEXT, "
                        + siso + " int);";
                String sqlStudent = "create table if not exists "
                        + tblsinhvien + " ( "
                        + masv + " TEXT PRIMARY KEY, "
                        + tensv + " TEXT, "
                        + malop + " TEXT NOT NULL CONSTRAINT "
                        + malop + " REFERENCES "
                        + tbllop + " ( "
                        + malop + " ) ON DELETE CASCADE)";
                mysql.execSQL(sqlClass);
                mysql.execSQL(sqlStudent);
                Toast.makeText(context, tbllop + " " + tblsinhvien + " is create", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Table exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "First, create database", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteTable_lop(Context context) {
        mysql = openDatabase();
        File tmpFile = new File(context.getApplicationInfo().dataDir + "/databases/" + DATABASE_NAME);

        if (tmpFile.exists()) {
            if (checkForTableExists(tbllop) == true) {
                mysql.execSQL("drop table if exists " + tbllop);
                mysql.execSQL("drop table if exists " + tblsinhvien);
                Toast.makeText(context, "Drop success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Table is not exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "First, create database", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteTable_sinhvien(Context context) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (tmpFile.exists()) {
            if (checkForTableExists(tblsinhvien) == true) {
                mysql.execSQL("drop table if exists " + tblsinhvien);
                Toast.makeText(context, "Drop success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Table is not exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "First, create databse", Toast.LENGTH_SHORT).show();
        }
    }

    public void ins_class(Context context, String id_class, String name, int number_of_class) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        String msg = "";
        if (tmpFile.exists()) {
            if (checkForTableExists(tbllop) == true) {

                ContentValues values = new ContentValues();
                values.put(malop, id_class);
                values.put(tenlop, name);
                values.put(siso, number_of_class);

                if (mysql.insert(tbllop, null, values) == -1) {
                    msg = " failed to insert record";
                } else {
                    msg = " insert record is successful";
                }

                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, tbllop + " not exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "First, create databse", Toast.LENGTH_SHORT).show();
        }

    }

    public void del_class(Context context, String id_class) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        String msg = "";
        if (tmpFile.exists()) {
            if (checkForTableExists(tbllop) == true) {

                if (mysql.delete(tbllop, malop + " = ?", new String[]{id_class}) == 0) {
                    msg = " delete record is failed";
                } else {
                    msg = " delete record is successful";
                }

                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, tbllop + " not exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "First, create databse", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateClass(Context context, String id_lop, String tenlopmoi, int sisomoi) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        String msg = "";
        if (tmpFile.exists()) {
            if (checkForTableExists(tbllop) == true) {
                ContentValues values = new ContentValues();
                values.put(tenlop, tenlopmoi);
                values.put(siso, sisomoi);

                if (mysql.update(tbllop, values, malop + " = ?", new String[]{id_lop}) == 0) {
                    msg = " update record is failed";
                } else {
                    msg = " update record is successful";
                }

                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, tbllop + " not exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "First, create databse", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Classroom> loadAllClass(Context context) {
        mysql = openDatabase();
        ArrayList<Classroom> classrooms = null;
        if (checkTableIsEmpty(tbllop)) {
            Toast.makeText(context, tbllop + " don't have data", Toast.LENGTH_SHORT).show();
        } else {
            classrooms = new ArrayList<Classroom>();
            String sql = "select * from " + tbllop;
            Cursor cursor = mysql.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    Classroom classroom = new Classroom();
                    classroom.setMalop(cursor.getString(0));
                    classroom.setTenlop(cursor.getString(1));
                    classroom.setSiso(cursor.getInt(2));
                    classrooms.add(classroom);
                } while (cursor.moveToNext());
            }
            cursor.close();
            Toast.makeText(context, "load list class is successful", Toast.LENGTH_SHORT).show();
            for (Classroom cl : classrooms) {
                Log.d("TAG", cl.toString());
            }
        }
        return classrooms;
    }

    public void ins_student(Context context, String id_sinhvien, String name, String malop_sinhvien) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        String msg = "";
        if (tmpFile.exists()) {
            if (checkForTableExists(tblsinhvien) == true) {

                ContentValues values = new ContentValues();
                values.put(masv, id_sinhvien);
                values.put(tensv, name);
                values.put(malop, malop_sinhvien);

                if (mysql.insert(tblsinhvien, null, values) == -1) {
                    msg = " failed to insert record";
                } else {
                    msg = " insert record is successful";
                }

                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, tblsinhvien + " not exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "First, create databse", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Student> loadAllStudent(Context context) {
        mysql = openDatabase();
        ArrayList<Student> students = null;
        if (checkTableIsEmpty(tblsinhvien)) {
            Toast.makeText(context, tblsinhvien + " dont't have data", Toast.LENGTH_SHORT).show();
        } else {
            students = new ArrayList<Student>();
            String sql = "select * from " + tblsinhvien;
            Cursor cursor = mysql.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();
                    student.setMasv(cursor.getString(0));
                    student.setTensv(cursor.getString(1));
                    student.setMalop(cursor.getString(2));
                    students.add(student);
                } while (cursor.moveToNext());
            }
            cursor.close();

            for (Student student : students) {
                Log.d("TAG", student.toString());
            }
        }
        return students;
    }

    // rong thi true, co data thi false
    private boolean checkTableIsEmpty(String table) {
        boolean result = true;
        mysql = openDatabase();
        String count = "select count(*) from " + table;
        Cursor cursor = mysql.rawQuery(count, null);
        cursor.moveToFirst();
        int count_row = cursor.getInt(0);
        if ((count_row > 0)) {
            result = false;
        }
        return result;
    }
}
