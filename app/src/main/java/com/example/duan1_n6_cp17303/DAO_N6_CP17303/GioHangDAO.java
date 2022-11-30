package com.example.duan1_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan1_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.CTHDDTO;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.GioHangDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GioHangDAO {
    Connection objConn;
    public GioHangDAO(){
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<GioHangDTO> getAll(){
        List<GioHangDTO> listCat = new ArrayList<GioHangDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM GIOHANG ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    GioHangDTO gioHangDTO = new GioHangDTO();
                    gioHangDTO.setIdgiohang(resultSet.getInt("ID"));
                    gioHangDTO.setTensanpham(resultSet.getString("TENSANPHAM"));
                    gioHangDTO.setGiatien(resultSet.getFloat("GIATIEN"));
                    gioHangDTO.setSoluong(resultSet.getInt("SOLUONG"));
                    gioHangDTO.setImg(resultSet.getString("ANHSANPHAM"));
                    gioHangDTO.setIdkhachhang(resultSet.getInt("IDKHACHHANG"));



                    listCat.add(gioHangDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  listCat;
    }
    public void insertRow (GioHangDTO gioHangDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO GIOHANG(TENSANPHAM,GIATIEN,SOLUONG,ANHSANPHAM) VALUES (N'" +gioHangDTO.getTensanpham() + "','"+gioHangDTO.getGiatien()+"','"+gioHangDTO.getSoluong()+"'+'"+gioHangDTO.getImg()+"')";

                String generatedColumns[] = { "ID" };

                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL, generatedColumns);
                stmtInsert.execute();

                Log.d("zzzzz", "insertRow: finish insert");
                // lấy ra ID cột tự động tăng
                ResultSet rs = stmtInsert.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    Log.d("zzzz", "insertRow: ID = " + id);
                }

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu " );
            e.printStackTrace();
        }
    }

    public void deleteRow(GioHangDTO gioHangDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "DELETE FROM GIOHANG WHERE id = " + gioHangDTO.getIdgiohang();

                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Delete");

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi xóa dữ liệu " );
            e.printStackTrace();
        }
    }
}
