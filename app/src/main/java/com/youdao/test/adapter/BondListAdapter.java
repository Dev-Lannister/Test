package com.youdao.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youdao.test.R;
import com.youdao.test.model.bean.BondBean;

import java.util.ArrayList;
import java.util.List;

public class BondListAdapter extends RecyclerView.Adapter<BondListAdapter.BondViewHolder> {

    private List<BondBean> mDatas = new ArrayList<>();

    public void addAllData(List<BondBean> datas) {
        mDatas.addAll(datas);
    }

    @NonNull
    @Override
    public BondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_bond_list_item, parent, false);
        return new BondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BondViewHolder holder, int position) {
        holder.tvName.setText(mDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class BondViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public BondViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }

    }
}
