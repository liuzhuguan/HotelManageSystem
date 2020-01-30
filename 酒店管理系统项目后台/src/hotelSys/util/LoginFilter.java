package hotelSys.util;

import hotelSys.bean.User;
import hotelSys.service.UserService;
import hotelSys.service.UserServiceImp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter("*.action")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        User user = (User)httpServletRequest.getSession().getAttribute("session_user");

        if (user == null) {
            Cookie cookie = CookieUtils.getCookieByName(httpServletRequest,"loginNameAndPass");

            if (cookie != null) {
//                有效性判断
                String value = URLDecoder.decode(cookie.getValue(),"utf-8");
                String[] loginNameAndPass = value.split("#");

                UserService userService = new UserServiceImp();
                User user1 = userService.findByLoginNameAndPass(loginNameAndPass[0],loginNameAndPass[1]);

                if (user1 == null) {
//                    无效
                    httpServletRequest.getRequestDispatcher("/sys/login.do").forward(httpServletRequest, resp);
                } else {
                    httpServletRequest.getSession().setAttribute("session_user",user1);
                }

            } else {
                httpServletRequest.getRequestDispatcher("/sys/login.do").forward(httpServletRequest, resp);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
