package com.thebest.resturant.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thebest.resturant.Activities.AllCategories;
import com.thebest.resturant.Activities.EditProduct;
import com.thebest.resturant.Activities.ProductDetails;
import com.thebest.resturant.Models.MenuModel;
import com.thebest.resturant.Models.Responses.FoodShopsCategoriesProductsitem;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class FoodProductAdapter extends RecyclerView.Adapter<FoodProductAdapter.ViewHolder> {

    private Context mContext;
    private List<FoodShopsCategoriesProductsitem> mList;

    public FoodProductAdapter(Context context, List<FoodShopsCategoriesProductsitem> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final FoodShopsCategoriesProductsitem item = mList.get(position);
        Glide.with(mContext).load(item.getImage()).into(holder.ivImage);
        holder.ivname.setText(item.getName());
        holder.price.setText(item.getPrice()+" kWD");
        holder.itemView.findViewById(R.id.container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProductDetails.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mList.get(position));
                intent.putExtras(bundle);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.itemView.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditProduct.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mList.get(position));
                intent.putExtras(bundle);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.itemView.findViewById(R.id.delete).setOnClickListener(view -> onlistenToDelete(position));

    }
    public void onlistenToDelete(int position) {
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.DeleteProduct(mList.get(position).getId())
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call,
                                           @NonNull Response<String> response) {
                        notifyItemRemoved(position);
                    }
                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        notifyItemRemoved(position);

                    }
                });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //TODO Bind views
        private ImageView ivImage;
        private TextView ivname;
        private TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.iv_cart_product_image);
            ivname = itemView.findViewById(R.id.tv_cart_product_name);
            price = itemView.findViewById(R.id.tv_cart_product_price);

        }
    }
}