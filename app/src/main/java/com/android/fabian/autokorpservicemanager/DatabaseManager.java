package com.android.fabian.autokorpservicemanager;

/**
 * Created by Faby on 24/12/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseManager {

    private static final String DB_NAME = "asm.db";
    private static final int DB_VERSION = 9;
    private static DatabaseManager sDatabaseManager;
    private SQLiteDatabase mSqLiteDatabase;
    private DatabaseHelper mDatabaseHelper;


    private static final String TABLE_AUTOMOVIL = "CREATE TABLE IF NOT EXISTS automovil (" +
            " id_automovil integer PRIMARY KEY AUTOINCREMENT, " +
            " marca varchar(100) NOT NULL," +
            " modelo varchar(200)," +
            " tipo varchar(100)," +
            " placa varchar(100)," +
            " chasis varchar(100)," +
            " km long(20)," +
            " color varchar(20)," +
            " motor varchar(100))";
    private static final String TABLE_FOTO = "CREATE TABLE IF NOT EXISTS foto (" +
            " id_foto integer PRIMARY KEY AUTOINCREMENT, " +
            " id_automovil integer(10) NOT NULL, " +
            " foto BLOB) ";

    private static final String TABLE_CLIENTE = "CREATE TABLE IF NOT EXISTS cliente (" +
            " id_cliente integer PRIMARY KEY AUTOINCREMENT, " +
            " apellido_pat varchar(100) NOT NULL," +
            " apellido_mat varchar(200)," +
            " nombre varchar(100)," +
            " ci integer," +
            " telefono integer," +
            " email varchar(100))";

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(TABLE_AUTOMOVIL);
            db.execSQL(TABLE_FOTO);
            db.execSQL(TABLE_CLIENTE);
            updateTable(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.i("DatabaseManager", String.format("migrando de la version %d a la version %d",
                    oldVersion, newVersion));

            switch (oldVersion) {
                case 8:
                    updateTable(db);
                case 2:

            }

        }

        private void updateTable(SQLiteDatabase db) {
            db.execSQL(TABLE_CLIENTE);
      //    String update = "DROP TABLE automovil";
     //     db.execSQL(update);

//            update = "ALTER TABLE customer ADD COLUMN latitude decimal(12,8)";
//            db.execSQL(update);
//            update = "ALTER TABLE customer ADD COLUMN longitude decimal(12,8)";
//            db.execSQL(update);
        }
    }

    private DatabaseManager(Context context) {
        this.mDatabaseHelper = new DatabaseHelper(context);
        openDataBase();
    }

    public static DatabaseManager getInstance(Context context) {
        if (sDatabaseManager == null) {
            sDatabaseManager = new DatabaseManager(context);
        }

        return sDatabaseManager;
    }

    public SQLiteDatabase openDataBase() throws SQLiteException {

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        return mSqLiteDatabase;
    }

    public void closeDatabase() {

        if (mSqLiteDatabase != null && mSqLiteDatabase.isOpen()) {
            mSqLiteDatabase.close();
        }

    }


    public void beginTransaction() throws SQLException {
        try {
            mSqLiteDatabase.beginTransaction();
        } catch (SQLException e) {
            throw e;
        }

    }


    public void commit() throws SQLException {
        try {
            mSqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            throw e;
        }

    }


    public void endTransaction() throws SQLException {
        try {
            mSqLiteDatabase.endTransaction();
        } catch (SQLException e) {
            throw e;
        }

    }


    public long insert(String table, ContentValues values)
            throws SQLException {
        try {

            return mSqLiteDatabase.insertOrThrow(table, null, values);
        } catch (SQLException e) {
            throw e;
        }
    }


    public int update(String table, ContentValues values,
                      String whereClause, String[] whereArgs) throws SQLException {
        try {

            return mSqLiteDatabase.update(table, values, whereClause, whereArgs);
        } catch (SQLException e) {
            throw e;
        }
    }


    public int delete(String table, String whereClause, String[] whereArgs)
            throws SQLException {
        try {
            return mSqLiteDatabase.delete(table, whereClause, whereArgs);
        } catch (SQLException e) {
            throw e;
        }
    }


    public Cursor rawQuery(String sql, String[] selectionArgs)
            throws SQLException {
        try {
            return mSqLiteDatabase.rawQuery(sql, selectionArgs);
        } catch (SQLException e) {
            throw e;
        }
    }


    public Cursor query(String table, String[] selectionArgs,
                        String whereClause, String[] whereArgs, String groupBy,
                        String having, String orderBy, String limit) throws SQLException {
        try {
            return mSqLiteDatabase.query(table, selectionArgs, whereClause, whereArgs, groupBy,
                    having, orderBy, limit);
        } catch (SQLException e) {
            throw e;
        }
    }


    public Cursor query(boolean distinct, String table,
                        String[] selectionArgs, String whereClause, String[] whereArgs,
                        String groupBy, String having, String orderBy, String limit)
            throws SQLException {
        try {
            return mSqLiteDatabase.query(distinct, table, selectionArgs, whereClause, whereArgs,
                    groupBy, having, orderBy, limit);
        } catch (SQLException e) {
            throw e;
        }
    }
}
