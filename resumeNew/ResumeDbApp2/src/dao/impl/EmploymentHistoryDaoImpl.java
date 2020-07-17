package dao.impl;

import dao.AbstractDAO;
import dao.inter.EmploymentHistoryDaoInter;
import entity.EmploymentHistory;
import entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {


    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<EmploymentHistory>();
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("select * from employment_history where user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);
                result.add(emp);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws SQLException {
        String header  = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate  = rs.getDate("end_Date");
        int userId = rs.getInt("user_id");

        EmploymentHistory emp = new EmploymentHistory(null,header,beginDate,endDate,jobDescription,new User(userId));
        return emp;

    }
}
