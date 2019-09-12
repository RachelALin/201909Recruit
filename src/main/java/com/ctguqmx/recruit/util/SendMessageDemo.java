package com.ctguqmx.recruit.util;

public class SendMessageDemo {
    public static void sendmessage() {
        Qiniu qiniu = new Qiniu();
        boolean flag = qiniu.SendMessage("15871595179","罗闯","9月1号","一机房");
        if(flag) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
    }
}
