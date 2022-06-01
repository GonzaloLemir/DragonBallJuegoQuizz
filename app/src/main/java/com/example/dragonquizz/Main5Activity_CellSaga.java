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

public class Main5Activity_CellSaga extends AppCompatActivity {

    private MediaPlayer mp,mp2,mp3,mp4,mp5;
    private TextView tv,nom,vida,pun;
    private RadioButton op1,op2,op3,op4,e;
    private ImageView iv,vidaiv;
    private RadioGroup rg;
    private Button bt;

    int lvl = 1;
    int puntajeI , vidas ;
    String jugador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5__cell_saga);

        jugador = getIntent().getStringExtra("jugador");
        vidas = getIntent().getIntExtra("vidas", vidas);
        puntajeI = getIntent().getIntExtra("puntos", puntajeI);


        vidaiv = findViewById(R.id.cellv);
        nom = findViewById(R.id.jugador4);
        vida = findViewById(R.id.vidas4);
        pun = findViewById(R.id.puntos4);
        e = findViewById(R.id.e4);
        iv = findViewById(R.id.cell);
        tv = findViewById(R.id.textView5);
        op1 = findViewById(R.id.radioButton13);
        op2 = findViewById(R.id.radioButton14);
        op3 = findViewById(R.id.radioButton15);
        op4 = findViewById(R.id.radioButton16);
        rg = findViewById(R.id.radioGroup5);
        bt = findViewById(R.id.button5);

        nom.setText("Jugador: " + jugador);
        if(vidas == 2){
            int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
            vidaiv.setImageResource(id);
        }
        else if(vidas == 1){
            int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
            vidaiv.setImageResource(id);
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.lvl4);
        this.setTitle("  Cell Saga");


        mp = MediaPlayer.create(this,R.raw.juegocell);
        mp.start();
        mp.setLooping(true);

        mp2 = MediaPlayer.create(this,R.raw.acierto);

        mp4 = MediaPlayer.create(this, R.raw.error);
    }

    public void comprobar(View view){
        if(lvl==6){

            if(vidas>0){
                mp.stop();
                mp.release();

                mp2.release();

                mp3 = MediaPlayer.create(this, R.raw.cambionivel);
                mp3.start();

                mp4.release();


                Intent intent = new Intent(this, Main6Activity_BuuSaga.class);

                intent.putExtra("jugador",jugador);
                intent.putExtra("vidas",vidas);
                intent.putExtra("puntos",puntajeI);

                startActivity(intent);

                finish();
            }
            else{
                mp5.stop();
                mp5.release();

                mp2.release();

                mp3 = MediaPlayer.create(this, R.raw.cambionivel);
                mp3.start();

                mp4.release();

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor buscador = db.rawQuery("select * from historial where puntaje = (select max(puntaje) from historial)",null);
                if(buscador.moveToFirst()){
                    String tempNombre = buscador.getString(1);
                    String tempPuntaje = buscador.getString(2);

                    int tPuntaje = Integer.parseInt(tempPuntaje);

                    if(tPuntaje<=puntajeI){
                        ContentValues modificar = new ContentValues();
                        modificar.put("nombre",jugador);
                        modificar.put("puntaje",puntajeI);

                        db.update("historial",modificar,"puntaje = " + tPuntaje, null);
                    }

                    db.close();
                }
                else{
                    ContentValues insertar = new ContentValues();
                    insertar.put("nombre",jugador);
                    insertar.put("puntaje",puntajeI);

                    db.insert("historial",null, insertar);
                }

                db.close();


                Intent intent = new Intent(this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        }
        else{

            if(lvl == 1){
                if(op2.isChecked()){
                    lvl++;
                    int id = getResources().getIdentifier("supertrunks", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Super Roshi");
                    op2.setText("Bojak");
                    op3.setText("Broly SSL");
                    op4.setText("Super Trunks");

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
                }
                else if(!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()){

                }
                else{
                    vidas = vidas - 1;
                    if(vidas == 2){
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else if(vidas == 1){
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else{
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

                        mp5 = MediaPlayer.create(this,R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }

            else if(lvl == 2){
                if(op4.isChecked()){
                    lvl++;
                    int id = getResources().getIdentifier("supervegeta", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Super Vegeta");
                    op2.setText("Vegeta SS2");
                    op3.setText("Goku SS1");
                    op4.setText("Super Trunks");

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
                }
                else if(!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()){

                }
                else{
                    vidas = vidas - 1;
                    if(vidas == 2){
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else if(vidas == 1){
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else{
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

                        mp5 = MediaPlayer.create(this,R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }

            else if(lvl == 3){
                if(op1.isChecked()){
                    lvl++;
                    int id = getResources().getIdentifier("cell", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Androide 20");
                    op2.setText("Cell Junior");
                    op3.setText("Cell");
                    op4.setText("Androide 16");

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
                }
                else if(!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()){

                }
                else{
                    vidas = vidas - 1;
                    if(vidas == 2){
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else if(vidas == 1){
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else{
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

                        mp5 = MediaPlayer.create(this,R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }

            else if(lvl == 4){
                if(op3.isChecked()){
                    lvl++;
                    int id = getResources().getIdentifier("gohanss2", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Gohan SS2");
                    op2.setText("Gohan Mistico");
                    op3.setText("Goku SS2");
                    op4.setText("Super Gohan");

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
                }
                else if(!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()){

                }
                else{
                    vidas = vidas - 1;
                    if(vidas == 2){
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else if(vidas == 1){
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else{
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

                        mp5 = MediaPlayer.create(this,R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }

            else if(lvl == 5){
                if(op1.isChecked()) {
                    lvl++;
                    int id = getResources().getIdentifier("nivel4", "drawable", getPackageName());
                    iv.setImageResource(id);

                    puntajeI = puntajeI + 50;

                    rg.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);
                    pun.setText("Puntaje: " + puntajeI);
                    pun.setVisibility(View.VISIBLE);
                    bt.setText("Siguiente!");

                    mp2.start();
                }
                else if(!op1.isChecked() && !op2.isChecked() && !op3.isChecked() && !op4.isChecked()){

                }
                else{
                    vidas = vidas - 1;
                    if(vidas == 2){
                        int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else if(vidas == 1){
                        int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
                        vidaiv.setImageResource(id);
                    }
                    else{
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

                        mp5 = MediaPlayer.create(this,R.raw.perder);
                        mp5.start();

                        lvl = 6;

                    }
                    mp4.start();
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

}
