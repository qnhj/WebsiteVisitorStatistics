package xyz.baochao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
    private String loginUuid;       //(32) 唯一标识
    private String userName;        //(50) 用户名
    private String loginTime;       //(30) 登录时间
    private String loginIp;         //(20) 登录ip
    private String loginRemarks;    //(200) 备注
    private String msgUuid;         //(32) 总记录表的uuid
}
