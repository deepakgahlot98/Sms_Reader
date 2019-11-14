package com.gahlot.smsreader.repositories;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.text.format.DateUtils;

import androidx.lifecycle.MutableLiveData;

import com.gahlot.smsreader.model.Sms;
import com.gahlot.smsreader.utils.RelativeTime;

import java.util.ArrayList;
import java.util.HashMap;
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
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();

        Sms objSms = new Sms();
        Uri message = Uri.parse("content://sms/");
        ContentResolver cr =application.getContentResolver();

        Cursor c = cr.query(message, null, null, null, null);
        int totalSMS = c.getCount();

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {

                objSms = new Sms();
                long time = Long.valueOf(c.getString(c.getColumnIndexOrThrow("date")));
                if (DateUtils.getRelativeTimeSpanString(time).toString().contains(RelativeTime.recent)) {
                    Sms header = objSms.createSection(RelativeTime.onehour);
                    Sms item = objSms.createRow(c.getString(c.getColumnIndexOrThrow("_id")),c.getString(c
                                    .getColumnIndexOrThrow("address")),c.getString(c.getColumnIndexOrThrow("body")),
                            c.getString(c.getColumnIndexOrThrow("date")));
                    if (map.containsKey(RelativeTime.recent) && map.get(RelativeTime.recent)) {
                        lstSms.add(item);
                    } else {
                        map.put(RelativeTime.recent,true);
                        lstSms.add(header);
                        lstSms.add(item);
                    }
                }
                else if (DateUtils.getRelativeTimeSpanString(time).equals(RelativeTime.onehour)) {
                    Sms header = objSms.createSection(RelativeTime.onehour);
                    Sms item = objSms.createRow(c.getString(c.getColumnIndexOrThrow("_id")),c.getString(c
                            .getColumnIndexOrThrow("address")),c.getString(c.getColumnIndexOrThrow("body")),
                            c.getString(c.getColumnIndexOrThrow("date")));
                    if (map.containsKey(RelativeTime.onehour) && map.get(RelativeTime.onehour)) {
                        lstSms.add(item);
                    } else {
                        map.put(RelativeTime.onehour,true);
                        lstSms.add(header);
                        lstSms.add(item);
                    }

                } else if (DateUtils.getRelativeTimeSpanString(Long.valueOf(c.getString(c.getColumnIndexOrThrow("date")))).equals(RelativeTime.twohour)) {
                    Sms header = objSms.createSection(RelativeTime.twohour);
                    Sms item = objSms.createRow(c.getString(c.getColumnIndexOrThrow("_id")),c.getString(c
                                    .getColumnIndexOrThrow("address")),c.getString(c.getColumnIndexOrThrow("body")),
                            c.getString(c.getColumnIndexOrThrow("date")));
                    if (map.containsKey(RelativeTime.twohour) && map.get(RelativeTime.twohour)) {
                        lstSms.add(item);
                    } else {
                        map.put(RelativeTime.twohour,true);
                        lstSms.add(header);
                        lstSms.add(item);
                    }
                } else if (DateUtils.getRelativeTimeSpanString(Long.valueOf(c.getString(c.getColumnIndexOrThrow("date")))).equals(RelativeTime.threehour)) {
                    Sms header = objSms.createSection(RelativeTime.threehour);
                    Sms item = objSms.createRow(c.getString(c.getColumnIndexOrThrow("_id")),c.getString(c
                                    .getColumnIndexOrThrow("address")),c.getString(c.getColumnIndexOrThrow("body")),
                            c.getString(c.getColumnIndexOrThrow("date")));
                    if (map.containsKey(RelativeTime.threehour) && map.get(RelativeTime.threehour)) {
                        lstSms.add(item);
                    } else {
                        map.put(RelativeTime.threehour,true);
                        lstSms.add(header);
                        lstSms.add(item);
                    }
                } else if (DateUtils.getRelativeTimeSpanString(Long.valueOf(c.getString(c.getColumnIndexOrThrow("date")))).equals(RelativeTime.sixhour)) {
                    Sms header = objSms.createSection(RelativeTime.sixhour);
                    Sms item = objSms.createRow(c.getString(c.getColumnIndexOrThrow("_id")),c.getString(c
                                    .getColumnIndexOrThrow("address")),c.getString(c.getColumnIndexOrThrow("body")),
                            c.getString(c.getColumnIndexOrThrow("date")));
                    if (map.containsKey(RelativeTime.threehour) && map.get(RelativeTime.threehour)) {
                        lstSms.add(item);
                    } else {
                        map.put(RelativeTime.threehour,true);
                        lstSms.add(header);
                        lstSms.add(item);
                    }
                } else if (DateUtils.getRelativeTimeSpanString(Long.valueOf(c.getString(c.getColumnIndexOrThrow("date")))).equals(RelativeTime.twelevehour)) {
                    Sms header = objSms.createSection(RelativeTime.twelevehour);
                    Sms item = objSms.createRow(c.getString(c.getColumnIndexOrThrow("_id")),c.getString(c
                                    .getColumnIndexOrThrow("address")),c.getString(c.getColumnIndexOrThrow("body")),
                            c.getString(c.getColumnIndexOrThrow("date")));
                    if (map.containsKey(RelativeTime.twelevehour) && map.get(RelativeTime.twelevehour)) {
                        lstSms.add(item);
                    } else {
                        map.put(RelativeTime.twelevehour,true);
                        lstSms.add(header);
                        lstSms.add(item);
                    }
                } else if (DateUtils.getRelativeTimeSpanString(Long.valueOf(c.getString(c.getColumnIndexOrThrow("date")))).equals(RelativeTime.oneday)) {
                    Sms header = objSms.createSection(RelativeTime.oneday);
                    Sms item = objSms.createRow(c.getString(c.getColumnIndexOrThrow("_id")),c.getString(c
                                    .getColumnIndexOrThrow("address")),c.getString(c.getColumnIndexOrThrow("body")),
                            c.getString(c.getColumnIndexOrThrow("date")));
                    if (map.containsKey(RelativeTime.oneday) && map.get(RelativeTime.oneday)) {
                        lstSms.add(item);
                    } else {
                        map.put(RelativeTime.oneday,true);
                        lstSms.add(header);
                        lstSms.add(item);
                    }
                }
                c.moveToNext();
            }
        }
        // else {
        // throw new RuntimeException("You have no SMS");
        // }
        c.close();
        data.setValue(lstSms);
        return data;

    }

}
