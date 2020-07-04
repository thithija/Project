package com.example.mysqlinsert.Activities.AdminUi;

import com.example.mysqlinsert.DbCon;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminMethods {
    Connection conn;
    DbCon con = new DbCon();

    //add interruption
    public  void adInterruption(String intDate, String intDesc,String startT,String EndT){
        conn = con.connectTODB();

        try {
            String insert = "INSERT INTO interruption(int_date,int_desc,s_time,e_time) VALUES('"+intDate+"','"+intDesc+"','"+startT+"','"+EndT+"')";
            Statement RegisterUser = conn.createStatement();
            RegisterUser.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    //create new admin account
    public  void createAminAcc(String name, String pwd,String mail,String nic){
        conn = con.connectTODB();

        try {
            String insert = "INSERT INTO admin(ad_name,ad_pwd,ad_email,ad_nic) VALUES('"+name+"','"+pwd+"','"+mail+"','"+nic+"')";
            Statement RegisterUser = conn.createStatement();
            RegisterUser.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //add peak time
    public void addPeakTime(String st,String et,String price){
        conn = con.connectTODB();

        try {
            String insert = "UPDATE peak set start_time='"+st+"',end_time='"+et+"',price='"+price+"' where 1";
            Statement RegisterUser = conn.createStatement();
            RegisterUser.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAdvertisement(String description,String endDate,String adUrl){
        conn = con.connectTODB();

        try {
            String insert = "INSERT INTO advertisement(ad_desc,ad_end,ad_url) VALUES('"+description+"','"+endDate+"','"+adUrl+"')";
            Statement RegisterUser = conn.createStatement();
            RegisterUser.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
