package dao.inter;

import entity.EmploymentHistory;
import entity.User;
import entity.UserSkill;

import java.sql.SQLException;
import java.util.List;

public interface EmploymentHistoryDaoInter {

    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
    
    public boolean insertEmploymentHistory(EmploymentHistory emp);
    
    public boolean removeEmploymentHistory(int id);
     
    public boolean updateEmploymentHistory(EmploymentHistory emp);

}
