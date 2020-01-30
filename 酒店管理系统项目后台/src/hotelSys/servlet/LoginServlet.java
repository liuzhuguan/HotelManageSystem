package hotelSys.servlet;

import hotelSys.bean.User;
import hotelSys.service.UserService;
import hotelSys.service.UserServiceImp;
import hotelSys.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/sys/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        String password = request.getParameter("password");
        String loginname = request.getParameter("loginname");
        String remenber = request.getParameter("remenber");

        UserService userService = new UserServiceImp();
        if (method != null  &&  !method.equals("")  &&  method.equals("submitTable")) {
//            查询匹配否
            if (loginname != null  &&  !loginname.equals("")  &&  password != null  &&  !password.equals("")) {
                User user = userService.findByLoginNameAndPass(loginname,password);

                if (user != null ) {
//                    登陆成功
//                    记住一月
                    if (remenber != null  &&  remenber.equals("reme")) {

                        CookieUtils.addCookie(URLEncoder.encode(loginname,"utf-8"),URLEncoder.encode(password,"utf-8"),30*24*3600*1000,"loginNameAndPass",response,request);
                    }

                    request.getSession().setAttribute("session_user",user);
                    response.sendRedirect(getServletContext().getContextPath()+"/sys/index.action");
                } else {
                    request.setAttribute("message","用户名或密码错误");
                    request.getRequestDispatcher("/WEB-INF/jsp/sys/login.jsp").forward(request, response);
                }
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/sys/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
