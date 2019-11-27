//201802104068高睿超
package school;
import domain.Degree;
import service.DegreeService;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Collection<Degree> degrees = DegreeService.getInstance().findAll();
        StringBuilder sb = new StringBuilder("{");
        sb.append(Helper.quote("degrees"));
        sb.append(":");
        sb.append("[");
        for (Degree degree:degrees){
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

        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        sb.append("}");
        System.out.println(sb);
        resp.getWriter().println(sb);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}