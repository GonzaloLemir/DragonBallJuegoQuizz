package com.example.dragonquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre;
    private ImageView inicio;
    private TextView puntaje;
    private MediaPlayer mp;
    private MediaPlayer mp2;

    int numA = (int) (Math.random() * 7);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.editText2);
        inicio = findViewById(R.id.imageView3);
        puntaje = findViewById(R.id.textView);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Toast.makeText(this,"" + numA, Toast.LENGTH_LONG);



        int id;

        if(numA == 0){
            id = getResources().getIdentifier("gokuninoinicio", "drawable", getPackageName());
            inicio.setImageResource(id);
            mp = MediaPlayer.create(this, R.raw.iniciodb);
            mp.start();
            mp.setLooping(true);
        }
        if(numA == 1){
            id = getResources().getIdentifier("inicio", "drawable", getPackageName());
            inicio.setImageResource(id);
            mp = MediaPlayer.create(this, R.raw.iniciodbz);
            mp.start();
            mp.setLooping(true);
        }
        if(numA == 2){
            id = getResources().getIdentifier("inicio1", "drawable", getPackageName());
            inicio.setImageResource(id);
            mp = MediaPlayer.create(this, R.raw.iniciodbzss1);
            mp.start();
            mp.setLooping(true);
        }
        if(numA == 3){
            id = getResources().getIdentifier("inicio3", "drawable", getPackageName());
            inicio.setImageResource(id);
            mp = MediaPlayer.create(this, R.raw.iniciodbzss3);
            mp.start();
            mp.setLooping(true);
        }
        if(numA == 4){
            id = getResources().getIdentifier("iniciogt", "drawable", getPackageName());
            inicio.setImageResource(id);
            mp = MediaPlayer.create(this, R.raw.iniciogt);
            mp.start();
            mp.setLooping(true);
        }
        if(numA == 5){
            id = getResources().getIdentifier("inicioazul", "drawable", getPackageName());
            inicio.setImageResource(id);
            mp = MediaPlayer.create(this, R.raw.iniciosuper);
            mp.start();
            mp.setLooping(true);
        }
        if(numA == 6){
            id = getResources().getIdentifier("iniciodoctrina", "drawable", getPackageName());
            inicio.setImageResource(id);
            mp = MediaPlayer.create(this, R.raw.inicioultra);
            mp.start();
            mp.setLooping(true);
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"db",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor buscador = db.rawQuery(
                "select * from historial where puntaje = (select max(puntaje) from historial)", null);

        if(buscador.moveToFirst()){
            String nombre = buscador.getString(1);
            String puntos = buscador.getString(2);

            puntaje.setText("Record de\n" + nombre + "\ncon " + puntos + " puntos");

            db.close();
        }
        else{
            db.close();
        }


    }


    public void jugar(View view){
        String nom = nombre.getText().toString();

        if(nom.length()==0){
            Toast.makeText(this,"Ingresa un nombre", Toast.LENGTH_SHORT).show();
        }
        else{
            mp.stop();
            mp.release();

            mp2 = MediaPlayer.create(this, R.raw.cambionivel);
            mp2.start();


            Intent intent = new Intent(this, Main2Activity_DragonBallSaga.class);
            intent.putExtra("nombre", nom);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed(){

    }
}
