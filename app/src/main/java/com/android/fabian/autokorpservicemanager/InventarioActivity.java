package com.android.fabian.autokorpservicemanager;

import android.support.v4.app.Fragment;

/**
 * Created by gf on 13/05/2015.
 */
public class InventarioActivity extends AbstractBaseActivity {
    @Override
    protected Fragment createFragment() {
        return InventarioFragment.newInstance();
    }
}
