package AiLvYou.servlet;

import AiLvYou.entity.Client;
import AiLvYou.service.UserServeImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/DeleteHistoryServlet")
public class DeleteHistoryServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("user");

    }
}
