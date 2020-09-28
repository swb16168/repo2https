package com.swb.shiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 72774
 * @Description
 * @create 2020-09-27 14:03
 */
@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroConfig {
    private static final String SHIRO_FILTER = "shiroFilter";
    private String loginUrl = "/index.html";// 默认的登陆页面
    private String[] anonUrls;
    private String logOutUrl;
    private String[] authcUlrs;

    @Bean("defaultWebSessionManager")
    public DefaultWebSessionManager defaultWebSessionManager(){
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        manager.setGlobalSessionTimeout(60*1000);
        return  manager;
    }


    /**
     * 配置SecurityManager
     */
    @Bean("securityManager")
    public SecurityManager securityManager(ShiroRelam userRealm ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 注入userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    @Bean(SHIRO_FILTER)
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        factoryBean.setSecurityManager(securityManager);
        // 设置未登陆的时要跳转的页面
        factoryBean.setLoginUrl(loginUrl);
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        // 设置放行的路径
        if (anonUrls != null && anonUrls.length > 0) {
            System.out.println("设置放行的路径");
            for (String anon : anonUrls) {
                System.out.println(anon);
                filterChainDefinitionMap.put(anon, "anon");

            }
        }
        // 设置登出的路径
        if (null != logOutUrl) {
            filterChainDefinitionMap.put(logOutUrl, "logout");
        }
        // 设置拦截的路径
        if (authcUlrs != null && authcUlrs.length > 0) {
            System.out.println("设置拦截的路径");
            for (String authc : authcUlrs) {

                System.out.println(authc);
                filterChainDefinitionMap.put(authc, "authc");
            }
        }
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return factoryBean;
    }

    /**
     * 声明userRealm
     */
    @Bean("userRealm")
    public ShiroRelam getRealm(){
        return new ShiroRelam();
    }

   // @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }



    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String[] getAnonUrls() {
        return anonUrls;
    }

    public void setAnonUrls(String[] anonUrls) {
        this.anonUrls = anonUrls;
    }

    public String getLogOutUrl() {
        return logOutUrl;
    }

    public void setLogOutUrl(String logOutUrl) {
        this.logOutUrl = logOutUrl;
    }

    public String[] getAuthcUlrs() {
        return authcUlrs;
    }

    public void setAuthcUlrs(String[] authcUlrs) {
        this.authcUlrs = authcUlrs;
    }
}
