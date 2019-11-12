package com.gahlot.smsreader.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gahlot.smsreader.R;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    public final TextView tvTitle;

    public HeaderViewHolder(@NonNull final View view) {
        super(view);

        tvTitle = view.findViewById(R.id.tvTitle);
    }

}
