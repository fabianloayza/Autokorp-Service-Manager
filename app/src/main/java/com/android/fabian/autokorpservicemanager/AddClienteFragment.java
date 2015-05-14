package com.android.fabian.autokorpservicemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Faby on 6/01/15.
 */
public class AddClienteFragment extends Fragment {

    private ClienteDao mClienteDao;

    private EditText mEtApellidoPat;
    private EditText mEtApellidoMat;
    private EditText mEtNombre;
    private EditText mEtCi;
    private EditText mEtTelefono;
    private EditText mEtEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_cliente, container, false);


        mEtApellidoPat= (EditText) view.findViewById(R.id.et_apellido_pat);
        mEtApellidoMat= (EditText) view.findViewById(R.id.et_apellido_mat);
        mEtNombre= (EditText) view.findViewById(R.id.et_nombre);
        mEtCi= (EditText) view.findViewById(R.id.et_ci);
        mEtTelefono= (EditText) view.findViewById(R.id.et_telefono);
        mEtEmail= (EditText) view.findViewById(R.id.et_email);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mClienteDao = new ClienteDao(getActivity());

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_cliente, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save_cliente:
                ClienteEntity clienteEntity= getClienteData();
                int clienteId= (int) saveCliente(clienteEntity);


                Toast.makeText(getActivity(), String.format("Cliente Saved: Id=%d", clienteId), Toast.LENGTH_LONG).show();
                //Toast.makeText(getActivity(), "Automovil Guardado", Toast.LENGTH_LONG).show();

                getActivity().setResult(Activity.RESULT_OK);

                getActivity().finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private long saveCliente(ClienteEntity clienteEntity) {

        return mClienteDao.addCliente(clienteEntity);
    }

    private ClienteEntity getClienteData() {
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setApellido_pat(mEtApellidoPat.getText().toString());
        clienteEntity.setApellido_mat(mEtApellidoMat.getText().toString());
        clienteEntity.setNombre(mEtNombre.getText().toString());
        clienteEntity.setCi(Integer.parseInt(mEtCi.getText().toString()));
        clienteEntity.setTelefono(Integer.parseInt(mEtTelefono.getText().toString()));
        clienteEntity.setEmail(mEtEmail.getText().toString());

        return clienteEntity;
    }

}
