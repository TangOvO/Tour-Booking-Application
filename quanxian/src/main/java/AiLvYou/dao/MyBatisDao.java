package AiLvYou.dao;

import AiLvYou.entity.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyBatisDao implements UserDaoInterface {

    public static SqlSessionFactory getSqlSessionFactory() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(is);
    }

    @Override
    public List<Map<String, Object>> queryA114() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisDao.getSqlSessionFactory().openSession();
            UserDaoInterface dao = sqlSession.getMapper(UserDaoInterface.class);
            return dao.queryA114();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public List<Client> LoginSearch(Client client) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisDao.getSqlSessionFactory().openSession();
            UserDaoInterface dao = sqlSession.getMapper(UserDaoInterface.class);
            return dao.LoginSearch(client);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public Client searchClientByName(String name) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisDao.getSqlSessionFactory().openSession();
            UserDaoInterface dao = sqlSession.getMapper(UserDaoInterface.class);
            return dao.searchClientByName(name);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public Client searchClientByID(int clientID) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisDao.getSqlSessionFactory().openSession();
            UserDaoInterface dao = sqlSession.getMapper(UserDaoInterface.class);
            return dao.searchClientByID(clientID);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public int insertClient(Client client) {
        return 0;
    }

    @Override
    public int deleteClient(Client client) {
        return 0;
    }

    @Override
    public int updateClient(Client client) {
        return 0;
    }

    @Override
    public int updateGuest(Guest guest) {
        return 0;
    }

    @Override
    public int insertGuest(Guest guest) {
        return 0;
    }

    @Override
    public int deleteGuest(Guest guest) {
        return 0;
    }

    @Override
    public int deleteGuestByIdentity(long identity) {
        return 0;
    }

    @Override
    public int deleteGuest(long orderID) {
        return 0;
    }

    @Override
    public Guest searchGuest(int orderID, int identity) {
        return null;
    }

    @Override
    public List<Guest> searchGuest(long orderID) {
        return null;
    }

    @Override
    public List<Order> searchOrders() {
        return null;
    }

    @Override //需改
    public List<Order> searchOrders(int clientID) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisDao.getSqlSessionFactory().openSession();
            UserDaoInterface dao = sqlSession.getMapper(UserDaoInterface.class);
            return dao.searchOrders(clientID);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public Order searchOrders(int clientID, int routineID) {
        return null;
    }

    @Override
    public Order searchOrder(long orderID) {
        return null;
    }

    @Override
    public List<Order> searchOrdersUncomment(int clientID) {
        return null;
    }

    @Override
    public int updateOrder(Order order, String fieldName) {
        return 0;
    }

    @Override
    public int insertOrder(Order order) {
        return 0;
    }

    @Override
    public List<Order> searchOrderByRoutine(int routnieID) {
        return null;
    }

    @Override
    public int updateOrder(Order order) {
        return 0;
    }

    @Override
    public int deleteOrder(long orderID) {
        return 0;
    }

    @Override
    public List<History> searchHistory(int clientID) {
        return null;
    }

    @Override
    public int insertHistory(History history) {
        return 0;
    }

    @Override
    public int updateHistory(History history) {
        return 0;
    }

    @Override
    public List<Routine> allRoutines() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisDao.getSqlSessionFactory().openSession();
            UserDaoInterface dao = sqlSession.getMapper(UserDaoInterface.class);
            return dao.allRoutines();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public Routine searchRoutine(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisDao.getSqlSessionFactory().openSession();
            UserDaoInterface dao = sqlSession.getMapper(UserDaoInterface.class);
            return dao.searchRoutine(id);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public int updateRoutine(Routine routine) {
        return 0;
    }

    @Override
    public List<Detail> details(int routineID) {
        return null;
    }

    @Override
    public List<RoutineFeatureImg> featureImgs(int routineID) {
        return null;
    }

    @Override
    public int UpdateFeatureImgs(List<RoutineFeatureImg> list) {
        return 0;
    }

    @Override
    public int deleteFeatureImgs(int routineID) {
        return 0;
    }

    @Override
    public int insertRoutineFeatureImg(RoutineFeatureImg featureImg) {
        return 0;
    }

    @Override
    public int numOfRoutines() {
        return 0;
    }

    @Override
    public List<Routine> allRoutinesPage(int currPage, int numPerPage) {
        return null;
    }

    @Override
    public int deleteRoutine(int routineID) {
        return 0;
    }

    @Override
    public int newRoutine(Routine routine) {
        return 0;
    }

    @Override
    public int insertComment(Comment comment) {
        return 0;
    }

    @Override//改
    public List<Comment> searchComments(String fieldName, int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisDao.getSqlSessionFactory().openSession();
            UserDaoInterface dao = sqlSession.getMapper(UserDaoInterface.class);
            //return dao.LoginSearch(client);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return null;
    }

    @Override
    public Discount searchDiscount(int routineID) {
        return null;
    }

    @Override
    public int updateDiscount(Discount discount) {
        return 0;
    }

    @Override
    public int updateAllDetail(List<Detail> details) {
        return 0;
    }

    @Override
    public int deleteAllDetail(int routineID) {
        return 0;
    }

    @Override
    public int deleteDetail(Detail detail) {
        return 0;
    }

    @Override
    public int updateDetail(Detail detail) {
        return 0;
    }

    @Override
    public int insertDetail(Detail detail) {
        return 0;
    }

    @Override
    public List<Category> allCategoryPage(int currPage, int numPerPage) {
        return null;
    }

    @Override
    public List<Category> searchCategory(String type) {
        return null;
    }

    @Override
    public List<Category> searchCategory() {
        return null;
    }

    @Override
    public List<Routine> srarchRoutineByCategory(int categoryID) {
        return null;
    }

    @Override
    public int deleteCategory(int categoryID) {
        return 0;
    }

    @Override
    public int updateCategory(Category category) {
        return 0;
    }

    @Override
    public int insertCateGory(Category category) {
        return 0;
    }

    @Override
    public int insertCategoryRoutine(CategoryRoutine categoryRoutine) {
        return 0;
    }

    @Override
    public int deleteCategoryRoutine(CategoryRoutine categoryRoutine) {
        return 0;
    }

    public static void main(String[] args) {
        MyBatisDao myBatisDao = new MyBatisDao();

        /*
        List<Client> clients = myBatisDao.LoginSearch(null);
        for (Client client : clients) {
            System.out.println(client.toString());
        }

         */
        //Client client = myBatisDao.searchClientByID(10);
        //System.out.println(client.toString());
        /*
        List<Routine> clients = myBatisDao.allRoutines();
        for (Routine client : clients) {
            System.out.println(client.toString());
        }

         */
        //Routine routine = myBatisDao.searchRoutine(1);
        //System.out.println(routine.toString());

        List<Map<String, Object>> maps = myBatisDao.queryA114();
        int n = 1;
        for (Map<String, Object> map : maps) {
            System.out.println(n + "." + map.get("id") + map.get("title")+ map.get("s2")+ map.get("s3"));
            n++;
        }
    }
}
