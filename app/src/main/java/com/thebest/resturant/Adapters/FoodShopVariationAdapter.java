package com.thebest.resturant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import com.thebest.resturant.Models.Responses.FoodShopsItemAttributes;
import com.thebest.resturant.R;
import com.thebest.resturant.listenToDeleteVariation;


public class FoodShopVariationAdapter extends RecyclerView.Adapter<FoodShopVariationAdapter.ViewHolder> {
    private Context mContext;
    boolean editing;
    private List<FoodShopsItemAttributes> mList;
    listenToDeleteVariation listener;
    int type;
    public FoodShopVariationAdapter(Context context, List<FoodShopsItemAttributes> list, boolean editing , listenToDeleteVariation listener,int type) {
        this.mContext = context;
        this.mList = list;
        this.editing = editing;
        this.listener = listener;
        this.type = type;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.variation_row_product, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FoodShopsItemAttributes item = mList.get(position);
        holder.ivname.setText(item.getName_en()+"-"+item.getName_ar());
        holder.price.setText(item.getPrice()+" KWD");
        if (editing) {
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(view -> {
                listener.deleteVariation(position,type);
            });
        }
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView price;
        private TextView ivname;
        private ImageView delete;
        public ViewHolder(View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.delete);
            price = itemView.findViewById(R.id.price);
            ivname = itemView.findViewById(R.id.text);

        }
    }
}