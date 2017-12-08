package com.full.aivan.regente;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class InfoEstActivity extends Fragment {

    ListView lista;
    List<Comunicados> comunicados;
    FirebaseDatabase firebaseDatabase;

    public static InfoEstActivity newInstance() {
        InfoEstActivity fragment = new InfoEstActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_infoest, container, false);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("DATO", MODE_PRIVATE);
        final String carnet = preferences.getString("CI", "No se encontro");


        comunicados = new ArrayList<>();
        lista = (ListView) view.findViewById(R.id.LVComunicados);
        final MyAdapterComun adapterComun = new MyAdapterComun(getContext(), R.layout.comunicados_item, comunicados);
        lista.setAdapter(adapterComun);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference();
        DatabaseReference refPer = ref.child("Comunicados");

        refPer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot resuPe : dataSnapshot.getChildren()
                        ) {
                    Comunicados n = resuPe.getValue(Comunicados.class);

                    comunicados.add(n);
                    adapterComun.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
