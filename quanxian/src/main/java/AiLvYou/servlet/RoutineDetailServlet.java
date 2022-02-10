package AiLvYou.servlet;

import AiLvYou.entity.Detail;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/RoutineDetailServlet")
public class RoutineDetailServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int routineID = Integer.parseInt(req.getParameter("routineID"));
        List<Detail> details = serve.details(routineID);
        RequestResult data = new RequestResult();
        data.setErrorCode(200);
        data.setResult(details);
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
