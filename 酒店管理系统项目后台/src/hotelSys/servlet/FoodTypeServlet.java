package hotelSys.servlet;

import hotelSys.bean.FoodType;
import hotelSys.service.FoodTypeService;
import hotelSys.service.FoodTypeServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sys/foodType.action")
public class FoodTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String disabled = request.getParameter("disabled");
        String method = request.getParameter("method");
        String foodTypeName = request.getParameter("foodTypeName");
        String id = request.getParameter("id");         //菜品种类

        FoodTypeService foodTypeService = new FoodTypeServiceImp();
        List<FoodType> foodTypes =  foodTypeService.find(keyword,disabled);

        if (method != null  &&  !method.equals("")  &&  method.equals("list") ) {
            request.setAttribute("disabled",disabled);
            request.setAttribute("keyword",keyword);
            request.setAttribute("foodTypes",foodTypes);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/foodTypeList.jsp").forward(request,response);
        } else if (method != null  &&  !method.equals("")  &&  method.equals("addPage") ) {
            request.getRequestDispatcher("/WEB-INF/jsp/sys/foodTypeAdd.jsp").forward(request,response);
        }else if (method != null  &&  !method.equals("")  &&  method.equals("addSubmit")) {
            FoodType foodType = foodTypeService.findByFoodName(foodTypeName);
            String message = null;

            if (foodType == null) {
                FoodType foodType1 = new FoodType();
                foodType1.setTypeName(foodTypeName);

                foodTypeService.save(foodType1);
                message = "success";
            } else {
                message = "fail";
            }
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(message);
        } else if (method != null  &&  !method.equals("")  &&  method.equals("viewUpdate")) {
            FoodType foodType = foodTypeService.findById(Integer.parseInt(id));

            request.setAttribute("foodType",foodType);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/foodTypeUpdate.jsp").forward(request,response);
        } else if (method != null  &&  !method.equals("")  &&  method.equals("updateSubmit")) {
            FoodType foodType = foodTypeService.findByFoodName(foodTypeName);
            String message = null;

            if (foodType == null) {
                FoodType foodType1 = foodTypeService.findById(Integer.parseInt(id));
                foodType1.setTypeName(foodTypeName);

                foodTypeService.update(foodType1);
                message = "success";
            } else {
                message = "fail";
            }
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(message);
        } else if (method != null  &&  !method.equals("")  &&  method.equals("update")) {
            FoodType foodType2 = foodTypeService.findById(Integer.parseInt(id));
            foodType2.setDisabled(Integer.parseInt(disabled));
            foodTypeService.update(foodType2);

            response.sendRedirect(getServletContext().getContextPath()+"/sys/foodType.action?method=list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
