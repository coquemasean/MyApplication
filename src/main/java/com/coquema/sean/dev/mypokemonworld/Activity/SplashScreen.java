package com.coquema.sean.dev.mypokemonworld.Activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.coquema.sean.dev.mypokemonworld.R;
import com.felipecsl.gifimageview.library.GifImageView;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Sean Coquema on 05/03/2019.
 *
 */

public class SplashScreen extends AppCompatActivity implements Animator.AnimatorListener {

    private GifImageView gifImageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gifImageView = (GifImageView) findViewById(R.id.gifImageView);
        /*progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(progressBar.VISIBLE);
        */
        //Set GIFImageView resource
        try {
            InputStream inputStream = getAssets().open("electro.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        } catch (IOException ex) {

        }

        //Wait for 3 seconds and start Activity Main
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //SplashScreen.this.startActivity(new Intent(SplashScreen.this, MainActivity.class));
                SplashScreen.this.finish();
            }
        }, 2000); // 3000 = 3seconds

        Animation();
    }

    private void Animation(){

        Animator anim = AnimatorInflater.loadAnimator(this,
                R.animator.splash_animator);
        anim.addListener(this);
        anim.start();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);
        overridePendingTransition(R.transition.entrada_izquierda, R.transition.salida_derecha);
        finish();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}