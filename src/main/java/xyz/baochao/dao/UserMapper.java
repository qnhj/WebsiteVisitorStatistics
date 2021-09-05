package xyz.baochao.dao;

import org.springframework.stereotype.Repository;
import xyz.baochao.pojo.User;

import java.util.HashMap;
import java.util.List;
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
    //错误次数清零
    int userLoginIndexTrue(String uuid);

    int upDataUserName(Map map);
    int upDataUserPw(Map map);

    List<User> selectLock(int i);

    int addUser(User user);

    int userName(String userName);


}
