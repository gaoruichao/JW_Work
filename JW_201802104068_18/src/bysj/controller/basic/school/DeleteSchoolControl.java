package bysj.controller.basic.school;

import bysj.service.SchoolService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/school/deleteA.ctl")
public class DeleteSchoolControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //读取参数id
        String id_str = request.getParameter("id");
        int id = Integer.parseInt(id_str);
        //删除学院
        SchoolService.getInstance().delete(id);
        System.out.println("delete doGet");
        //将请求重定向查询学院，以刷新学院信息
//        response.sendRedirect("/html/basic/school_json.html");
        //创建JSON对象
        JSONObject resp = new JSONObject();
        //加入数据信息
        resp.put("MSG", "OK");
        //响应
        response.getWriter().println(resp);
    }
}
