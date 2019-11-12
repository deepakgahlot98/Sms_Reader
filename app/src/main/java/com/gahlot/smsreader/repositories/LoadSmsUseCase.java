package com.gahlot.smsreader.repositories;

import android.content.Context;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;

import com.gahlot.smsreader.R;
import com.gahlot.smsreader.model.Sms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class LoadSmsUseCase {

    Map<String, List<Sms>> execute(@NonNull final Context context,List<Sms> sms) {

        final Map<String, List<Sms>> map = new LinkedHashMap<>();

        for(String s: context.getResources().getStringArray(R.array.relative_time)) {
            final List<Sms> filteredContacts = getOneHourAgo(sms,s);

            if (filteredContacts.size() > 0) {

                map.put(s, filteredContacts);
            }
        }
        return map;
    }

    private List<Sms> getOneHourAgo(List<Sms> sms, String timeline) {
        final List<Sms> smsList = new ArrayList<>();

        for (int i = 0; i < sms.size(); i++) {
            System.out.println(DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())));
            if (DateUtils.getRelativeTimeSpanString(Long.parseLong(sms.get(i).getTime())).equals(timeline)) {
                smsList.add(sms.get(i));
            }
        }
        return smsList;
    }
}
