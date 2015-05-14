package com.android.fabian.autokorpservicemanager;

/**
 * Created by Faby on 18/12/14.
 */
import java.util.ArrayList;
import java.util.List;

public class AutomovilFactory {
    private static AutomovilFactory sInstance;
    private List<AutomovilEntity> mAutomoviles;

    private AutomovilFactory() {
        mAutomoviles= new ArrayList<AutomovilEntity>();
    }

    public static AutomovilFactory getInstance() {
        if (sInstance == null) {
            sInstance = new AutomovilFactory();
        }

        return sInstance;
    }

    public List<AutomovilEntity> getAutomoviles() {

        mAutomoviles.clear();
        for (int i = 0; i < 20; i++) {
            AutomovilEntity automovilEntity = new AutomovilEntity();
            automovilEntity.setId_automovil(i+1);
            automovilEntity.setModelo("Haval M" + i);
            automovilEntity.setMarca("GreatWall");
            automovilEntity.setChasis("FJK" + i);

            mAutomoviles.add(automovilEntity);
        }

        return mAutomoviles;
    }

}
