//201802104068高睿超
package bysj.controller.basic.degree;

import bysj.service.DegreeService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/degree/delete.ctl")
public class DeleteDegreeControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String id_str = request.getParameter("id");
        int id = Integer.parseInt(id_str);
        //删除学位
        DegreeService.getInstance().delete(id);
        System.out.println("delete doGet");
        //将请求重定向查询学位，以刷新学位信息
        request.getRequestDispatcher("/html/basic/degree.html").forward(request, response);
    }
}
