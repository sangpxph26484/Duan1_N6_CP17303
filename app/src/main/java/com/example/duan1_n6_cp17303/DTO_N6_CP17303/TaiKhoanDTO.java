package com.example.duan1_n6_cp17303.DTO_N6_CP17303;

import java.sql.Blob;

public class TaiKhoanDTO {
    String username;
    String pass;
    String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public TaiKhoanDTO() {
    }
}
