package com.android.fabian.autokorpservicemanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.webkit.WebView;

/**
 * Created by Faby on 17/12/14.
 */
public class TabAdapter extends FragmentPagerAdapter{

    private static final int TAB_COUNT = 5;
    private static final int AUTOMOVILES_TAB = 0;
    private static final int CLIENTES_TAB = 1;
    private static final int PROCESOS_TAB = 2;
    private static final int HERRAMIENTAS_TAB = 3;
    private static final int OPCIONES_TAB = 4;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case AUTOMOVILES_TAB:
                return new AutomovilListFragment();
            case CLIENTES_TAB:
                return new ClienteListFragment();
            case PROCESOS_TAB:
                return new NullFragment();
            case HERRAMIENTAS_TAB:
                return new NullFragment();
            case OPCIONES_TAB:
                return new NullFragment();
            }

        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

}
