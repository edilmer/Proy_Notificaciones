package com.i022213.notificaciones.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;



import com.i022213.notificaciones.Models.Empresa;
import com.i022213.notificaciones.R;
import com.i022213.notificaciones.Views.Fragments.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class EmpresaAdapter extends BaseAdapter {

    List<Empresa> userList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public EmpresaAdapter(Context context, List<Empresa> userList) {
        this.context = context;
        this.userList = userList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    public int getCount() {
        return userList.size();
    }

    @Override
    public Empresa getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_b, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

            viewHolder.checkMark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkbo = (CheckBox) v;
                    Empresa empresa = (Empresa) checkbo.getTag();
                    if (checkbo.isChecked()){
                        SearchFragment.empresaMark = empresa;
                        SearchFragment.createDataMark();
                    }

                    empresa.setCheck(checkbo.isChecked());

                }
            });

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Empresa empresa = getItem(position);
       viewHolder.empresa.setText(empresa.getEmpresa());
        viewHolder.nit.setText(empresa.getNit());
        viewHolder.checkMark.setChecked(empresa.isCheck());
        viewHolder.checkMark.setTag(empresa);

        return convertView;

    }


    public class ViewHolder{
        TextView empresa;
        TextView nit;
        CheckBox checkMark;

        public ViewHolder(View item) {
            empresa = (TextView) item.findViewById(R.id.id_empresa);
            nit = (TextView) item.findViewById(R.id.id_nit);
            checkMark = (CheckBox) item.findViewById(R.id.id_mark);
        }
    }


}