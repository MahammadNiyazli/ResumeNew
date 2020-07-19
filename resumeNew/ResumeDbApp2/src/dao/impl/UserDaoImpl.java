package dao.impl;


import entity.Country;
import entity.Skill;
import entity.User;
import entity.UserSkill;
import dao.AbstractDAO;
import dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {


    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");
       

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);
        return new User(id, name, surname, phone, email,profileDesc,address,birthdate, nationality, birthplace);
    }

    public List<User> getAll() {

        List<User> result = new ArrayList<User>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select u.*,n.nationality as nationality , c.name as birthplace " +
                    "from user u " +
                    "left join country n on n.id=u.nationality_id" +
                    " left join country c on c.id=u.birthplace_id;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean updateUser(User u) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,phone=?,email=?"
                    + ",profile_description=?,birthdate=?,address=?,birthplace_id=?,nationality_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setDate(6, new Date(u.getBirthDate().getTime()));
            stmt.setString(7, u.getAddress());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addUser(User u) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email,profile_description,birthdate,address) values(?,?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setDate(6, new Date(u.getBirthDate().getTime()));
            stmt.setString(7, u.getAddress());
            return stmt.execute();
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public boolean removeUser(int id) {
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user  where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public User getById(int userId) {
        User result = null;
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select u.*,n.nationality as nationality , c.name as birthplace " +
                    "from user u " +
                    "left join country n on n.id=u.nationality_id" +
                    " left join country c on c.id=u.birthplace_id where u.id = " + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                result = getUser(rs);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


}
