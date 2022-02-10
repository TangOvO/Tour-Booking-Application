package AiLvYou.servlet;

import AiLvYou.entity.Client;
import AiLvYou.entity.Order;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/PayServlet")
public class PayServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Client client = (Client) req.getSession().getAttribute("user");

        System.out.println("PayServlet orderID:" + req.getParameter("orderID"));

        long orderID = Long.parseLong(req.getParameter("orderID"));
        Order order = serve.searchOrder(orderID);
        System.out.println("price:" + order.getPayment() + "   orderId:" + orderID); //测试
        RequestResult data = new RequestResult();

        if (serve.orderPayStatusUpdate(orderID) == 1) {
            data.setErrorCode(200);
            data.setResult("支付成功！");
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
        } else {
            System.out.println("PayServlet serve.orderPayStatusUpdate(orderID) err");
        }
        /*
        if (serve.orderPayStatusUpdate((int) client.getId(), routineID) == 1) {
            data.setErrorCode(100);
            data.setResult("支付成功！");
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        data.setErrorCode(200);
        data.setResult("支付失败！");
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();

         */
    }
}
