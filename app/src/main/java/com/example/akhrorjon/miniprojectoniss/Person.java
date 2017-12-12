package com.example.akhrorjon.miniprojectoniss;

/**
 * Created by SHAKHZOD on 12/12/2017.
 */


public class Person {
    private String uname;
    private String upass;
    private String salt;

    public Person(String uname, String upass,String salt){
        this.uname = uname;
        this.upass =upass;
        this.salt =salt;
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
    public String getUname(){
        return uname;
    }
    public String getUpass(){
        return upass;
    }
    public String getSalt(){
        return salt;
    }

}
