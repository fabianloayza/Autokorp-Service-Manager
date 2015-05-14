package com.android.fabian.autokorpservicemanager;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Faby on 6/01/15.
 */
public class ClienteItemAdapter extends BaseAdapter {

    private List<ClienteEntity> mClientes;
    private Context mContext;


    public ClienteItemAdapter(Context context, List<ClienteEntity> clientes) {
        mContext = context;
        mClientes = clientes;

    }

    private static class ViewHolder {
        TextView tvApellidoPat;
        TextView tvApellidoMat;
        TextView tvNombre;

    }



    @Override
    public int getCount() {
        return mClientes.size();
    }

    @Override
    public Object getItem(int i) {
        return mClientes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mClientes.get(i).getId_cliente();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_view_cliente, viewGroup, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvApellidoPat= (TextView) row.findViewById(R.id.tv_apellido_pat);
            viewHolder.tvApellidoMat= (TextView) row.findViewById(R.id.tv_apellido_mat);
            viewHolder.tvNombre= (TextView) row.findViewById(R.id.tv_nombre);

            row.setTag(viewHolder);
        }


        ViewHolder viewHolder = (ViewHolder) row.getTag();


        ClienteEntity clienteEntity = mClientes.get(i);
        viewHolder.tvApellidoPat.setText(clienteEntity.getApellido_pat());
        viewHolder.tvApellidoMat.setText(clienteEntity.getApellido_mat());
        viewHolder.tvNombre.setText(clienteEntity.getNombre());

        return row;
    }
}
