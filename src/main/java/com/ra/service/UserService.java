package com.ra.service;

import com.ra.dto.request.UserLoginDTO;
import com.ra.dto.request.UserRegisterDTO;
import com.ra.dto.response.ResponseUserLoginDTO;

import java.util.List;

public interface UserService {
    Boolean register(UserRegisterDTO user);
    ResponseUserLoginDTO login(UserLoginDTO user);

    ResponseUserLoginDTO loginAdmin(UserLoginDTO user);
    Boolean lock(Integer id);
    List<ResponseUserLoginDTO> listUser();
    List<String> uniquelist(String string);
}
