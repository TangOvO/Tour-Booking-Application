package AiLvYou.servlet;

import AiLvYou.dao.UserDaoInterface;
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

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    UserServeImp userServe = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        String phoneNumber = req.getParameter("phoneNumber");
        RequestResult result = new RequestResult();
        Client client = new Client();
        if (name == null || pwd == null || phoneNumber == null) {
            result.setErrorCode(200);
            result.setResult("信息空指针");
            resp.getWriter().print(JSON.toJSONString(result));
            return;
        }
        client.setName(name);
        client.setPwd(pwd);
        client.setPhoneNumber(Long.valueOf(phoneNumber));
        int insertResult = userServe.registerClient(client);
        if (insertResult == 0) {
            result.setErrorCode(200);
            result.setResult("该用户名已被注册");
            resp.getWriter().print(JSON.toJSONString(result));
        } else {
            //注册成功后，查询出来完成登录
            Client client1 = userServe.Login(client.getName(), client.getPwd());
            req.getSession().setAttribute("user", client1);
            result.setErrorCode(100);
            result.setResult("注册成功");
            resp.getWriter().print(JSON.toJSONString(result));
        }

    }
}
