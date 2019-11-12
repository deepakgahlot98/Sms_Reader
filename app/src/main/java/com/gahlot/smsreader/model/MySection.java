package com.gahlot.smsreader.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gahlot.smsreader.R;
import com.gahlot.smsreader.adapters.FooterViewHolder;
import com.gahlot.smsreader.adapters.HeaderViewHolder;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class MySection extends Section {

    private final String title;
    private final List<Sms> list;

    /**
     * Create a Section object based on {@link SectionParameters}.
     * @param title
     * @param list
     */
    public MySection(String title, List<Sms> list) {
        super(SectionParameters.builder()
        .itemResourceId(R.layout.section_itemlist)
        .headerResourceId(R.layout.section_header)
        .build());
        this.title = title;
        this.list = list;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final ItemViewHolder itemHolder = (ItemViewHolder) viewHolder;

        final Sms sms = list.get(i);

        itemHolder.smsHeader.setText(sms.getMsg());
//        itemHolder.rootView.setOnClickListener(v ->
//                clickListener.onItemRootViewClicked(title, itemHolder.getAdapterPosition())
//        );
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);
    }


    final class ItemViewHolder extends RecyclerView.ViewHolder {

        final View rootView;
        final TextView smsHeader;

        ItemViewHolder(@NonNull final View view) {
            super(view);

            rootView = view;
            smsHeader = view.findViewById(R.id.tvHeader);
        }
    }
}
