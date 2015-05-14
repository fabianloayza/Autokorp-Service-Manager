package com.android.fabian.autokorpservicemanager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Faby on 30/12/14.
 */
public class AutomovilInfoFragment extends Fragment {
    private static final String TAG = AutomovilInfoFragment.class.getSimpleName();

    private EditText mEtMarca;
    private EditText mEtModelo;
    private EditText mEtPlaca;
    private EditText mEtTipo;
    private EditText mEtChasis;
    private EditText mEtKm;
    private EditText mEtColor;
    private EditText mEtMotor;
    private ImageView mIvFoto;
    private Bitmap bm=null;

    //private ImageButton mIbEmail;
    //private ImageButton mIbCall;

    private int mAutomovilId;
    private AutomovilDao mAutomovilDao;


    public static AutomovilInfoFragment newInstance(int automovilId,byte[] foto) {
        AutomovilInfoFragment automovilInfoFragment= new AutomovilInfoFragment();

        Bundle args = new Bundle();
        args.putInt(BundleKeys.KEY_AUTOMOVIL_INFO_ID, automovilId);
        args.putByteArray(BundleKeys.KEY_AUTOMOVIL_INFO_FOTO,foto);
        automovilInfoFragment.setArguments(args);

        return automovilInfoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_automovil_info, container, false);
      //  mTvName = (TextView) view.findViewById(R.id.tv_name);
        mEtMarca = (EditText) view.findViewById(R.id.et_marca);
        mEtModelo = (EditText) view.findViewById(R.id.et_modelo);
        mEtPlaca= (EditText) view.findViewById(R.id.et_placa);
        mEtTipo= (EditText) view.findViewById(R.id.et_tipo);
        mEtChasis= (EditText) view.findViewById(R.id.et_chasis);
        mEtKm= (EditText) view.findViewById(R.id.et_km);
        mEtColor= (EditText) view.findViewById(R.id.et_color);
        mEtMotor= (EditText) view.findViewById(R.id.et_motor);
        mIvFoto = (ImageView) view.findViewById(R.id.iv_foto);



//        mIbCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                makeCall(mEtPhone.getText().toString());
//            }
//        });
//        mIbEmail = (ImageButton) view.findViewById(R.id.ib_email);
//        mIbEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendEmail(mEtEmail.getText().toString());
//            }
//        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAutomovilDao = new AutomovilDao(getActivity());

        int automovilId = getArguments().getInt(BundleKeys.KEY_AUTOMOVIL_INFO_ID);
        byte[] foto = getArguments().getByteArray(BundleKeys.KEY_AUTOMOVIL_INFO_FOTO);

        loadAutomovil(automovilId,foto);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_info_automovil, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_ot:
                goToAddOrdenTrabajo();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void loadAutomovil(int automovilId, byte[] foto) {
        AutomovilEntity automovilEntity= mAutomovilDao.getAutomovil(automovilId);

        if (automovilEntity != null) {
           // mTvName.setText(customerEntity.getName());
           // mEtAddress.setText(customerEntity.getAddress());
           // mEtPhone.setText(customerEntity.getPhone());
           // mEtEmail.setText(customerEntity.getEmail());
            String km = String.valueOf(automovilEntity.getKm());

            mEtMarca.setText(automovilEntity.getMarca());
            mEtModelo.setText(automovilEntity.getModelo());
            mEtPlaca.setText(automovilEntity.getPlaca());
            mEtTipo.setText(automovilEntity.getTipo());
            mEtChasis.setText(automovilEntity.getChasis());
            mEtKm.setText(km);
            mEtColor.setText(automovilEntity.getColor());
            mEtMotor.setText(automovilEntity.getMotor());

            bm = BitmapFactory.decodeByteArray(foto, 0, foto.length);
            mIvFoto.setImageBitmap(bm);

        }
    }

    private void goToAddOrdenTrabajo(){
        Intent intent= new Intent(getActivity(),OrdenTrabajoActivity.class);
        startActivity(intent);
    }

//    private void sendEmail(String email) {
//        try {
//            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                    "mailto", email, null));
//
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            Log.e(TAG, "", e);
//        }
//    }
//
//    private void makeCall(String phone) {
//        try {
//            String uri = "tel:" + phone.trim();
//            Intent intent = new Intent(Intent.ACTION_DIAL);
//            intent.setData(Uri.parse(uri));
//
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            Log.e(TAG, "", e);
//        }
//    }



}
