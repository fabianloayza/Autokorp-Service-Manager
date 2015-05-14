package com.android.fabian.autokorpservicemanager;

/**
 * Created by Faby on 24/12/14.
 */
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class AddAutomovilActivity extends AbstractBaseActivity{



    @Override
    protected Fragment createFragment() {
        return new AddAutomovilFragment();
    }


}
