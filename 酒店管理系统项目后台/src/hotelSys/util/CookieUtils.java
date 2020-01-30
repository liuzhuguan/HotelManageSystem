package hotelSys.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static void addCookie(String loginname, String password, int i, String loginNameAndPass, HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = getCookieByName(request,loginNameAndPass);
        StringBuffer value = new StringBuffer();
        value.append(loginname);
        value.append("#");
        value.append(password);

        if (cookie != null) {
            cookie.setValue(value.toString());
        } else {
            cookie = new Cookie(loginNameAndPass,value.toString());
        }

        cookie.setMaxAge(i);
        cookie.setPath(request.getContextPath());

        response.addCookie(cookie);
    }

    public static Cookie getCookieByName(HttpServletRequest request, String loginNameAndPass) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null  &&  cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(loginNameAndPass)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    public static void remove(String loginNameAndPass, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtils.getCookieByName(request,loginNameAndPass);

        if (cookie != null ) {
            cookie.setMaxAge(0);
            cookie.setPath(request.getContextPath());

            response.addCookie(cookie);
        }
    }
}
