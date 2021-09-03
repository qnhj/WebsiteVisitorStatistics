package xyz.baochao.service;


import xyz.baochao.pojo.UserLogin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserLoginService {
    int selectLoginAllNum(String userName);
    List<UserLogin> selectLoginMsgMe(HttpServletRequest request);
}
