package com.example.duan1_n6_cp17303;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentGioHang;
import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentMenu;
import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentThongTinCn;
import com.example.duan1_n6_cp17303.Fragment_N6_CP17303.FragmentThongbao;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();
        FragmentMenu fragmentMenu = new FragmentMenu();
        FragmentGioHang fragmentGioHang= new FragmentGioHang();
        FragmentThongTinCn fragmentThongTinCn= new FragmentThongTinCn();
        FragmentThongbao fragmentThongbao = new FragmentThongbao();

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
                    case R.id.nav_donHang:
                        replaceFragment(fragmentGioHang.newInstance());
                        break;
                    case R.id.nav_sanPham:
                        replaceFragment(fragmentThongTinCn.newInstance());
                        break;
                    case R.id.nav_them:
                        replaceFragment(fragmentThongbao.newInstance());
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