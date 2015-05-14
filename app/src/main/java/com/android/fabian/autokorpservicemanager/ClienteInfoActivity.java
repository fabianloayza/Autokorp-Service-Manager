package com.android.fabian.autokorpservicemanager;

import android.support.v4.app.Fragment;

/**
 * Created by Faby on 7/01/15.
 */
public class ClienteInfoActivity extends AbstractBaseActivity {
    @Override
    protected Fragment createFragment() {
        int clienteId = getIntent().getIntExtra(BundleKeys.KEY_CLIENTE_INFO_ID, 0);
        return ClienteInfoFragment.newInstance(clienteId);
    }
}
