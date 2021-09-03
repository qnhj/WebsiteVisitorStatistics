package xyz.baochao.util;

import org.junit.Test;

public class DateTimeUtilTest {
    @Test
    public void Test1(){
        System.out.println(DateTimeUtil.getDateTime());
        System.out.println(MyUuid.getUuid());
        System.out.println(MyUuid.getUuid().length());
        String a = null+"asdf";
        System.out.println("a = " + a);
    }
    
    @Test
    public void Test2(){
//        String a = null;
        String a = "2";
        int aa = Integer.valueOf(a);
        System.out.println("aa = " + aa);
    }
}
