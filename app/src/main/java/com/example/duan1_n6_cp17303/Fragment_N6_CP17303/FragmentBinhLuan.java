package com.example.duan1_n6_cp17303.Fragment_N6_CP17303;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1_n6_cp17303.Adapter_N6_CP17303.BinhLuanAdapter;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.BinhLuanDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.BinhLuanDTO;
import com.example.duan1_n6_cp17303.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class FragmentBinhLuan extends Fragment {

    BinhLuanDAO binhLuanDAO;
    BinhLuanAdapter adapter;
    ListView lv;
    List<BinhLuanDTO> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_binh_luan, container, false);

        lv = view.findViewById(R.id.lv_binhluan);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.btn_danhgia);
        binhLuanDAO = new BinhLuanDAO();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd(v.getContext());

            }
        });
        loaddata();


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_sua_binhluan);

                EditText binhluan = dialog.findViewById(R.id.suabl_ed_danhgia);


                Button sua = dialog.findViewById(R.id.suabl_btn_sua);
                Button huy = dialog.findViewById(R.id.suabl_btn_huy);
                Button xoa = dialog.findViewById(R.id.suabl_btn_xoa);
                BinhLuanDTO binhLuanDTO1 = list.get(position);

                binhluan.setText(binhLuanDTO1.getBinhluan());

                sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bl = binhluan.getText().toString();

                        binhLuanDTO1.setBinhluan(String.valueOf(bl));

                        try {
                            binhLuanDAO.updateRow(binhLuanDTO1);
                            Toast.makeText(getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                            loaddata();
                            dialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }

                    }
                });
                xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            binhLuanDAO.deleteRow(list.get(position));
                            Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            loaddata();
                            dialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }
                    }
                });

                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return  false;
            }

        });


    }
    public  void loaddata(){

        list = binhLuanDAO.getAll();
        adapter = new BinhLuanAdapter( binhLuanDAO.getAll(),getContext());
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public void showDialogAdd(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_danhgia);

        EditText binhluan = dialog.findViewById(R.id.ed_binhluan);

        Button danhgia = dialog.findViewById(R.id.btn_danhgiasp);
        Button huy = dialog.findViewById(R.id.btn_huydanhgiasp);

        danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bl = binhluan.getText().toString();


                BinhLuanDTO binhLuanDTO = new BinhLuanDTO();
                binhLuanDTO.setBinhluan(String.valueOf(bl));

                try {
                    binhLuanDAO.insertRow(binhLuanDTO);
                    Toast.makeText(context, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                    loaddata();
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }

            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}