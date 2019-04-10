package com.example.bmobdemo;

import cn.bmob.v3.BmobUser;

public class MyUser  extends BmobUser {

    //补充其他属性
    private  String fakename;//昵称
    private  String introd;//简介
    private  String sex; //性别 男1女0
    private  String interesting; //兴趣爱好

    public String getFakename()
    {
        return  fakename;
    }
    public void  setFakename(String fakename)
    {
        this.fakename=fakename;
    }
    public String getIntrod()
    {
        return  introd;
    }
    public  void  setIntrod(String introd)
    {
        this.introd=introd;
    }
    public  String getSex()
    {
        return  sex;
    }
    public  void  setSex(String sex)
    {
        this.sex=sex;
    }
    public  String getInteresting()
    {
        return interesting;
    }
    public  void  setInteresting(String interesting)
    {
        this.interesting=interesting;
    }
}
