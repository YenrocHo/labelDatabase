/**
 * projectName: labelDatabase
 * fileName: CredentialsMatcher.java
 * packageName: com.fc.aden.shiro.service
 * date: 2019-09-23
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.shiro.service;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: CredentialsMatcher
 * @packageName: com.fc.aden.shiro.service
 * @description: 自定义密码验证的方式
 * @data: 2019-09-23
 **/
public class CredentialsMatcher  extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        //获得数据库中的密码
        String dbPassword=(String) info.getCredentials();
        //进行密码的比对
        return this.equals(inPassword, dbPassword);
    }

}
