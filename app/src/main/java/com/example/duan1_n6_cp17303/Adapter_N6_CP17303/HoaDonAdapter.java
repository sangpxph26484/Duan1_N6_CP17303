package com.example.duan1_n6_cp17303.Adapter_N6_CP17303;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.CTHDDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.GioHangDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.HoaDonDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentHoaDon;
import com.example.duan1_n6_cp17303.R;
import com.example.duan1_n6_cp17303.SanPhamActivity;

import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    List<HoaDonDTO> list;
    Context context;

    public HoaDonAdapter(List<HoaDonDTO> list, Context context) {

        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        HoaDonDTO hoaDonDTO = list.get(position);
        return hoaDonDTO;
    }

    @Override
    public long getItemId(int position) {
        HoaDonDTO hoaDonDTO = list.get(position);
        return hoaDonDTO.getIdhoadon();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = View.inflate(parent.getContext(), R.layout.item_hoadon, null);
        }else{
            view = convertView;
        }
        ImageView imgItemsp = view.findViewById(R.id.item_hoadon_img);
        TextView tvItemtenkh = view.findViewById(R.id.tv_item_hoadon_tenkh);
        TextView tvItemtensp = view.findViewById(R.id.tv_item_hoadon_sp);
        TextView tvItemsoluong = view.findViewById(R.id.tv_item_hoadon_sl);
        TextView tvItemtongtien = view.findViewById(R.id.tv_item_hoadon_tongtien);
        TextView tvItemtrangthai = view.findViewById(R.id.tv_item_hoadon_trangthai);

//        HoaDonDTO hoaDonDTO =list.get(position);
//        Glide.with(view.getContext()).load(Uri.parse(hoaDonDTO.getAnhsanpham())).into(imgItemsp);
//        tvItemtenkh.setText( hoaDonDTO.getIdkhachhang());
//        tvItemtensp.setText( hoaDonDTO.get()+"đ");
//        txtItemthongtinsp.setText (hoaDonDTO.getThongtin());


        return view;
    }
}
