package com.gahlot.smsreader.repositories;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;

import androidx.lifecycle.MutableLiveData;

import com.gahlot.smsreader.model.Sms;
import com.gahlot.smsreader.utils.RelativeTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SmsRepository {

    private static SmsRepository instance;
    private ArrayList<Sms> dataSet = new ArrayList<>();
    private RelativeTime relativeTime = new RelativeTime();

    public static SmsRepository getInstance(Application application){
        if(instance == null){
            instance = new SmsRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Sms>> getSms(Application application) {

        List<Sms> lstSms = new ArrayList<Sms>();
        MutableLiveData<List<Sms>> data = new MutableLiveData<>();

        Sms objSms = new Sms();
        Uri message = Uri.parse("content://sms/");
        ContentResolver cr =application.getContentResolver();

        Cursor c = cr.query(message, null, null, null, null);
        int totalSMS = c.getCount();

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {

                objSms = new Sms();
                objSms.setId(c.getString(c.getColumnIndexOrThrow("_id")));
                objSms.setAddress(c.getString(c
                        .getColumnIndexOrThrow("address")));
                objSms.setMsg(c.getString(c.getColumnIndexOrThrow("body")));
                objSms.setReadState(c.getString(c.getColumnIndex("read")));
                objSms.setTime(c.getString(c.getColumnIndexOrThrow("date")));
                if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
                    objSms.setFolderName("inbox");
                } else {
                    objSms.setFolderName("sent");
                }

                lstSms.add(objSms);
                c.moveToNext();
            }
        }
        // else {
        // throw new RuntimeException("You have no SMS");
        // }
        c.close();
        final Map<String, List<Sms>> SmsMap = new LoadSmsUseCase().execute(application,lstSms);
        data.setValue(lstSms);
        return data;

    }

}
