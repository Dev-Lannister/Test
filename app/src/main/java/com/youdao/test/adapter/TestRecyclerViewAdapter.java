package com.youdao.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youdao.test.R;
import com.youdao.test.model.bean.LexiconEntity;
import com.youdao.test.widget.CircleProgressBar;

import java.util.ArrayList;
import java.util.List;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.TestViewHolder> {

    private List<LexiconEntity> mDatas = new ArrayList<>();
    private Context mContext;

    public TestRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void addData(LexiconEntity data) {
        mDatas.add(data);
    }

    public void addAll(List<LexiconEntity> datas) {
        mDatas.addAll(datas);
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestViewHolder(LayoutInflater.from(mContext).inflate(R.layout.lexicon_manage_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        holder.lexiconName.setText(mDatas.get(position).getName());
        holder.lexiconContent.setText(mDatas.get(position).getContent());
        holder.lexiconSize.setText(mDatas.get(position).getSize());
        holder.lexiconStatus.setText(mDatas.get(position).getStatus());
        holder.progress.setProgress(60);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {

        TextView lexiconName;
        TextView lexiconContent;
        TextView lexiconSize;
        TextView lexiconStatus;
        CircleProgressBar progress;


        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            lexiconName = itemView.findViewById(R.id.tv_dict_name);
            lexiconContent = itemView.findViewById(R.id.tv_dict_content);
            lexiconSize = itemView.findViewById(R.id.tv_lexicon_size);
            lexiconStatus = itemView.findViewById(R.id.tv_lexicon_status);
            progress = itemView.findViewById(R.id.download_progress);
        }
    }

}
