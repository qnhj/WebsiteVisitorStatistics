package xyz.baochao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    private String uuid; //uuid 32位 主键
    private String time; //添加时间 30位
    private String protocol; //使用协议 20位
    private String remoteAddr; //客户端IP 20位
    private Integer remotePort; //客户端端口
    private String method; //请求方式 10位
    private String locale; //用户语言环境 20位
    private String remoteUser; //远程用户 我也不知道是干什么的 50位
    private String queryString; //查询字符串 还是不知道这是干什么用的 100位
    private String osFamily; //用户操作系统家族 30位
    private String osName; //用户操作系统信息 100位
    private String uaName; //用户浏览器信息，比较专业的名称 30位
    private String uaFamily; //用户浏览器名称，浏览时品牌 30位
    private String browserVersion; //用户浏览器版本 30位
    private String deviceType; //用户设备类型 50位
    private String type; //类型 30位
    private String remarks; //备注 200位
    private Boolean mark; //标星标记 null和false代表未标星
    private String markRemarks; //标星备注 200位
    private String admin; //哪个用户的统计记录
    private String url; //需要统计的url备注
}
