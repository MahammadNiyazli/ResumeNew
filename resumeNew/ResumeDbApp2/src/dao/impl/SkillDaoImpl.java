package dao.impl;

import dao.AbstractDAO;
import dao.inter.SkillDaoInter;
import dao.inter.UserSkillDaoInter;
import entity.Country;
import entity.Skill;
import entity.User;
import entity.UserSkill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {


    public List<Skill> getAllSkill() {
        List<Skill> result = new ArrayList<Skill>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select * from skill");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill skill = getSkill(rs);
                result.add(skill);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Skill getSkill(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        Skill s = new Skill(id, name);
        return s;
    }
}

