package xyz.baochao.dao;

import org.springframework.stereotype.Repository;
import xyz.baochao.pojo.User;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface UserMapper {
    /**
     * 验证用户名和密码
     * @param map
     * @return User
     */
    User userLogin(Map<String,String> map);

    int userLoginIndexFalse(Map<String,String> map);
    int userLoginIndexTrue(String uuid);
}
