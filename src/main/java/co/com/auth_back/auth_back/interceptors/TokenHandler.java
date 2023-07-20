package co.com.auth_back.auth_back.interceptors;

import co.com.auth_back.auth_back.constants.MessageConstants;
import co.com.auth_back.auth_back.models.User;
import co.com.auth_back.auth_back.service.UserService;
import co.com.auth_back.auth_back.utils.RequestUtil;
import co.com.auth_back.auth_back.utils.TokenUtil;
import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenHandler implements HandlerInterceptor {
    private final UserService userService;
    private final Map<String, char[]> permissionsListByRoute;

    @Autowired
    public TokenHandler(UserService userService) {
        this.userService = userService;
        this.permissionsListByRoute = new HashMap<>();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        String method = request.getMethod();

        if (method.equals("OPTIONS")) {
            return true;
        }

        String route = RequestUtil.getPartUrI(request.getRequestURI(), 1);
        char[] permissions = permissionsListByRoute.get(route);

        Map<String, Claim> payload;
        try {
            payload = TokenUtil.validateToken(token);
            char role = payload.get("role").asString().charAt(0);
            String id  =  payload.get("id").asString();

            if (isValidInPermissionList(role, permissions) && isValidInUserList(id,role)) {
                return true;
            }
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MessageConstants.UNAUTHORIZED_TOKEN);
            return false;
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MessageConstants.UNAUTHORIZED_TOKEN);
            return false;
        }
    }

    private boolean isValidInUserList(String id, char role) throws Exception {
        User userFind = userService.findById(id);
        return userFind.getRole() == role;
    }

    private boolean isValidInPermissionList(char role, char[] permissions) {
        for(char permission : permissions) {
            if (role == permission)
                return true;
        }
        return false;
    }

    public void addToPermissionListByPath(String path, char[] permissions) {
        this.permissionsListByRoute.put(path, permissions);
    }
}
