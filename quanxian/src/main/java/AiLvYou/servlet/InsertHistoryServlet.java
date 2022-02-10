package AiLvYou.servlet;

import AiLvYou.entity.Client;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/InsertHistoryServlet")
public class InsertHistoryServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestResult data = new RequestResult();
        data.setErrorCode(200);
        if (req.getSession().getAttribute("user") == null) {
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        Client client = (Client) req.getSession().getAttribute("user");
        //System.out.println("InsertHistoryServlet routineID:" + req.getParameter("routineID")); //测试
        int routineID = Integer.parseInt(req.getParameter("routineID"));
        if (serve.insertHistory(routineID, (int) client.getId()) == 1) {
            data.setErrorCode(100);
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
        } else {
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }
}
