package com.gahlot.smsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AttributeSet;

import com.gahlot.smsreader.adapters.RecyclerAdapter;
import com.gahlot.smsreader.model.MySection;
import com.gahlot.smsreader.model.Sms;
import com.gahlot.smsreader.utils.RelativeTime;
import com.gahlot.smsreader.viewmodel.MainAcitivityViewModel;

import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {


    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SectionedRecyclerViewAdapter sectionedAdapter;
    private MainAcitivityViewModel mMainAcitivityViewModel;
    private static final int REQUEST_READ_SMS = 0;
    private RelativeTime relativeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        sectionedAdapter = new SectionedRecyclerViewAdapter();
        relativeTime = new RelativeTime();

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
}
