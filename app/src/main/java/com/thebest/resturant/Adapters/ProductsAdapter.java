package com.thebest.resturant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thebest.resturant.Models.Responses.OrderItemsModel;
import com.thebest.resturant.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private Context mContext;
    private List<OrderItemsModel> mList;

    public ProductsAdapter(Context context, List<OrderItemsModel> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.resturant_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final OrderItemsModel item = mList.get(position);
        holder.name.setText(item.getName());
        holder.quantity.setText(item.getCount()+"");
        holder.price.setText(item.getPrice()+"");
        Glide.with(mContext).load(item.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView quantity;
        private TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_cart_product_image);
            name = itemView.findViewById(R.id.tv_cart_product_name);
            quantity = itemView.findViewById(R.id.np_cart_product_quantity);
            price = itemView.findViewById(R.id.tv_cart_product_price);
        }
    }
}