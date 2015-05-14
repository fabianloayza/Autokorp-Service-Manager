package com.android.fabian.autokorpservicemanager;

import android.app.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
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
import android.widget.SearchView;


import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Faby on 17/12/14.
 */
public class AutomovilListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Object>{

    private static final int LOADER_ID = 0;
    private static final int REQUEST_CODE = 0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_automovil_list, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(LOADER_ID, null, this);

        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_automovil:
                goToAddAutomovilScreen();
                return true;
            case R.id.action_search_automovil:
                getActivity().onSearchRequested();
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


    private void goToAddAutomovilScreen() {
        Intent intent = new Intent(getActivity(), AddAutomovilActivity.class);

        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle bundle) {

        return new AutomovilListLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Object> objectLoader, Object data) {
        if (data != null) {
            List<AutomovilEntity> automoviles = (List<AutomovilEntity>) data;
            AutomovilListLoader automovilListLoader= new AutomovilListLoader(getActivity());
            List<FotoEntity> fotos= automovilListLoader.LoadFotos();
            loadAutomoviles(automoviles,fotos);
        }
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        List<AutomovilEntity> automoviles = AutomovilFactory.getInstance().getAutomoviles();
//        loadAutomoviles(automoviles);
//    }

    private void loadAutomoviles(List<AutomovilEntity> automoviles, List<FotoEntity> fotos) {

        AutomovilItemAdapter automovilItemAdapter =  new AutomovilItemAdapter(getActivity(),automoviles,fotos);

        setListAdapter(automovilItemAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Object> objectLoader) {
        // do nothing
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        AutomovilItemAdapter automovilItemAdapter= (AutomovilItemAdapter) l.getAdapter();
        AutomovilEntity automovilEntity = (AutomovilEntity) automovilItemAdapter.getItem(position);

        final ImageView imageView = (ImageView) v.findViewById(R.id.iv_automovil);
        final BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        final Bitmap bm = bitmapDrawable.getBitmap();

        //bm = BitmapFactory.decodeResource(getResources(),R.drawable.unknown_automovil);
        byte[] foto;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
        foto=bos.toByteArray();

        Intent intent = new Intent(getActivity(),AutomovilInfoActivity.class);
        intent.putExtra(BundleKeys.KEY_AUTOMOVIL_INFO_ID, automovilEntity.getId_automovil());
        intent.putExtra(BundleKeys.KEY_AUTOMOVIL_INFO_FOTO,foto);


        startActivity(intent);
    }



}
