package com.gahlot.smsreader.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gahlot.smsreader.model.Sms;
import com.gahlot.smsreader.repositories.SmsRepository;

import java.util.List;

public class MainAcitivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Sms>> mSms;
    private SmsRepository mRepo;

    public MainAcitivityViewModel(@NonNull Application application) {
        super(application);
        mRepo = SmsRepository.getInstance(application);
        mSms = mRepo.getSms(application);

    }

    public LiveData<List<Sms>> getSms() {
        return mSms;
    }
}
