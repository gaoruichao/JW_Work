//201802104068高睿超
package bysj.controller.basic.degree;

import bysj.domain.Degree;
import bysj.service.DegreeService;
import util.Helper;

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
        StringBuilder sb = new StringBuilder("{");
        sb.append(Helper.quote("degrees"));
        sb.append(":");
        sb.append("[");
        for (Degree degree : degrees) {
            sb.append("{")
                    .append(Helper.quote("id")).append(":").append(degree.getId())
                    .append(",")
                    .append(Helper.quote("no")).append(":").append(Helper.quote(degree.getNo()))
                    .append(",")
                    .append(Helper.quote("description")).append(":").append(Helper.quote(degree.getDescription()))
                    .append(",")
                    .append(Helper.quote("remarks")).append(":").append(Helper.quote(degree.getRemarks()))
                    .append("},");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        sb.append("}");
        System.out.println(sb);
        response.getWriter().println(sb);
    }
}
