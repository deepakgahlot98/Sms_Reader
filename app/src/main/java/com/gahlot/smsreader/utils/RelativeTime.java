package com.gahlot.smsreader.utils;

import android.text.format.DateUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gahlot.smsreader.model.Sms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RelativeTime {

    private static String onehour = "1 hour ago";
    private static String twohour = "2 hour ago";
    private static String threehour = "3 hour ago";
    private static String sixhour = "6 hour ago";
    private static String twelevehour = "12 hour ago";
    private static String oneday = "1 day ago";

    public List<String> oneHourAgo = new ArrayList<>();
    public List<String> twoHourAgo = new ArrayList<>();
    public List<String> threeHourAgo = new ArrayList<>();
    public List<String> sixHourAgo = new ArrayList<>();
    public List<String> twveleHourAgo = new ArrayList<>();
    public List<String> onedayAgo = new ArrayList<>();

    public List<String> addOneHourAgo(List<Sms> sms) {
        if (sms != null) {
            for (int i = 0; i < sms.size(); i++) {
                System.out.println(DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())));
                if (DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())).equals(onehour)) {
                    oneHourAgo.add(sms.get(i).getMsg());
                }
            }
            return oneHourAgo;
        }
        return null;
    }

    public List<String> addOTwoHourAgo(List<Sms> sms) {
        if (sms != null) {
            for (int i = 0; i < sms.size(); i++) {
                System.out.println(DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())));
                if (DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())).equals(twohour)) {
                    twoHourAgo.add(sms.get(i).getMsg());
                }
            }
            return twoHourAgo;
        }
        return null;
    }

    public List<String> addThreeHourAgo(List<Sms> sms) {
        if (sms != null) {
            for (int i = 0; i < sms.size(); i++) {
                System.out.println(DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())));
                if (DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())).equals(threehour)) {
                    threeHourAgo.add(sms.get(i).getMsg());
                }
            }
            return threeHourAgo;
        }
        return null;
    }

    public List<String> addSixHourAgo(List<Sms> sms) {
        if (sms != null) {
            for (int i = 0; i < sms.size(); i++) {
                System.out.println(DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())));
                if (DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())).equals(sixhour)) {
                    sixHourAgo.add(sms.get(i).getMsg());
                }
            }
            return sixHourAgo;
        }
        return null;
    }

    public List<String> addtweleveHourAgo(List<Sms> sms) {
        if (sms != null) {
            for (int i = 0; i < sms.size(); i++) {
                System.out.println(DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())));
                if (DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())).equals(twelevehour)) {
                    twveleHourAgo.add(sms.get(i).getMsg());
                }
            }
            return twveleHourAgo;
        }
        return null;
    }

    public List<String> addOneDayAgo(List<Sms> sms) {
        if (sms != null) {
            for (int i = 0; i < sms.size(); i++) {
                System.out.println(DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())));
                if (DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())).equals(oneday)) {
                    onedayAgo.add(sms.get(i).getMsg());
                }
            }
            return onedayAgo;
        }
        return null;
    }

}
