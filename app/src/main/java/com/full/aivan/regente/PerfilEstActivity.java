package com.full.aivan.regente;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class PerfilEstActivity extends Fragment {

    TextView nom;
    TextView cur;
    TextView niv;
    TextView ant;
    TextView fec;
    ImageView foto;
    FirebaseDatabase firebaseDatabase;

    public static PerfilEstActivity newInstance() {
        PerfilEstActivity fragment = new PerfilEstActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view=inflater.inflate(R.layout.activity_perfilest, container, false);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("DATO", MODE_PRIVATE);
        final String carnet = preferences.getString("CI", "No se encontro");

        nom = (TextView) view.findViewById(R.id.TVNombreEst);
        cur = (TextView) view.findViewById(R.id.TVCursoEst);
        niv = (TextView) view.findViewById(R.id.TVNivel);
        ant = (TextView) view.findViewById(R.id.TVAnt);
        fec = (TextView) view.findViewById(R.id.TVFecha);
        foto = (ImageView) view.findViewById(R.id.IVFotoEst);


        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference();
        DatabaseReference refPer = ref.child("Estudiante");

        refPer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot resulPer : dataSnapshot.getChildren()
                     ) {
                    Estudiante e = resulPer.getValue(Estudiante.class);

                    if ((Integer.parseInt(carnet)==1112347)){
                        foto.setImageResource(R.mipmap.carlos_perfil);
                    }else if(Integer.parseInt(carnet) == 606066){
                        foto.setImageResource(R.mipmap.greace_perfil);
                    }else{
                        foto.setImageResource(R.mipmap.pepito_perfil);
                    }
                    if (Integer.toString(e.getCi()).equals(carnet)){
                        nom.setText(e.getNombre()+" " + e.getAp_pat() +" "+ e.getAp_mat());
                        String[] aux = e.getFecha_nac().split("-");
                        int edad = 2017 - Integer.parseInt(aux[2]);
                        niv.setText(Integer.toString(edad));
                        if(e.getNivel().equalsIgnoreCase("Secundaria")){
                             edad = edad - 11;
                        }else{
                             edad = edad - 5;
                        }

                        cur.setText(Integer.toString(edad)+"° de "+ e.getNivel());
                        ant.setText(e.getAnti());
                        fec.setText(e.getFecha_nac());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return view;
    }
}
