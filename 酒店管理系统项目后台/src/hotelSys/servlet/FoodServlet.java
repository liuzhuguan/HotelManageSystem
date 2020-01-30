package hotelSys.servlet;

import hotelSys.bean.Food;
import hotelSys.bean.FoodType;
import hotelSys.service.FoodService;
import hotelSys.service.FoodServiceImp;
import hotelSys.service.FoodTypeService;
import hotelSys.service.FoodTypeServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/sys/food.action")
@MultipartConfig            //可上传
public class FoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String foodTypeId = request.getParameter("foodTypeId");
        String keyword = request.getParameter("keyword");
        String method = request.getParameter("method");
        String id = request.getParameter("id");
        String disabled = request.getParameter("disabled");
        String foodName = request.getParameter("foodName");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String remark = request.getParameter("remark");

        FoodService foodService = new FoodServiceImp();

        if (method != null  &&  !method.equals("")  &&  method.equals("list")) {
            List<Food> foods = foodService.find(keyword,foodTypeId);

            FoodTypeService foodTypeService = new FoodTypeServiceImp();
            List<FoodType> foodTypes =  foodTypeService.find(null,null);

            request.setAttribute("foodTypeId",foodTypeId);
            request.setAttribute("keyword",keyword);
            request.setAttribute("foodTypes",foodTypes);
            request.setAttribute("foods",foods);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/foodList.jsp").forward(request,response);
        } else if (method != null  &&  !method.equals("")  &&  method.equals("update")) {
            Food food = foodService.findById(Integer.parseInt(id));
            food.setDisabled(Integer.parseInt(disabled));

            foodService.update(food);
            response.sendRedirect(getServletContext().getContextPath()+"/sys/food.action?method=list");
        } else if (method != null  &&  !method.equals("")  &&  method.equals("addPage")) {
            FoodTypeService foodTypeService = new FoodTypeServiceImp();
            List<FoodType> foodTypes =  foodTypeService.find(null,null);

            request.setAttribute("foodTypes",foodTypes);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/foodAdd.jsp").forward(request,response);
        }else if (method != null  &&  !method.equals("")  &&  method.equals("ajaxFoodName")) {
            Food food = foodService.findByFoodName(foodName);
            String message = null;
            response.setCharacterEncoding("utf-8");

            if (food != null) {
//                已存在

                message = "fail";
                response.getWriter().print(message);
            }
        }else if (method != null  &&  !method.equals("")  &&  method.equals("addSubmit")) {
            Part part = request.getPart("img");//获取上传的图片
//            上传图片到项目目录，只保存上传后的地址到数据库
            String filePath = getServletContext().getRealPath("upload/food");
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            String fileName = part.getSubmittedFileName();
            String extName = fileName.substring(fileName.lastIndexOf("."));
            String name = UUID.randomUUID().toString();

            StringBuffer newName = new StringBuffer();
            newName.append(name).append(extName);
            part.write(filePath + File.separator + newName.toString());

//            保存到数据库
            Double dis = discount != null && !discount.equals("") ? Double.parseDouble(discount) : 1;

            Food food = new Food(foodName,Integer.parseInt(foodTypeId),Double.parseDouble(price),dis,remark,newName.toString(),0);
            foodService.save(food);

            response.sendRedirect(getServletContext().getContextPath()+"/sys/food.action?method=list");
        } else if (method != null  &&  !method.equals("")  &&  method.equals("viewUpdate")) {
            Food food = foodService.findById(Integer.parseInt(id));

            FoodTypeService foodTypeService = new FoodTypeServiceImp();
            List<FoodType> foodTypes =  foodTypeService.find(null,null);

            request.setAttribute("foodTypes",foodTypes);
            request.setAttribute("food",food);
            request.getRequestDispatcher("/WEB-INF/jsp/sys/foodUpdate.jsp").forward(request,response);
        } else if (method != null  &&  !method.equals("")  &&  method.equals("updateSubmit")) {
            Part part = request.getPart("img");//获取上传的图片
//            上传图片到项目目录，只保存上传后的地址到数据库
            String filePath = getServletContext().getRealPath("upload/food");
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            String fileName = part.getSubmittedFileName();
            //            保存到数据库
            Double dis = discount != null && !discount.equals("") ? Double.parseDouble(discount) : 1;

            Food food = foodService.findById(Integer.parseInt(id));
            food.setFoodName(foodName);
            food.setFoodTypeId(Integer.parseInt(foodTypeId));
            food.setDiscount(dis);
            food.setPrice(Double.parseDouble(price));
            food.setRemark(remark);

            foodService.update(food);
            if (fileName != null  &&  !fileName.equals("")) {
                String extName = fileName.substring(fileName.lastIndexOf("."));
                String name = UUID.randomUUID().toString();

                StringBuffer newName = new StringBuffer();
                newName.append(name).append(extName);
                part.write(filePath + File.separator + newName.toString());

                food.setImg(newName.toString());
            }
            foodService.update(food);
            response.sendRedirect(getServletContext().getContextPath()+"/sys/food.action?method=list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
