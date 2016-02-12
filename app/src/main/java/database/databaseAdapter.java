package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cesarmendes.mediasapp.models.Cadeira;

import java.util.ArrayList;


/**
 * Created by cesar.mendes on 09/02/2016.
 */
public class databaseAdapter {
    private databaseContract mDbHelper;
    private SQLiteDatabase db;

    public databaseAdapter(Context cont){
        mDbHelper = new databaseContract(cont);
    }

    public boolean insert(String nome, Double credit, Integer ano, Integer nota){
        ContentValues values = new ContentValues();
        values.put(databaseContract.COLUMN_NAME_CADEIRA, nome);
        values.put(databaseContract.COLUMN_NAME_ENTRY_CREDIT, credit);
        values.put(databaseContract.COLUMN_NAME_YEAR, ano);
        values.put(databaseContract.COLUMN_ENTRY_VALUE, nota);

        long newRowID;
        newRowID = db.insert(databaseContract.TABLE_NAME,null,values);

        return newRowID != -1;
    }

    public ArrayList<Cadeira> getCadeiras(){
        String nome;
        Integer ano, nota,id;
        Double creditos;
        Cursor c = db.rawQuery("SELECT * FROM " + databaseContract.TABLE_NAME + " ORDER BY " + databaseContract.COLUMN_NAME_YEAR, null);
        ArrayList<Cadeira> listaCadeiras = new ArrayList<>();
        while (c.moveToNext()) {
            nome = c.getString(c.getColumnIndex(databaseContract.COLUMN_NAME_CADEIRA));
            ano = Integer.parseInt(c.getString(c.getColumnIndex(databaseContract.COLUMN_NAME_YEAR)));
            nota = Integer.parseInt(c.getString(c.getColumnIndex(databaseContract.COLUMN_ENTRY_VALUE)));
            creditos = Double.parseDouble((c.getString(c.getColumnIndex(databaseContract.COLUMN_NAME_ENTRY_CREDIT))));
            id = Integer.parseInt(c.getString(c.getColumnIndex(databaseContract._ID)));
            listaCadeiras.add(new Cadeira(nome,creditos,ano,nota,id));
        }
        return listaCadeiras;
    }

    public void delete_cadeira(Integer id){
        db.execSQL("DELETE FROM " + databaseContract.TABLE_NAME + " WHERE " + databaseContract._ID + "=" + id);
    }
    public void openBD(){
        db = mDbHelper.getWritableDatabase();
    }

    public void close() {
        if (db.isOpen()) db.close();
    }

    public boolean update(Cadeira cadeira) {
        ContentValues cv = new ContentValues();
        cv.put(databaseContract.COLUMN_NAME_CADEIRA,cadeira.getNome());
        cv.put(databaseContract.COLUMN_NAME_ENTRY_CREDIT,cadeira.getCredito());
        cv.put(databaseContract.COLUMN_NAME_YEAR,cadeira.getAno());
        cv.put(databaseContract.COLUMN_ENTRY_VALUE,cadeira.getNota());

        if(db.update(databaseContract.TABLE_NAME,cv,databaseContract._ID + "=" + cadeira.getId(),null) > 0)
            return true;

        return false;
    }
}
