package com.ctguqmx.recruit.util;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;

import java.util.HashMap;
import java.util.Map;

public class Qiniu {

    // 设置需要操作的账号的AK和SK
    /*这些信息要写进配置文件*/
    private String ACCESS_KEY = "S1cZP0JfVTsjxz1mVWqS90Mt8jsDeP40PeeVvHiY";
    private String SECRET_KEY = "LkS4iunsde4__j_nmbd7nV_IbQe4EWOBx8UamwXO";
    // 考试通知模板
    private String TemplateId = "1160862326644019200";
    // 报名成功模板
//    private String TemplateId = "1160863137293934592";

    /**
     * @param address 考试地点
     * @param name 考生姓名
     * @param mobile 手机号码
     * @param time 考试时间
     */
    public boolean SendMessage(String mobile, String name, String time, String address) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        // 实例化一个SmsManager对象
        SmsManager smsManager = new SmsManager(auth);

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name",name);
            map.put("time",time);
            map.put("address",address);
            Response resp = smsManager.sendMessage(TemplateId, new String[] {mobile}, map);

            System.out.println(resp.bodyString());
            return true;

        } catch (QiniuException e) {
            System.out.println(e);
            return false;
        }
    }

}
