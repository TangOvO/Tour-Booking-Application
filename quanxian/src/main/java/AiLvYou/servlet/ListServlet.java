package AiLvYou.servlet;

import AiLvYou.dao.UserDaoImp;
import AiLvYou.entity.Page;
import AiLvYou.entity.Routine;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/ListServlet")
public class ListServlet extends HttpServlet {
    UserDaoImp dao = new UserDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currPage = Integer.parseInt(req.getParameter("currPage"));
        int totalPage = Integer.parseInt(req.getParameter("totalPage"));
        int numPerPage = Integer.parseInt(req.getParameter("numPerPage"));
        int totalResults = Integer.parseInt(req.getParameter("totalResults"));
        RequestResult data = new RequestResult();

        if (totalPage == -1) { //totalPage=-1说明是第一次查询
            totalResults = dao.numOfRoutines();
            totalPage = (int) Math.ceil((double) totalResults/numPerPage);
        }

        List<Routine> routines = dao.allRoutinesPage(currPage, numPerPage);
        if (routines != null) {
            Object[] os = {totalPage, routines, totalResults};
            data.setErrorCode(200);
            data.setResult(os);
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
        } else {
            data.setErrorCode(100);
            data.setResult("routines == null");
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }

    public void test() {

    }

    public static void main(String[] args) {
        ListServlet listServlet = new ListServlet();
        listServlet.test();
    }
}
