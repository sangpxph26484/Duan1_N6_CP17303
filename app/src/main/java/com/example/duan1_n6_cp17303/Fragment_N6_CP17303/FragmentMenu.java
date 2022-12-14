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
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1_n6_cp17303.Adapter_N6_CP17303.SanPhamAdapter;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.SanPhamDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan1_n6_cp17303.R;
import com.example.duan1_n6_cp17303.SanPhamActivity;

import java.util.ArrayList;


public class FragmentMenu extends Fragment {
    public static FragmentMenu newInstance() {
        FragmentMenu fragment = new FragmentMenu();

        return fragment;
    }

    RecyclerView rcv;
    SanPhamAdapter adapter;
    SanPhamDAO dao;
    ImageSlider imageSlider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        rcv = view.findViewById(R.id.rcv_pro);
        imageSlider= view.findViewById(R.id.image_slider);

        ArrayList<SlideModel> slideModels =new ArrayList<>();

        slideModels.add(new SlideModel("https://iili.io/HfgrBKx.jpg","tui1", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://iili.io/HfgrCcQ.jpg","tui2", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://iili.io/HfgrfPj.jpg","tui3", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://iili.io/HfgrKMb.jpg","tui4", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://iili.io/HfgrxHB.jpg","tui5", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://iili.io/HfgrzAP.jpg","tui6", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://iili.io/HfgrKMb.jpg","tui7", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showdata();

    }
    public void showdata(){
        dao = new SanPhamDAO();
        adapter = new SanPhamAdapter(dao.getAll(), getContext());
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}