package com.example.duan1_n6_cp17303;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_n6_cp17303.DAO_N6_CP17303.TaiKhoanDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;


public class DangKyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_ky);

        Button btn_dk = findViewById(R.id.btn_dangkyuser);
        EditText ed_hoTen = findViewById(R.id.ed_taiKhoan);

        EditText ed_mk = findViewById(R.id.ed_mk);
        EditText ed_laimk = findViewById(R.id.ed_laimk);


        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        ImageView img_backdky = findViewById(R.id.imgBackdangky);

        img_backdky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();

                String tk = ed_hoTen.getText().toString();

                String mk = ed_mk.getText().toString();
                String laimk = ed_laimk.getText().toString();

                if (tk.equals("") || mk.equals("") || laimk.equals("")) {
                    Toast.makeText(DangKyActivity.this, "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (laimk.equals(mk)) {
                        int a = taiKhoanDAO.checkUser(tk);
                        if (a == -1) {
                            taiKhoanDTO.setPass(mk);
                            taiKhoanDTO.setUsername(tk);
                            if (taiKhoanDAO.insertRow(taiKhoanDTO) == true) {
                                Toast.makeText(DangKyActivity.this, "Đăng Ký Thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getBaseContext(),DangKyThongTinActivity.class));

                                SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Mypref",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("key_TK",tk);
                                editor.commit();

                            } else {
                                Toast.makeText(DangKyActivity.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
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