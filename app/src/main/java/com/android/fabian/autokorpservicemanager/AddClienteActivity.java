package com.android.fabian.autokorpservicemanager;

import android.support.v4.app.Fragment;

/**
 * Created by Faby on 6/01/15.
 */
public class AddClienteActivity extends AbstractBaseActivity {
    @Override
    protected Fragment createFragment() {
        return new AddClienteFragment();
    }
}
