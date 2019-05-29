package com.lea.oauth.config;

import com.lea.api.mapper.ResourceMapper;
import com.lea.oauth.security.CustomInvocationSecurityMetaDataSource;
import com.lea.oauth.security.CustomPasswordEncoder;
import com.lea.oauth.security.CustomVoter;
import com.lea.oauth.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("customUserDetails")
    UserDetailsService userDetailsService;

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    JwtFilter jwtFilter;

//    @Autowired
//    AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsn) {

                        fsn.setSecurityMetadataSource(customInvocationSecurityMetaDataSource(fsn.getSecurityMetadataSource(), resourceMapper));

                        fsn.setAccessDecisionManager(accessDecisionManager());

                        return fsn;
                    }
                })
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.formLogin()

//                .failureHandler(authenticationFailureHandler)
//                .loginPage("/login_page")
//                .loginProcessingUrl("/login")
//                .usernameParameter("userName")
//                .passwordParameter("password")
//                .permitAll()
                .successHandler(authenticationSuccessHandler);


        http.csrf().disable();
//
//        http.exceptionHandling()
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//                        response.setContentType("text/plain");
//                        response.setCharacterEncoding("UTF-8");
//
//                        try (PrintWriter writer = response.getWriter()) {
//                            writer.write(e.getMessage());
//                            writer.flush();
//                        }
//
//                    }
//                });


        //允许跨域
        http.cors();

        //是不是可以分布式调用
//        http.securityContext().securityContextRepository()


        //不创建session
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.authenticationProvider()

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new CustomPasswordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {

        /**
         *      忽略一些路径
         */
        web.ignoring().antMatchers("/actuator/**", "/info", "/health", "/hystrix.stream", "/open/**");
        web.ignoring().antMatchers(HttpMethod.OPTIONS);


        super.configure(web);
    }

    /**
     * 自定义的metasource
     *
     * @param securityMetadataSource
     * @return
     */
    @Bean
    public CustomInvocationSecurityMetaDataSource customInvocationSecurityMetaDataSource(FilterInvocationSecurityMetadataSource securityMetadataSource, ResourceMapper resourceMapper) {
        return new CustomInvocationSecurityMetaDataSource(securityMetadataSource, resourceMapper);
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(
                new CustomVoter(),
                new WebExpressionVoter()
        );
        return new AffirmativeBased(decisionVoters);
    }

    /**
     * 跨域配置
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));

        corsConfiguration.setAllowedMethods(Arrays.asList("PUT", "DELETE", "GET", "POST"));

        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));

        corsConfiguration.setExposedHeaders(Arrays.asList("access-control-allow-headers",
                "access-control-allow-methods",
                "access-control-allow-origin"));

        /**
         *  用户凭证
         */
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;

    }

}
