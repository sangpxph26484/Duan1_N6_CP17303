package com.example.duan1_n6_cp17303;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1_n6_cp17303.DAO_N6_CP17303.TaiKhoanDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;

public class DoiMatKhauActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        Button btn_dmk = findViewById(R.id.btn_doimatkhau);
        EditText ed_mkht = findViewById(R.id.ed_matkhauhientai);

        EditText ed_matkhaumoi = findViewById(R.id.ed_matkhaumoi);
        EditText ed_laimk = findViewById(R.id.ed_nhaplaimkmoi);


        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        ImageView img_backdky = findViewById(R.id.imgBackdmk);

        img_backdky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = sharedPreferences.getString("key_TK1","");
        btn_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mkht = ed_mkht.getText().toString();

                String mkm = ed_matkhaumoi.getText().toString();
                String laimk = ed_laimk.getText().toString();

                if (mkht.equals("") || mkm.equals("") || laimk.equals("")) {
                    Toast.makeText(getBaseContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (laimk.equals(mkm)) {
                        int a = taiKhoanDAO.checkPass(mkht);
                        if (a == 1) {

                            if (taiKhoanDAO.updateMatKhau(mkm,user) == true) {
                                Toast.makeText(DoiMatKhauActivity.this, "Đổi Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
                                onBackPressed();

                            } else {
                                Toast.makeText(DoiMatKhauActivity.this, "Đổi Mật Khẩu Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(DoiMatKhauActivity.this, "Mật Khẩu Hiện Tại Không Đúng", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DoiMatKhauActivity.this, "Mật Khẩu Mới Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}