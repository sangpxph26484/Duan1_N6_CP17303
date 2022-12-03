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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.GioHangDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.GioHangDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentGioHang;

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
        txtsoluongsp.setText("1");
        btnbotsp = findViewById(R.id.btnBotsanpham);
        btnthemsp = findViewById(R.id.btnThemsanpham);

        btnthem = findViewById(R.id.btnThemvaogiohang);


        dao = new SanPhamDAO();
        sanPhamDTO = (SanPhamDTO)  getIntent().getSerializableExtra(  "chitiet");



        Glide.with(this).load(sanPhamDTO.getAnhsanpham()).into(imgsanpham);
        tensp.setText("Tên Sản Phẩm: "+sanPhamDTO.getTensanpham());
        giasp.setText("Giá: "+sanPhamDTO.getGiatien());
        mota.setText(sanPhamDTO.getThongtin());



        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHangDAO gioHangDAO1 = new GioHangDAO();
                GioHangDTO gioHangDTO1 = new GioHangDTO();

                String tensp1, soluong1, anh1;
                tensp1 = tensp.getText().toString();
                soluong1 = txtsoluongsp.getText().toString();
                anh1= sanPhamDTO.getAnhsanpham();
                gioHangDTO1.setTensanpham(tensp1);
                gioHangDTO1.setGiatien(sanPhamDTO.getGiatien());
                gioHangDTO1.setSoluong(Integer.parseInt(soluong1));
                gioHangDTO1.setAnhsanpham(anh1);

                if (gioHangDAO1.insertRow(gioHangDTO1) == true){
                    Toast.makeText(SanPhamActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else {
                    Toast.makeText(SanPhamActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();



            }
        });




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