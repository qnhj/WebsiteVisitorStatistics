package xyz.baochao.util;

import java.util.UUID;

public class MyUuid {
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
