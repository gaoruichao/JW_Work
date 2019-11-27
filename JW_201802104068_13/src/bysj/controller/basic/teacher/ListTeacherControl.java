package bysj.controller.basic.teacher;

import bysj.domain.Teacher;
import bysj.service.TeacherService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

//映射的url分两部分：实体名称(教师)和动作名称(列表),ctl说明这个url是一个controller(servlet)
@WebServlet("/teacher/list.ctl")
public class ListTeacherControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获得所有教师
        Collection<Teacher> teachers = TeacherService.getInstance().findAll();
        String teacher_str = JSON.toJSONString(teachers, SerializerFeature.DisableCircularReferenceDetect);

        response.getWriter().println(teacher_str);
    }
}
