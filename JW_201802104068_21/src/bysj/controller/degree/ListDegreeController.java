package bysj.controller.degree;
import bysj.domain.Degree;
import bysj.service.DegreeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
@WebServlet("/degree/list.ctl")
public class ListDegreeController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Collection<Degree> degrees = DegreeService.getInstance().findAll();
        String degrees_str = JSON.toJSONString(degrees, SerializerFeature.DisableCircularReferenceDetect);
        response.getWriter().println(degrees_str);
    }
}

