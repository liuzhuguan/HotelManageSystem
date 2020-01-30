package hotelSys.servlet;

import hotelSys.bean.Order;
import hotelSys.service.OrderService;
import hotelSys.service.OrderServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sys/order.action")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");
        String disabled = request.getParameter("disabled");


        OrderService orderService = new OrderServiceImp();
        if (method != null  &&  !method.equals("")  &&  method.equals("list")) {
            List<Order> orders = orderService.find();


            request.setAttribute("orders",orders);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/orderList.jsp").forward(request,response);
        } else if (method != null  &&  !method.equals("")  &&  method.equals("update")) {
            Order order = orderService.findById(Integer.parseInt(id));
            order.setDisabled(Integer.parseInt(disabled));

            orderService.update(order);
            request.getRequestDispatcher("/sys/order.action?method=list").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
