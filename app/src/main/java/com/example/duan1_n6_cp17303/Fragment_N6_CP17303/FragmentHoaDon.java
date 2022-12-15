package com.example.duan1_n6_cp17303.Fragment_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duan1_n6_cp17303.Adapter_N6_CP17303.HoaDonAdapter;
import com.example.duan1_n6_cp17303.Adapter_N6_CP17303.SanPhamAdapter;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.HoaDonDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.KhachHangDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.HoaDonDTO;
import com.example.duan1_n6_cp17303.R;

import java.util.List;


public class FragmentHoaDon extends Fragment {
    ListView lv_hd;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter adapter;
    List<HoaDonDTO> list;
    public static FragmentHoaDon newInstance() {
        FragmentHoaDon fragment = new FragmentHoaDon();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);




        lv_hd = view.findViewById(R.id.lv_hoadon);


        return view;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showdata();
    }
    public void showdata(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = sharedPreferences.getString("key_TK1","");
        hoaDonDAO = new HoaDonDAO();
        list = hoaDonDAO.gethoadonkh(user);
        adapter = new HoaDonAdapter(list, getContext());
        lv_hd.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}