package com.example.duan1_n6_cp17303.Fragment_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1_n6_cp17303.DAO_N6_CP17303.KhachHangDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.KhachHangDTO;
import com.example.duan1_n6_cp17303.DangKyActivity;
import com.example.duan1_n6_cp17303.DangNhapActivity;
import com.example.duan1_n6_cp17303.MainActivity;
import com.example.duan1_n6_cp17303.R;

import java.util.List;

public class FragmentThongTinCn extends Fragment {
    ImageView img;
    TextView name, email, sdt, diaChi;
    Button dangki, dangnhap;
    KhachHangDAO dao;
    List<KhachHangDTO> list;

    public static FragmentThongTinCn newInstance() {
        FragmentThongTinCn fragment = new FragmentThongTinCn();
String a;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_cn, container, false);

        SharedPreferences mypref = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = mypref.getString("key_TK1", "");



        name = view.findViewById(R.id.tv_name);
        email = view.findViewById(R.id.tv_email);
        sdt = view.findViewById(R.id.tv_sdt);
        diaChi = view.findViewById(R.id.tv_diachi);
        img = view.findViewById(R.id.img_ttcn);
        dangki = view.findViewById(R.id.btn_dangky_ttcn);
        dangnhap = view.findViewById(R.id.btn_dangnhap_ttcn);
        dao = new KhachHangDAO();

        String ten = dao.getTenByUser(user);
        String diachi1 = dao.getDcByUser(user);
        String sdt1 = dao.getSdtByUser(user);
        String mail = dao.getEmailByUser(user);
        Log.d("chuongdk", "onCreateView: " + ten);





        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");

        if (u.equals("") || p.equals("")){
            dangnhap.setText("Đăng nhập");
            name.setText("");
            email.setText("");
            diaChi.setText("");
            sdt.setText("");
        }else {
            dangnhap.setText("Đăng Xuất");

            name.setText(ten);
            email.setText(mail);
            diaChi.setText(diachi1);
            sdt.setText(sdt1);
        }

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangxuat();
                Intent intent = new Intent(getContext(), DangNhapActivity.class);
                startActivity(intent);
            }

        });
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DangKyActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    void dangxuat(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");

        if (u.equals("") || p.equals("")){
            Log.d("TAG", "dang nhap");
            Intent intent = new Intent(getContext(), DangNhapActivity.class);
            startActivity(intent);
        }else {
            Log.d("TAG", "dang xuat");
            Toast.makeText(getContext(), "Bạn đã đăng xuất thành công", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}