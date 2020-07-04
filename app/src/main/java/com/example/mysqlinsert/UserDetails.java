package com.example.mysqlinsert;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDetails {
    Connection conn;
    public static String uName;
    public static String uPassword;
    public static String uTp;
    public static String uEmail;
    public static String uAcc;


    public  void setuName(String uName) {
        UserDetails.uName = uName;
    }
    public  void setuTp(String uTp) {
        UserDetails.uTp = uTp;
    }
    public  void setuEmail(String uEmail) {
        UserDetails.uEmail = uEmail;
    }

    public void setuAcc(String uacc){
        UserDetails.uAcc = uacc;
    }

    public  String getuPassword() {
        return uPassword;
    }

    public  void setuPassword(String uPassword) {
        UserDetails.uPassword = uPassword;
    }

    public  String getuName() {
        return uName;
    }
    public  String getuTp() {
        return uTp;
    }
    public  String getuEmail() {
        return uEmail;
    }

    public String getuAcc(){
        return uAcc;
    }

    public void details(){
        DbCon ncon = new DbCon();
        conn = ncon.connectTODB();
        try {
            String select = "SELECT email,u_tp,account_number FROM users WHERE name='"+uName+"'";
            Statement selectstmt = conn.createStatement();
            ResultSet rst = selectstmt.executeQuery(select);

            while (rst.next()){
              setuEmail(rst.getString("email"));
              setuTp(rst.getString("u_tp"));
              setuAcc(rst.getString("account_number"));
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

}
