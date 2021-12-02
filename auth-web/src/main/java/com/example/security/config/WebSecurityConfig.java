package com.example.security.config;

import cn.hutool.core.util.ObjectUtil;
import com.example.rest.Anonymous;
import com.example.security.handler.MyAccessDeniedHandler;
import com.example.security.service.MyUserDetailsServiceImpl;
import com.example.security.handler.MyAuthenticationEntryPoint;
import com.example.security.filter.AuthorizationTokenFilter;
import com.example.jwt.JwtProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author jiangqiangqiang
 * @description: security配置文件
 * @date 2021/10/19 7:48 下午
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;
    @Resource
    private MyAuthenticationEntryPoint authEntryPoint;
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private JwtProvider jwtProvider;
    @Resource
    private ApplicationContext applicationContext;

    /**
     * 密码加密
     *
     * @return /
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 指定认证对象来源
     *
     * @param auth /
     * @throws Exception /
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 用户注册的时候密码加密
        auth.userDetailsService(myUserDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 获取匿名url
        RequestMappingHandlerMapping handlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
        Map<String, Set<String>> anonymousUrl = getAnonymousUrl(handlerMapping);
        // authorizeRequests()开启权限认证
        // anyRequest().authenticated()所有的请求都要认证才能访问
        // formLogin()对应表单认证
        // logout()对应注销配置
        http.csrf().disable()
                // 认证失败处理
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .accessDeniedHandler(myAccessDeniedHandler).and()
                // 基于token，不要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 防止iframe 造成跨域
                .headers().frameOptions().disable().and().formLogin().and()
                // 过滤请求
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/index").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/webSocket/**"
                ).permitAll()
                .antMatchers(HttpMethod.GET, anonymousUrl.get(RequestMethod.GET.name()).toArray(String[]::new)).permitAll()
                .antMatchers(HttpMethod.PUT, anonymousUrl.get(RequestMethod.PUT.name()).toArray(String[]::new)).permitAll()
                .antMatchers(HttpMethod.POST, anonymousUrl.get(RequestMethod.POST.name()).toArray(String[]::new)).permitAll()
                .antMatchers(HttpMethod.PATCH, anonymousUrl.get(RequestMethod.PATCH.name()).toArray(String[]::new)).permitAll()
                .antMatchers(HttpMethod.DELETE, anonymousUrl.get(RequestMethod.DELETE.name()).toArray(String[]::new)).permitAll()
                .antMatchers(anonymousUrl.get("ALL").toArray(String[]::new)).permitAll()
                // 放行OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 需要鉴权认证的
                .anyRequest().authenticated()
                // 添加过滤器
                .and().addFilter(new AuthorizationTokenFilter(authenticationManagerBean(), jwtProvider, myUserDetailsServiceImpl));
    }

    /**
     * AuthenticationManager加入spring容器
     *
     * @return /
     * @throws Exception /
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 获取匿名url
     *
     * @param handlerMapping handlerMapping
     * @return /
     */
    private Map<String, Set<String>> getAnonymousUrl(RequestMappingHandlerMapping handlerMapping) {
        Set<String> get = new HashSet<>();
        Set<String> post = new HashSet<>();
        Set<String> put = new HashSet<>();
        Set<String> patch = new HashSet<>();
        Set<String> delete = new HashSet<>();
        Set<String> all = new HashSet<>();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> methodEntry : handlerMethods.entrySet()) {
            Anonymous anonymous = methodEntry.getValue().getMethodAnnotation(Anonymous.class);
            if (ObjectUtil.isNotNull(anonymous)) {
                ArrayList<RequestMethod> requestMethods = new ArrayList<>(methodEntry.getKey().getMethodsCondition().getMethods());
                RequestMethod method = requestMethods.get(0);
                switch (method) {
                    case GET:
                        get.addAll(methodEntry.getKey().getPatternValues());
                        break;
                    case PUT:
                        put.addAll(methodEntry.getKey().getPatternValues());
                        break;
                    case POST:
                        post.addAll(methodEntry.getKey().getPatternValues());
                        break;
                    case PATCH:
                        patch.addAll(methodEntry.getKey().getPatternValues());
                        break;
                    case DELETE:
                        delete.addAll(methodEntry.getKey().getPatternValues());
                        break;
                    default:
                        all.addAll(methodEntry.getKey().getPatternValues());
                        break;
                }
            }
        }
        Map<String, Set<String>> anonymousUrls = new HashMap<>(6);
        anonymousUrls.put(RequestMethod.GET.name(), get);
        anonymousUrls.put(RequestMethod.PUT.name(), put);
        anonymousUrls.put(RequestMethod.POST.name(), post);
        anonymousUrls.put(RequestMethod.PATCH.name(), patch);
        anonymousUrls.put(RequestMethod.DELETE.name(), delete);
        anonymousUrls.put("ALL", all);
        return anonymousUrls;
    }
}
