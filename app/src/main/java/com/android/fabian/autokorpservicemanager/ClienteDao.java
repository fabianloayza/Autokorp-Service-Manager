package com.android.fabian.autokorpservicemanager;

/**
 * Created by Faby on 6/01/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;


import java.util.ArrayList;
import java.util.List;
public class ClienteDao {

    private DatabaseManager mDatabaseManager;

    public ClienteDao(Context context) {
        mDatabaseManager = DatabaseManager.getInstance(context);
    }

    public long addCliente(ClienteEntity clienteEntity) {
        ContentValues values = new ContentValues();

        values.putNull("id_cliente");
        values.put("apellido_pat", clienteEntity.getApellido_pat());
        values.put("apellido_mat", clienteEntity.getApellido_mat());
        values.put("nombre", clienteEntity.getNombre());
        values.put("ci", clienteEntity.getCi());
        values.put("telefono", clienteEntity.getTelefono());
        values.put("email", clienteEntity.getEmail());

//        values.put("birthday", customerEntity.getBirthday() != null ? customerEntity.getBirthday().getTime(): 0);
//        values.put("latitude", customerEntity.getLatitude());
//        values.put("longitude", customerEntity.getLongitude());

        return mDatabaseManager.insert("cliente", values);

    }

    public List<ClienteEntity> getClientes() {
        List<ClienteEntity> clienteEntities= new ArrayList<ClienteEntity>();

        Cursor cursor = null;
        try {
            String[] selectionArgs = new String[]{"id_cliente", "apellido_pat", "apellido_mat", "nombre", "ci","telefono","email"};
            cursor = mDatabaseManager.query("cliente", selectionArgs, null, null, null, null, "apellido_pat", null);
            while (cursor.moveToNext()) {
                ClienteEntity clienteEntity= cursorToCliente(cursor);

                clienteEntities.add(clienteEntity);
            }
        } catch (SQLException e) {
            throw  e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return clienteEntities;
    }

    public ClienteEntity getCliente(int clienteId) {
        ClienteEntity clienteEntity= null;
        Cursor cursor = null;
        try {
            String[] selectionArgs = new String[]{"id_cliente", "apellido_pat", "apellido_mat", "nombre", "ci","telefono","email"};
            String whereClause = "id_cliente = ?";
            String[] whereArgs = new String[] {String.valueOf(clienteId)};
            cursor = mDatabaseManager.query("cliente", selectionArgs, whereClause, whereArgs, null, null, null, null);
            if (cursor.moveToNext()) {
                clienteEntity= cursorToCliente(cursor);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return clienteEntity;
    }

    private ClienteEntity cursorToCliente(Cursor cursor) {
        ClienteEntity clienteEntity= new ClienteEntity();
        clienteEntity.setId_cliente(cursor.getInt(0));
        clienteEntity.setApellido_pat(cursor.getString(1));
        clienteEntity.setApellido_mat(cursor.getString(2));
        clienteEntity.setNombre(cursor.getString(3));
        clienteEntity.setCi(cursor.getInt(4));
        clienteEntity.setTelefono(cursor.getInt(5));
        clienteEntity.setEmail(cursor.getString(6));

//        customerEntity.setBirthday(new Date(cursor.getLong(5)));
//        customerEntity.setLatitude(cursor.getDouble(6));
//        customerEntity.setLongitude(cursor.getDouble(7));

        return clienteEntity;
    }
}
