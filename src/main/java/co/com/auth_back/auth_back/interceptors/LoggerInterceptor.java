package co.com.auth_back.auth_back.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggerInterceptor {

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI();
        ///String cliente = HttpUtils
        String method = request.getMethod();

        int status = response.getStatus();

        //Logger
    }
}
