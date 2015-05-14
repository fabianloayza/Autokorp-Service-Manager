package com.android.fabian.autokorpservicemanager;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.graphics.Matrix;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Faby on 24/12/14.
 */
public class AddAutomovilFragment extends Fragment {

    Uri imageuri;
    private EditText mEtMarca;
    private EditText mEtModelo;
    private EditText mEtTipo;
    private EditText mEtPlaca;
    private EditText mEtChasis;
    private EditText mEtKm;
    private EditText mEtColor;
    private EditText mEtMotor;
    private ImageView mIvFoto;
    private ImageButton mIbCamara;


    private AutomovilDao mAutomovilDao;
    private static final int CAMERA_PIC_REQUEST = 2500;
    private Rect Padding = new Rect(-1,-1,-1,-1);
    private Matrix matrix = new Matrix();
    private Bitmap rotatedBitmap = null;
    private Bitmap bm = null;
    private Bitmap scaledBitmap= null;
    private ExifInterface exif ;
    private int rotation=0;
    private final BitmapFactory.Options options = new BitmapFactory.Options();
    private byte[] fotoBlob;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_automovil, container, false);
        ImageButton btn_camera = (ImageButton) view.findViewById(R.id.ib_camara);



        mEtMarca = (EditText) view.findViewById(R.id.et_marca);
        mEtModelo= (EditText) view.findViewById(R.id.et_modelo);
        mEtTipo = (EditText) view.findViewById(R.id.et_tipo);
        mEtPlaca= (EditText) view.findViewById(R.id.et_placa);
        mEtChasis= (EditText) view.findViewById(R.id.et_chasis);
        mEtKm= (EditText) view.findViewById(R.id.et_km);
        mEtColor= (EditText) view.findViewById(R.id.et_color);
        mEtMotor= (EditText) view.findViewById(R.id.et_motor);
        mIvFoto = (ImageView) view.findViewById(R.id.iv_foto);
        mIbCamara = (ImageButton) view.findViewById(R.id.ib_camara);



        btn_camera.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                  startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

              }
        }
        );

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST)
            if (data != null) {
            imageuri = data.getData();
            //Bitmap image = (Bitmap) data.getExtras().get("data");

            try {
                options.inSampleSize = 2;
                exif = new ExifInterface(getRealPathFromURI(imageuri));
                rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                rotation = exifToDegrees(rotation);

                bm = BitmapFactory.decodeStream(
                        getActivity().getContentResolver().openInputStream(imageuri),Padding,options);

                scaledBitmap = Bitmap.createScaledBitmap(bm, 400,400, true);

                if (rotation != 0)
                {matrix.postRotate(rotation);}
                //matrix.postRotate(180);
                rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mIvFoto.setImageBitmap(rotatedBitmap);
//                 mIvFoto.setImageURI(imageuri);

        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAutomovilDao = new AutomovilDao(getActivity());

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_automovil, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save_automovil:
                AutomovilEntity automovilEntity= getAutomovilData();
                int automovilId= (int) saveAutomovil(automovilEntity);

                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                fotoBlob=bos.toByteArray();


                FotoEntity fotoEntity = getFotoData(automovilId,fotoBlob);

                int fotoId = (int) saveFoto(fotoEntity);


                Toast.makeText(getActivity(), String.format("Foto Saved: Id=%d", fotoId), Toast.LENGTH_LONG).show();
                //Toast.makeText(getActivity(), "Automovil Guardado", Toast.LENGTH_LONG).show();

                getActivity().setResult(Activity.RESULT_OK);

                getActivity().finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private long saveAutomovil(AutomovilEntity automovilEntity) {

        return mAutomovilDao.addAutomovil(automovilEntity);
    }

    private long saveFoto(FotoEntity fotoEntity)
    {
        return mAutomovilDao.addFoto(fotoEntity);
    }

    private AutomovilEntity getAutomovilData() {
        AutomovilEntity automovilEntity= new AutomovilEntity();
        automovilEntity.setMarca(mEtMarca.getText().toString());
        automovilEntity.setModelo(mEtModelo.getText().toString());
        automovilEntity.setTipo(mEtTipo.getText().toString());
        automovilEntity.setPlaca(mEtPlaca.getText().toString());
        automovilEntity.setChasis(mEtChasis.getText().toString());
        automovilEntity.setKm(Long.parseLong(mEtKm.getText().toString()));
        automovilEntity.setColor(mEtColor.getText().toString());
        automovilEntity.setMotor(mEtMotor.getText().toString());


        return automovilEntity;
    }
    private FotoEntity getFotoData(int automovilId, byte[] foto) {
        FotoEntity fotoEntity= new FotoEntity();
        fotoEntity.setId_automovil(automovilId);
        fotoEntity.setFoto(foto);

        return fotoEntity;
    }

    private static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) { return 90; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {  return 180; }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {  return 270; }
        return 0;
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor =getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}
