package com.i022213.notificaciones.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.i022213.notificaciones.Adapters.EmpresaAdapter;
import com.i022213.notificaciones.Data.DataUser;
import com.i022213.notificaciones.LoginActivity;
import com.i022213.notificaciones.Models.Empresa;
import com.i022213.notificaciones.Models.Mark;
import com.i022213.notificaciones.R;

import java.util.List;

public class SearchFragment extends Fragment {

    View view;
    List<Empresa> blist;
    public static DataUser dempresa;
    ListView listView;
    String cancel="";
    Button cancelempresabtn;
    EmpresaAdapter empresaAdapter;
    EditText cancelempresa;
    CheckBox markempresa;
    public static Empresa empresaMark;


    public SearchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_search, container, false);
        showTolbar(getResources().getString(R.string.info_title_toolbar_search_empresa),true);
        setHasOptionsMenu(true);
        cancelempresa = (EditText) view.findViewById(R.id.id_search_empresa);
        listView = (ListView) view.findViewById(R.id.id_list_empresas);
        cancelempresabtn = (Button) view.findViewById(R.id.id_btn_search_empresa);
        markempresa = (CheckBox) view.findViewById(R.id.id_mark);






        dempresa = new DataUser(getActivity());
        dempresa.open();

        blist = dempresa.findAllEmpresas();

        if (blist.size()<=0) {
            createData();
        }
        cancelempresabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel= cancelempresa.getText().toString();
                blist = dempresa.findEmpresas(cancel.toUpperCase());
                if (blist.size()<=0){
                    Toast.makeText(getActivity().getApplicationContext(), getString(R.string.info_no_results) + cancelempresa.getText().toString()  , Toast.LENGTH_SHORT).show();
                }else{
                    empresaAdapter = new EmpresaAdapter(getActivity().getApplicationContext(), blist);
                    listView.setAdapter(empresaAdapter);
                }
            }
        });



        return view;
    }




    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void createData(){
        Empresa empresa = new Empresa();

        empresa.setEmpresa("CLARO");
        empresa.setNit("8476985632");dempresa.createEmpresa(empresa);

        empresa.setEmpresa("TIGO");
        empresa.setNit("9676985639");dempresa.createEmpresa(empresa);

        empresa.setEmpresa("MOVISTAR");
        empresa.setNit("0356985630");dempresa.createEmpresa(empresa);

        empresa.setEmpresa("SONY");
        empresa.setNit("0436985631");dempresa.createEmpresa(empresa);

        empresa.setEmpresa("LG");
        empresa.setNit("7446985632");dempresa.createEmpresa(empresa);

        empresa.setEmpresa("ALCATEL");
        empresa.setNit("223985638");dempresa.createEmpresa(empresa);

        empresa.setEmpresa("NOKIA");
        empresa.setNit("1116985636");dempresa.createEmpresa(empresa);

        empresa.setEmpresa("MOTOROLA");
        empresa.setNit("9996985633");dempresa.createEmpresa(empresa);



    }

     public static void createDataMark(){

        Mark mark = new Mark();
        mark.setIdUser(LoginActivity.userLogin.getId());
        mark.setIdEmpresa(SearchFragment.empresaMark.getId());
         dempresa.createMark(mark);
    }




}
