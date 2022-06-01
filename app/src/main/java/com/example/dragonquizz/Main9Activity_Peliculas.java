package com.example.dragonquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Main9Activity_Peliculas extends AppCompatActivity {

    private MediaPlayer mp;
    private RadioButton op1, op2, op3, op4, e;
    private ImageView iv, vidaiv;
    private Button bt;
    private RadioGroup rg;
    private TextView tv, nom, vida, pun;
    private MediaPlayer mp2, mp3, mp4, mp5, mp6;

    int lvl = 1;
    int puntajeI, vidas;
    String jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9__peliculas);

        jugador = getIntent().getStringExtra("jugador");
        vidas = getIntent().getIntExtra("vidas", vidas);
        puntajeI = getIntent().getIntExtra("puntos", puntajeI);

        vidaiv = findViewById(R.id.peliv);
        nom = findViewById(R.id.jugador8);
        vida = findViewById(R.id.vidas8);
        pun = findViewById(R.id.puntos8);
        e = findViewById(R.id.e8);
        op1 = findViewById(R.id.radioButton29);
        op2 = findViewById(R.id.radioButton30);
        op3 = findViewById(R.id.radioButton31);
        op4 = findViewById(R.id.radioButton32);
        iv = findViewById(R.id.peli);
        bt = findViewById(R.id.button9);
        rg = findViewById(R.id.radioGroup9);
        tv = findViewById(R.id.textView9);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.lvl8);
        this.setTitle("  Peliculas Y Ovas");

        nom.setText("Jugador: " + jugador);
        if (vidas == 2) {
            int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
            vidaiv.setImageResource(id);
        } else if (vidas == 1) {
            int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
            vidaiv.setImageResource(id);
        }


        mp = MediaPlayer.create(this, R.raw.juegopeliculas);
        mp.start();
        mp.setLooping(true);

        mp2 = MediaPlayer.create(this, R.raw.acierto);

        mp4 = MediaPlayer.create(this, R.raw.error);

    }

    public void comprobar(View view) {
        if (lvl == 6) {

            if (vidas > 0) {
                mp6.stop();
                mp6.release();

                mp2.release();

                mp3 = MediaPlayer.create(this, R.raw.cambionivel);
                mp3.start();

                mp4.release();

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor buscador = db.rawQuery("select * from historial where puntaje = (select max(puntaje) from historial)", null);
                if (buscador.moveToFirst()) {
                    String tempNombre = buscador.getString(1);
                    String tempPuntaje = buscador.getString(2);

                    int tPuntaje = Integer.parseInt(tempPuntaje);

                    if (tPuntaje <= puntajeI) {
                        ContentValues modificar = new ContentValues();
                        modificar.put("nombre", jugador);
                        modificar.put("puntaje", puntajeI);

                        db.update("historial", modificar, "puntaje = " + tPuntaje, null);
                    }

                    db.close();
                } else {
                    ContentValues insertar = new ContentValues();
                    insertar.put("nombre", jugador);
                    insertar.put("puntaje", puntajeI);

                    db.insert("historial", null, insertar);
                }

                db.close();


                Intent intent = new Intent(this, MainActivity.class);

                startActivity(intent);

                finish();
            } else {
                mp5.stop();
                mp5.release();

                mp2.release();

                mp3 = MediaPlayer.create(this, R.raw.cambionivel);
                mp3.start();

                mp4.release();

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor buscador = db.rawQuery("select * from historial where puntaje = (select max(puntaje) from historial)", null);
                if (buscador.moveToFirst()) {
                    String tempNombre = buscador.getString(1);
                    String tempPuntaje = buscador.getString(2);

                    int tPuntaje = Integer.parseInt(tempPuntaje);

                    if (tPuntaje <= puntajeI) {
                        ContentValues modificar = new ContentValues();
                        modificar.put("nombre", jugador);
                        modificar.put("puntaje", puntajeI);

                        db.update("historial", modificar, "puntaje = " + tPuntaje, null);
                    }

                    db.close();
                } else {
                    ContentValues insertar = new ContentValues();
                    insertar.put("nombre", jugador);
                    insertar.put("puntaje", puntajeI);

                    db.insert("historial", null, insertar);
                }

                db.close();


                Intent intent = new Intent(this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        } else {

            if (lvl == 1) {
                if (op4.isChecked()) {
                    lvl++;
                    int id = getResources().getIdentifier("a13", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Androide 14");
                    op2.setText("Androide 17");
                    op3.setText("Androide 13");
                    op4.setText("Super Androide");

                    op1.setChecked(true);
                    op2.setChecked(true);
                    op3.setChecked(true);
                    op4.setChecked(true);
                    e.setChecked(true);

                    op1.setChecked(false);
                    op2.setChecked(false);
                    op3.setChecked(false);
                    op4.setChecked(false);
                    e.setChecked(false);

                    puntajeI = puntajeI + 50;

                    mp2.start();
                } else if (!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()) {

                } else {
                    vidas = vidas - 1;
                    if (vidas == 2) {
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else if (vidas == 1) {
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else {
                        int id = getResources().getIdentifier("perdiste", "drawable", getPackageName());
                        iv.setImageResource(id);

                        vidaiv.setVisibility(View.INVISIBLE);
                        rg.setVisibility(View.GONE);
                        tv.setText("Te quedaste sin vidas!");
                        tv.setVisibility(View.VISIBLE);
                        pun.setText("Puntaje: " + puntajeI);
                        pun.setVisibility(View.VISIBLE);
                        bt.setText("Menu");

                        mp.stop();
                        mp.release();

                        mp5 = MediaPlayer.create(this, R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
                }
            } else if (lvl == 2) {
                if (op3.isChecked()) {
                    lvl++;
                    int id = getResources().getIdentifier("hild", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Janemba");
                    op2.setText("Hildegan");
                    op3.setText("Ozaru");
                    op4.setText("Baby");

                    op1.setChecked(true);
                    op2.setChecked(true);
                    op3.setChecked(true);
                    op4.setChecked(true);
                    e.setChecked(true);

                    op1.setChecked(false);
                    op2.setChecked(false);
                    op3.setChecked(false);
                    op4.setChecked(false);
                    e.setChecked(false);

                    puntajeI = puntajeI + 50;

                    mp2.start();
                } else if (!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()) {

                } else {
                    vidas = vidas - 1;
                    if (vidas == 2) {
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else if (vidas == 1) {
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else {
                        int id = getResources().getIdentifier("perdiste", "drawable", getPackageName());
                        iv.setImageResource(id);

                        vidaiv.setVisibility(View.INVISIBLE);
                        rg.setVisibility(View.GONE);
                        tv.setText("Te quedaste sin vidas!");
                        tv.setVisibility(View.VISIBLE);
                        pun.setText("Puntaje: " + puntajeI);
                        pun.setVisibility(View.VISIBLE);
                        bt.setText("Menu");

                        mp.stop();
                        mp.release();

                        mp5 = MediaPlayer.create(this, R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
                }
            } else if (lvl == 3) {
                if (op2.isChecked()) {
                    lvl++;
                    int id = getResources().getIdentifier("broly", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Broly SS Legen.");
                    op2.setText("Super Trunks");
                    op3.setText("Broly SS 1");
                    op4.setText("Super Vegeta");

                    op1.setChecked(true);
                    op2.setChecked(true);
                    op3.setChecked(true);
                    op4.setChecked(true);
                    e.setChecked(true);

                    op1.setChecked(false);
                    op2.setChecked(false);
                    op3.setChecked(false);
                    op4.setChecked(false);
                    e.setChecked(false);

                    puntajeI = puntajeI + 50;

                    mp2.start();
                } else if (!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()) {

                } else {
                    vidas = vidas - 1;
                    if (vidas == 2) {
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else if (vidas == 1) {
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else {
                        int id = getResources().getIdentifier("perdiste", "drawable", getPackageName());
                        iv.setImageResource(id);

                        vidaiv.setVisibility(View.INVISIBLE);
                        rg.setVisibility(View.GONE);
                        tv.setText("Te quedaste sin vidas!");
                        tv.setVisibility(View.VISIBLE);
                        pun.setText("Puntaje: " + puntajeI);
                        pun.setVisibility(View.VISIBLE);
                        bt.setText("Menu");

                        mp.stop();
                        mp.release();

                        mp5 = MediaPlayer.create(this, R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
                }
            } else if (lvl == 4) {
                if (op1.isChecked()) {
                    lvl++;
                    int id = getResources().getIdentifier("gegetablue", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Gogeta Mistico");
                    op2.setText("Vegeta SS Blue");
                    op3.setText("Gogeta SS Blue");
                    op4.setText("Vegetto Blue");

                    op1.setChecked(true);
                    op2.setChecked(true);
                    op3.setChecked(true);
                    op4.setChecked(true);
                    e.setChecked(true);

                    op1.setChecked(false);
                    op2.setChecked(false);
                    op3.setChecked(false);
                    op4.setChecked(false);
                    e.setChecked(false);

                    puntajeI = puntajeI + 50;

                    mp2.start();
                } else if (!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()) {

                } else {
                    vidas = vidas - 1;
                    if (vidas == 2) {
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else if (vidas == 1) {
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else {
                        int id = getResources().getIdentifier("perdiste", "drawable", getPackageName());
                        iv.setImageResource(id);

                        vidaiv.setVisibility(View.INVISIBLE);
                        rg.setVisibility(View.GONE);
                        tv.setText("Te quedaste sin vidas!");
                        tv.setVisibility(View.VISIBLE);
                        pun.setText("Puntaje: " + puntajeI);
                        pun.setVisibility(View.VISIBLE);
                        bt.setText("Menu");

                        mp.stop();
                        mp.release();

                        mp5 = MediaPlayer.create(this, R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
                }
            } else if (lvl == 5) {
                if (op3.isChecked()) {
                    lvl++;
                    int id = getResources().getIdentifier("shengo", "drawable", getPackageName());
                    iv.setImageResource(id);

                    puntajeI = puntajeI + 50;

                    rg.setVisibility(View.GONE);
                    pun.setText("Puntaje: " + puntajeI);
                    pun.setVisibility(View.VISIBLE);
                    tv.setText("Ganaste!!!");
                    tv.setVisibility(View.VISIBLE);
                    bt.setText("Siguiente!");

                    mp2.start();

                    mp.stop();
                    mp.release();

                    mp6 = MediaPlayer.create(this, R.raw.ganar);
                    mp6.start();
                } else if (!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()) {

                } else {
                    vidas = vidas - 1;
                    if (vidas == 2) {
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else if (vidas == 1) {
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    } else {
                        int id = getResources().getIdentifier("perdiste", "drawable", getPackageName());
                        iv.setImageResource(id);

                        vidaiv.setVisibility(View.INVISIBLE);
                        rg.setVisibility(View.GONE);
                        tv.setText("Te quedaste sin vidas!");
                        tv.setVisibility(View.VISIBLE);
                        pun.setText("Puntaje: " + puntajeI);
                        pun.setVisibility(View.VISIBLE);
                        bt.setText("Menu");

                        mp.stop();
                        mp.release();

                        mp5 = MediaPlayer.create(this, R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }


        }

    }
}

