package com.android.fabian.autokorpservicemanager;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Faby on 7/01/15.
 */
public class ClienteInfoFragment extends Fragment {

    private static final String TAG = ClienteInfoFragment.class.getSimpleName();

    private ClienteDao mClienteDao;
    private int clienteId;

    private TextView mTvNombre;
    private TextView mTvApellidoPat;
    private TextView mTvApellidoMat;
    private TextView mTvTelefono;
    private TextView mTvEmail;
    private ImageButton mIbCall;
    private ImageButton mIbSendEmail;


    public static ClienteInfoFragment newInstance(int clienteId) {
        ClienteInfoFragment clienteInfoFragment= new ClienteInfoFragment();

        Bundle args = new Bundle();
        args.putInt(BundleKeys.KEY_CLIENTE_INFO_ID, clienteId);
        clienteInfoFragment.setArguments(args);

        return clienteInfoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cliente_info, container, false);
        //  mTvName = (TextView) view.findViewById(R.id.tv_name);
        mTvNombre = (TextView) view.findViewById(R.id.tv_nombre);
        mTvApellidoPat = (TextView) view.findViewById(R.id.tv_apellido_pat);
        mTvApellidoMat= (TextView) view.findViewById(R.id.tv_apellido_mat);
        mTvTelefono= (TextView) view.findViewById(R.id.tv_telefono);
        mTvEmail= (TextView) view.findViewById(R.id.tv_email);
        mIbCall = (ImageButton) view.findViewById(R.id.ib_call);
        mIbSendEmail = (ImageButton) view.findViewById(R.id.ib_send_email);




        mIbCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall(mTvTelefono.getText().toString());
            }
        });

        mIbSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(mTvEmail.getText().toString());
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mClienteDao = new ClienteDao(getActivity());

        clienteId = getArguments().getInt(BundleKeys.KEY_CLIENTE_INFO_ID);
        loadCliente(clienteId);

    }

    private void loadCliente(int clienteId) {
        ClienteEntity clienteEntity= mClienteDao.getCliente(clienteId);

        if (clienteEntity!= null) {

            String telefono = String.valueOf(clienteEntity.getTelefono());


            mTvNombre.setText(clienteEntity.getNombre());
            mTvApellidoPat.setText(clienteEntity.getApellido_pat());
            mTvApellidoMat.setText(clienteEntity.getApellido_mat());
            mTvTelefono.setText(telefono);
            mTvEmail.setText(clienteEntity.getEmail());




        }
    }

        private void sendEmail(String email) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", email, null));

            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "", e);
        }
    }

    private void makeCall(String phone) {
        try {
            String uri = "tel:" + phone.trim();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));

            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "", e);
        }
    }


}
