package com.example.akhrorjon.miniprojectoniss;

/**
 * Created by SHAKHZOD on 12/12/2017.
 */


public class Person {
    private String uname;
    private String upass;
    private String salt;
    private String fname;
    private String baccount;
    private String balance;

    public Person(String uname, String upass,String salt,String fname,String baccount,String balance){
        this.uname = uname;
        this.upass = upass;
        this.salt = salt;
        this.fname = fname;
        this.baccount = baccount;
        this.balance = balance;
    }

    public void setUname(String uname){
        this.uname = uname;
    }
    public void setUpass(String surname){
        this.upass = upass;
    }
    public void setSalt(String salt){
        this.salt = salt;
    }
    public void setFname(String fname){
        this.fname = fname;
    }
    public void setBaccount(String baccount){
        this.baccount = baccount;
    }
    public void setBalance(String balance){
        this.balance = balance;
    }
    public String getUname(){
        return uname;
    }
    public String getUpass(){
        return upass;
    }
    public String getSalt(){
        return salt;
    }
    public String getFname(){
        return fname;
    }
    public String getBaccount(){
        return baccount;
    }
    public String getBalance(){
        return balance;
    }

}
