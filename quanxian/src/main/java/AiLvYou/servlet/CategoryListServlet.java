package AiLvYou.servlet;

import AiLvYou.dao.UserDaoImp;
import AiLvYou.entity.Category;
import AiLvYou.entity.CategoryRoutine;
import AiLvYou.entity.Routine;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/CategoryListServlet")
public class CategoryListServlet extends HttpServlet {
    UserDaoImp dao = new UserDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //管理页面方法
        if (req.getParameter("method").equals("CategoryList")) {
            categoryList(req, resp);
            return;
        }

        if (req.getParameter("method").equals("updateOne")) {
            String categoryJSON = req.getParameter("CategoryJSON");
            Category category = JSON.parseObject(categoryJSON, Category.class);
            System.out.println("servlet category update:" + category.toString()); //测试
            updateCategory(category, resp);
            return;
        }

        if (req.getParameter("method").equals("searchRoutine")) {
            int categoryID = Integer.parseInt(req.getParameter("categoryID"));
            searchRoutine(categoryID, resp);
            return;
        }

        if (req.getParameter("method").equals("removeCategoeyRoutine")) {
            String dataJson = req.getParameter("dataJson");
            CategoryRoutine categoryRoutine = JSON.parseObject(dataJson, CategoryRoutine.class);
            if (dao.deleteCategoryRoutine(categoryRoutine) == 1) {
                RequestResult data = new RequestResult();
                data.setErrorCode(200);
                resp.getWriter().print(JSON.toJSONString(data));
                resp.getWriter().flush();
                resp.getWriter().close();
            }
            return;
        }

        if (req.getParameter("method").equals("addCategoeyRoutine")) {
            String dataJson = req.getParameter("dataJson");
            CategoryRoutine categoryRoutine = JSON.parseObject(dataJson, CategoryRoutine.class);
            if (dao.insertCategoryRoutine(categoryRoutine) == 1) {
                RequestResult data = new RequestResult();
                data.setErrorCode(200);
                resp.getWriter().print(JSON.toJSONString(data));
                resp.getWriter().flush();
                resp.getWriter().close();
            }
            return;
        }

        if (req.getParameter("method").equals("deleteCategory")) {
            int categoryID = Integer.parseInt(req.getParameter("categoryID"));
            deleteCategory(categoryID, resp);
            return;
        }

        if (req.getParameter("method").equals("insertBlankCategory")) {
            Category category = new Category();
            category.setName("请修改");
            category.setType("请修改");
            if (dao.insertCateGory(category) == 1) {
                RequestResult data = new RequestResult();
                data.setErrorCode(200);
                resp.getWriter().print(JSON.toJSONString(data));
                resp.getWriter().flush();
                resp.getWriter().close();
            }
            return;
        }

        List<Category> domesticList = new ArrayList<>();
        List<Category> foreignList = new ArrayList<>();
        List<Category> allList = new ArrayList<>();

        domesticList = dao.searchCategory("国内游");
        allList = dao.searchCategory();
        foreignList = dao.searchCategory("国外游");

        Object[] os = {allList, domesticList, foreignList};
        RequestResult data = new RequestResult();
        data.setResult(os);

        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private void categoryList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int currPage = Integer.parseInt(req.getParameter("currPage"));
        int totalPage = Integer.parseInt(req.getParameter("totalPage"));
        int numPerPage = Integer.parseInt(req.getParameter("numPerPage"));
        int totalResults = Integer.parseInt(req.getParameter("totalResults"));
        RequestResult data = new RequestResult();

        if (totalPage == -1) { //totalPage=-1说明是第一次查询
            totalResults = dao.numOfRoutines();
            totalPage = (int) Math.ceil((double) totalResults/numPerPage);
        }

        List<Category> categoryList = dao.allCategoryPage(currPage, numPerPage);
        if (categoryList != null) {
            Object[] os = {totalPage, categoryList, totalResults};
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

    private void updateCategory(Category category, HttpServletResponse resp) throws IOException {
        RequestResult data = new RequestResult();
        if (dao.updateCategory(category) == 1) {
            data.setErrorCode(200);
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }

    private void deleteCategory(int categoryID, HttpServletResponse resp) throws IOException {
        if (dao.deleteCategory(categoryID) == 1) {
            RequestResult data = new RequestResult();
            data.setErrorCode(200);
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }

    private void searchRoutine(int categoryID, HttpServletResponse resp) throws IOException {
        List<Routine> routines = dao.srarchRoutineByCategory(categoryID);
        RequestResult data = new RequestResult();
        data.setResult(routines);
        data.setErrorCode(200);
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}

