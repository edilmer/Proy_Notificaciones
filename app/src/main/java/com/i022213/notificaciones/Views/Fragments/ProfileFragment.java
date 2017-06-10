package com.i022213.notificaciones.Views.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.i022213.notificaciones.Data.DataUser;
import com.i022213.notificaciones.LoginActivity;
import com.i022213.notificaciones.Models.User;
import com.i022213.notificaciones.R;

import java.util.List;


public class ProfileFragment extends Fragment {

    View view;
    List<User> userList;
    DataUser dataUser;
    TextView name;
    User user;
    Button signOff;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        showTolbar(getResources().getString(R.string.txt_title_toolbar_profile),true);
        setHasOptionsMenu(true);


        signOff = (Button) view.findViewById(R.id.id_btn_fragment_profile_Sign_off);

        dataUser = new DataUser(getActivity());
        dataUser.open();
        user = dataUser.checkStatusLogin();

        signOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.info_sign_off), Toast.LENGTH_SHORT).show();
                 dataUser.statusOff(user.getUsername(),user.getPassword());
                loinctivity();
                System.exit(0);
            }
        });

        return view ;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    public void loinctivity(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }


}
