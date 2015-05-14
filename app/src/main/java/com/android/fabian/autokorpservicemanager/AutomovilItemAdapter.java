package com.android.fabian.autokorpservicemanager;

/**
 * Created by Faby on 17/12/14.
 */
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class AutomovilItemAdapter extends BaseAdapter {

    //private List<AutomovilEntity> mAutomoviles;
    private List<AutomovilEntity> mAutomoviles;
    private List<FotoEntity> mFotos;
    private Context mContext;
    private int id_automovil;

    private final BitmapFactory.Options options = new BitmapFactory.Options();
    private byte[] fotoBlob;

    public AutomovilItemAdapter(Context context, List<AutomovilEntity> automoviles,List<FotoEntity> fotos) {
        mContext = context;
        mAutomoviles = automoviles;
        mFotos = fotos;
    }

    private static class ViewHolder {
        TextView tvModelo;
        TextView tvMarca;
        TextView tvPlaca;
        ImageView ivFoto;
    }


    @Override
    public int getCount() {
        return mAutomoviles.size();
    }

    @Override
    public Object getItem(int i) {
        return mAutomoviles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mAutomoviles.get(i).getId_automovil();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_view_automovil, viewGroup, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvModelo= (TextView) row.findViewById(R.id.tv_modelo);
            viewHolder.tvMarca= (TextView) row.findViewById(R.id.tv_marca);
            viewHolder.tvPlaca= (TextView) row.findViewById(R.id.tv_placa);
            viewHolder.ivFoto = (ImageView) row.findViewById(R.id.iv_automovil);

            row.setTag(viewHolder);
        }


        ViewHolder viewHolder = (ViewHolder) row.getTag();
        //Drawable drawable = Drawable.createFromPath(String.valueOf(R.drawable.unknown_automovil));

        AutomovilEntity automovilEntity = mAutomoviles.get(i);
        viewHolder.tvModelo.setText(automovilEntity.getModelo());
        viewHolder.tvMarca.setText(automovilEntity.getMarca());
        viewHolder.tvPlaca.setText(automovilEntity.getPlaca());

        id_automovil = automovilEntity.getId_automovil();

        fotoBlob = (searchFoto(id_automovil,mFotos));

        Bitmap b1=BitmapFactory.decodeByteArray(fotoBlob, 0, fotoBlob.length);

        viewHolder.ivFoto.setImageBitmap(b1);


        return row;
    }

    public byte[] searchFoto(int id_automovil, List<FotoEntity> fotos)
    {
        byte[] blob=null;
        FotoEntity fotoEntity;
        for(int i=0;i<fotos.size();i++)
        {
             fotoEntity = fotos.get(i);
            if(fotoEntity.getId_automovil() == id_automovil)
            {
                blob = fotoEntity.getFoto();
            }
        }
        return blob;
    }



}
