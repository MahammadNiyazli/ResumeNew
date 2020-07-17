package dao.inter;

import entity.User;
import entity.UserSkill;

import java.sql.SQLException;
import java.util.List;

public interface UserDaoInter {

    public List<User> getAll() throws SQLException, Exception;

    public boolean updateUser(User u);

    public boolean addUser(User u);

    public boolean removeUser(int id);

    public User getById(int id);

}
