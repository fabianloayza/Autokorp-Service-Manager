package com.android.fabian.autokorpservicemanager;

import android.content.Context;
import android.database.SQLException;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by Faby on 6/01/15.
 */
public class ClienteListLoader extends AsyncTaskLoader<Object> {

    private static final String TAG = ClienteListLoader.class.getSimpleName();

    private ClienteDao mClienteDao;
    private List<ClienteEntity> mClientes;

    public ClienteListLoader(Context context) {
        super(context);
        mClienteDao= new ClienteDao(context);
    }

    @Override
    public Object loadInBackground() {
        try {

            mClientes = mClienteDao.getClientes();

        } catch (SQLException e) {
            Log.e(TAG, "", e);
        }

        return mClientes;
    }

    @Override
    protected void onStartLoading() {

        if (mClientes != null) {
            deliverResult(mClientes);
        } else {
            forceLoad();
        }

    }
}
