package com.example.duan1_n6_cp17303.Adapter_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan1_n6_cp17303.MainActivity;
import com.example.duan1_n6_cp17303.R;
import com.example.duan1_n6_cp17303.SanPhamActivity;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<SanPhamDTO> list;
    Context context;

    public SanPhamAdapter(List<SanPhamDTO> list, Context context) {
        this.list = list;
        this.context = context;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SanPhamDTO sanPhamDTO = list.get(position);
        SanPhamAdapter.ItemViewHolder viewHolder = (ItemViewHolder) holder;

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Glide.with(context).load(Uri.parse(sanPhamDTO.getAnhsanpham())).into(viewHolder.imganhsp);
        viewHolder.tvtensp.setText(sanPhamDTO.getTensanpham());
        viewHolder.tvgia.setText("Giá:" +decimalFormat.format(sanPhamDTO.getGiatien()) +"đ");
        viewHolder.tvthongtin.setText(sanPhamDTO.getThongtin());
        viewHolder.layout_itemsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(context, SanPhamActivity.class);
                intent.putExtra( "chitiet", sanPhamDTO);
                intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("Mypref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("key_SP",sanPhamDTO.getIdsanpham());
                editor.commit();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        SanPhamDTO sanPhamDTO = list.get(position);
        return sanPhamDTO.getIdsanpham();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imganhsp;
        TextView tvtensp, tvgia, tvthongtin;
        CardView layout_itemsp;
        public ItemViewHolder(View view) {
            super(view);
            layout_itemsp = view.findViewById(R.id.itemsplayout);
            imganhsp = view.findViewById(R.id.imgItemsp);
            tvtensp = view.findViewById(R.id.txtItemtensp);
            tvgia = view.findViewById(R.id.txtItemgiasp);
            tvthongtin = view.findViewById(R.id.txtItemthongtinsp);
        }
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view;
//        if (convertView == null){
//            view = View.inflate(parent.getContext(), R.layout.item_sanpham, null);
//        }else{
//            view = convertView;
//        }
//        ImageView imgItemsp = view.findViewById(R.id.imgItemsp);
//        TextView txtItemtensp = view.findViewById(R.id.txtItemtensp);
//        TextView txtItemgiasp = view.findViewById(R.id.txtItemgiasp);
//        TextView txtItemthongtinsp = view.findViewById(R.id.txtItemthongtinsp);
//
//        SanPhamDTO sanPhamDTO =list.get(position);
//        Glide.with(view.getContext()).load(Uri.parse(sanPhamDTO.getAnhsanpham())).into(imgItemsp);
//        txtItemtensp.setText( sanPhamDTO.getTensanpham());
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        txtItemgiasp.setText( decimalFormat.format(sanPhamDTO.getGiatien())+"đ");
//        txtItemthongtinsp.setText (sanPhamDTO.getThongtin());
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, SanPhamActivity.class);
//                intent.putExtra( "chitiet", sanPhamDTO);
//                intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//
//                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("Mypref",MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt("key_SP",sanPhamDTO.getIdsanpham());
//                editor.commit();
//            }
//        });
//        return view;
//    }

}
