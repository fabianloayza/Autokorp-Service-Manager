package com.android.fabian.autokorpservicemanager;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * Created by gf on 12/05/2015.
 */
public class OrdenTrabajoFragment extends Fragment {

    View view;
    Button btnInventario;
    Button btnServicios;
    public static OrdenTrabajoFragment newInstance()
    {
       OrdenTrabajoFragment ordenTrabajoFragment = new OrdenTrabajoFragment();

        return ordenTrabajoFragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_orden_trabajo,container,false);
        final Animation animScale = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_scale);
        ListView listView = (ListView) view.findViewById(R.id.list_aceites);
        btnInventario = (Button) view.findViewById(R.id.btn_inventario);
        setListViewHeightBasedOnChildren(listView);
        listView.setFocusable(false);

        btnInventario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.startAnimation(animScale);
                Intent intent = new Intent(getActivity(), InventarioActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom);
            }
        });

        return view;
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
       // listView.requestLayout();
    }
}
