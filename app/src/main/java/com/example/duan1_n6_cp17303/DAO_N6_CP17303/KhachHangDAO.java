package com.example.duan1_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan1_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan1_n6_cp17303.DTO_N6_CP17303.KhachHangDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    Connection objConn;

    public KhachHangDAO() {
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<KhachHangDTO> getAll() {
        List<KhachHangDTO> listCat = new ArrayList<KhachHangDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM KHACHHANG ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    KhachHangDTO khachHangDTO = new KhachHangDTO();
                    khachHangDTO.setIdkhachhang(resultSet.getInt("ID"));
                    khachHangDTO.setTenkhachhang(resultSet.getString("TENKHACHHANG"));
                    khachHangDTO.setPhone(resultSet.getString("PHONE"));
                    khachHangDTO.setEmail(resultSet.getString("EMAIL"));
                    khachHangDTO.setDiachi(resultSet.getString("DIACHI"));
                    khachHangDTO.setUsername(resultSet.getString("USERNAME"));


                    listCat.add(khachHangDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return listCat;
    }

    public boolean insertRow(KhachHangDTO khachHangDTO) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO KHACHHANG(TENKHACHHANG,PHONE,EMAIL,DIACHI,USERNAME) VALUES (N'" + khachHangDTO.getTenkhachhang() + "','" + khachHangDTO.getPhone() + "','" + khachHangDTO.getEmail() + "',N'" + khachHangDTO.getDiachi() + "','" + khachHangDTO.getUsername() + "')";

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

    public void updateRow(KhachHangDTO khachHangDTO) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE KHACHHANG SET name= N'" + khachHangDTO.getTenkhachhang() + "',N'" + khachHangDTO.getPhone() + "','" + khachHangDTO.getEmail() + "',N'" + khachHangDTO.getDiachi() + "'WHERE USERNAME = '" + khachHangDTO.getUsername() + "'";


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu ");
            e.printStackTrace();
        }
    }

    public String getTenByUser(String user) {
        String ten = null;

        try {
            if (this.objConn != null) {

                String sqlQuery = "select TENKHACHHANG from KHACHHANG where USERNAME = '" + user + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    ten = resultSet.getString("TENKHACHHANG");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return ten;
    }

    public String getEmailByUser(String user) {
        String email = null;

        try {
            if (this.objConn != null) {

                String sqlQuery = "select EMAIL from KHACHHANG where USERNAME = '" + user + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    email = resultSet.getString("EMAIL");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return email;
    }

    public String getSdtByUser(String user) {
        String sdt = null;

        try {
            if (this.objConn != null) {

                String sqlQuery = "select PHONE from KHACHHANG where USERNAME = '" + user + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    sdt = resultSet.getString("PHONE");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return sdt;
    }

    public String getDcByUser(String user) {
        String diachi = null;

        try {
            if (this.objConn != null) {

                String sqlQuery = "select DIACHI from KHACHHANG where USERNAME = '" + user + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    diachi = resultSet.getString("DIACHI");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return diachi;
    }

    public int getidKh(String user) {

        int id = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT ID FROM KHACHHANG where USERNAME like '" + user + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list


                    id = resultSet.getInt("ID");


                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return id;
    }
    public String getAvatarByUser(String user){
        String avatar1 = null;

        try {
            if (this.objConn != null) {

                String sqlQuery = "select AVATAR from TAIKHOAN where USERNAME = '"+user+"'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    avatar1 = resultSet.getString("AVATAR");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return  avatar1;
    }
    public boolean updateDiaChiKh(String diachi, String user) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE KHACHHANG SET DIACHI= N'" + diachi + "'WHERE USERNAME = '" + user + "'";


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng

            return true;
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmailKh(String email, String user) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE KHACHHANG SET EMAIL= N'" + email + "'WHERE USERNAME = '" + user + "'";


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng

            return true;
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePhoneKh(String phone, String user) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE KHACHHANG SET PHONE= N'" + phone + "'WHERE USERNAME = '" + user + "'";


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng

            return true;
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTenKh(String ten, String user) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE KHACHHANG SET TENKHACHHANG= N'" + ten + "'WHERE USERNAME = '" + user + "'";


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng
            return true;

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu ");
            e.printStackTrace();
            return false;
        }
    }

    public String getDiachiByUser(String user){
        String diachi = null;

        try {
            if (this.objConn != null) {

                String sqlQuery = "select DIACHI from KHACHHANG where USERNAME = '"+user+"'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) {
                    diachi = resultSet.getString("DIACHI");
                }
            }


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return  diachi;


    }

    public int getidByUser(String user){
        int id = 0;

        try {
            if (this.objConn != null) {

                String sqlQuery = "select ID from KHACHHANG where USERNAME = '"+user+"'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) {

                    id = resultSet.getInt("ID");
                }
            }


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return  id;


    }

}
