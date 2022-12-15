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

import java.text.DecimalFormat;
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
        TextView tv_tenkh = view.findViewById(R.id.tv_tenkh);
        TextView tv_tensp = view.findViewById(R.id.tv_tensp);
        TextView tv_soluong = view.findViewById(R.id.tv_soluongsp);
        TextView tv_tongtien = view.findViewById(R.id.tv_tongtien);
        TextView tv_trangthai = view.findViewById(R.id.tv_trangthai);
        TextView tv_date = view.findViewById(R.id.tv_date);

        HoaDonDTO hoaDonDTO =list.get(position);

        tv_tenkh.setText( "Tên Khách Hàng: "+hoaDonDTO.getTenkhachhang());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        tv_tongtien.setText(decimalFormat.format(hoaDonDTO.getTongtien()) +"đ");
        tv_soluong.setText("Số Lượng: "+hoaDonDTO.getSoluong());
        tv_trangthai.setText("Trạng Thái Đơn: "+hoaDonDTO.getTrangthai());
        tv_date.setText(hoaDonDTO.getNgaymua());


        return view;
    }
}
