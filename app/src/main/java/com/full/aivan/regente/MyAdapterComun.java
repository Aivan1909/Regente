package com.full.aivan.regente;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by usuario on 05/12/2017.
 */

public class MyAdapterComun extends ArrayAdapter<Comunicados>{
    public MyAdapterComun(@NonNull Context context, int resource, @NonNull List<Comunicados> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.comunicados_item, null);
        }

        TextView mat = (TextView) convertView.findViewById(R.id.TVComunicado);
        TextView fe = (TextView) convertView.findViewById(R.id.TVFecha);

        Comunicados comunicados = getItem(position);

        mat.setText(comunicados.getDescripcion());
        fe.setText(comunicados.getFecha());

        return convertView;
    }
}