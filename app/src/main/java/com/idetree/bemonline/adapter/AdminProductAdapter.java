package com.idetree.bemonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.idetree.bemonline.R;
import com.idetree.bemonline.admin_product.AdminProductModel;
import com.idetree.bemonline.model.JsonArrayProductModel;

import java.util.ArrayList;
import java.util.List;

public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductAdapter.HolderData> {

    private Context context;
    List<AdminProductModel> productModels = new ArrayList<AdminProductModel>();

    public AdminProductAdapter(Context context, List<AdminProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product, parent, false);
        HolderData holderData = new HolderData(view);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        AdminProductModel arrayProductModel = productModels.get(position);
        holder.textView.setText(arrayProductModel.getProductName());
        Glide.with(context).load(arrayProductModel.getProductImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_product);
            textView = itemView.findViewById(R.id.tv_title);
        }
    }
}
