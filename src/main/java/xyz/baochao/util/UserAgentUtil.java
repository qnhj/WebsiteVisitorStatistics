package xyz.baochao.util;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserAgentUtil {
    /**
     * 解析用户端请求头信息的工具类
     * 获取用户系统详细信息使用 MyUserAgent.getOs(HttpServletRequest request)方法
     *
     * @param request
     * @return userAgentInfo
     * @throws IOException
     */
    public static UserAgentInfo getUserAgent(HttpServletRequest request) throws IOException {
        String str = request.getHeader("User-Agent");
        UASparser uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
        UserAgentInfo userAgentInfo = uasParser.parse(str);
        return userAgentInfo;
    }

    /**
     * 对操作系统的补充
     * @param request
     * @return String
     */
    public static String getOs(HttpServletRequest request) {
        String str = request.getHeader("User-Agent");
        return str.substring(str.indexOf("("), (str.indexOf(")") + 1));
    }

}

