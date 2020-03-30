package com.TimeForStudy.service;



public interface LoginUserService {

    public Boolean CheckPhone(String phone);

    public String getNameByPhone(String phone);

    public Boolean CheckCode(int code);


}
