package com.thebest.resturant.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thebest.resturant.Activities.AllProducts;
import com.thebest.resturant.Activities.EditProduct;
import com.thebest.resturant.Models.Responses.MyMenusItemModel;
import com.thebest.resturant.R;
import com.thebest.resturant.listenToOpenEdit;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class FoodShopCategoryAdapter extends RecyclerView.Adapter<FoodShopCategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<MyMenusItemModel> mList;
    listenToOpenEdit listener;
    public FoodShopCategoryAdapter(Context context, List<MyMenusItemModel> list,listenToOpenEdit listener) {
        this.mContext = context;
        this.mList = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MyMenusItemModel item = mList.get(position);
        holder.ivname.setText(item.getName());
        Glide.with(mContext).load(item.getImage()).into(holder.ivimage);
        holder.itemView.findViewById(R.id.container).setOnClickListener(view -> {
            Intent intent = new Intent(mContext, AllProducts.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", mList.get(position));
            intent.putExtras(bundle);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        });
        holder.itemView.findViewById(R.id.delete).setOnClickListener(view -> {
            listener.onlistenToDelete(position);
        });
        holder.itemView.findViewById(R.id.edit).setOnClickListener(view -> listener.onlistenToEdit(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //TODO Bind views
        private TextView ivname;
        private ImageView ivimage;

        public ViewHolder(View itemView) {
            super(itemView);
            ivname = itemView.findViewById(R.id.text);
            ivimage = itemView.findViewById(R.id.image);

        }
    }
}