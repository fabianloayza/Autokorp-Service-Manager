package com.android.fabian.autokorpservicemanager;

/**
 * Created by Faby on 24/12/14.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class AutomovilDao {
    private DatabaseManager mDatabaseManager;

    public AutomovilDao(Context context) {
        mDatabaseManager = DatabaseManager.getInstance(context);
    }

    public long addAutomovil(AutomovilEntity automovilEntity) {
        ContentValues values = new ContentValues();

        values.putNull("id_automovil");
        values.put("marca", automovilEntity.getMarca());
        values.put("modelo", automovilEntity.getModelo());
        values.put("tipo", automovilEntity.getTipo());
        values.put("placa", automovilEntity.getPlaca());
        values.put("chasis", automovilEntity.getChasis());
        values.put("km", automovilEntity.getKm());
        values.put("color", automovilEntity.getColor());
        values.put("motor", automovilEntity.getMotor());

//        values.put("birthday", customerEntity.getBirthday() != null ? customerEntity.getBirthday().getTime(): 0);
//        values.put("latitude", customerEntity.getLatitude());
//        values.put("longitude", customerEntity.getLongitude());

        return mDatabaseManager.insert("automovil", values);

    }
    public long addFoto(FotoEntity fotoEntity)
    {
        ContentValues values = new ContentValues();
        values.putNull("id_foto");
        values.put("id_automovil",fotoEntity.getId_automovil());
        values.put("foto",fotoEntity.getFoto());

        return mDatabaseManager.insert("foto",values);
    }

    public List<AutomovilEntity> getAutomoviles() {
        List<AutomovilEntity> automovilEntities= new ArrayList<AutomovilEntity>();

        Cursor cursor = null;
        try {
            String[] selectionArgs = new String[]{"id_automovil", "marca", "modelo", "tipo", "placa","chasis","km","color","motor"};
            cursor = mDatabaseManager.query("automovil", selectionArgs, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                AutomovilEntity automovilEntity = cursorToAutomovil(cursor);

                automovilEntities.add(automovilEntity);
            }
        } catch (SQLException e) {
            throw  e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return automovilEntities;
    }
    public List<FotoEntity> getFotos() {
        List<FotoEntity> fotoEntities= new ArrayList<FotoEntity>();

        Cursor cursor = null;
        try {
            String[] selectionArgs = new String[]{"id_foto", "id_automovil", "foto"};
            cursor = mDatabaseManager.query("foto", selectionArgs, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                FotoEntity fotoEntity= cursorToFoto(cursor);

                fotoEntities.add(fotoEntity);
            }
        } catch (SQLException e) {
            throw  e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return fotoEntities;
    }
    public AutomovilEntity getAutomovil(int automovilId) {
        AutomovilEntity automovilEntity= null;
        Cursor cursor = null;
        try {
            String[] selectionArgs = new String[]{"id_automovil", "marca", "modelo", "tipo", "placa","chasis","km","color","motor"};
            String whereClause = "id_automovil = ?";
            String[] whereArgs = new String[] {String.valueOf(automovilId)};
            cursor = mDatabaseManager.query("automovil", selectionArgs, whereClause, whereArgs, null, null, null, null);
            if (cursor.moveToNext()) {
                automovilEntity= cursorToAutomovil(cursor);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return automovilEntity;
    }

    private AutomovilEntity cursorToAutomovil(Cursor cursor) {
        AutomovilEntity automovilEntity = new AutomovilEntity();
        automovilEntity.setId_automovil(cursor.getInt(0));
        automovilEntity.setMarca(cursor.getString(1));
        automovilEntity.setModelo(cursor.getString(2));
        automovilEntity.setTipo(cursor.getString(3));
        automovilEntity.setPlaca(cursor.getString(4));
        automovilEntity.setChasis(cursor.getString(5));
        automovilEntity.setKm(cursor.getLong(6));
        automovilEntity.setColor(cursor.getString(7));
        automovilEntity.setMotor(cursor.getString(8));

//        customerEntity.setBirthday(new Date(cursor.getLong(5)));
//        customerEntity.setLatitude(cursor.getDouble(6));
//        customerEntity.setLongitude(cursor.getDouble(7));

        return automovilEntity;
    }

    private FotoEntity cursorToFoto(Cursor cursor) {
        FotoEntity fotoEntity= new FotoEntity();
        fotoEntity.setId_foto(cursor.getInt(0));
        fotoEntity.setId_automovil(cursor.getInt(1));
        fotoEntity.setFoto(cursor.getBlob(2));


//        customerEntity.setBirthday(new Date(cursor.getLong(5)));
//        customerEntity.setLatitude(cursor.getDouble(6));
//        customerEntity.setLongitude(cursor.getDouble(7));

        return fotoEntity;
    }

}
