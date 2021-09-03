package xyz.baochao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.baochao.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

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
    public String userSettingIndex(){
        return "UserSettings";
    }
}
