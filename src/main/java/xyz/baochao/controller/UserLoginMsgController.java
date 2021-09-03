package xyz.baochao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.baochao.pojo.User;
import xyz.baochao.pojo.UserLogin;
import xyz.baochao.service.IUserLoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/loginMsg")
public class UserLoginMsgController {

    @Autowired
    private IUserLoginService iUserLoginService;

    //麻烦的分页查询
    @RequestMapping("/select/me")
    public String selectme(HttpServletRequest request){
        //查询主体
        List<UserLogin> userLogins = iUserLoginService.selectLoginMsgMe(request);
        request.setAttribute("userLogins",userLogins);

        //总记录数
        User user = (User) request.getSession().getAttribute("user");
        int loginAllNum = iUserLoginService.selectLoginAllNum(user.getUserName());
        request.setAttribute("loginAllNum",loginAllNum);

        //当前页面
        String loginPage = request.getParameter("loginPage");
        if (loginPage==null || "".equals(loginPage)){
            loginPage = "1";
        }
        request.setAttribute("loginPage",loginPage);

        //总页面
        HttpSession session = request.getSession();
        int quantity = (int)session.getAttribute("quantity");
        int loginPages = loginAllNum / quantity;
        if (loginAllNum % quantity != 0) {
            loginPages += 1;
        }
        request.setAttribute("loginPages",loginPages);

        return "selectLoginMsg";
    }
}
