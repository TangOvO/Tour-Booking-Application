package AiLvYou.servlet;

import AiLvYou.dao.UserDaoImp;
import AiLvYou.entity.Routine;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/EditRoutineServlet")
public class EditRoutineServlet extends HttpServlet {
    UserDaoImp dao = new UserDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestResult data = new RequestResult();
        String method = req.getParameter("method");

        if (method.equals("deleteRoutine")) {
            int routineID = Integer.parseInt(req.getParameter("routineID"));
            if (deleteRoutine(routineID)) {
                data.setErrorCode(200);
                data.setResult("路线删除成功！");
                sendBackJson(data, resp);
            } else {
                data.setErrorCode(100);
                data.setResult("路线删除失败！");
                sendBackJson(data, resp);
            }
            return;
        }

        if (method.equals("modifyRoutine")) {
            if (false) { //modifyRoutine()
                data.setErrorCode(200);
                data.setResult("路线更新成功！");
                sendBackJson(data, resp);
            } else {
                data.setErrorCode(100);
                data.setResult("路线更新失败！");
                sendBackJson(data, resp);
            }
            return;
        }

        if (method.equals("insertNewRoutine")) {
            if (false) { //insertNewRoutine()
                data.setErrorCode(200);
                data.setResult("路线新增成功！");
                sendBackJson(data, resp);
            } else {
                data.setErrorCode(100);
                data.setResult("路线新增失败！");
                sendBackJson(data, resp);
            }
            return;
        }
    }

    //删除路线
    private boolean deleteRoutine(int routineID) {
        if (dao.deleteRoutine(routineID) == 1) {
            return true;
        } else {
            return false;
        }
    }

    //修改路线
    private boolean modifyRoutine(Routine routine) {
        if (dao.updateRoutine(routine) == 1) {
            return true;
        }
        return false;
    }

    //新增路线
    private boolean insertNewRoutine(Routine routine) {
        if (dao.newRoutine(routine) == 1) {
            return true;
        }
        return false;
    }

    //回发json
    private void sendBackJson(RequestResult data, HttpServletResponse resp) throws IOException {
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
