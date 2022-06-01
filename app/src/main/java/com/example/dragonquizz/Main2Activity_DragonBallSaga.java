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

public class Main2Activity_DragonBallSaga extends AppCompatActivity {

    private MediaPlayer mp;
    private RadioButton op1,op2,op3,op4,e;
    private ImageView iv,vidaiv;
    private Button bt;
    private RadioGroup rg ;
    private TextView tv,nom,vida,pun;
    private MediaPlayer mp2,mp3,mp4,mp5;

    int lvl = 1;
    int puntajeI = 0 , vidas = 3;
    String jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__dragon_ball_saga);

        jugador = getIntent().getStringExtra("nombre");

        vidaiv = findViewById(R.id.ballv);
        nom = findViewById(R.id.jugador1);
        vida = findViewById(R.id.vidas1);
        pun = findViewById(R.id.puntos1);
        e = findViewById(R.id.e1);
        op1 = findViewById(R.id.radioButton);
        op2 = findViewById(R.id.radioButton2);
        op3 = findViewById(R.id.radioButton3);
        op4 = findViewById(R.id.radioButton4);
        iv = findViewById(R.id.ball);
        bt = findViewById(R.id.button2);
        rg = findViewById(R.id.radioGroup3);
        tv = findViewById(R.id.textView2);

        nom.setText("Jugador: " + jugador);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.lvl1);
        this.setTitle("  Dragon Ball Saga");



        mp = MediaPlayer.create(this, R.raw.juegodb);
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


                Intent intent = new Intent(this, Main3Activity_SaiyanSaga.class);

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
                    int id = getResources().getIdentifier("taopaipai", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Karin");
                    op2.setText("Tao Pai Pai");
                    op3.setText("Ten Shin Han");
                    op4.setText("Maestro Roshi");

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
                    int id = getResources().getIdentifier("a8", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Androide 16");
                    op2.setText("Androide R.R");
                    op3.setText("Androide 8");
                    op4.setText("Androide 18");

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
                    int id = getResources().getIdentifier("reypicolo", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Evil Man");
                    op2.setText("Celula");
                    op3.setText("Rey Ox Satan");
                    op4.setText("Rey Demonio Piccolo");

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
                    int id = getResources().getIdentifier("gokuninojuego", "drawable", getPackageName());
                    iv.setImageResource(id);
                    op1.setText("Goten");
                    op2.setText("Goku Niño");
                    op3.setText("Krilin");
                    op4.setText("Gohan Niño");

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

                    puntajeI = puntajeI + 50;
                    lvl++;
                    int id = getResources().getIdentifier("nivel1", "drawable", getPackageName());
                    iv.setImageResource(id);

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

