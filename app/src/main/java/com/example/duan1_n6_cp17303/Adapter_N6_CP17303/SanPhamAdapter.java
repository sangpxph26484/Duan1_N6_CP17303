package com.example.duan1_n6_cp17303.Adapter_N6_CP17303;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan1_n6_cp17303.R;

import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    List<SanPhamDTO> list;
    Context context;
    onclicksanpham onclicksanpham;
    public SanPhamAdapter(List<SanPhamDTO> list, Context context,onclicksanpham onclicksanpham) {
        this.list = list;
        this.context = context;
        this.onclicksanpham = onclicksanpham;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        SanPhamDTO sanPhamDTO = list.get(position);
        return sanPhamDTO;
    }

    @Override
    public long getItemId(int position) {
        SanPhamDTO sanPhamDTO = list.get(position);
        return sanPhamDTO.getIdsanpham();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = View.inflate(parent.getContext(), R.layout.item_sanpham, null);
        }else{
            view = convertView;
        }
        ImageView imgItemsp = view.findViewById(R.id.imgItemsp);
        TextView txtItemtensp = view.findViewById(R.id.txtItemtensp);
        TextView txtItemgiasp = view.findViewById(R.id.txtItemgiasp);
        TextView txtItemthongtinsp = view.findViewById(R.id.txtItemthongtinsp);
        TextView txtItemsoluongsp = view.findViewById(R.id.txtItemsoluongsp);

        SanPhamDTO sanPhamDTO =list.get(position);
        Glide.with(view.getContext()).load(Uri.parse(sanPhamDTO.getAnhsanpham())).into(imgItemsp);
        txtItemtensp.setText( sanPhamDTO.getTensanpham());
        txtItemgiasp.setText( sanPhamDTO.getGiatien()+"đ");
        txtItemthongtinsp.setText (sanPhamDTO.getThongtin());
        txtItemsoluongsp.setText(sanPhamDTO.getSoluong()+"");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicksanpham.hihi(v, sanPhamDTO.getIdsanpham());
            }
        });
        return view;
    }
    public interface onclicksanpham{
        void hihi(View view,int id);
    }
}
