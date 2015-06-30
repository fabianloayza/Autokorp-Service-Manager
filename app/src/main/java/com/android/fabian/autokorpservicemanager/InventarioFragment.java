package com.android.fabian.autokorpservicemanager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gf on 13/05/2015.
 */

public class InventarioFragment extends Fragment {

    private Activity activity;
    private ListView listInventario;
    private String[] inventatioItems;
    public static InventarioFragment newInstance()
    {
       return new InventarioFragment();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_inventario_list, container, false);
        listInventario = (ListView) view.findViewById(R.id.list_inv);
        inventatioItems = getResources().getStringArray(R.array.list_inventario);

        InverntarioAdapter adapter = new InverntarioAdapter(activity);
        listInventario.setAdapter(adapter);
        listInventario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int pos, long arg3) {
                listInventario.setItemChecked(pos, true);
                ;
            }
        });

        return view;
    }

    public class InverntarioAdapter extends BaseAdapter {

        private LayoutInflater inflator;

        public InverntarioAdapter(Context context) {
            inflator = (LayoutInflater) context.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return inventatioItems.length;
        }

        @Override
        public Object getItem(int position) {
            return inventatioItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                convertView = inflator.inflate(android.R.layout.simple_list_item_1, null);
                //convertView.setBackground(getResources().getDrawable(R.drawable.ic_black));
                convertView.setBackgroundColor(getResources().getColor(R.color.black));
            }
            tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(inventatioItems[position]);

            if (listInventario.isItemChecked(position)) {
                //background Color of selected items
                convertView.setBackgroundColor(Color.BLUE);
            } else {
                convertView.setBackgroundColor(Color.WHITE);
            }

            return convertView;
        }
    }
}
//public class InventarioFragment extends Fragment {
//
//    ListView listInventario;
//    public static InventarioFragment newInstance()
//    {
//        return new InventarioFragment();
//    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view  = inflater.inflate(R.layout.fragment_inventario_list,container,false);
//        listInventario = (ListView) view.findViewById(R.id.list_inv);
//
//        listInventario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//
//               // ArrayAdapter arrayAdapter = (ArrayAdapter) parent.getAdapter();
//                //String item = (String) arrayAdapter.getItem(position);
//                //v.setVisibility(View.INVISIBLE);
//
//            }
//        });
//        return view;
//    }
//}
