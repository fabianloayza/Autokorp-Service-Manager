package com.android.fabian.autokorpservicemanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faby on 6/01/15.
 */
public class ClienteFactory {
    private static ClienteFactory sInstance;
    private List<ClienteEntity> mClientes;

    private ClienteFactory() {
        mClientes= new ArrayList<ClienteEntity>();
    }

    public static ClienteFactory getInstance() {
        if (sInstance == null) {
            sInstance = new ClienteFactory();
        }

        return sInstance;
    }

    public List<ClienteEntity> getClientes() {

        mClientes.clear();
        for (int i = 0; i < 20; i++) {
            ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setId_cliente(i+1);
            clienteEntity.setNombre("Cliente" + i);
            clienteEntity.setApellido_pat("Apellido_Pat"+i);
            clienteEntity.setApellido_mat("Apellido_Mat" + i);

            mClientes.add(clienteEntity);
        }

        return mClientes;
    }
}
