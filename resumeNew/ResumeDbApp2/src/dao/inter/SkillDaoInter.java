package dao.inter;

import entity.Skill;

import java.util.List;

public interface SkillDaoInter {

    public List<Skill> getAllSkill();
    
    public boolean insertSkill(Skill skill);
    
    public boolean removeSkill(int id);
    
    public boolean updateSkill(Skill skill);

}
