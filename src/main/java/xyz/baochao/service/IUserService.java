package xyz.baochao.service;

import xyz.baochao.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface IUserService {
    boolean userLogin(HttpServletRequest request, HashMap<String,String> map) throws IOException;
    boolean userUpDataUserName(HttpServletRequest request);
    boolean userUpDataUserPw(HttpServletRequest request);
    boolean userAdmin(String uuid);
    List<User> selectLock(int i);
    boolean addUser(String userName,String userPw,HttpSession session);
    //验证用户名是否可用
    boolean addUserUserName(String userName,HttpSession session);
}
