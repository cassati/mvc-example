package com.example.roma.sys.shiro.realm;

import com.example.roma.sys.entity.User;
import com.example.roma.sys.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleAuthorizingRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private IUserService userService;

    @Override
    public String getName() {
        return "myTestRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("准备获取授权信息");
        String username = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(userService.queryUserRoles(username));
        info.setStringPermissions(userService.queryUserMenus(username));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("准备获取验证信息");
        String username = (String)authenticationToken.getPrincipal();
        User user = userService.getByUsername(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                username,
                user.getPassword(),
                getName()
        );
        info.setCredentialsSalt(ByteSource.Util.bytes(username + user.getSalt()));
        return info;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
