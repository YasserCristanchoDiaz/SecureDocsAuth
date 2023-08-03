package co.com.auth_back.auth_back.interceptors;

import co.com.auth_back.auth_back.utils.HttpUtils;
import co.com.auth_back.auth_back.utils.LoggerUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI();
        String clientIp = HttpUtils.getRequestIp(request);
        String method = request.getMethod();

        Date now = new Date();
        String timestamp = now.toString();
        int status = response.getStatus();

        LoggerUtil.petitionLog(url,timestamp, method, String.valueOf(status), clientIp);
    }
}
