package com.i022213.notificaciones.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.i022213.notificaciones.Helper.HelperUser;
import com.i022213.notificaciones.Models.Empresa;
import com.i022213.notificaciones.Models.Mark;
import com.i022213.notificaciones.Models.User;

import java.util.ArrayList;
import java.util.List;


public class DataUser {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            HelperUser.COLUMN_ID,
            HelperUser.COLUMN_NAME,
            HelperUser.COLUMN_EMAIL,
            HelperUser.COLUMN_USERNAME,
            HelperUser.COLUMN_PASSWORD
    };

    public DataUser(Context context){
        dbHelper = new HelperUser(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();

    }

    public void close(){
        dbHelper.close();
    }

    public User create(User user){
        ContentValues values = new ContentValues();
        values.put(HelperUser.COLUMN_NAME, user.getName());
        values.put(HelperUser.COLUMN_EMAIL, user.getEmail());
        values.put(HelperUser.COLUMN_USERNAME, user.getUsername());
        values.put(HelperUser.COLUMN_PASSWORD, user.getPassword());
        values.put(HelperUser.COLUMN_STATUS, user.getStatus());

        long insertId = database.insert(HelperUser.TABLE_USERS, null, values);

        user.setId(insertId);
        return user;
    }

    public List<User> cursorToList(Cursor cursor){
        List<User> users = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_EMAIL)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_PASSWORD)));
                user.setStatus(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_STATUS)));

                users.add(user);
            }
        }
        return users;
    }

    public List<User> findAll(){
        Cursor cursor = database.rawQuery("select * from users", null);
        List<User> users = cursorToList(cursor);
        return users;
    }

    public String[] findUser (String username, String password){

        String[] findUser = new String[2];
        Cursor cursor = database.rawQuery("select username,password from users where username = '"+username+"' and " +
                "password = '"+password+"'", null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                findUser[0] = cursor.getString(0);
                findUser[1] = cursor.getString(1);
            } while (cursor.moveToNext());
        }else{
            findUser[0] =" ";
            findUser[1] = " ";
        }
        return findUser;

    }

    public void statusOn (String username, String password){
        database.execSQL("update users set status = 'true' where username = '"+username+"' and " +
                "password = '"+password+"'");
    }

    public void statusOff (String username, String password){
        database.execSQL("update users set status = 'false' where username = '"+username+"' and " +
                "password = '"+password+"'");
    }

    public User checkStatusLogin (){

        User userLogin = new User();

        Cursor cursor = database.rawQuery("select * from users where status = 'true'", null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                userLogin.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID)));
                userLogin.setName(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_NAME)));
                userLogin.setEmail(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_EMAIL)));
                userLogin.setUsername(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_USERNAME)));
                userLogin.setPassword(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_PASSWORD)));
                userLogin.setStatus(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_STATUS)));
            } while (cursor.moveToNext());
        } else {
            userLogin = null;
        }
        return userLogin;
    }

// empresas
    public Empresa createEmpresa(Empresa empresa){
        ContentValues values = new ContentValues();
        values.put(HelperUser.COLUMN_EMPRESA, empresa.getEmpresa());
        values.put(HelperUser.COLUMN_NIT, empresa.getNit());

        long insertId = database.insert(HelperUser.TABLE_EMPRESAS, null, values);

        empresa.setId(insertId);
        return empresa;
    }


    //=========================================================================================================

    public List<Empresa> cursorToListEmpresa(Cursor cursor){
        List<Empresa> empresas = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Empresa empresa = new Empresa();
                empresa.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID)));
                empresa.setEmpresa(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_EMPRESA)));
                empresa.setNit(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_NIT)));

                empresas.add(empresa);
            }
        }
        return empresas;
    }

    public List<Empresa> findAllEmpresas(){
        Cursor cursor = database.rawQuery("select * from empresas", null);
        List<Empresa> empresas = cursorToListEmpresa(cursor);
        return empresas;
    }

    public List<Empresa> findEmpresas(String findEmpresa){
        Cursor cursor = database.rawQuery("select * from empresas where item_b ='"+findEmpresa+"' or nit = '"+findEmpresa+"'", null);
        List<Empresa> empresas = cursorToListEmpresa(cursor);
        return empresas;
    }


    // marcadores

    public Mark createMark(Mark mark){

        ContentValues values = new ContentValues();
        values.put(HelperUser.COLUMN_ID_USER, mark.getIdUser());
        values.put(HelperUser.COLUMN_ID_EMPRESA, mark.getIdEmpresa());

        long insertId = database.insert(HelperUser.TABLE_MARK_EMPRESAS_USERS, null, values);

        mark.setId(insertId);
        return mark;
    }

    public List<Mark> cursorToListMarks(Cursor cursor){
        List<Mark> mark = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Mark marks = new Mark();
                marks.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID)));
                marks.setIdUser(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID_USER)));
                marks.setIdEmpresa(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID_EMPRESA)));
                mark.add(marks);
            }
        }
        return mark;
    }

    public List<Mark> findAllMarks(){
        Cursor cursor = database.rawQuery("select * from markEmpresasUsers", null);
        List<Mark> mark = cursorToListMarks(cursor);
        return mark;
    }

    public List<Empresa> listMarks(long idUser){
        Cursor cursor = database.rawQuery("select t1.id,t1.item_b,t1.nit from empresas as t1 join markEmpresasUsers as t2 on t1.id = t2.idEmpresa where t2.idUser = "+idUser, null);
        List<Empresa> mark = cursorToListEmpresa(cursor);
        return mark;
    }


}