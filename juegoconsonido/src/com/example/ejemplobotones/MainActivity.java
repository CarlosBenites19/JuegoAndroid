package com.example.ejemplobotones;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity{
	Button[] botones;
	boolean[] r = new boolean[25];
	Random random = new Random();
	int conta = 1;
	TextView mensajecuenta;
	SoundPool sp;
	int flujodemusica = 0, flujodemusica2 = 0, flujodemusica3 = 0, flujodemusica4 = 0, flujodemusica5 = 0;
	private MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		botones = new Button[25];

		botones[0] = (Button) findViewById(R.id.boton1);
		botones[1] = (Button) findViewById(R.id.boton2);
	    botones[2] = (Button) findViewById(R.id.boton3);
		botones[3] = (Button) findViewById(R.id.boton4);
		botones[4] = (Button) findViewById(R.id.boton5);
		botones[5] = (Button) findViewById(R.id.boton6);
		botones[6] = (Button) findViewById(R.id.boton7);
		botones[7] = (Button) findViewById(R.id.boton8);
		botones[8] = (Button) findViewById(R.id.boton9);
		botones[9] = (Button) findViewById(R.id.boton10);
		botones[10] = (Button) findViewById(R.id.boton11);
		botones[11] = (Button) findViewById(R.id.boton12);
		botones[12] = (Button) findViewById(R.id.boton13);
		botones[13] = (Button) findViewById(R.id.boton14);
		botones[14] = (Button) findViewById(R.id.boton15);
		botones[15] = (Button) findViewById(R.id.boton16);
		botones[16] = (Button) findViewById(R.id.boton17);
		botones[17] = (Button) findViewById(R.id.boton18);
		botones[18] = (Button) findViewById(R.id.boton19);
		botones[19] = (Button) findViewById(R.id.boton20);
		botones[20] = (Button) findViewById(R.id.boton21);
		botones[21] = (Button) findViewById(R.id.boton22);
		botones[22] = (Button) findViewById(R.id.boton23);
		botones[23] = (Button) findViewById(R.id.boton24);
		botones[24] = (Button) findViewById(R.id.boton25);
		esperar();
		acomodar();
		sp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		flujodemusica = sp.load(this, R.raw.bassdrum01,1);
		flujodemusica2 = sp.load(this, R.raw.dong01,1);
		flujodemusica3 = sp.load(this, R.raw.explode01,1);
		flujodemusica4 = sp.load(this, R.raw.kick01,1);
		flujodemusica5 = sp.load(this, R.raw.crash02, 1);
	}
	public void esperar() {
		botonesEnabled(false);

		new CountDownTimer(5000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				TextView mensaje_inicio = (TextView) findViewById(R.id.mensajecuenta);
				mensaje_inicio.setVisibility(View.VISIBLE);
				mensaje_inicio.setText("¡Vamos! "+ (millisUntilFinished / 1000));				
				sp.play(flujodemusica4, 1, 1, 0, 0, 1);
				
			}

			@Override
			public void onFinish() {
				TextView mensaje_inicio = (TextView) findViewById(R.id.mensajecuenta);
				mensaje_inicio.setVisibility(View.INVISIBLE);
				botonesEnabled(true);
				sp.play(flujodemusica5, 1, 1, 0, 0, 1);
				mp = MediaPlayer.create(MainActivity.this, R.raw.tom_me_3_128_0);
				mp.start();	
				mp.setLooping(true);
				mp.setVolume(0.05f, 0.05f);
			}

		}.start();
	}
	public void botonesEnabled(boolean opcion) {

		if (opcion == true) {
			for (int i = 0; i < botones.length; i++) {
				botones[i].setEnabled(true);
			}
		} else {
			for (int i = 0; i < botones.length; i++) {
				botones[i].setEnabled(false);
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			if(mp.isPlaying() == true){
				mp.pause();
			}else if(mp.isPlaying() == false){		
			mp.start();
			}
		}
		return super.onOptionsItemSelected(item);
	}

	public void acomodar() {
		boolean[] repetir = new boolean[25];
		for (int j = 0; j < botones.length; j++) {
			int rando = random.nextInt(25);

			if (repetir[rando] == false) {
				repetir[rando] = true;
			} else {
				while (repetir[rando] == true) {
					rando = random.nextInt(25);
				}
				repetir[rando] = true;
			}

			String mostar = String.valueOf(rando + 1);
			botones[j].setText(mostar);
		}
	}


	public void onClick(View v) {
		int numero = 0;

		for (int i = 0; i < botones.length; i++) {
			if (v.getId() == botones[i].getId()) {
				numero = Integer.parseInt(botones[i].getText().toString());
				if (conta == numero) {
					botones[i].setEnabled(false);
					sp.play(flujodemusica, 1, 1, 0, 0, 1);
					conta++;
				} else {
					Toast toas = Toast.makeText(this, "Game Over :(",
							Toast.LENGTH_SHORT);
					toas.show();
					sp.play(flujodemusica3, 1, 1, 0, 0, 1);
					mp.stop();
					esperar();
					conta = 1;
					acomodar();
				}
			}
		}
		if (conta > 25) {
			Toast toas = Toast.makeText(this, "¡Eres el mejor!",
					Toast.LENGTH_SHORT);
			toas.show();
			sp.play(flujodemusica2, 1, 1, 0, 0, 1);
			mp.stop();
			for (int j = 0; j < botones.length; j++) {
				
			}esperar();
			conta= 1;
			acomodar();
		}
	}	
}
