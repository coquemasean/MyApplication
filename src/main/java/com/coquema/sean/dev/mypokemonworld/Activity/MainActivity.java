package com.coquema.sean.dev.mypokemonworld.Activity;

import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coquema.sean.dev.mypokemonworld.BroadcastReciver.AlarmReceiver;
import com.coquema.sean.dev.mypokemonworld.Common.Common;
import com.coquema.sean.dev.mypokemonworld.R;

import java.util.Random;

/**
 * Created by Sean Coquema on 05/03/2019.
 *
 */

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mListLayout;

    private ImageView mProfileImage;
    private TextView mNameText;
    private TextView mDescText;

    BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Register Alarm!
        registerAlarm();

        registrationNotification();

        mListLayout = (RelativeLayout) findViewById(R.id.list_Layout);
        mProfileImage = (ImageView) findViewById(R.id.profile_image);
        mNameText = (TextView) findViewById(R.id.profile_name);
        mDescText = (TextView) findViewById(R.id.profile_desc);

        mListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharedIntent = new Intent( MainActivity.this, SharedActivity.class);

                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View, String>(mProfileImage, "imageTransition");
                pairs[1] = new Pair<View, String>(mNameText, "nameTransition");
                pairs[2] = new Pair<View, String>(mDescText, "descTransition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);


                startActivity(sharedIntent, options.toBundle());
            }
        });

    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter("registrationComplete"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Common.STR_PUSH));
    }

    private void registrationNotification() {

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(Common.STR_PUSH))
                {
                    String message = intent.getStringExtra("message");
                    showNotification("Pokemon by Sean", message);
                }
            }
        };
    }

    private void showNotification(String title, String message) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());

    }

    private void registerAlarm() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 13);
            calendar.set(Calendar.MINUTE, 39);
            calendar.set(Calendar.SECOND, 0);

            Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_ultimo) {
            //
            Intent intent = new Intent( this, InsertData.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

