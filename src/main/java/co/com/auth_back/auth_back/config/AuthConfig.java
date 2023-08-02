package co.com.auth_back.auth_back.config;

import co.com.auth_back.auth_back.interceptors.LoggerInterceptor;
import co.com.auth_back.auth_back.interceptors.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@EnableWebMvc
public class AuthConfig implements WebMvcConfigurer {
    private final TokenHandler tokenHandler;
    private final LoggerInterceptor loggerInterceptor;

    @Autowired
    public AuthConfig(TokenHandler tokenHandler, LoggerInterceptor loggerInterceptor) {
        this.tokenHandler = tokenHandler;
        this.loggerInterceptor = loggerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(loggerInterceptor).addPathPatterns("/**");
        initializeAuthRoute(interceptorRegistry);
        initializeUserRoute(interceptorRegistry);
    }

    private void initializeAuthRoute(InterceptorRegistry interceptorRegistry) {
        this.tokenHandler.addToPermissionListByPath("auth", new char[]{'A', 'L', 'E'});
        interceptorRegistry.addInterceptor(tokenHandler).addPathPatterns("/auth/**").excludePathPatterns(
                "/auth/login",
                "/auth/register",
                "/auth/validateCredential",
                "/auth/enableUser",
                "/auth/recoverPassword"
        );
    }

    private void initializeUserRoute(InterceptorRegistry interceptorRegistry) {
        this.tokenHandler.addToPermissionListByPath("user", new char[]{'A'});
        interceptorRegistry.addInterceptor(tokenHandler).addPathPatterns("/user/**");
    }

    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("PUT","DELETE","POST","GET","OPTIONS")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
