package AiLvYou.servlet;

import AiLvYou.dao.UserDaoImp;
import AiLvYou.entity.Guest;
import AiLvYou.entity.Order;
import AiLvYou.entity.Routine;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/OrderCopeServlet")
public class OrderCopeServlet extends HttpServlet {
    UserDaoImp dao = new UserDaoImp();
    RequestResult data = new RequestResult();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method.equals("orders")) {
            int routineID = Integer.parseInt(req.getParameter("routineID"));
            List<Order> orders = dao.searchOrderByRoutine(routineID);
            data.setErrorCode(200);
            data.setResult(orders);
            sendSuccess(resp, data);
        } else if (method.equals("deleteOrder")) {
            long orderID = Long.parseLong(req.getParameter("id"));
            System.out.println("orderID:" + orderID);//测试
            if (dao.deleteOrder(orderID) == 1) {
                if (dao.deleteGuest(orderID) != 0) {
                    data.setErrorCode(300); //操作成功，data不含result数据
                    sendSuccess(resp, data);
                }
            }
        } else if (method.equals("guest")) {
            long orderID = Long.parseLong(req.getParameter("id"));
            List<Guest> guests = dao.searchGuest(orderID);
            data.setErrorCode(200);
            data.setResult(guests);
            sendSuccess(resp, data);
        } else if (method.equals("updateOneGuest")) {
            String guestJSON = req.getParameter("guestJSON");
            Guest guest = JSON.parseObject(guestJSON, Guest.class);
            if (dao.updateGuest(guest) == 1) {
                data.setErrorCode(400);
                sendSuccess(resp, data);
            }
        } else if (method.equals("deleteGuest")) {
            long identity = Integer.parseInt(req.getParameter("identity"));
            long orderID = Long.parseLong(req.getParameter("orderID"));
            if (dao.deleteGuestByIdentity(identity) == 1) {
                //修改订单
                Order order = dao.searchOrder(orderID);
                int amount = order.getAmount() - 1;
                order.setAmount(amount);
                dao.updateOrder(order);
                //修改路线人数
                Routine routine = dao.searchRoutine((int) order.getRoutineID());
                amount = routine.getActualClients() - 1;
                routine.setActualClients(amount);
                dao.updateRoutine(routine);
                data.setResult(400);
                sendSuccess(resp, data);
            }
        } else if (method.equals("insertGuest")) {
            String guestJSON = req.getParameter("guestJSON");
            Guest guest = JSON.parseObject(guestJSON, Guest.class);
            long orderID = guest.getOrderID();
            if (dao.insertGuest(guest) == 1) {
                //修改订单
                Order order = dao.searchOrder(orderID);
                int amount = order.getAmount() + 1;
                order.setAmount(amount);
                dao.updateOrder(order);
                //修改路线人数
                Routine routine = dao.searchRoutine((int) order.getRoutineID());
                amount = routine.getActualClients() + 1;
                routine.setActualClients(amount);
                dao.updateRoutine(routine);
                data.setResult(400);
                sendSuccess(resp, data);
            }
        }
    }

    private void sendSuccess(HttpServletResponse resp, RequestResult data) throws IOException {
        //日期使用之前全局化的格式
        resp.getWriter().print(JSON.toJSONString(data, SerializerFeature.WriteDateUseDateFormat));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
