package xyz.baochao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String userUuid;        //用户唯一标识 32
    private String userName;        //用户名 50
    private String userPw;          //密码 50
    private Integer admin;          //权限级别 管理员1 普通用户0 管理员解封专用2
    private String userRemarks;     //备注 200
    private Integer userIndex;      //状态 用户连续登录失败次数
}
