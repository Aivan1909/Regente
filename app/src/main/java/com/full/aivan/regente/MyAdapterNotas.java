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
import java.util.Objects;

import static java.util.Objects.*;

/**
 * Created by usuario on 05/12/2017.
 */

public class MyAdapterNotas extends ArrayAdapter<Notas>{
    public MyAdapterNotas(@NonNull Context context, int resource, @NonNull List<Notas> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.notas_item, null);
        }

        TextView mat = (TextView) convertView.findViewById(R.id.TVMateria);
        TextView pri = (TextView) convertView.findViewById(R.id.TVPrimera);
        TextView seg = (TextView) convertView.findViewById(R.id.TVSegunda );
        TextView ter = (TextView) convertView.findViewById(R.id.TVExamen);
        TextView tot = (TextView) convertView.findViewById(R.id.TVTotal);

        Notas n = getItem(position);

        if (n.getIdmat()==1){
            mat.setText("Lenguaje");
        }else if(n.getIdmat()==2){
            mat.setText("Matematica");
        }else if(n.getIdmat()==3){
            mat.setText("Quimica");
        }else if(n.getIdmat()==4){
            mat.setText("Fisica");
        }else if(n.getIdmat()==5){
            mat.setText("Artes");
        }else if(n.getIdmat()==6){
            mat.setText("Ed. Fisica");
        }else if(n.getIdmat()==7){
            mat.setText("Religion");
        }else if(n.getIdmat()==8){
            mat.setText("Filosofia");
        }else if(n.getIdmat()==9){
            mat.setText("Ingles");
        }else if(n.getIdmat()==10){
            mat.setText("Musica");
        }else if(n.getIdmat()==11){
            mat.setText("Cien. Sociales");
        }else if(n.getIdmat()==12){
            mat.setText("Psicologia");
        }else if(n.getIdmat()==13){
            mat.setText("Cien. Naturales");
        }


        String[] aux=n.getNotas().split(",");
        pri.setText(aux[0]);
        seg.setText(aux[1]);
        ter.setText(aux[2]);
        int p=Integer.parseInt(aux[0]),s=Integer.parseInt(aux[1]),t=Integer.parseInt(aux[2]);
        int to= (p+s+t)/3;
        tot.setText(Integer.toString(to));

        return convertView;
    }
}