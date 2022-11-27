package com.example.duan1_n6_cp17303;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_n6_cp17303.DAO_N6_CP17303.KhachHangDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.TaiKhoanDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.KhachHangDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;


public class DangKyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_ky);

        Button btn_dk = findViewById(R.id.btn_dangkyuser);
        EditText ed_hoTen = findViewById(R.id.ed_hoTen);
        EditText ed_sdt = findViewById(R.id.ed_sdt);
        EditText ed_mk = findViewById(R.id.ed_mk);
        EditText ed_laimk = findViewById(R.id.ed_laimk);
        EditText ed_email = findViewById(R.id.ed_email);
        EditText ed_diaChi = findViewById(R.id.ed_diaChi);

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        KhachHangDAO khachHangDAO = new KhachHangDAO();

        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();
                KhachHangDTO khachHangDTO = new KhachHangDTO();

                String hoTen = ed_hoTen.getText().toString();
                String sdt = ed_sdt.getText().toString();
                String mk = ed_mk.getText().toString();
                String laimk = ed_laimk.getText().toString();
                String email = ed_email.getText().toString();
                String diachi = ed_diaChi.getText().toString();


                if (hoTen.equals("") || sdt.equals("") || mk.equals("") || laimk.equals("") || email.equals("") || diachi.equals("")) {
                    Toast.makeText(DangKyActivity.this, "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (laimk.equals(mk)) {
                        int a = taiKhoanDAO.checkUser(sdt);
                        if (a == -1) {
                            taiKhoanDTO.setUsername(sdt);
                            taiKhoanDTO.setPassword(mk);
                            khachHangDTO.setDiachi(diachi);
                            khachHangDTO.setEmail(email);
                            khachHangDTO.setTenkhachhang(hoTen);




                            if (taiKhoanDAO.insertRow(taiKhoanDTO) == true || khachHangDAO.insertRow(khachHangDTO) == true) {
                                Toast.makeText(DangKyActivity.this, "Đăng Ký Thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(DangKyActivity.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(DangKyActivity.this, "Tài Khoản Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(DangKyActivity.this, "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}