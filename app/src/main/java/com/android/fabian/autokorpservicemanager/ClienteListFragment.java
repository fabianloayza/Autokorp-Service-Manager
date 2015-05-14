package com.android.fabian.autokorpservicemanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Faby on 6/01/15.
 */
public class ClienteListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Object> {


    private static final int LOADER_ID = 0;
    private static final int REQUEST_CODE = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cliente_list, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(LOADER_ID, null, this);

        setHasOptionsMenu(true);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_clientes, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_cliente:
                goToAddClienteScreen();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE) {
            getLoaderManager().restartLoader(LOADER_ID, null, this);
        }
    }

    private void goToAddClienteScreen() {
        Intent intent = new Intent(getActivity(), AddClienteActivity.class);

        startActivityForResult(intent, REQUEST_CODE);
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        List<ClienteEntity> clientes = ClienteFactory.getInstance().getClientes();
//        loadClientes(clientes);
//    }

    private void loadClientes(List<ClienteEntity> clientes ) {

        ClienteItemAdapter clienteItemAdapter =  new ClienteItemAdapter(getActivity(),clientes);

        setListAdapter(clienteItemAdapter);
    }

    @Override
    public Loader<Object> onCreateLoader(int i, Bundle bundle) {
        return new ClienteListLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Object> objectLoader, Object data) {
        if (data != null) {
            List<ClienteEntity> clientes = (List<ClienteEntity>) data;
            ClienteListLoader clienteListLoader= new ClienteListLoader(getActivity());

            loadClientes(clientes);
        }
    }

    @Override
    public void onLoaderReset(Loader<Object> objectLoader) {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        ClienteItemAdapter clienteItemAdapter= (ClienteItemAdapter) l.getAdapter();
        ClienteEntity clienteEntity= (ClienteEntity) clienteItemAdapter.getItem(position);


        Intent intent = new Intent(getActivity(),ClienteInfoActivity.class);
        intent.putExtra(BundleKeys.KEY_CLIENTE_INFO_ID, clienteEntity.getId_cliente());

        startActivity(intent);
    }


}
