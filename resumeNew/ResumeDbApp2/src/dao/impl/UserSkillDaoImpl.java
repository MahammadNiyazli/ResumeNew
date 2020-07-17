package dao.impl;

import dao.AbstractDAO;
import dao.inter.UserDaoInter;
import dao.inter.UserSkillDaoInter;
import entity.Country;
import entity.Skill;
import entity.User;
import entity.UserSkill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {


    public List<UserSkill> getAllSkillByUserId(int userId) {

        List<UserSkill> result = new ArrayList<UserSkill>();
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("select u.*,us.skill_id,us.power,s.name as skill_name " +
                    "from user_skill us " +
                    "left join user u on us.user_id = u.id " +
                    "left join skill s on s.id = us.skill_id " +
                    "where us.user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        int power = rs.getInt("power");
        String skillName = rs.getString("skill_name");
        Skill skill = new Skill(skillId, skillName);
        return new UserSkill(null, new User(userId), skill, power);
    }

}
