package main;

import dao.inter.*;

public class Main {

    public static void main(String[] args) throws Exception {
        SkillDaoInter dao = Context.instanceSkillDao();
        CountryDaoInter dao2 = Context.instanceCountryDao();
        System.out.println(dao2.getAllCountry());

    }
}


   
                