package com.example.duan1_n6_cp17303;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.Adapter_N6_CP17303.BinhLuanAdapter;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.BinhLuanDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.GioHangDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.KhachHangDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.BinhLuanDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.GioHangDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentGioHang;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongToDoubleFunction;

public class SanPhamActivity extends AppCompatActivity {
    TextView tensp, giasp, mota, txtsoluongsp;
    Button btnthem, btnbotsp, btnthemsp;
    ImageView imgsanpham, imgback;
    SanPhamDAO dao;
    int count = 0;
    SanPhamDTO sanPhamDTO;
    ListView lv_binhluan;
    EditText ed_binhluan;
    ImageView img_binhluan;
    BinhLuanAdapter binhLuanAdapter;
    BinhLuanDAO binhLuanDAO;
    BinhLuanDTO binhLuanDTO;
    List<BinhLuanDTO> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);


        tensp = findViewById(R.id.txtTensanpham);
        giasp = findViewById(R.id.txtGiasanpham);
        mota = findViewById(R.id.txtMotachitiet);
        imgsanpham = findViewById(R.id.imgSanpham);
        imgback = findViewById(R.id.imgBackctsp);

        lv_binhluan = findViewById(R.id.sanpham_lv_binhluan);
        ed_binhluan = findViewById(R.id.sanpham_ed_binhluan);
        img_binhluan = findViewById(R.id.sanpham_img_binhluan);
        Intent intent1 = getIntent();
        String id_sanpham = intent1.getStringExtra("IDSANPHAM");


        txtsoluongsp = findViewById(R.id.txtSoluongsp);
        txtsoluongsp.setText("1");
        btnbotsp = findViewById(R.id.btnBotsanpham);
        btnthemsp = findViewById(R.id.btnThemsanpham);

        btnthem = findViewById(R.id.btnThemvaogiohang);
        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", this.MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        Log.e("u", u);

//==========
//
//        try {
//            binhLuanDAO = new BinhLuanDAO();
//            list = binhLuanDAO.getAll(Integer.parseInt(id_sanpham));
//            binhLuanAdapter = new BinhLuanAdapter(list,this);
//            lv_binhluan.setAdapter(binhLuanAdapter);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        showdatabinhluan();

        img_binhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (u.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
                    builder.setTitle("Bạn cần đăng nhập để thêm bình luận!!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(SanPhamActivity.this, DangNhapActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                } else {
                    BinhLuanDTO bl = new BinhLuanDTO();
                    KhachHangDAO dao = new KhachHangDAO();
                    SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                    String user = sharedPreferences.getString("key_TK1", "");
                    int idkh = dao.getidKh(user);
                    SharedPreferences sharedPreferences1 = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                    int idsp = sharedPreferences1.getInt("key_SP", 0);
                    bl.setBinhluan(ed_binhluan.getText().toString());

                    binhLuanDAO = new BinhLuanDAO();

                    if (binhLuanDAO.insertRow(ed_binhluan.getText().toString(), idsp, idkh) == false) {
                        Toast.makeText(getBaseContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                        showdatabinhluan();
                    } else {
                        Toast.makeText(SanPhamActivity.this, "Bình Luận Thất Bại", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });



        dao = new SanPhamDAO();
        sanPhamDTO = (SanPhamDTO) getIntent().getSerializableExtra("chitiet");


        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        Glide.with(this).load(sanPhamDTO.getAnhsanpham()).into(imgsanpham);
        tensp.setText("Tên Sản Phẩm: " + sanPhamDTO.getTensanpham());
        giasp.setText("Giá: " + decimalFormat.format(sanPhamDTO.getGiatien()));
        mota.setText(sanPhamDTO.getThongtin());


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHangDAO gioHangDAO1 = new GioHangDAO();
                GioHangDTO gioHangDTO1 = new GioHangDTO();

                String tensp1, soluong1, anh1;
                tensp1 = tensp.getText().toString();
                soluong1 = txtsoluongsp.getText().toString();
                anh1 = sanPhamDTO.getAnhsanpham();
                gioHangDTO1.setTensanpham(tensp1);
                gioHangDTO1.setGiatien(sanPhamDTO.getGiatien());
                gioHangDTO1.setSoluong(Integer.parseInt(soluong1));
                gioHangDTO1.setAnhsanpham(anh1);

                SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Login", MODE_PRIVATE);
                String u = sharedPreferences.getString("name", "");
                if (u.equals("")) {
                    Toast.makeText(SanPhamActivity.this, "Bạn Cần Phải Đăng Nhập", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), DangNhapActivity.class);
                    startActivity(intent);
                } else {

                    if (gioHangDAO1.insertRow(gioHangDTO1) == true) {
                        Toast.makeText(SanPhamActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(SanPhamActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
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

    private void showdatabinhluan() {
        SharedPreferences sharedPreferences1 = getBaseContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        int idsp = sharedPreferences1.getInt("key_SP", 0);
        binhLuanDAO = new BinhLuanDAO();
        list = binhLuanDAO.getAll1(idsp);
        binhLuanAdapter = new BinhLuanAdapter(list, getBaseContext());
        lv_binhluan.setAdapter(binhLuanAdapter);
    }

    public void increment(View view) {
        count++;
        txtsoluongsp.setText("" + count);

        giasp.setText("Giá: " + sanPhamDTO.getGiatien() * count + "");
    }

    public void decrement(View view) {
        if (count <= 0) count = 0;
        else count--;
        txtsoluongsp.setText("" + count);
        giasp.setText("Giá: " + sanPhamDTO.getGiatien() * count + "");
    }
}