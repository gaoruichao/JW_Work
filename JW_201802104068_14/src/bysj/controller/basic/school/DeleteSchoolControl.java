package bysj.controller.basic.school;

import bysj.service.SchoolService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/school/delete.ctl")
public class DeleteSchoolControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String id_str = request.getParameter("id");
        int id = Integer.parseInt(id_str);
        //删除学院
        SchoolService.getInstance().delete(id);
        System.out.println("delete doGet");
        //将请求重定向查询学院，以刷新学院信息
        response.sendRedirect("/html/basic/school.html");
    }
}
