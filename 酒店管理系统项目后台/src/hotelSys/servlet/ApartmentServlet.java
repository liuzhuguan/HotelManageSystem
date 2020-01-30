package hotelSys.servlet;

import hotelSys.bean.Apartment;
import hotelSys.service.ApartmentService;
import hotelSys.service.ApartmentServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sys/apartment.action")
public class ApartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String keyword = request.getParameter("keyword");
        String apartmentStatus = request.getParameter("apartmentStatus");
        String disabled = request.getParameter("disabled");
        String method = request.getParameter("method");
        String id = request.getParameter("id");         //更新使用
        String apartmentName = request.getParameter("apartmentName");
        System.out.println(method);
        ApartmentService apartmentService = new ApartmentServiceImp();
        if (method != null  &&  !method.equals("")  &&  method.equals("list")) {
            List<Apartment> apartments = apartmentService.find(keyword,apartmentStatus,disabled);

            request.setAttribute("keyword",keyword);
            request.setAttribute("apartmentStatus",apartmentStatus);
            request.setAttribute("disabled",disabled);
            request.setAttribute("apartments",apartments);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/apartmentList.jsp").forward(request,response);
        } else if (method != null  &&  !method.equals("")  &&  method.equals("update")) {
            Apartment apartment = apartmentService.findById(Integer.parseInt(id));
            apartment.setDisabled(Integer.parseInt(disabled));

            apartmentService.update(apartment);         //数据更新
            response.sendRedirect(getServletContext().getContextPath()+"/sys/apartment.action?method=list");
        } else if (method != null  &&  !method.equals("")  &&  method.equals("addPage") ) {
            request.getRequestDispatcher("/WEB-INF/jsp/sys/apartmentAdd.jsp").forward(request,response);
        }else if (method != null  &&  !method.equals("")  &&  method.equals("addSubmit")) {
            Apartment apartment = apartmentService.findByApartmentName(apartmentName);

            String message = null;
            if (apartment == null ) {
//                不存在
                Apartment apartment1 = new Apartment();
                apartment1.setApartment_name(apartmentName);

                apartmentService.save(apartment1);
                message = "success";
            } else {
                message = "fail";
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(message);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
