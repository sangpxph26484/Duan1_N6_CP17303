package com.example.duan1_n6_cp17303.Fragment_N6_CP17303;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.KhachHangDAO;
import com.example.duan1_n6_cp17303.DAO_N6_CP17303.TaiKhoanDAO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.KhachHangDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;
import com.example.duan1_n6_cp17303.DangKyActivity;
import com.example.duan1_n6_cp17303.DangNhapActivity;
import com.example.duan1_n6_cp17303.MainActivity;
import com.example.duan1_n6_cp17303.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class FragmentThongTinCn extends Fragment {
    ImageView avatar;
    TextView name, email, sdt, diaChi,tv_suatten, tv_suaemail, tv_suasdt, tv_suadiachi;
    Button dangki, dangnhap;
    KhachHangDAO dao;
    List<KhachHangDTO> list;
    String avatar1;


    public static FragmentThongTinCn newInstance() {
        FragmentThongTinCn fragment = new FragmentThongTinCn();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_cn, container, false);

        SharedPreferences mypref = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = mypref.getString("key_TK1", "");

        tv_suatten = view.findViewById(R.id.tv_suaten);
        tv_suaemail = view.findViewById(R.id.tv_suaemail);
        tv_suasdt = view.findViewById(R.id.tv_suasdt);
        tv_suadiachi = view.findViewById(R.id.tv_suadiachi);

        name = view.findViewById(R.id.tv_name);
        email = view.findViewById(R.id.tv_email);
        sdt = view.findViewById(R.id.tv_sdt);
        diaChi = view.findViewById(R.id.tv_diachi);
        avatar = view.findViewById(R.id.img_ttcn);
        dangki = view.findViewById(R.id.btn_dangky_ttcn);
        dangnhap = view.findViewById(R.id.btn_dangnhap_ttcn);
        dao = new KhachHangDAO();

        String ten = dao.getTenByUser(user);
        String diachi1 = dao.getDcByUser(user);
        String sdt1 = dao.getSdtByUser(user);
        String mail = dao.getEmailByUser(user);
        avatar1 = dao.getAvatarByUser(user);

        Log.d("chuongdk", "onCreateView: " + ten);


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");

        if (u.equals("") || p.equals("")) {
            dangnhap.setText("Đăng nhập");
            name.setText("");
            email.setText("");
            diaChi.setText("");
            sdt.setText("");
            Glide.with(getContext()).load(Uri.parse(avatar1)).into(avatar);


        } else {
            dangnhap.setText("Đăng Xuất");
            name.setText(ten);
            email.setText(mail);
            diaChi.setText(diachi1);
            sdt.setText(sdt1);
            Glide.with(getContext()).load(avatar1).into(avatar);


        }
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAvatar();
            }
        });

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangxuat();
                Intent intent = new Intent(getContext(), DangNhapActivity.class);
                startActivity(intent);
            }

        });
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DangKyActivity.class);
                startActivity(intent);
            }
        });
        tv_suatten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.dialog_suathongtin);

                EditText thongtin = dialog1.findViewById(R.id.ed_thongtin);

                Button capnhat = dialog1.findViewById(R.id.btn_capnhat);
                Button huy = dialog1.findViewById(R.id.btn_huy);

                capnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = thongtin.getText().toString();

                        if (dao.updateTenKh(a, user) == true) {
                            Toast.makeText(getContext(), "Cập nhật Thành Công", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                            name.setText(a);

                        } else {
                            Toast.makeText(getContext(), "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                        }

                    }
                });
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                dialog1.show();

            }
        });

        tv_suaemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.dialog_suathongtin);

                EditText thongtin = dialog1.findViewById(R.id.ed_thongtin);

                Button capnhat = dialog1.findViewById(R.id.btn_capnhat);
                Button huy = dialog1.findViewById(R.id.btn_huy);

                capnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = thongtin.getText().toString();

                        if (dao.updateEmailKh(a, user) == true) {
                            Toast.makeText(getContext(), "Cập nhật Thành Công", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                            email.setText(a);

                        } else {
                            Toast.makeText(getContext(), "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                        }

                    }
                });
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                dialog1.show();
            }
        });

        tv_suasdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.dialog_suathongtin);

                EditText thongtin = dialog1.findViewById(R.id.ed_thongtin);

                Button capnhat = dialog1.findViewById(R.id.btn_capnhat);
                Button huy = dialog1.findViewById(R.id.btn_huy);

                capnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = thongtin.getText().toString();

                        if (dao.updatePhoneKh(a, user) == true) {
                            Toast.makeText(getContext(), "Cập nhật Thành Công", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();

                            sdt.setText(a);
                        } else {
                            Toast.makeText(getContext(), "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                        }

                    }
                });
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                dialog1.show();
            }
        });

        tv_suadiachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.dialog_suathongtin);

                EditText thongtin = dialog1.findViewById(R.id.ed_thongtin);

                Button capnhat = dialog1.findViewById(R.id.btn_capnhat);
                Button huy = dialog1.findViewById(R.id.btn_huy);

                capnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = thongtin.getText().toString();

                        if (dao.updateDiaChiKh(a, user) == true) {
                            Toast.makeText(getContext(), "Cập nhật Thành Công", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                            diaChi.setText(a);

                        } else {
                            Toast.makeText(getContext(), "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                        }

                    }
                });
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                dialog1.show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    void dangxuat() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");

        if (u.equals("") || p.equals("")) {
            Log.d("TAG", "dang nhap");
            Intent intent = new Intent(getContext(), DangNhapActivity.class);
            startActivity(intent);
        } else {
            Log.d("TAG", "dang xuat");
            Toast.makeText(getContext(), "Bạn đã đăng xuất thành công", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    void dialogAvatar() {
        SharedPreferences mypref = getContext().getSharedPreferences("Mypref", MODE_PRIVATE);
        String user = mypref.getString("key_TK1", "");

        //Khởi tạo dialog
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_avatar);


        //ánh xạ
        TextInputLayout layoutavatar = dialog.findViewById(R.id.layout_avatar);
        Button btnthem = dialog.findViewById(R.id.them_avatar);
        Button btnhuy = dialog.findViewById(R.id.huy_avatar);

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link_avatar = layoutavatar.getEditText().getText().toString();

                if (taiKhoanDAO.updateavatar(link_avatar, user)) {
                    Toast.makeText(getContext(), "Thay avatar thành công", Toast.LENGTH_SHORT).show();
                    Glide.with(getContext()).load(Uri.parse(link_avatar)).into(avatar);
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Thay Avatar Thất Bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();


    }
}