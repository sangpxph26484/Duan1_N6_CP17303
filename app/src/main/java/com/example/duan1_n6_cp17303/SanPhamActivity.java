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
    TextView tensp, giasp, mota, txtsoluongsp;
    Button btnthem, btnbotsp, btnthemsp ;
    ImageView imgsanpham, imgback;
    SanPhamDAO dao;
    int count = 0;
    SanPhamDTO sanPhamDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);


        tensp = findViewById(R.id.txtTensanpham);
        giasp = findViewById(R.id.txtGiasanpham);
        mota = findViewById(R.id.txtMotachitiet);
        imgsanpham = findViewById(R.id.imgSanpham);
        imgback = findViewById(R.id.imgBackctsp);

        txtsoluongsp = findViewById(R.id.txtSoluongsp);
        btnbotsp = findViewById(R.id.btnBotsanpham);
        btnthemsp = findViewById(R.id.btnThemsanpham);

        btnthem = findViewById(R.id.btnThemvaogiohang);


        dao = new SanPhamDAO();
        sanPhamDTO = (SanPhamDTO)  getIntent().getSerializableExtra(  "chitiet");



        Glide.with(this).load(sanPhamDTO.getAnhsanpham()).into(imgsanpham);
        tensp.setText("Tên Sản Phẩm: "+sanPhamDTO.getTensanpham());
        giasp.setText("Giá: "+sanPhamDTO.getGiatien());
        mota.setText(sanPhamDTO.getThongtin());

        txtsoluongsp.setText(sanPhamDTO.getSoluong() + "");

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();



            }
        });
        txtsoluongsp = findViewById(R.id.txtSoluongsp);



    }
    public void increment(View view){
        count ++;
        txtsoluongsp.setText(""+count);

        giasp.setText("Giá: "+sanPhamDTO.getGiatien() * count +"");
    }
    public void decrement(View view){
        if (count <= 0) count = 0;
        else count --;
        txtsoluongsp.setText(""+count);
        giasp.setText("Giá: "+sanPhamDTO.getGiatien() * count +"");
    }
}