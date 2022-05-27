package com.example.myapplication;

import static com.example.myapplication.Settings.triggerRebirth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Vend_sett extends AppCompatActivity {


    private FirebaseUser user;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vend_sett);

        Button btn_Out = (Button) findViewById(R.id.vend_log_out);

        Button sound = (Button) findViewById(R.id.sound);
        Button notif = (Button) findViewById(R.id.notify);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openDialog();
            }
        });

        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openDialog2();
            }
        });

        btn_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();

                triggerRebirth(Vend_sett.this);
            }
        });
    }




    public void openDialog()
    {
        dialog_snd dl_snd=new dialog_snd();
        dl_snd.show(getSupportFragmentManager(),"example dialog");
    }

    public void openDialog2()
    {
        dialog_notif dl_not=new dialog_notif();
        dl_not.show(getSupportFragmentManager(),"example dialog");
    }

    public static void triggerRebirth(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }

}