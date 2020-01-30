package hotelSys.servlet;

import com.sun.org.apache.regexp.internal.RE;
import hotelSys.bean.User;
import hotelSys.service.UserService;
import hotelSys.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sys/register.do")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        String loginname = request.getParameter("loginname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String passWord = request.getParameter("passWord");


        UserService userService = new UserServiceImp();
        if (method != null  &&  !method.equals("")  &&  method.equals("ajaxLoginName")) {
            User user = userService.findByLoginNameAndPass(loginname, null);

            if (user != null)  {
                response.setCharacterEncoding("utf-8");
                PrintWriter printWriter = response.getWriter();


                printWriter.write("有了，重输");
                printWriter.flush();
                printWriter.close();
            }
        } else if(method != null && !method.equals("") && method.equals("submitTable")) {
            User user = new User();
            user.setEmail(email);
            user.setLoginName(loginname);
            user.setPassword(passWord);
            user.setPhone(phone);

            userService.save(user);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/sys/register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
