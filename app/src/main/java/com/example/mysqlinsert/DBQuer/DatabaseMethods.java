package com.example.mysqlinsert.DBQuer;

import com.example.mysqlinsert.DbCon;
import com.example.mysqlinsert.UserDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseMethods {
    Connection conn;
    DbCon con = new DbCon();
    String splitDate[],sDate;
    String message;
    public static String collectDate;
    public static String  nUnit="",nDates="",amount="",pDate="",fCharge="";
    public static float total;

    //Register users
    public  void UsRegister(String uName, String uAcc, String uNic, String uTp, String uEmail, String uPwd){
        conn = con.connectTODB();

        try {
            String insert = "INSERT INTO users(name,password,nic_num,email,u_tp,account_number) VALUES('"+uName+"','"+uPwd+"','"+uNic+"','"+uEmail+"','"+uTp+"','"+uAcc+"')";
            Statement RegisterUser = conn.createStatement();
            RegisterUser.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //add complain to the database
    public  void complainAdd(String uName,String cm_type, String uCom,String userEmail,String UserTp){

        conn = con.connectTODB();

        try {
            String insert = "INSERT INTO complain(u_name,c_type,u_complain,u_email,u_tp) VALUES('"+uName+"','"+cm_type+"','"+uCom+"','"+userEmail+"','"+UserTp+"')";
            Statement RegisterUser = conn.createStatement();
            RegisterUser.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //select interruptions dates
    public void setInterruptionDates(){
        conn = con.connectTODB();
        ResultSet rst;

        try {
            String select = "SELECT int_date FROM interruption";
            Statement selectstmt = conn.createStatement();
            rst = selectstmt.executeQuery(select);

            while (rst.next()){
                sDate = rst.getString("int_date");
                splitDate = sDate.split("-");

                collectDate = splitDate[2];


                splitDate=null;

            }
        }catch (Exception e){
            System.out.println(e);
        }



    }

    //return interruptions dates
    public String getCollectDate(){

        return collectDate;

    }

    //show interruption
    public String getInterMessage(String uDate){
        conn = con.connectTODB();
        ResultSet rst;
        try {
            String select = "SELECT int_desc FROM interruption WHERE int_date='"+uDate+"'";
            Statement selectstmt = conn.createStatement();
            rst = selectstmt.executeQuery(select);

            if (rst.equals(null)){
                message = null;
            }
            else{
                while (rst.next()){
                    message = rst.getString("int_desc");
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return message;
    }

    //get all interruption details
    public ResultSet getAllInterDetails(){
        ResultSet rst = null;


        try {
            String select = "SELECT * FROM interruption";
            Statement selectstmt = conn.createStatement();
            rst = selectstmt.executeQuery(select);


        }catch (Exception e){
            System.out.println(e);
        }
        return rst;
    }

    public void setBillDetails(){
        String Uacc;
        conn = con.connectTODB();
        UserDetails acc = new UserDetails();
        ResultSet rst;

        Uacc = acc.getuAcc();

        try {
            String select = "SELECT * FROM monthbill WHERE u_acc='"+Uacc+"'";
            Statement selectstmt = conn.createStatement();
            rst = selectstmt.executeQuery(select);

            if(rst==null){
                System.out.println("RstError");
            }
            else{
                System.out.println("RstOk");
            }
            while (rst.next()){
                this.nUnit = rst.getString("n_units");
                this.nDates = rst.getString("n_dates");
                this.amount = rst.getString("amount");
                this.pDate = rst.getString("p_date");

            }
            if (Float.parseFloat(this.nUnit) >= 0.00 && Float.parseFloat(this.nUnit) <= 60.00 ){
               this.total = Float.parseFloat(this.amount);
               this.fCharge="00.00";
            }
            else if (Float.parseFloat(this.nUnit) >= 61.00 && Float.parseFloat(this.nUnit) <= 90.00){
                this.total = (float) (Float.parseFloat(this.amount) + 90.00);
                this.fCharge="90.00";
            }
            else if (Float.parseFloat(this.nUnit) >= 91.00 && Float.parseFloat(this.nUnit) <= 120.00){
                this.total = (float) (Float.parseFloat(this.amount) + 480.00);
                this.fCharge="480.00";
            }
            else if (Float.parseFloat(this.nUnit) >= 121.00 && Float.parseFloat(this.nUnit) <= 180.00){

                this.total = (float) (Float.parseFloat(this.amount) + 480.00);
                this.fCharge="480.00";
            }
            else{
                this.total = (float) (Float.parseFloat(this.amount) + 540.00);
                this.fCharge="540.00";
                }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public String getnUnit(){
        return this.nUnit;
    }

    public String getnDates(){
        return this.nDates;
    }

    public String getAmount(){
        return this.amount;
    }

    public String getpDate(){
        return this.pDate;
    }

    public Float getTotal(){
        return this.total;
    }

    public String getfCharge(){
        return this.fCharge;
    }
}
