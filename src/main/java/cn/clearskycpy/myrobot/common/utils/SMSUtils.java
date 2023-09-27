package cn.clearskycpy.myrobot.common.utils;

/**
 * @Author yunqing
 * @Date 2023/9/25 16:22
 * @PackageName:cn.clearskycpy.myrobot.utils
 * @ClassName: SMSUtils
 * @Description: TODO
 * @Version 1.0
 */

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 短信发送工具类
 */
public class SMSUtils {

    /**
     * 发送短信
     * @param signName 签名
     * @param templateCode 模板
     * @param phoneNumbers 手机号
     * @param param 参数
     */
    public static void sendMessage(String signName, String templateCode,String phoneNumbers,String param){
        //DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "accessKeyId", "secret");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5t9Jcsca2s4mqTUHUMnW", "aC7k8j7aTsUzE4PWlJVeHd2kVgRG7i");
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setSysRegionId("cn-hangzhou");
        // 设置要发送的手机号
        request.setPhoneNumbers(phoneNumbers);
        // 设置签名
        request.setSignName(signName);
        // 设置模板Code
        request.setTemplateCode(templateCode);
        // 使用一个动态参数(随机的验证码)替换Code
        request.setTemplateParam("{\"code\":\""+param+"\"}");
        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println("短信发送成功");
        }catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
