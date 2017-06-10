package com.i022213.notificaciones.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.i022213.notificaciones.Models.Mark;
import com.i022213.notificaciones.R;

import java.util.ArrayList;
import java.util.List;

public class MarkAdapter extends BaseAdapter{

    List<Mark> userList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public MarkAdapter(Context context, List<Mark> userList) {
        this.context = context;
        this.userList = userList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    public int getCount() {
        return userList.size();
    }

    @Override
    public Mark getItem(int position) {
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
            convertView = layoutInflater.inflate(R.layout.item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Mark favorit = getItem(position);
        viewHolder.name.setText(String.valueOf(favorit.getIdUser()));
        viewHolder.email.setText( String.valueOf(favorit.getIdEmpresa()));

        return convertView;
    }

    public class ViewHolder{
        TextView name;
        TextView email;

        public ViewHolder(View item) {
            name = (TextView) item.findViewById(R.id.id_item_name);
            email = (TextView) item.findViewById(R.id.id_item_email);
        }
    }
}