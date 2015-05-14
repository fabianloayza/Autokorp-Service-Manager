package com.android.fabian.autokorpservicemanager;

/**
 * Created by Faby on 24/12/14.
 */


import android.content.Context;
import android.database.SQLException;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AutomovilListLoader  extends AsyncTaskLoader<Object> {

    private static final String TAG = AutomovilListLoader.class.getSimpleName();

    private AutomovilDao mAutomovilDao;
    private List<AutomovilEntity> mAutomoviles;
    private List<FotoEntity> mFotos;



    public AutomovilListLoader(Context context) {
        super(context);
        mAutomovilDao= new AutomovilDao(context);
    }

    @Override
    public Object loadInBackground() {

        try {

            mAutomoviles = mAutomovilDao.getAutomoviles();

        } catch (SQLException e) {
            Log.e(TAG, "", e);
        }

        return mAutomoviles;

    }

    @Override
    protected void onStartLoading() {

        if (mAutomoviles != null) {
            deliverResult(mAutomoviles);
        } else {
            forceLoad();
        }

    }

    public List<FotoEntity> LoadFotos()
    {
        try{
            mFotos = mAutomovilDao.getFotos();
        }
        catch (SQLException e)
        {
            Log.e(TAG, "", e);
        }
        return  mFotos;
    }

}
