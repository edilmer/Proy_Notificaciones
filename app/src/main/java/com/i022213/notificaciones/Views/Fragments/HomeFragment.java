package com.i022213.notificaciones.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;


import com.i022213.notificaciones.Adapters.EmpresaAdapter;
import com.i022213.notificaciones.Adapters.MarkAdapter;
import com.i022213.notificaciones.Adapters.UserAdapter;
import com.i022213.notificaciones.Data.DataUser;
import com.i022213.notificaciones.LoginActivity;
import com.i022213.notificaciones.Models.Empresa;
import com.i022213.notificaciones.Models.Mark;
import com.i022213.notificaciones.Models.User;
import com.i022213.notificaciones.R;

import java.util.List;

public class HomeFragment extends Fragment {

    View view;
    ProgressBar loader;
    ListView lista, listaFavor;
    List<User> myUser;
    UserAdapter adapterUser;
    DataUser dataUser;
    List<Mark> mysFavorites;
    MarkAdapter adapterFavorites;
    List<Empresa> empresaList;
    EmpresaAdapter empresaAdapter;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_home, container, false);
        loader = (ProgressBar) view.findViewById(R.id.loader);
        listaFavor = (ListView) view.findViewById(R.id.id_mark);
        dataUser = new DataUser(getActivity());
        dataUser.open();

        LoginActivity.userLogin = dataUser.checkStatusLogin();
        showTolbar(getResources().getString(R.string.txt_title_toolbar_Container),true);
        setHasOptionsMenu(true);
        empresaList = dataUser.listMarks(LoginActivity.userLogin.getId());

            empresaAdapter = new EmpresaAdapter(getActivity().getApplicationContext(), empresaList);
            listaFavor.setAdapter(empresaAdapter);
        return view;
    }

    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
