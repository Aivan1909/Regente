package com.full.aivan.regente;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().hide();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        String carnet = intent.getStringExtra("CI");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.MenuBajo);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_perfil:
                        fragment = PerfilEstActivity.newInstance();
                        break;
                    case R.id.navigation_notas:
                        fragment = NotasEstActivity.newInstance();
                        break;
                    case R.id.navigation_info:
                        fragment = InfoEstActivity.newInstance();
                        break;
                    case R.id.navigation_salir:
                        fragment = ExitActivity.newInstance();
                        MiDialogo();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.commit();
                return true;
            }
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, PerfilEstActivity.newInstance());
        transaction.commit();
    }
    public void MiDialogo(){
        //TRABAJAR CON ALERTDIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setView(R.layout.dialog_salir);
        builder.setTitle("Cerrar Sesion");
        builder.setMessage("¿Está seguro de Cerrar Sesión?");
        builder.setCancelable(false);
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //AlertDialog dialog = builder.create();
        //dialog.show();
        builder.create().show();
        }
    }