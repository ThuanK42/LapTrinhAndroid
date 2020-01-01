package com.thuannluit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
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

    private static final String TABLE_LOP = "tbllop";
    private static final String MALOP = "malop";
    private static final String TENLOP = "tenlop";
    private static final String SISO = "siso";

    private static final String TABLE_SINHVIEN = "tblsinhvien";
    private static final String MASV = "masv";
    private static final String TENSV = "tensv";
    private static final String IDLOP = "lopsv";

    private final String ERR_UPDATE_OBJECT = "Information modification failed";
    private final String SUCCESSFULL_UPDATE_OBJECT = "Edit information successfully";
    private final String ERR_ADD_OBJECT = "Add object failed";
    private final String SUCCESSFULL_ADD_OBJECT = "Add object successfully";
    private final String SUCCESSFULL_DELETE_OBJECT = "Successfully deleted object";
    private final String ERR_DELETE_OBJECT = "Delete object failed";
    private final String ERR_LOAD_DATA = "Load information failure";
    private final String NOT_DATA = " don't have data";

    private final String ERR_DEL_DATABASE = "Database deletion failed";
    private final String SUCCESSFULL_DEL_DATABASE = "Database successfully deleted";
    private final String HINT_CREATE_DATABASE = "Please create database first";

    private final String SUCCESSFULL_CREATE_TABLE = " two table successfully";
    private final String TABLE_HAS_AVAILABLE = "already exist";
    private final String TABLE_NOT_EXISTED = "not existed yet";
    private final String SUCCESSFULL_DEL_TABLE = "Successfully deleted the table";

    private final String REMINDER_DATA_ENTRY = "Please enter enough information !";
    private final String FIND_STUDENT_BY_ID_CLASS = "find list student by id class is successful";


    // mo database -  quyen doc ghi
    public SQLiteDatabase openDatabase() {
        mysql = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        return mysql;
    }

    // check bang co ton tai hay khong
    public boolean checkForTableExists(String table) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = openDatabase();
        String sql = "SELECT name FROM sqlite_master WHERE type='table'";
        Cursor mCursor = sqLiteDatabase.rawQuery(sql, null);
        mCursor.moveToFirst();

        while (!mCursor.isAfterLast()) {
            list.add(mCursor.getString(0));

            mCursor.moveToNext();
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).trim().equals(table.trim())) {
                return true;
            }
        }
        return false;
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

    // tao database
    // -> lay database co san trong assets
    // -> hoac tao moi hoan toan
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
    public void deleteDataBase(Context context) throws SQLException {
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (tmpFile.exists()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                SQLiteDatabase.deleteDatabase(tmpFile);
                Toast.makeText(context, SUCCESSFULL_DEL_DATABASE, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, ERR_DEL_DATABASE, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        }
    }

    // Tao bang
    public void createTable(Context context) throws SQLException {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (tmpFile.exists()) {
            if (checkForTableExists(TABLE_SINHVIEN) == false && checkForTableExists(TABLE_SINHVIEN) == false) {
                String sqlClass = "create table if not exists "
                        + TABLE_LOP + " ( "
                        + MALOP + " TEXT PRIMARY KEY unique, "
                        + TENLOP + " TEXT, "
                        + SISO + " int);";
                String sqlStudent = "create table if not exists "
                        + TABLE_SINHVIEN + " ( "
                        + MASV + " TEXT PRIMARY KEY, "
                        + TENSV + " TEXT, "
                        + IDLOP + " TEXT NOT NULL CONSTRAINT "
                        + IDLOP + " REFERENCES "
                        + TABLE_LOP + " ( "
                        + MALOP + " ) ON DELETE CASCADE)";
                mysql.execSQL(sqlClass);
                mysql.execSQL(sqlStudent);
                Toast.makeText(context, "create " + TABLE_LOP + " " + TABLE_SINHVIEN + " " + SUCCESSFULL_CREATE_TABLE, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, TABLE_HAS_AVAILABLE, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        }
    }

    // xoa bang
    public void deleteTable_lop(Context context) throws SQLException {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);

        if (tmpFile.exists()) { // neu file database ton tai
            if (checkForTableExists(TABLE_LOP) == true) { // neu bang ton tai thi moi cho xoa
                mysql.execSQL("drop table if exists " + TABLE_LOP);
                mysql.execSQL("drop table if exists " + TABLE_SINHVIEN);
                Toast.makeText(context, SUCCESSFULL_DEL_TABLE, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteTable_sinhvien(Context context) throws SQLException {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (tmpFile.exists()) {
            if (checkForTableExists(TABLE_SINHVIEN) == true) {
                mysql.execSQL("drop table if exists " + TABLE_SINHVIEN);
                Toast.makeText(context, SUCCESSFULL_DEL_TABLE, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        }
    }

    public void ins_class(Context context, String id_class, String name, int number_of_class) {
        mysql = openDatabase();
        ContentValues values;
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (!tmpFile.exists()) {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        } else {
            if (checkForTableExists(TABLE_LOP) == true) {
                values = new ContentValues();
                values.put(MALOP, id_class);
                values.put(TENLOP, name);
                values.put(SISO, number_of_class);
                if (mysql.insert(TABLE_LOP, null, values) == -1) {
                    Toast.makeText(context, ERR_ADD_OBJECT, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, SUCCESSFULL_ADD_OBJECT, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, TABLE_LOP + TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void del_class(Context context, String id_class) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (tmpFile.exists()) {
            if (checkForTableExists(TABLE_LOP) == true) {
                if (mysql.delete(TABLE_LOP, MALOP + " = ?", new String[]{id_class}) == 0) {
                    Toast.makeText(context, ERR_DELETE_OBJECT, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, SUCCESSFULL_DELETE_OBJECT, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, TABLE_LOP + TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        }
    }

    public void updateClass(Context context, String id_lop, String tenlopmoi, int sisomoi) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (tmpFile.exists()) {
            if (checkForTableExists(TABLE_LOP) == true) {
                ContentValues values = new ContentValues();
                values.put(TENLOP, tenlopmoi);
                values.put(SISO, sisomoi);
                if (mysql.update(TABLE_LOP, values, MALOP + " = ?", new String[]{id_lop}) == 0) {
                    Toast.makeText(context, ERR_UPDATE_OBJECT, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, SUCCESSFULL_UPDATE_OBJECT, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, TABLE_LOP + TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        }
    }

    public void ins_student(Context context, String id_sinhvien, String name, String
            malop_sinhvien) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (tmpFile.exists()) {
            if (checkForTableExists(TABLE_SINHVIEN) == true) {
                if (id_sinhvien.isEmpty() || name.isEmpty() || malop_sinhvien.isEmpty()) {
                    Toast.makeText(context, REMINDER_DATA_ENTRY, Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(MASV, id_sinhvien);
                    values.put(TENSV, name);
                    values.put(IDLOP, malop_sinhvien);
                    if (mysql.insert(TABLE_SINHVIEN, null, values) == -1) {
                        Toast.makeText(context, ERR_ADD_OBJECT, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, SUCCESSFULL_ADD_OBJECT, Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(context, TABLE_SINHVIEN + TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteStudent(Context context, String id_sv) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);

        if (tmpFile.exists()) {
            if (checkForTableExists(TABLE_SINHVIEN) == true) {
                if (id_sv.isEmpty()) {
                    Toast.makeText(context, REMINDER_DATA_ENTRY, Toast.LENGTH_SHORT).show();
                } else {
                    if (mysql.delete(TABLE_SINHVIEN, MASV + " = ?", new String[]{id_sv}) == 0) {
                        Toast.makeText(context, ERR_DELETE_OBJECT, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, SUCCESSFULL_DELETE_OBJECT, Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(context, TABLE_SINHVIEN + TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        }
    }

    public void updateStudent(Context context, String id_sv, String tenmoi) {
        mysql = openDatabase();
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);

        if (tmpFile.exists()) {
            if (checkForTableExists(TABLE_SINHVIEN) == true) {
                if (id_sv.isEmpty() || tenmoi.isEmpty()) {
                    Toast.makeText(context, REMINDER_DATA_ENTRY, Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(TENSV, tenmoi);

                    if (mysql.update(TABLE_SINHVIEN, values, MASV + " = ?", new String[]{id_sv}) == 0) {
                        Toast.makeText(context, ERR_UPDATE_OBJECT, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, SUCCESSFULL_UPDATE_OBJECT, Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(context, TABLE_SINHVIEN + TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Classroom> loadAllClass(Context context) {
        ArrayList<Classroom> classrooms = null;
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (!tmpFile.exists()) {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        } else {
            if (checkForTableExists(TABLE_LOP) == false) {
                Toast.makeText(context, TABLE_LOP + " " + TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            } else {
                if (checkTableIsEmpty(TABLE_LOP)) {
                    Toast.makeText(context, TABLE_LOP + NOT_DATA, Toast.LENGTH_SHORT).show();
                } else {
                    classrooms = new ArrayList<Classroom>();
                    String sql = "select * from " + TABLE_LOP;
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
                }
            }
        }
        return classrooms;
    }

    public ArrayList<Student> loadAllStudent(Context context) {
        ArrayList<Student> students = null;
        Student student;
        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (!tmpFile.exists()) {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        } else {
            if (checkForTableExists(TABLE_SINHVIEN) == false) {
                Toast.makeText(context, TABLE_SINHVIEN + " " + TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            } else {
                if (checkTableIsEmpty(TABLE_SINHVIEN)) {
                    Toast.makeText(context, TABLE_SINHVIEN + NOT_DATA, Toast.LENGTH_SHORT).show();
                } else {
                    students = new ArrayList<Student>();
                    String sql = "select * from " + TABLE_SINHVIEN;
                    Cursor cursor = mysql.rawQuery(sql, null);
                    if (cursor.moveToFirst()) {
                        do {
                            student = new Student();
                            student.setMasv(cursor.getString(0));
                            student.setTensv(cursor.getString(1));
                            student.setMalop(cursor.getString(2));
                            students.add(student);
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }
            }
        }
        return students;
    }

    public ArrayList<Student> findStudentByIDStudent(Context context, String id_sv) {
        ArrayList<Student> students=null;

        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (!tmpFile.exists()) {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        } else {
            if (checkForTableExists(TABLE_SINHVIEN) == true) {
                if (checkTableIsEmpty(TABLE_SINHVIEN)) {
                    Toast.makeText(context, TABLE_SINHVIEN + " dont't have data", Toast.LENGTH_SHORT).show();
                } else {
                    students = new ArrayList<Student>();
                    String sql = "select * from " + TABLE_SINHVIEN + " where masv =  " + id_sv;
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
                    if (students.isEmpty()) {
                        Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "find list student by id student is successful", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(context, TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        }
        return students;
    }

    public ArrayList<Student> findStudentByNameStudent(Context context, String name) {
        ArrayList<Student> students;
        ArrayList<Student> listFindStudent = null;

        File tmpFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (!tmpFile.exists()) {
            Toast.makeText(context, HINT_CREATE_DATABASE, Toast.LENGTH_SHORT).show();
        } else {
            if (checkForTableExists(TABLE_SINHVIEN) == true) {
                if (checkTableIsEmpty(TABLE_SINHVIEN)) {
                    Toast.makeText(context, TABLE_SINHVIEN + " dont't have data", Toast.LENGTH_SHORT).show();
                } else {
                    students = new ArrayList<Student>();
                    String sql = "select * from " + TABLE_SINHVIEN;
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
                    listFindStudent = new ArrayList<Student>();
                    for (Student st : students) {
                        if (compareName(st.getTensv(), name)) {
                            listFindStudent.add(st);
                        }
                    }
                    if (listFindStudent.isEmpty()) {
                        Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "find list student by name student is successful", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(context, TABLE_NOT_EXISTED, Toast.LENGTH_SHORT).show();
            }
        }
        return listFindStudent;
    }

    // So sanh ten
    private boolean compareName(String nameEmp, String nameEmp2) {
        boolean result = false;
        nameEmp = nameEmp.trim(); // cat khoang trang dau cuoi
        nameEmp2 = nameEmp2.trim(); // cat khoang trang dau cuoi
        String[] name = nameEmp.split(" "); // mang chua cac phan tu duoc cat tu ten 1
        String[] name2 = nameEmp2.split(" "); // mang chua cac phan tu duoc cat tu ten 2
        //lay chuoi cuoi trong ten cua 2 dua ra chuyen ve chu thuong sau do kiem tra name 1 co chua nam 2 hay khong
        if (name[name.length - 1].toLowerCase().equalsIgnoreCase(name2[name2.length - 1].toLowerCase()))
            return result = true;
        return result;
    }

    public ArrayList<String> getIDClass(Context context) {
        ArrayList<String> classrooms = null;
        String sql;
        Cursor cursor;
        if (checkTableIsEmpty(TABLE_LOP)) {
            Toast.makeText(context, TABLE_LOP + " dont't have data", Toast.LENGTH_SHORT).show();
        } else {
            classrooms = new ArrayList<String>();
            sql = "select malop from " + TABLE_LOP;
            cursor = mysql.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    classrooms.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
            if (classrooms.isEmpty()) {
                Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show();
            }
        }
        return classrooms;
    }


}
