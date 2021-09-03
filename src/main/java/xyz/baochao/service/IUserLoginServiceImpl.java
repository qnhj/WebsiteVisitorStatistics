package xyz.baochao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.baochao.dao.UserLoginMapper;
import xyz.baochao.dao.UserMapper;
import xyz.baochao.pojo.Msg;
import xyz.baochao.pojo.User;
import xyz.baochao.pojo.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IUserLoginServiceImpl implements IUserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public int selectLoginAllNum(String userName) {
        return userLoginMapper.selectLoginAllNum(userName);
    }

    @Override
    public List<UserLogin> selectLoginMsgMe(HttpServletRequest request) {
        //获取参数
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userName = user.getUserName();
        String loginPage = request.getParameter("loginPage");
        String loginQuantity = request.getParameter("loginQuantity");
        if (loginPage == null) {
            loginPage = "1";
        }
        if (loginQuantity == null) {
            if (session.getAttribute("loginQuantity") == null) {
                loginQuantity = "10";
            }else {
                loginQuantity = session.getAttribute("loginQuantity").toString();
            }
        }
        int intLoginPage = Integer.parseInt(loginPage);
        int intLoginQuantity = Integer.parseInt(loginQuantity);
        //把每页显示的条数存入session
        session.setAttribute("loginQuantity",intLoginQuantity);
        //计算起始页面
        int loginBeginNum = intLoginPage * intLoginQuantity - intLoginQuantity;

        Map map = new HashMap();
        map.put("userName",userName);
        map.put("loginBeginNum",loginBeginNum);
        map.put("loginQuantity",intLoginQuantity);
        List<UserLogin> msgs = userLoginMapper.selectLoginMsg(map);
        return msgs;
    }
}
