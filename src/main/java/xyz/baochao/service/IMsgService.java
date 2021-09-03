package xyz.baochao.service;

import xyz.baochao.pojo.Msg;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface IMsgService {
    int addMsg(HttpServletRequest request) throws IOException;
    List<Msg> selectMe(HttpServletRequest request);
    int selectMeAllNum(String userName);
    Msg selectOne(String uuid);
}
