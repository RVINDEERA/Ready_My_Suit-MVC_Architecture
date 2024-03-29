package lk.ijse.rms.model;

import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.UserDto;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterModel {
    public boolean checkDuplicate(String email,String password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password =?");

        pstm.setString(1,email);
        pstm.setString(2,password);

        ResultSet resultSet = pstm.executeQuery();

        return resultSet.next();

    }


    public boolean userRegister(UserDto userDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO  user VALUES (?,?,?)");

        pstm.setString(1,userDto.getEmail());
        pstm.setString(2,userDto.getUserName());
        pstm.setString(3,userDto.getPassword());

        return pstm.executeUpdate() > 0 ;

    }

    public boolean checkAdminPassword(String password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE password = ?");

        pstm.setString(1,password);

        ResultSet resultSet = pstm.executeQuery();

        return resultSet.next();

    }
}
