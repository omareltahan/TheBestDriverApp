package com.thebest.resturant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thebest.resturant.R;


public class AllHowToUseAdapter extends RecyclerView.Adapter<AllHowToUseAdapter.ViewHolder> {
    private Context mContext;
    private String[] mQuestions;
    private String[] mAnswers;
    public AllHowToUseAdapter(Context context, String[] mQuestions, String[] mAnswers) {
        this.mContext = context;
        this.mAnswers = mAnswers;
        this.mQuestions = mQuestions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_how_to_use, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mQuestions[position]);
        holder.desc.setText(mAnswers[position]);
    }

    @Override
    public int getItemCount() {
        return mQuestions.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //TODO Bind views
        private TextView title,desc;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);

        }

    }
}