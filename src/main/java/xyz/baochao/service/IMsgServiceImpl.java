package xyz.baochao.service;

import cz.mallat.uasparser.UserAgentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.baochao.dao.MsgMapper;
import xyz.baochao.pojo.Msg;
import xyz.baochao.pojo.User;
import xyz.baochao.util.DateTimeUtil;
import xyz.baochao.util.MyUuid;
import xyz.baochao.util.UserAgentUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IMsgServiceImpl implements IMsgService {

    @Autowired
    private MsgMapper msgMapper;

    @Override
    @Transactional
    public int addMsg(HttpServletRequest request) throws IOException {
        Msg msg = getMsg(request);
        msgMapper.addMsg(msg);
        return 0;
    }

    @Override
    public List<Msg> selectMe(HttpServletRequest request) {
        //获取参数 处理参数
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userName = user.getUserName();
        String pageNumString = request.getParameter("pageNum");
        String quantityString = request.getParameter("quantity");
        if (pageNumString == null || "".equals(pageNumString)){
            pageNumString = "1";
        }
        if (quantityString == null || "".equals(quantityString)){
            if (session.getAttribute("quantity")==null){
                quantityString = "10";
            }else {
                quantityString = session.getAttribute("quantity").toString();
            }
        }
        int pageNum = Integer.valueOf(pageNumString);
        int quantity = Integer.valueOf(quantityString);
        int beginNum = pageNum * quantity - quantity;
        Map map = new HashMap<>();
        map.put("beginNum",beginNum);
        map.put("quantity",quantity);
        map.put("userName",userName);

        //记录一下每页条数
        session.setAttribute("quantity",quantity);

        return msgMapper.selectMe(map);
    }

    @Override
    public int selectMeAllNum(String userName) {
        return msgMapper.selectMeAllNum(userName);
    }

    @Override
    public Msg selectOne(String uuid) {
        return msgMapper.selectOne(uuid);
    }

    @Override
    @Transactional
    public boolean updataChangeMark(Map map) {
        msgMapper.upMark(map);
        map.put("markRemarks",DateTimeUtil.getDateTime()+"修改了标记");
        msgMapper.upMarkRemarks(map);
        return true;
    }

    @Override
    public boolean updataChangeMarkRemarks(Map map) {
        msgMapper.upMarkRemarks(map);
        return true;
    }
    @Override
    public boolean updataChangeRemarks(Map map) {
        msgMapper.upRemarks(map);
        return true;
    }

    /**
     * 通过请求获取一个Msg实体类对象
     * @param request
     * @return Msg
     */
    public static Msg getMsg(HttpServletRequest request) throws IOException {
        UserAgentInfo userAgentInfo = UserAgentUtil.getUserAgent(request);

        Msg msg = new Msg();
        msg.setUuid(MyUuid.getUuid());
        msg.setTime(DateTimeUtil.getDateTime());

        msg.setProtocol(request.getProtocol()); //协议：http协议
        msg.setRemoteAddr(request.getRemoteAddr()); //客户端IP
        msg.setRemotePort(request.getRemotePort()); //客户端端口
        msg.setMethod(request.getMethod()); //请求方式 GET还是POST 这里应该只能抓到get请求
        msg.setLocale(request.getLocale().toString()); //用户的语言环境
        msg.setRemoteUser(request.getRemoteUser()); //远程用户
        msg.setQueryString(request.getQueryString()); //查询字符串

        msg.setOsFamily(userAgentInfo.getOsFamily()); //操作系统家族
        msg.setOsName(UserAgentUtil.getOs(request)); //操作系统详细数据
        msg.setUaName(userAgentInfo.getUaName()); //浏览器名称和版本
        msg.setUaFamily(userAgentInfo.getUaFamily()); //浏览器品牌名称
        msg.setBrowserVersion(userAgentInfo.getBrowserVersionInfo()); //浏览器版本
        msg.setDeviceType(userAgentInfo.getDeviceType()); //用户设备类型
        msg.setRemoteUser(userAgentInfo.getType()); //类型

        msg.setMark(false); //默认不标星

        String who = request.getParameter("who");
        String url = request.getParameter("url");
        if (who != null) {
            msg.setAdmin(who);
        }
        if (url != null) {
            msg.setUrl(url);
        }

        return msg;
    }

}
