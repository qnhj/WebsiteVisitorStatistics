package xyz.baochao.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public interface IUserService {
    boolean userLogin(HttpServletRequest request, HashMap<String,String> map) throws IOException;
}
