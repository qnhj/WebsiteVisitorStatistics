package xyz.baochao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.baochao.pojo.User;
import xyz.baochao.pojo.UserLogin;
import xyz.baochao.service.IUserLoginService;
import xyz.baochao.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @ResponseBody
    @PostMapping("/login")
    public String userLogin(HttpServletRequest request, @RequestParam HashMap<String,String> map){
        String userName = null;
        String userPw = null;
        for (String key : map.keySet()){ //安全的进行数据校验
            if ("userName".equals(key)){
                userName = map.get(key);
            }else if ("userPw".equals(key)){
                userPw = map.get(key);
            }
        }

        if (userName != null && userPw != null){ //数据无误传入下一层
            try {
                if (iUserService.userLogin(request,map)){
                    return "true";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "false";
    }


    @GetMapping("/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response){
        //清除用户的session
        request.getSession().removeAttribute("user");
        //页面重定向
        try {
            response.sendRedirect(request.getContextPath()+"/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/addTxt")
    public String add(){
        return "addTxt";
    }

    @GetMapping("/settings")
    public String userSettingIndex(HttpSession session){
        return "UserSettings";
    }

    @PostMapping("/upData/userPw")
    @ResponseBody
    public String updataUserPw(HttpServletRequest request){
        return iUserService.userUpDataUserPw(request)+"";
    }

    @PostMapping("/upData/userName")
    @ResponseBody
    public String updataUserName(HttpServletRequest request){
        return iUserService.userUpDataUserName(request)+"";
    }

    @RequestMapping("/admin")
    public String adminHelp(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        if (user.getAdmin()==1){
            //查询被锁定的账户
            List<User> userLogins = iUserService.selectLock(0);
            request.setAttribute("loginsUser",userLogins);
        }else if (user.getAdmin() == 2){
            //查询被锁定的管理员
            List<User> userLogins = iUserService.selectLock(1);
            request.setAttribute("loginsUser",userLogins);
        }else {
            return "UserSettings";
        }
        return "adminHelp";
    }

    @RequestMapping("/admin/do")
    public String adminHelpOne(String uuid){
        iUserService.userAdmin(uuid);
        return "adminHelp";
    }

    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(String userName,String userPw,HttpSession session){
        if (userName != null && !("".equals(userName))){
            if (userPw != null && !("".equals(userPw))){
                return iUserService.addUser(userName, userPw,session)+"";
            }
        }
        return "false";
    }

    @PostMapping("/addUser/userName")
    @ResponseBody
    public String addUserUserName(String userName,HttpSession session){
        if (userName != null && !("".equals(userName))){
            return iUserService.addUserUserName(userName, session)+"";
        }
        return "false";
    }


}
