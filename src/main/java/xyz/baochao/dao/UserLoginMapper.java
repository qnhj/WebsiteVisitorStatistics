package xyz.baochao.dao;

import xyz.baochao.pojo.Msg;
import xyz.baochao.pojo.UserLogin;

import java.util.List;
import java.util.Map;

public interface UserLoginMapper {
    int addUserLogin(UserLogin userLogin);
    List<UserLogin> selectLoginMsg(Map map);
    int selectLoginAllNum(String uuid);
    int upDataUserName(Map map);
}
