package com.android.fabian.autokorpservicemanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by gf on 13/05/2015.
 */
public class InventarioFragment extends Fragment {

    ListView listInventario;
    public static InventarioFragment newInstance()
    {
        return new InventarioFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_inventario_list,container,false);
        listInventario = (ListView) view.findViewById(R.id.list_inv);

        listInventario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

               // ArrayAdapter arrayAdapter = (ArrayAdapter) parent.getAdapter();
                //String item = (String) arrayAdapter.getItem(position);
                //v.setVisibility(View.INVISIBLE);

            }
        });
        return view;
    }
}
