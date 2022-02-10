package AiLvYou.servlet;

import AiLvYou.dao.UserDaoImp;
import AiLvYou.entity.Detail;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

@WebServlet(urlPatterns = "/DetailCopeServlet")
public class DetailCopeServlet extends HttpServlet {
    UserDaoImp dao = new UserDaoImp();
    RequestResult data = new RequestResult();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String detailsJSON = req.getParameter("detailsJSON");
        System.out.println("detail cope servlet method:" + method); //测试

        if (method.equals("updateAllDetail")) {
            List<Detail> details = JSONObject.parseArray(detailsJSON, Detail.class);
            if (dao.updateAllDetail(details) == details.size()) {
                success(resp, data);
            }
        } else if (method.equals("updateOneImg")) { //传入一个detail的json，更新一个detail
            Detail detail = JSON.parseObject(detailsJSON, Detail.class);
            System.out.println("detail cope servlet method Detail:" + detail.toString());//测试

            if (dao.updateDetail(detail) == 1) {
                success(resp, data);
            }
        } else if (method.equals("insertOneDetail")) {
            Detail detail = JSON.parseObject(detailsJSON, Detail.class);
            System.out.println("detail cope servlet method Detail:" + detail.toString());//测试

            if (dao.insertDetail(detail) == 1) {
                success(resp, data);
            }
        }
    }

    private void success(HttpServletResponse resp, RequestResult data) throws IOException {
        data.setErrorCode(200);
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
