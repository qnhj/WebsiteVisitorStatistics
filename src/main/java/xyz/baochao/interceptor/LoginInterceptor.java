package xyz.baochao.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
    拦截未登录用户进行登录
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
//        String uri = request.getRequestURI(); // 获取登录的uri，这个是不进行拦截的
//        if(session.getAttribute("loginUser")!=null || uri.indexOf("user/login")!=-1 || uri.indexOf("login.html")!=-1 ) {// 说明登录成功 或者 执行登录功能
        if(session.getAttribute("user")!=null){
            // 登录成功不拦截
            return true;
        }else {
            // 拦截后进入登录页面
            response.sendRedirect(request.getContextPath()+"/login.html");
            return false;
        }
    }
}
