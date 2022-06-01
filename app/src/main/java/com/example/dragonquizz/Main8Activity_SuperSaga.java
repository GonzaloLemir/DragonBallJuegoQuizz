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

public class Main8Activity_SuperSaga extends AppCompatActivity {

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
        setContentView(R.layout.activity_main8__super_saga);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.lvl7);
        this.setTitle("  Super Saga");

        jugador = getIntent().getStringExtra("jugador");
        vidas = getIntent().getIntExtra("vidas", vidas);
        puntajeI = getIntent().getIntExtra("puntos", puntajeI);

        vidaiv = findViewById(R.id.superv);
        nom = findViewById(R.id.jugador7);
        vida = findViewById(R.id.vidas7);
        pun = findViewById(R.id.puntos7);
        e = findViewById(R.id.e7);
        iv = findViewById(R.id.superr);
        tv = findViewById(R.id.textView8);
        op1 = findViewById(R.id.radioButton25);
        op2 = findViewById(R.id.radioButton26);
        op3 = findViewById(R.id.radioButton27);
        op4 = findViewById(R.id.radioButton28);
        rg = findViewById(R.id.radioGroup8);
        bt = findViewById(R.id.button8);

        nom.setText("Jugador: " + jugador);
        if(vidas == 2){
            int id = getResources().getIdentifier("lifes2", "drawable", getPackageName());
            vidaiv.setImageResource(id);
        }
        else if(vidas == 1){
            int id = getResources().getIdentifier("lifes1", "drawable", getPackageName());
            vidaiv.setImageResource(id);
        }

        mp = MediaPlayer.create(this,R.raw.juegosuper);
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


                Intent intent = new Intent(this, Main9Activity_Peliculas.class);

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
                if(op1.isChecked()){
                    lvl++;
                    int id = getResources().getIdentifier("bills2", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Kaio Sama");
                    op2.setText("Bills");
                    op3.setText("Whiss");
                    op4.setText("Supremo Kaio");

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
                if(op2.isChecked()){
                    lvl++;
                    int id = getResources().getIdentifier("goldenfrezer", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Mecha Freezer");
                    op2.setText("Freezer Final");
                    op3.setText("Golden Freezer");
                    op4.setText("Cooler");

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
                if(op3.isChecked()){
                    lvl++;
                    int id = getResources().getIdentifier("jiren", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Black");
                    op2.setText("Topo");
                    op3.setText("Hit");
                    op4.setText("Jiren");

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
                if(op4.isChecked()){
                    lvl++;
                    int id = getResources().getIdentifier("doctrinajuego", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Goku Mistico");
                    op2.setText("Goku Ultra Instinto");
                    op3.setText("Goku SS Dios");
                    op4.setText("Goku Definitivo");

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
                if(op2.isChecked()) {
                    lvl++;
                    int id = getResources().getIdentifier("esferas", "drawable", getPackageName());
                    iv.setImageResource(id);

                    puntajeI = puntajeI + 50;

                    rg.setVisibility(View.GONE);
                    pun.setText("Puntaje: " + puntajeI);
                    pun.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
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
