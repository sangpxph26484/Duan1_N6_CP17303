package com.example.duan1_n6_cp17303.Adapter_N6_CP17303;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.BinhLuanDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.KhachHangDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;
import com.example.duan1_n6_cp17303.R;

import java.util.List;

public class BinhLuanAdapter extends BaseAdapter {
    List<BinhLuanDTO> list;
    List<TaiKhoanDTO> list1;
    List<KhachHangDTO> list2;
    Context context;
    public BinhLuanAdapter(List<BinhLuanDTO> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        BinhLuanDTO binhLuanDTO = list.get(position);

        return binhLuanDTO;
    }

    @Override
    public long getItemId(int position) {
        BinhLuanDTO binhLuanDTO = list.get(position);

        return binhLuanDTO.getIdbinhluan();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            view = View.inflate(parent.getContext(), R.layout.item_binhluan,null);
        }else{
            view = convertView;
        }

        TextView tv_binhluan= view.findViewById(R.id.item_binhluan_tv_binhluan);
        //TextView tv_ten = view.findViewById(R.id.item_binhluan_tv_name);
        //ImageView img_bl = view.findViewById(R.id.item_binhluan_img);

        BinhLuanDTO binhLuanDTO = list.get(position);
        //KhachHangDTO khachHangDTO = list2.get(position);
        //TaiKhoanDTO taiKhoanDTO = list1.get(position);


        tv_binhluan.setText(binhLuanDTO.getBinhluan());
        //tv_ten.setText(khachHangDTO.getTenkhachhang());
        //Glide.with(view.getContext()).load(Uri.parse(taiKhoanDTO.getAvatar())).into(img_bl);

        return view;
    }
}
