package com.example.duan1_n6_cp17303.Adapter_N6_CP17303;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.GioHangDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan1_n6_cp17303.R;
import com.example.duan1_n6_cp17303.SanPhamActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends BaseAdapter {
    List<GioHangDTO> list;
    Context context;
    int count = 0;
    boolean bulkflag = false;
    public GioHangAdapter(List<GioHangDTO> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        GioHangDTO gioHangDTO = list.get(position);
        return gioHangDTO;
    }

    @Override
    public long getItemId(int position) {
        GioHangDTO gioHangDTO = list.get(position);
        return gioHangDTO.getIdgiohang();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = View.inflate(parent.getContext(), R.layout.item_giohang, null);
        }else{
            view = convertView;
        }
        ImageView imgItemimggiohang = view.findViewById(R.id.item_giohang_img);
        TextView txtItemtenspgiohang = view.findViewById(R.id.item_giohang_tensanpham);
        TextView txtItemgiaspgiohang = view.findViewById(R.id.item_giohang_gia);
        TextView txtItemsoluongspgiohang = view.findViewById(R.id.item_giohang_soluong);


        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        GioHangDTO gioHangDTO =list.get(position);
        Glide.with(view.getContext()).load(Uri.parse(gioHangDTO.getAnhsanpham())).into(imgItemimggiohang);
        txtItemtenspgiohang.setText( gioHangDTO.getTensanpham());
        txtItemgiaspgiohang.setText("Giá:" +decimalFormat.format(gioHangDTO.getGiatien()) +"đ");
        txtItemsoluongspgiohang.setText(gioHangDTO.getSoluong()+"");

        count = Integer.parseInt(txtItemsoluongspgiohang.getText().toString());

        if(bulkflag){
//            cbo.setVisibility(View.VISIBLE);
        }else {
//            cbo.setVisibility(View.GONE);
        }

        return view;
    }

}

