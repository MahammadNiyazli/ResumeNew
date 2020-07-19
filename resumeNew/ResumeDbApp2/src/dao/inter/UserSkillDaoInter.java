package dao.inter;

import entity.User;
import entity.UserSkill;

import java.sql.SQLException;
import java.util.List;

public interface UserSkillDaoInter {

    public List<UserSkill> getAllSkillByUserId(int userId);
    
    public boolean insertUserSkill(UserSkill userSkill);
    
    public boolean removeUserSkill(int id);
    
    public boolean updateUserSkill(UserSkill userSkill);
}
