package com.thebest.resturant.Adapters;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.thebest.resturant.Models.DateModel;
import com.thebest.resturant.Models.Responses.CurrnetTrip;
import com.thebest.resturant.Models.StatusModel;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllOrdersAdapter extends RecyclerView.Adapter<AllOrdersAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CurrnetTrip> mList;
    private boolean isNew;
    public AllOrdersAdapter(Context context, ArrayList<CurrnetTrip> mList,boolean isNew) {
        this.mContext = context;
        this.mList = mList;
        this.isNew = isNew;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.old_order_item, parent, false);
        if (isNew) {
            view = inflater.inflate(R.layout.new_order_item, parent, false);
        }

        return new ViewHolder(view);
    }
    public void ChangeOrderStatus(int position) {
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        StatusModel model = new StatusModel();
        model.setStatus("in progress");
        storeServices.ChangeOrderStatus(mList.get(position).getId(),model)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call,
                                           @NonNull Response<String> response) {
                        Toast.makeText(mContext, "تم الانتهاء من الطلب", Toast.LENGTH_LONG).show();
                        notifyItemRemoved(position);
                    }
                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        Toast.makeText(mContext, "تم الانتهاء من الطلب", Toast.LENGTH_LONG).show();
                        notifyItemRemoved(position);
                    }
                });
    }
    public void OrderDeliveryAfterMinutes(int position , int minutes) {
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.Trip_URL);
        DateModel model = new DateModel();
        model.setDate(minutes+"");
        storeServices.setScheduleTrip(mList.get(position).getId(),model)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call,
                                           @NonNull Response<String> response) {
                    }
                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                    }
                });
    }
    public void CancelOrderStatus(int position) {
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.CancelOrderStatus(mList.get(position).getId())
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call,
                                           @NonNull Response<String> response) {
                        Toast.makeText(mContext, "تم إلغاء الطلب", Toast.LENGTH_LONG).show();
                        notifyItemRemoved(position);
                    }
                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        Toast.makeText(mContext, "تم الانتهاء من الطلب", Toast.LENGTH_LONG).show();
                        notifyItemRemoved(position);
                    }
                });
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CurrnetTrip item = mList.get(position);
        holder.date.setText(item.getCreated_at().substring(0,16));
        holder.name.setText(item.getUsername());
        holder.total.setText(item.getTotal() + mContext.getResources().getString(R.string.kwd));
        if (!isNew) {
            holder.status.setText(item.getStatus());
            if (item.getStatus().equals("done"))
                holder.status.setBackground(mContext.getResources().getDrawable(R.drawable.background_green));
            else
                holder.status.setBackground(mContext.getResources().getDrawable(R.drawable.background_primary));
        }
        else
            holder.id_.setText("#"+item.getId());
        Glide.with(mContext).load(item.getImage()).into(holder.image);
        holder.arrow.setOnClickListener(view -> {
            if (holder.listItems.getVisibility() == View.VISIBLE)
                holder.listItems.setVisibility(View.GONE);
            else
                holder.listItems.setVisibility(View.VISIBLE);
        });

        ProductsAdapter adapter = new ProductsAdapter(mContext,item.getOrder_items());
        holder.listItems.setAdapter(adapter);


        if (isNew && mList.get(position).getStatus().equals("pending")) {
            final Calendar calendar = Calendar.getInstance();
            TimePickerDialog.OnTimeSetListener time = (view, hourOfDay, minute) -> {
                if (hourOfDay < calendar.get(Calendar.HOUR_OF_DAY) ||
                        (hourOfDay == calendar.get(Calendar.HOUR_OF_DAY) && minute < calendar.get(Calendar.MINUTE))){
                    ((RadioButton)holder.itemView.findViewById(R.id.idother)).setChecked(false);
                    Toast.makeText(mContext,"Choose Time after current time",Toast.LENGTH_LONG).show();

                }
                else {
                    int current_minutes = ((hourOfDay - calendar.get(Calendar.HOUR_OF_DAY)) * 60) + (minute - calendar.get(Calendar.MINUTE));
                    mList.get(position).setTime_date(current_minutes);
                }
            };
            holder.idgroup.setOnCheckedChangeListener((radioGroup, i) -> {
                if (holder.idgroup.getCheckedRadioButtonId() == R.id.idother){
                    (new TimePickerDialog(mContext, R.style.DialogTheme , time, calendar
                            .get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true
                    )).show();
                }
            });
            holder.container_accept_decline.setVisibility(View.VISIBLE);
            holder.idgroup.setVisibility(View.VISIBLE);
            holder.accept.setOnClickListener(view -> {
                if (holder.idgroup.getCheckedRadioButtonId() == R.id.id15min){
                    OrderDeliveryAfterMinutes(position,15);
                }
                else if (holder.idgroup.getCheckedRadioButtonId() == R.id.idnow){
                    OrderDeliveryAfterMinutes(position,0);
                }
                else {
                    OrderDeliveryAfterMinutes(position,mList.get(position).getTime_date());
                }
                ChangeOrderStatus(position);
            });
            holder.decline.setOnClickListener(view -> {
                CancelOrderStatus(position);
            });
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //TODO Bind views
        TextView date,status,name,total;
        ImageView image,arrow;
        RecyclerView listItems;
        Button accept,decline;
        TextView id_;
        LinearLayout container_accept_decline;
        RadioGroup idgroup;
        public ViewHolder(View itemView) {
            super(itemView);
            container_accept_decline = itemView.findViewById(R.id.container_accept_decline);
            idgroup = itemView.findViewById(R.id.idgroup);
            id_ = itemView.findViewById(R.id.id_);
            accept = itemView.findViewById(R.id.accept);
            decline = itemView.findViewById(R.id.decline);
            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status);
            name = itemView.findViewById(R.id.name);
            total = itemView.findViewById(R.id.total);
            arrow = itemView.findViewById(R.id.arrow);
            image = itemView.findViewById(R.id.image);
            listItems = itemView.findViewById(R.id.listItems);
            listItems.setNestedScrollingEnabled(true);
            listItems.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            listItems.setLayoutManager(layoutManager);
        }
    }
}