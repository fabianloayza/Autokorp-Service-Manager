package com.android.fabian.autokorpservicemanager;

import android.support.v4.app.Fragment;


/**
 * Created by Faby on 30/12/14.
 */
public class AutomovilInfoActivity extends AbstractBaseActivity {
    @Override
    protected Fragment createFragment() {
        int automovilId = getIntent().getIntExtra(BundleKeys.KEY_AUTOMOVIL_INFO_ID, 0);
        byte[] foto = getIntent().getByteArrayExtra(BundleKeys.KEY_AUTOMOVIL_INFO_FOTO);
        return AutomovilInfoFragment.newInstance(automovilId,foto);
    }
}
