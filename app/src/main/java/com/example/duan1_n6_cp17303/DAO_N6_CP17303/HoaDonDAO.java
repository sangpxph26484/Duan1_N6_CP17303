package com.example.duan1_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan1_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.HoaDonDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {

    Connection objConn;

    public HoaDonDAO() {
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<HoaDonDTO> getAll() {
        List<HoaDonDTO> listCat = new ArrayList<HoaDonDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM HOADON ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    HoaDonDTO hoaDonDTO = new HoaDonDTO();
                    hoaDonDTO.setIdhoadon(resultSet.getInt("ID"));
                    hoaDonDTO.setIdkhachhang(resultSet.getInt("IDKHACHHANG"));
                    hoaDonDTO.setNgaymua(resultSet.getString("NGAYMUA"));
                    hoaDonDTO.setTrangthai(resultSet.getString("TRANGTHAI"));


                    listCat.add(hoaDonDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return listCat;
    }

    public List<HoaDonDTO> gethoadonkh(String user) {
        List<HoaDonDTO> listCat = new ArrayList<HoaDonDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "select b.NGAYMUA, b.TRANGTHAI,c.TENSANPHAM,a.SOLUONG,a.TENKHACHHANG,a.TONGTIEN from CHITIETHOADON a\n" +
                        "inner join HOADON b on a.IDHOADON = b.ID \n" +
                        "inner join SANPHAM c on  a.IDSANPHAM = c.ID\n" +
                        "inner join KHACHHANG d on b.IDKHACHHANG = d.ID\n" +
                        "where d.USERNAME like '"+user+"'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    HoaDonDTO hoaDonDTO = new HoaDonDTO();
                    hoaDonDTO.setNgaymua(resultSet.getString("NGAYMUA"));
                    hoaDonDTO.setTrangthai(resultSet.getString("TRANGTHAI"));
                    hoaDonDTO.setTenkhachhang(resultSet.getString("TENKHACHHANG"));
                    hoaDonDTO.setTensp(resultSet.getString("TENSANPHAM"));
                    hoaDonDTO.setTongtien(resultSet.getFloat("TONGTIEN"));
                    hoaDonDTO.setSoluong(resultSet.getInt("SOLUONG"));

                    listCat.add(hoaDonDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return listCat;
    }

    public boolean insertRow(int idkh) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO HOADON(NGAYMUA,TRANGTHAI,IDKHACHHANG) VALUES (GETDATE(),N'Chưa Giao','" + idkh + "')";

                String generatedColumns[] = {"ID"};

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

            return true;
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu ");
            e.printStackTrace();
            return false;
        }
    }

    public void updateRow(HoaDonDTO hoaDonDTO) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE HOADON SET name= N'" + hoaDonDTO.getNgaymua() + "',N'" + hoaDonDTO.getTrangthai() + "'WHERE id = " + hoaDonDTO.getIdhoadon();


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu ");
            e.printStackTrace();
        }
    }

    public int  getidhoadon() {
        int idhoadon = 0;

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT MAX(ID) as 'IDHOADON' FROM HOADON ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    idhoadon = resultSet.getInt("IDHOADON");




                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return idhoadon;
    }
}
