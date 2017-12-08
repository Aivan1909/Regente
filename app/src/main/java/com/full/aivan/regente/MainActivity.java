package com.full.aivan.regente;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button login, idioma;
    Locale locale;
    Configuration config = new Configuration();
    EditText ETLog;
    EditText contr;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    DatabaseReference refEst;
    boolean sw=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference();
        refEst= ref.child("Estudiante");

        ETLog = (EditText) findViewById(R.id.ETUsuario);
        contr = (EditText) findViewById(R.id.ETContraseña);

        login =(Button) findViewById(R.id.BtnIngresar);
        contr = (EditText) findViewById(R.id.ETContraseña);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refEst.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot resuEst : dataSnapshot.getChildren()
                                ) {
                            Estudiante e = resuEst.getValue(Estudiante.class);

                            if((e.getCodigo().equals(ETLog.getText().toString())) && (e.getPassword().equals(contr.getText().toString()))){
                                Intent intent=new Intent(getApplicationContext(), PerfilActivity.class);
                                SharedPreferences preferences = getSharedPreferences("DATO", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("CI", Integer.toString(e.getCi()));
                                editor.commit();
                                sw=true;
                                Toast.makeText(MainActivity.this, "Datos Correctos..", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        idioma = (Button) findViewById(R.id.BtnIdioma);
        idioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
    }

    public void ShowDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.str_btn));

        String[] tipos = getResources().getStringArray(R.array.languages);
        b.setItems(tipos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                switch (which){
                    case 0:
                        locale = new Locale("en");
                        config.locale = locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        config.locale = locale;
                        break;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                startActivity(refresh);
                finish();
            }
        });

        b.show();
    }

    public void ShowPass(View view) {
        boolean marcador = ((CheckBox)view).isChecked();
        switch (view.getId()){
            case R.id.CBContraseña:
                if (marcador){
                    contr.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    contr.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
        }
    }
}