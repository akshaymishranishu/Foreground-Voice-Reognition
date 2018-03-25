package hu.pe.yummykart.broadcastdemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SendBroadcast extends AppCompatActivity
{

    private static final int REQUEST_AUDIO_PERMISSIONS = 100;

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);
        btn1 = (Button)findViewById(R.id.btn1);

        requestForAudioPermission();



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               /* Intent intent = new Intent("SENDMSG");
                intent.putExtra("message", "broadcast received");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);*/
                Intent intent = new Intent(getApplicationContext(), ServiceClass.class);
                startService(intent);
            }
        });
    }

    public boolean checkForAudioPermissions(Context context)
    {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestForAudioPermission()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO_PERMISSIONS);
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
