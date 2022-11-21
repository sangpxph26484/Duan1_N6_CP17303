package com.example.duan1_n6_cp17303.Fragment_N6_CP17303;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1_n6_cp17303.Adapter_N6_CP17303.SanPhamAdapter;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan1_n6_cp17303.R;
import com.example.duan1_n6_cp17303.SanPhamActivity;


public class FragmentMenu extends Fragment {
    String a = "aa";
    public static FragmentMenu newInstance() {
        FragmentMenu fragment = new FragmentMenu();

        return fragment;
    }

    ListView lv;
    SanPhamAdapter adapter;
    SanPhamDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        lv = view.findViewById(R.id.rcv_pro);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showdata();

    }
    public void showdata(){
        dao = new SanPhamDAO();
        adapter = new SanPhamAdapter(dao.getAll(), getContext(), new SanPhamAdapter.onclicksanpham() {
            @Override
            public void hihi(View view, int id) {
                Intent intent = new Intent(getContext(), SanPhamActivity.class);
                intent.putExtra("id_sp", id);
                startActivity(intent);
            }
        });

        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}