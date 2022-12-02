package com.example.duan1_n6_cp17303.Fragment_N6_CP17303;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.TaiKhoanDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;
import com.example.duan1_n6_cp17303.R;

import java.util.List;

public class FragmentThongTinCn extends Fragment {
    ImageView img;
    TextView name, email,sdt,diaChi;
    Button dangki,dangnhap;
    TaiKhoanDAO dao1;

    public static FragmentThongTinCn newInstance() {
        FragmentThongTinCn fragment = new FragmentThongTinCn();

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_thong_tin_cn, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name=view.findViewById(R.id.tv_name);
        email=view.findViewById(R.id.tv_email);
        sdt=view.findViewById(R.id.tv_sdt);
        diaChi=view.findViewById(R.id.tv_diachi);
        img=view.findViewById(R.id.img_ttcn);
        dangki=view.findViewById(R.id.btn_dangky_ttcn);
        dangnhap=view.findViewById(R.id.btn_dangnhap_ttcn);

        dao1= new TaiKhoanDAO();





    }


}