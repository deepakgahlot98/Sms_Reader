package com.gahlot.smsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;

import com.gahlot.SmsReader;
import com.gahlot.smsreader.adapters.RecyclerAdapter;
import com.gahlot.smsreader.model.Sms;
import com.gahlot.smsreader.viewmodel.MainAcitivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private MainAcitivityViewModel mMainAcitivityViewModel;
    private static final int REQUEST_READ_SMS = 0;
    private SmsBroadcastReceiver receiver;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        receiver = new SmsBroadcastReceiver();
        notificationManagerCompat = NotificationManagerCompat.from(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS},REQUEST_READ_SMS);

        } else {
            mMainAcitivityViewModel = ViewModelProviders.of(this).get(MainAcitivityViewModel.class);

            mMainAcitivityViewModel.getSms().observe(this, new Observer<List<Sms>>() {
                @Override
                public void onChanged(List<Sms> sms) {
                            mAdapter.notifyDataSetChanged();
                        }
            });

            initRecyclerView();
        }
        Intent intent =
                new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                this.getPackageName());
        startActivity(intent);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String messageContent = extras.getString("msgContent");
            String messageSender = extras.getString("sender");
            onSmsReceived(messageSender,messageContent);
        }
    }

    private void initRecyclerView() {
        mAdapter = new RecyclerAdapter(this, mMainAcitivityViewModel.getSms().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case REQUEST_READ_SMS:  {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMainAcitivityViewModel = ViewModelProviders.of(this).get(MainAcitivityViewModel.class);

                    mMainAcitivityViewModel.getSms().observe(this, new Observer<List<Sms>>() {
                        @Override
                        public void onChanged(List<Sms> sms) {
                            mAdapter.notifyDataSetChanged();
                        }
                    });

                    initRecyclerView();
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_SMS},REQUEST_READ_SMS);
                }
            }
        }
    }

    public void onSmsReceived(String sender, String message) {
        Log.d(TAG, "onSmsReceived: " + sender + message);
        Notification notification = new NotificationCompat.Builder(this,SmsReader.CHANNEL_1_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(sender)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }
}
