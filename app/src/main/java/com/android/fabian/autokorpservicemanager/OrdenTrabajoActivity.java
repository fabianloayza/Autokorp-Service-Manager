package com.android.fabian.autokorpservicemanager;

import android.support.v4.app.Fragment;

/**
 * Created by gf on 12/05/2015.
 */
public class OrdenTrabajoActivity extends AbstractBaseActivity {
    @Override
    protected Fragment createFragment() {

        return OrdenTrabajoFragment.newInstance();
    }
}
