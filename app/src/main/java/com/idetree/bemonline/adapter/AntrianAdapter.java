package com.idetree.bemonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.idetree.bemonline.R;
import com.idetree.bemonline.model.AntrianModel;

import java.util.ArrayList;
import java.util.List;

public class AntrianAdapter extends RecyclerView.Adapter<AntrianAdapter.HolderData> {
    private Context context;
    List<AntrianModel> antrian = new ArrayList<AntrianModel>();

    public AntrianAdapter(Context context, List<AntrianModel> antrian) {
        this.context = context;
        this.antrian = antrian;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_antrian, parent, false);
        HolderData holderData = new HolderData(view);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        AntrianModel antrianModel = antrian.get(position);
        holder.no.setText("Nomor antrian : "+antrianModel.getNoAntrian().toString());
        holder.name.setText("Nama : "+antrianModel.getNama());
        holder.status.setText("Status Dalam : "+antrianModel.getStatus());
    }

    @Override
    public int getItemCount() {
        return antrian.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView no, name, status;
        LottieAnimationView lottieAnimationView;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            no = itemView.findViewById(R.id.tv_nomorAntrian);
            name = itemView.findViewById(R.id.tv_name);
            status = itemView.findViewById(R.id.tv_status);
        }
    }
}
