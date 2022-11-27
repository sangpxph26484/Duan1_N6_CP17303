package com.example.duan1_n6_cp17303;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongToDoubleFunction;

public class SanPhamActivity extends AppCompatActivity {
    TextView tensp, giasp, mota, botsp, themsp, soluongsp;
    Button btnthem;
    ImageView imgsanpham, imgback;
    SanPhamDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);


        tensp = findViewById(R.id.txtTensanpham);
        giasp = findViewById(R.id.txtGiasanpham);
        mota = findViewById(R.id.txtMotachitiet);
        imgsanpham = findViewById(R.id.imgSanpham);
        imgback = findViewById(R.id.imgBackctsp);

        soluongsp = findViewById(R.id.txtSoluongsp);
        botsp = findViewById(R.id.txtBotsanpham);
        themsp = findViewById(R.id.txtThemsanpham);

        btnthem = findViewById(R.id.btnThemvaogiohang);


        dao = new SanPhamDAO();

        int idSP = getIntent().getExtras().getInt("id_sp") -1;

        List<SanPhamDTO> sanPhamDTOS = dao.getAll();
        SanPhamDTO sanPhamDTO = sanPhamDTOS.get(idSP);

        Glide.with(this).load(sanPhamDTO.getAnhsanpham()).into(imgsanpham);
        tensp.setText("Tên Sản Phẩm: "+sanPhamDTO.getTensanpham());
        giasp.setText("Giá: "+sanPhamDTO.getGiatien());
        mota.setText(sanPhamDTO.getThongtin());

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SanPhamActivity.this, MainActivity.class);
                startActivity(intent);



            }
        });


    }
}