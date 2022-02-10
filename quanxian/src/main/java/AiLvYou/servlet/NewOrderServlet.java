package AiLvYou.servlet;

import AiLvYou.dao.GetOrderID;
import AiLvYou.dao.UserDaoImp;
import AiLvYou.entity.Client;
import AiLvYou.entity.Guest;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/NewOrderServlet")
public class NewOrderServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestResult data = new RequestResult();
        //验证是否登录

        if (req.getSession().getAttribute("user") == null) {
            data.setErrorCode(100);
            data.setResult("未登录！");
            resp.getWriter().print(data);
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        Client client = (Client) req.getSession().getAttribute("user");

        /*
        UserDaoImp dao = new UserDaoImp(); //测试
        Client client = dao.searchClientByID(1); //测试
        */

        String guestJson = req.getParameter("guestsJSON"); //json放入数组，再将该数组转为json发送的
        Double payment = Double.valueOf(req.getParameter("payment"));
        int routineID = Integer.parseInt(req.getParameter("routineID"));
        String travelDate = req.getParameter("date");
        long orderID = GetOrderID.getID();

        //System.out.println("NewOederServlet routineID：" + routineID); //测试

        List<Guest> guests = new ArrayList<>();
        //guest
        List<String> list = JSON.parseArray(guestJson, String.class); //将Json还原为String数组
        for (String s : list) {
            Guest guest = JSON.parseObject(s, Guest.class);
            guests.add(guest);
            guest.setOrderID(orderID);

            System.out.println("NewOederServlet guest：" + guest.toString()); //测试
        }

        if (serve.orderRoutine(client, travelDate, routineID, orderID, guests, payment) == 1) {
            data.setErrorCode(200);
            Object[] os = new Object[]{orderID, "预定成功！"};
            data.setResult(os);
            /**
             * 如果前端Ajax规定了dataType = “json”，这里如果不发送json格式数据会不能调用success回调函数，而是调用error回调函数
             */
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }
}
