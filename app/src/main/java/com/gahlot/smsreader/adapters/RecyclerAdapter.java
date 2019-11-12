package com.gahlot.smsreader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gahlot.smsreader.R;
import com.gahlot.smsreader.model.Sms;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Sms> mSms = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context context, List<Sms> nicePlaces) {
        mSms = nicePlaces;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        // Set the header of the 'sms'
        ((ViewHolder) viewHolder).mHeader.setText(mSms.get(i).getAddress());

        // Set the body of the Sms
        ((ViewHolder) viewHolder).mBody.setText(mSms.get(i).getMsg());
    }

    @Override
    public int getItemCount() {
        return mSms.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mHeader;
        private TextView mBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeader = itemView.findViewById(R.id.sms_header);
            mBody = itemView.findViewById(R.id.sms_body);
        }
    }
}
