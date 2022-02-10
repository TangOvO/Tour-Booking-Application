package AiLvYou.servlet;

import AiLvYou.entity.Client;
import AiLvYou.entity.History;
import AiLvYou.entity.Routine;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    UserServeImp dao = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestResult data = new RequestResult();
        if (req.getSession().getAttribute("user") == null) {
            data.setErrorCode(200);
            data.setResult("请先登录");
            //System.out.println(JSON.toJSONString(data));  //测试
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        Client client = (Client) req.getSession().getAttribute("user");
        List<History> histories = dao.searchHistory((int) client.getId());
        List os = new ArrayList<>();

        for (History history : histories) {

            Routine routine = dao.searchRoutineByID((int) history.getRoutineID());
            Object[] o = new Object[]{routine, history.getTime()};
            os.add(o);
        }
        //全局修改日期格式
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        data.setResult(os);
        data.setErrorCode(100);
        System.out.println("HistoryServlet-history：" + JSON.toJSONString(data, SerializerFeature.WriteDateUseDateFormat)); //测试
        //日期使用之前全局化的格式
        resp.getWriter().print(JSON.toJSONString(data, SerializerFeature.WriteDateUseDateFormat));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
