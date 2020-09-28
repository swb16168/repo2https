package com.swb.shiro.config;

import com.swb.shiro.domain.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author 72774
 * @Description
 * @create 2020-09-27 13:59
 */
public class ShiroRelam extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo info=null;
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        if(token.getPrincipal().toString().equals("admin")) {
            info = new SimpleAuthenticationInfo(user, "admin", this.getName());
        }
        return info;
    }
}
