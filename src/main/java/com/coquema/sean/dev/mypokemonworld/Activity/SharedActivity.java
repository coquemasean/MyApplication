package com.coquema.sean.dev.mypokemonworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.coquema.sean.dev.mypokemonworld.R;

/**
 * Created by Sean Coquema on 14/03/2019.
 *
 */

public class SharedActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shared);

        mImageView = (ImageView) findViewById(R.id.shared_images);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharedIntent = new Intent( SharedActivity.this, PokemonWorld.class);
                startActivity(sharedIntent);
            }
        });

    }

}
