//201802104044杨轲
package bysj.controller.department;

import bysj.domain.Department;
import bysj.service.DepartmentService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/department/list.ctl")
public class ListDepartmentController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Collection<Department> departments = DepartmentService.getInstance().findAll();
        String departments_str = JSON.toJSONString(departments, SerializerFeature.DisableCircularReferenceDetect);
        response.getWriter().println(departments_str);
    }
}
