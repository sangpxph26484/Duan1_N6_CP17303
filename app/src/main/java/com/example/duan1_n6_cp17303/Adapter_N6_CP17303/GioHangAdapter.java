package com.example.duan1_n6_cp17303.Adapter_N6_CP17303;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.GioHangDTO;
import com.example.duan1_n6_cp17303.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {
    Context context;
    List<GioHangDTO> gioHangDTOList;

    public GioHangAdapter(Context context, List<GioHangDTO> gioHangDTOList) {
        this.context = context;
        this.gioHangDTOList = gioHangDTOList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GioHangDTO gioHangDTO = gioHangDTOList.get(position);
        holder.item_giohang_tensanpham.setText(gioHangDTO.getTensanpham());
        holder.item_giohang_soluong.setText(gioHangDTO.getSoluong());
        Glide.with(context).load(gioHangDTO.getImg()).into(holder.item_giohang_img);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_giohang_gia.setText(decimalFormat.format(gioHangDTO.getGiatien())+"Đ");
        double soluong = gioHangDTO.getSoluong();
//        double giatien = Double.parseDouble(gioHangDTO.getGiatien());

//        double gia = soluong*giatien;
    }

    @Override
    public int getItemCount() {
        return gioHangDTOList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView item_giohang_img;
        TextView item_giohang_tensanpham, item_giohang_gia, item_giohang_soluong;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_giohang_img = itemView.findViewById(R.id.item_giohang_img);
            item_giohang_tensanpham = itemView.findViewById(R.id.item_giohang_tensanpham);
            item_giohang_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
        }
    }
}

