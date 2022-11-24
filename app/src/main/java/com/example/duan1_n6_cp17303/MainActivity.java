package com.example.duan1_n6_cp17303;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.duan1_n6_cp17303.DAO_N6_CP17303.KhachHangDAO;

import com.example.duan1_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.KhachHangDTO;

import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentGioHang;
import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentMenu;
import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentThem;
import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentThongTinCn;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.sql.Connection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper myDBHelper = new MyDBHelper();
        Connection conn = myDBHelper.openConnect();

        KhachHangDAO catDao = new KhachHangDAO();

        // bước 9 thì không cần phần trên, dùng DAO để lấy dữ liệu

        List<KhachHangDTO> listCat = catDao.getAll(); // lấy danh sách cho vào biến

        // duyệt mảng in ra danh sách
        for(int i = 0; i<listCat.size(); i++){
            KhachHangDTO objCat = listCat.get(i);

            Log.d("zzzzz", "onCreate: phần tử thứ " + i + ":  id = " + objCat.getTenkhachhang());

        }

        fragmentManager = getSupportFragmentManager();
        FragmentMenu fragmentMenu = new FragmentMenu();
        FragmentGioHang fragmentGioHang= new FragmentGioHang();
        FragmentThongTinCn fragmentThongTinCn= new FragmentThongTinCn();
        FragmentThem fragmentThem = new FragmentThem();

        fragmentManager.beginTransaction().add(R.id.container_frag,fragmentMenu).commit();

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_nav_bar);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_Menu:
                        replaceFragment(fragmentMenu.newInstance());
                        break;
                    case R.id.nav_ttcn:
                        replaceFragment(fragmentThongTinCn.newInstance());
                        break;
                    case R.id.nav_them:
                        replaceFragment(fragmentThem.newInstance());
                        break;
                    case R.id.nav_giohang:
                        replaceFragment(fragmentGioHang.newInstance());
                        break;
                    default:
                        replaceFragment(fragmentMenu.newInstance());
                        break;
                }

                return true;
            }
        });

    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_frag,fragment);
        transaction.commit();
    }
    protected void reloadFragment(String TAG){
        Fragment frg = null;
        frg = this.getSupportFragmentManager().findFragmentByTag(TAG);
        FragmentTransaction ft =this.getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }
}
