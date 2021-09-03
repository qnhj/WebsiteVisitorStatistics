package xyz.baochao.dao;

import xyz.baochao.pojo.Msg;

import java.util.List;
import java.util.Map;

public interface MsgMapper {
    int addMsg(Msg msg);
    List<Msg> selectMe(Map map);
    int selectMeAllNum(String userName);
    Msg selectOne(String uuid);
}
