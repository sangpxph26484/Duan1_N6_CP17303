package com.example.duan1_n6_cp17303.Fragment_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_n6_cp17303.Adapter_N6_CP17303.GioHangAdapter;
import com.example.duan1_n6_cp17303.Adapter_N6_CP17303.SanPhamAdapter;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.GioHangDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.GioHangDTO;
import com.example.duan1_n6_cp17303.R;

import java.text.DecimalFormat;
import java.util.List;

public class FragmentGioHang extends Fragment {
    ListView lv;
    GioHangAdapter adapter;
    GioHangDAO dao;
    GioHangDTO gioHangDTO;
    List<GioHangDTO> list;
    Button btndathang;
    TextView tvsoluongsp,tvgasp, tvbotsp, btnthemsp;
    public static FragmentGioHang newInstance() {
        FragmentGioHang fragment = new FragmentGioHang();

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
//        TextView tv_giatiengiohang = view.findViewById(R.id.tv_giatiengiohang);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = sharedPreferences.getString("key_TK1","");

        lv = view.findViewById(R.id.lv_giohang);
        dao = new GioHangDAO();
        int a = dao.getTongTien(user);
        //      DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        //       tv_giatiengiohang.setText(decimalFormat.format(a)+"đ");

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showdata();
        tvgasp = view.findViewById(R.id.item_giohang_gia);
 //       btndathang = view.findViewById(R.id.btn_dathang);
        tvsoluongsp = view.findViewById(R.id.item_giohang_soluong);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Xóa Sản Phẩm");
                builder.setMessage("Bạn có chắc chắn muốn xóa không");

                builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                            String user = sharedPreferences.getString("key_TK1","");
                            list = dao.getAll(user);
                            GioHangDTO gioHangDTO1 = list.get(position);
                            dao.deleteRow(gioHangDTO1.getIdgiohang());
                            Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            showdata();
                            dialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

//        btndathang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
    public void showdata(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = sharedPreferences.getString("key_TK1","");
        dao = new GioHangDAO();
        adapter = new GioHangAdapter(  dao.getAll(user),getContext());
        lv.setAdapter( adapter);
        adapter.notifyDataSetChanged();
    }




}