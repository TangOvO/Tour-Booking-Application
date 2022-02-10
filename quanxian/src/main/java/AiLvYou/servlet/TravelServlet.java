package AiLvYou.servlet;

import AiLvYou.entity.Client;
import AiLvYou.entity.Order;
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

@WebServlet(urlPatterns = "/TravelServlet")
public class TravelServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("user");
        RequestResult data = new RequestResult();
        //int routineID = Integer.parseInt(req.getParameter("id"));
        long orderID = Long.parseLong(req.getParameter("orderID"));
       // System.out.println("TravelServlet orderID:" + orderID); //测试

        if (serve.orderTravelStatusUpdate(orderID) == 1) {
            data.setErrorCode(200);
            data.setResult("完成出行");
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        data.setErrorCode(100);
        data.setResult("操作失败！");
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
