package com.ketd.auth.server;


import com.ketd.auth.dto.EmailDto;

public interface EmailService {

    /**
     * @description: 发送邮件接口
     *               该接口用于发送电子邮件，接受一个包含邮件相关信息的EmailDto对象作为参数。
     *               EmailDto包含邮件主题、邮件内容、收件人邮箱列表等信息。
     *               邮箱配置信息从类的成员变量中获取，若配置信息缺失将抛出运行时异常。
     *               使用JavaMail发送邮件，配置邮箱信息、设置邮件内容和标题，最终调用Mail.create().send()方法发送邮件。
     * @param: emailDto 包含邮件相关信息的EmailDto对象
     * @author: ketd
     * @date: 2024-01-11 13:19
     **/
    void send(EmailDto emailDto);

}
