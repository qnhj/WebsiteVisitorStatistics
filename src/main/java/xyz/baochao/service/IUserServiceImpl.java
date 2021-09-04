package xyz.baochao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.baochao.dao.MsgMapper;
import xyz.baochao.dao.UserLoginMapper;
import xyz.baochao.dao.UserMapper;
import xyz.baochao.pojo.Msg;
import xyz.baochao.pojo.User;
import xyz.baochao.pojo.UserLogin;
import xyz.baochao.util.DateTimeUtil;
import xyz.baochao.util.MyUuid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MsgMapper msgMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    @Transactional
    public boolean userLogin(HttpServletRequest request, HashMap<String, String> map) throws IOException {

        //写入总记录
        Msg msg = IMsgServiceImpl.getMsg(request);
        msgMapper.addMsg(msg);

        //写入登录日志
        String userName = map.get("userName");
        UserLogin userLogin = getUserLogin(request, userName, msg);

        //进行登录验证
        User user = userMapper.userLogin(map);
        boolean login = false;
        if (user != null) {
            Integer adminNum = user.getAdmin();
            Integer loginIndex = user.getUserIndex();
            if (adminNum == 0) { //普通用户
                if (loginIndex <= 5) {
                    login = true;
                }
            } else if (adminNum == 1) { //管理员
                if (loginIndex <= 3) {
                    login = true;
                }
            } else if (adminNum == 2) { //解封管理员的善后账户
                login = true;
            }
            //加个备注，如果登录成功会清空，只有账户被锁才会是这个
            userLogin.setLoginRemarks("\"" + userName + "\"第" + (loginIndex + 1) + "次登录异常");
        }

        if (login) { //判断登陆标记
            //登录错误计数器清零
            userMapper.userLoginIndexTrue(user.getUserUuid());
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //提交登录记录
            userLogin.setLoginRemarks(null); //上面登录失败的备注
            userLoginMapper.addUserLogin(userLogin);
            return true;
        }
        //登录错误次数加一
        userMapper.userLoginIndexFalse(map);
        //提交登录记录
        if (userLogin.getLoginRemarks() == null || "".equals(userLogin.getLoginRemarks())) {
            userLogin.setLoginRemarks("登录失败");
        }
        userLoginMapper.addUserLogin(userLogin);
        return false;
    }

    @Override
    @Transactional
    public boolean userUpDataUserName(HttpServletRequest request) {

        //获取原用户名
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //修改用户数据
        String newUserName = request.getParameter("newUserName");
        if (newUserName != null && !("".equals(newUserName))) {
            Map map = new HashMap();
            map.put("newUserName", newUserName);
            map.put("uuid", user.getUserUuid());
            userMapper.upDataUserName(map);
        } else {
            return false;
        }

        //修改信息记录表
        String oldUserName = user.getUserName();
        Map map = new HashMap();
        map.put("newUserName", newUserName);
        map.put("oldUserName", oldUserName);
        msgMapper.upDataAdmin(map);

        //修改登录记录
        userLoginMapper.upDataUserName(map);

        //修改session内用户
        user.setUserName(newUserName);
        return true;
    }

    @Override
    public boolean userUpDataUserPw(HttpServletRequest request) {
        //获取密码和session中的密码
        String userPw = request.getParameter("userPw");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user.getUserPw().equals(userPw)) { //当密码正确后才能修改
            String newUserPw = request.getParameter("newUserPw");
            if (newUserPw != null && !("".equals(newUserPw))) { //新密码不为空
                Map map = new HashMap();
                map.put("newUserPw", newUserPw);
                map.put("uuid", user.getUserUuid());
                userMapper.upDataUserPw(map);
                //改变session值
                user.setUserPw(newUserPw);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean userAdmin(String uuid) {
        int i = userMapper.userLoginIndexTrue(uuid);
        if (i == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> selectLock(int i) {
        List<User> userList = userMapper.selectLock(i);
        return userList;
    }

    public static UserLogin getUserLogin(HttpServletRequest request, String userName, Msg msg) {
        UserLogin userLogin = new UserLogin();
        userLogin.setLoginUuid(MyUuid.getUuid());
        userLogin.setUserName(userName);
        userLogin.setLoginTime(msg.getTime());
        userLogin.setLoginIp(request.getRemoteAddr());
        userLogin.setMsgUuid(msg.getUuid());
        return userLogin;
    }

}
