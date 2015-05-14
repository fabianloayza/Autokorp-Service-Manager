package com.android.fabian.autokorpservicemanager;

import android.support.v7.app.ActionBarActivity;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/**
 * Created by Faby on 17/12/14.
 */
public abstract class AbstractBaseActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = createFragment();
            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, fragment)
                        .commit();
            }
        }


    }

    protected abstract Fragment createFragment();
}
