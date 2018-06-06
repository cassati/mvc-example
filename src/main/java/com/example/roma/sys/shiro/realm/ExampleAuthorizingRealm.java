package com.example.roma.sys.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleAuthorizingRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getName() {
        return "myTestRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("准备获取授权信息");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("准备获取验证信息");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                authenticationToken.getPrincipal(),
                authenticationToken.getCredentials(),
                getName()
        );
        return info;
    }
}
