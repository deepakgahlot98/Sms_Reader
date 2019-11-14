package com.gahlot.smsreader.adapters;

import android.content.Context;
import android.text.format.DateUtils;
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

    private List<Sms> mSms;
    private Context mContext;

    public RecyclerAdapter(Context context, List<Sms> sms) {
        mSms = sms;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_layout,viewGroup, false);
            SectionViewHolder vh = new SectionViewHolder(view);
            return vh;
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
            RowViewHolder vh = new RowViewHolder(view);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Sms item = mSms.get(i);
        if (item.isSubItem()) {
            RowViewHolder h = (RowViewHolder) viewHolder;
            h.mHeader.setText(mSms.get(i).getAddress());
            h.mBody.setText(mSms.get(i).getMsg());
        } else {
            SectionViewHolder h = (SectionViewHolder) viewHolder;
            //h.textView.setText(DateUtils.getRelativeTimeSpanString(Long.parseLong(mSms.get(i).getTime())));
            h.textView.setText(mSms.get(i).getTime());
        }

        // Set the header of the 'sms'
        //((ViewHolder) viewHolder).mHeader.setText(mSms.get(i).getAddress());

        // Set the body of the Sms
        //((ViewHolder) viewHolder).mBody.setText(mSms.get(i).getMsg());
    }

    @Override
    public int getItemCount() {
        return mSms.size();
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        Sms item = mSms.get(position);
            if (!item.isSubItem()) {
            return 0;
        } else {
            return 1;
        }
    }

    public class RowViewHolder extends RecyclerView.ViewHolder{
        private TextView mHeader;
        private TextView mBody;
        public RowViewHolder(View itemView) {
            super(itemView);
            mHeader = itemView.findViewById(R.id.sms_header);
            mBody = itemView.findViewById(R.id.sms_body);
        }
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public SectionViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.header_id);
        }
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
