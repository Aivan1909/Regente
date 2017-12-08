package com.full.aivan.regente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class NotasEstActivity extends Fragment {

    ListView lista;
    List<Notas> notasList;
    FirebaseDatabase firebaseDatabase;

    public static NotasEstActivity newInstance() {
        NotasEstActivity fragment = new NotasEstActivity();
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
        View view=inflater.inflate(R.layout.activity_notasest, container, false);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("DATO", MODE_PRIVATE);
        final String carnet = preferences.getString("CI", "No se encontro");

        notasList = new ArrayList<>();
        lista = (ListView) view.findViewById(R.id.LVNotas);
        final MyAdapterNotas adapterNotas = new MyAdapterNotas(getContext() , R.layout.notas_item, notasList);
        lista.setAdapter(adapterNotas);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference();
        DatabaseReference refPer = ref.child("Notas");

        refPer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot resuPe: dataSnapshot.getChildren()
                        ) {
                    Notas n = resuPe.getValue(Notas.class);
                    if(n.getCi() == Integer.parseInt(carnet)){
                        notasList.add(n);
                        adapterNotas.notifyDataSetChanged();
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
