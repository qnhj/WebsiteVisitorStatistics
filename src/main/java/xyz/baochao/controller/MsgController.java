package xyz.baochao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.baochao.pojo.Msg;
import xyz.baochao.pojo.User;
import xyz.baochao.service.IMsgServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private IMsgServiceImpl iMsgService;

    @RequestMapping("/add")
    @ResponseBody
    public String addMsg(HttpServletRequest request){
        try {
            iMsgService.addMsg(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "msg add success";
    }

    @GetMapping("/select/me")
    public String selectMe(HttpServletRequest request){
        //获取分页查询内容
        List<Msg> msgs = iMsgService.selectMe(request);
        request.setAttribute("msgs",msgs);

        //获取该用户所有记录数量
        User user = (User) request.getSession().getAttribute("user");
        int allNum = iMsgService.selectMeAllNum(user.getUserName());
        request.setAttribute("allNum",allNum);

        //计算共有多少页面
        HttpSession session = request.getSession();
        int quantity = (int)session.getAttribute("quantity");
        int pages = allNum / quantity;
        if (allNum % quantity != 0) {
            pages += 1;
        }
        request.setAttribute("pages",pages);

        //计算第几页
        String page = request.getParameter("pageNum");
        if (page == null) {
            page="1";
        }
        request.setAttribute("page",page);

        return "selectMe";
    }

    @GetMapping("/select/one")
    public String selectOne(HttpServletRequest request,String uuid){
        Msg msg = iMsgService.selectOne(uuid);
        request.setAttribute("msg",msg);
        return "showOne";
    }

    @PostMapping("/updata/changeMark")
    @ResponseBody
    public String updataChangeMark(String msgUuid,String msgMark){
        Map map = new HashMap();
        map.put("uuid",msgUuid);
        boolean mark = true;
        if ("true".equals(msgMark)){
            mark = false;
        }
        map.put("mark",mark);
        return iMsgService.updataChangeMark(map)+"";
    }

}
