package com.example.duan1_n6_cp17303;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_n6_cp17303.DAO_N6_CP17303.KhachHangDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.KhachHangDTO;

public class DangKyThongTinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_thong_tin);


        Button btn_dk = findViewById(R.id.btn_dangkythongtin);
        EditText ed_hoTen = findViewById(R.id.ed_hoTen);

        EditText ed_diachi = findViewById(R.id.ed_diachi);
        EditText ed_email = findViewById(R.id.ed_email);
        EditText ed_phone = findViewById(R.id.ed_phone);
        ImageView img_backdky = findViewById(R.id.imgBackdangkytt);

        img_backdky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhachHangDTO khachHangDTO = new KhachHangDTO();
                KhachHangDAO khachHangDAO = new KhachHangDAO();

                String hoten = ed_hoTen.getText().toString();

                String diachi = ed_diachi.getText().toString();
                String email = ed_email.getText().toString();
                String phone = ed_phone.getText().toString();

                if (hoten.equals("") || diachi.equals("") || email.equals("") || phone.equals("")) {
                    Toast.makeText(DangKyThongTinActivity.this, "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                    String user = sharedPreferences.getString("key_TK","");

                    khachHangDTO.setUsername(user);
                    khachHangDTO.setTenkhachhang(hoten);
                    khachHangDTO.setEmail(email);
                    khachHangDTO.setPhone(phone);
                    khachHangDTO.setDiachi(diachi);

                    if (khachHangDAO.insertRow(khachHangDTO) == true) {
                        Toast.makeText(DangKyThongTinActivity.this, "Đăng Ký Thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getBaseContext(), DangNhapActivity.class));

                    } else {
                        Toast.makeText(DangKyThongTinActivity.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
}