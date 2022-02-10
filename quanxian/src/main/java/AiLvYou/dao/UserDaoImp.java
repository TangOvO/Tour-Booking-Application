package AiLvYou.dao;

import AiLvYou.entity.*;


import javax.servlet.annotation.WebInitParam;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImp extends BaseDao implements UserDaoInterface {
    //Connection conn = Utils.getConnection(); //jndi
    Connection conn = Utils.getMysqlCon(); //jdbc

    @Override
    public List<Map<String, Object>> queryA114() {
        return null;
    }

    @Override
    public List<Client> LoginSearch(Client client) {
        //Connection conn = Utils.getConnection();
        List<Client> clients = search(conn, Client.class, "client", "select * from client", null);
        return clients;
    }

    @Override
    public Client searchClientByName(String name) {
        List<Client> target = new ArrayList<>();
        List values = new ArrayList();
        values.add(name);
        target = search(conn, Client.class, "client", "select * from client where name=?", values);
        if (target.size() == 0) {
            return null;
        } else {
            return target.get(0);
        }
    }

    @Override
    public Client searchClientByID(int clientID) {
        List values = new ArrayList();
        values.add(clientID);
        List<Client> list = search(conn, Client.class, "client", "select * from client where id=?", values);
        return list.get(0);
    }


    @Override
    public int insertClient(Client client) {
        return insert(conn, "client", client);
    }

    @Override
    public int deleteClient(Client client) {
        List values = new ArrayList();
        values.add(client.getId());
        return delete(conn, "client", "id=?", values);
    }

    @Override
    public int updateClient(Client client) {
        return update(conn, "client", client);
    }

    @Override
    public int updateGuest(Guest guest) {
        System.out.println(guest.toString());//测试
        Object[] os = {guest.getName(), guest.getIdentity(), guest.getPhoneNumber(), guest.getStatus(), guest.getIdentity()};
        return update(conn, "update guest set name = ?, identity = ?, phoneNumber = ?, status = ? where identity = ?", os);
    }

    @Override
    public int insertGuest(Guest guest) {
        return insert(conn, "Guest", guest);
    }

    @Override
    public int deleteGuest(Guest guest) {
        List values = new ArrayList();
        values.add(guest.getOrderID());
        values.add(guest.getIdentity());
        return delete(conn, "guest", "orderID = ? and identity = ?", values);
    }

    @Override
    public int deleteGuestByIdentity(long identity) {
        List values = new ArrayList();
        values.add(identity);
        return delete(conn, "guest", "identity = ?", values);
    }

    @Override
    public int deleteGuest(long orderID) {
        List value = new ArrayList();
        value.add(orderID);
        return delete(conn, "guest", "orderID = ?", value);
    }

    @Override
    public Guest searchGuest(int orderID, int identity) {
        List values = new ArrayList();
        values.add(orderID);
        values.add(identity);
        List<Guest> guests = search(conn, Guest.class, "guest", "select * from guest where orderID = ? and identity = ?", values);
        return guests.get(0);
    }

    @Override
    public List<Guest> searchGuest(long orderID) {
        List values = new ArrayList();
        values.add(orderID);
        return search(conn, Guest.class, "guest", "select * from guest where orderID = ?", values);
    }

    @Override
    public List<Order> searchOrders() {
        return search(conn, Order.class, "order", "select * from order", null);
    }

    @Override
    public List<Order> searchOrders(int clientID) {
        List list = new ArrayList();
        list.add(clientID);
        return search(conn, Order.class, "orders", "select * from orders where clientID=?", list);
    }

    @Override
    public Order searchOrders(int clientID, int routineID) {
        List values = new ArrayList();
        values.add(clientID);
        values.add(routineID);
        List<Order> orders = search(conn, Order.class, "orders", "select * from orders where clientID = ? and routineID = ?", values);
        return orders.get(0);
    }

    @Override
    public Order searchOrder(long orderID) {
        List values = new ArrayList();
        values.add(orderID);
        List<Order> orders = search(conn, Order.class, "orders", "select * from orders where orderID = ?", values);
        return orders.get(0);
    }

    @Override
    public List<Order> searchOrdersUncomment(int clientID) {
        List value = new ArrayList();
        value.add(clientID);
        return search(conn, Order.class, "orders", "SELECT * FROM ORDERs WHERE clientID = ? AND isCommented = 0", value);
    }

    @Override
    public int deleteOrder(long orderID) {
        List list = new ArrayList();
        list.add(orderID);
        return delete(conn, "orders", " orderID=?", list);
    }

    @Override
    public int updateOrder(Order order, String fieldName) {
        Object[] os = {order.getOrderID()};
        String sql = "UPDATE orders SET " + fieldName + " = 1 WHERE orderID = ?";
        return update(conn, sql, os);
    }

    @Override
    public int insertOrder(Order order) {
        return insert(conn, "orders", order);
    }

    @Override
    public List<Order> searchOrderByRoutine(int routnieID) {
        List values = new ArrayList();
        values.add(routnieID);
        return search(conn, Order.class, "orders", "select * from orders where routineID = ?", values);
    }

    @Override
    public int updateOrder(Order order) {
        Object[] os = {order.getPayment(), order.getIsPaied(), order.getTravelDate(), order.getIsTraveled(), order.getAmount(), order.getOrderID()};
        return update(conn, "update orders set payment = ?, isPaied = ?, travelDate = ?, isTraveled = ?, amount = ? where orderID = ?", os);
    }

    @Override
    public List<History> searchHistory(int clientID) {
        List list = new ArrayList();
        list.add(clientID);
        return search(conn, History.class, "history", "select * from history where clientID = ?", list);
    }

    @Override
    public int insertHistory(History history) {
        return insert(conn, "history", history);
    }

    @Override
    public int updateHistory(History history) {
        Object[] os = new Object[]{history.getTime(), history.getClientID(), history.getRoutineID()};
        return update(conn, "UPDATE history SET TIME = ? WHERE clientID = ? AND routineID=?" , os);
    }

    @Override
    public List<Routine> allRoutines() {
        return search(conn, Routine.class, "routine", "select * from routine", null);
    }

    @Override
    public Routine searchRoutine(int id) {
        List list = new ArrayList();
        list.add(id);
        List list1 = search(conn, Routine.class, "routine", "select * from routine where id=?", list);
        return (Routine) list1.get(0);
    }

    @Override
    public int updateRoutine(Routine routine) {
        return update(conn, "Routine", routine);
    }

    @Override
    public List<Detail> details(int routineID) {
        List value = new ArrayList();
        value.add(routineID);
        return search(conn, Detail.class, "detail", "select * from detail where routineID = ?", value);
    }

    @Override
    public List<RoutineFeatureImg> featureImgs(int routineID) {
        List value = new ArrayList();
        value.add(routineID);
        return search(conn, RoutineFeatureImg.class, "featureimg", "select * from featureimg where routineID = ?", value);
    }

    @Override
    public int UpdateFeatureImgs(List<RoutineFeatureImg> list) {
        int num = 0;
        RoutineFeatureImg featureImg = list.get(0);
        int rouinteID = featureImg.getRoutineID();
        //删除原有的
        deleteFeatureImgs(rouinteID);
        //插入现有的
        for (RoutineFeatureImg featureImg1 : list) {
            //System.out.println("插入的FeatureImg：" + featureImg1.toString()); //测试

            if (insertRoutineFeatureImg(featureImg1) == 1) {
                num++;
            }
        }
        return num;
    }

    @Override
    public int deleteFeatureImgs(int routineID) {
        List values = new ArrayList();
        values.add(routineID);
        return delete(conn, "featureimg", "routineID = ?", values);
    }

    @Override
    public int insertRoutineFeatureImg(RoutineFeatureImg featureImg) {
        return insert(conn, "featureimg", featureImg);
    }

    @Override
    public int numOfRoutines() {
        PreparedStatement statement = null;
        ResultSet rs = null;
        int num = -1;
        try {
            statement = conn.prepareStatement("SELECT COUNT(1) FROM routine");
            rs = statement.executeQuery();
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<Routine> allRoutinesPage(int currPage, int numPerPage) {
        int startLoc;
        List values = new ArrayList();
        startLoc = (currPage - 1) * numPerPage;
        values.add(startLoc);
        values.add(numPerPage);
        return search(conn, Routine.class, "routine", "select * from routine limit ?,?", values);
    }

    @Override
    public int deleteRoutine(int routineID) {
        List value = new ArrayList();
        value.add(routineID);
        return delete(conn, "routine", "id = ?", value);
    }

    @Override
    public int newRoutine(Routine routine) {
        return insert(conn, "routine", routine);
    }

    @Override
    public int insertComment(Comment comment) {
        return insert(conn, "comment", comment);
    }

    @Override
    public List<Comment> searchComments(String fieldName, int id) {
        List list = new ArrayList();
        list.add(id);
        return search(conn, Comment.class, "comment", "select * from comment where " + fieldName + " = ?", list);
    }

    @Override
    public Discount searchDiscount(int routineID) {
        List value = new ArrayList();
        value.add(routineID);
        List<Discount> discounts = search(conn, Discount.class, "discount", "select * from discount where routineID = ?", value);
        if (discounts.size() > 0) {
            return discounts.get(0);
        } else {
            return new Discount();
        }
    }

    @Override
    public int updateDiscount(Discount discount) {
        Object[] os = {discount.getDiscount1(), discount.getDiscount2(), discount.getDiscount3(), discount.getDiscount4(), discount.getRoutineID()};

        return update(conn, "UPDATE discount set discount1 = ?, discount2 = ?, discount3 = ?, discount4 = ? WHERE routineID = ?", os);
    }

    @Override
    public int updateAllDetail(List<Detail> details) {
        //根据routineID全部删完，再更新
        int num = 0;
        deleteAllDetail(details.get(0).getRoutineID());
        for (Detail detail : details) {
            if (insertDetail(detail) == 1) {
                num++;
            }
        }
        return num;
    }

    @Override
    public int deleteAllDetail(int routineID) {
        List values = new ArrayList();
        values.add(routineID);
        return delete(conn, "detail", "routineID = ?", values);
    }

    @Override
    public int deleteDetail(Detail detail) {
        List values = new ArrayList();
        values.add(detail.getRoutineID());
        values.add(detail.getTime());
        return delete(conn, "detail", "routineID = ? and time = ?", values);
    }

    @Override
    public int updateDetail(Detail detail) {
        Object[] os = {detail.getS1(), detail.getS2(), detail.getS3(), detail.getImg1(), detail.getImg2(), detail.getImg3(), detail.getRoutineID(), detail.getTime()};
        return update(conn, "UPDATE detail SET s1 = ?, s2 = ?, s3 = ?, img1 = ?, img2 = ?, img3 = ? WHERE routineID = ? AND TIME = ?", os);
    }

    @Override
    public int insertDetail(Detail detail) {
        return insert(conn, "detail", detail);
    }

    @Override
    public List<Category> allCategoryPage(int currPage, int numPerPage) {
        int startLoc;
        List values = new ArrayList();
        startLoc = (currPage - 1) * numPerPage;
        values.add(startLoc);
        values.add(numPerPage);
        return search(conn, Category.class, "category", "select * from category limit ?,?", values);
    }

    @Override
    public List<Category> searchCategory(String type) {
        List values = new ArrayList();
        values.add(type);
        return search(conn, Category.class, "category", "select * from category where type = ?", values);
        /*
        String sql = "select * from category where type = '" + type + "'";
        return search(conn, Category.class, "category", sql, null);

         */
    }

    @Override
    public List<Category> searchCategory() {
        return search(conn, Category.class, "category", "select * from category", null);
    }

    @Override
    public List<Routine> srarchRoutineByCategory(int categoryID) {
        List value = new ArrayList();
        value.add(categoryID);
        return search(conn, Routine.class, "routine", "SELECT * FROM routine AS a INNER JOIN categoryroutine AS b ON a.id = b.routineID WHERE b.categoryID = ?", value);
    }

    @Override
    public int deleteCategory(int categoryID) {
        List value = new ArrayList();
        value.add(categoryID);
        return delete(conn, "category", "categoryID = ?", value);
    }

    @Override
    public int updateCategory(Category category) {
        return update(conn, "category", category);
    }

    @Override
    public int insertCateGory(Category category) {
        return insert(conn, "category", category);
    }

    @Override
    public int insertCategoryRoutine(CategoryRoutine categoryRoutine) {
        return insert(conn, "categoryroutine", categoryRoutine);
    }

    @Override
    public int deleteCategoryRoutine(CategoryRoutine categoryRoutine) {
        List values = new ArrayList();
        values.add(categoryRoutine.getCategoryID());
        values.add(categoryRoutine.getRoutineID());
        return delete(conn, "categoryroutine", "categoryID = ? and routineID = ?", values);
    }

    public static void main(String[] args) {
        UserDaoImp dao = new UserDaoImp();
        //Client client = dao.searchClientByName("Pig");
        //client.setId(4);
        //client.setId(3);
        //测试删除
        //System.out.println(dao.deleteClient(client));

        //测试插入
        //System.out.println(dao.insertClient(client));

        //更新测试
        //dao.updateClient(client);
        Date date = new Date(System.currentTimeMillis());
        //测试查找
        /*
        Order order = dao.searchOrders(1).get(0);
        System.out.println("测试：" + order.isCommented()); //测试，tinyint可直接转为boolean
        order.setOrderID(1);
        order.setPaied(true);
        order.setDate(date);
        order.setTravelDate("sss");
        dao.updateOrder(order);

         */
        //删除订单
        //dao.deleteOrder(1);

        //order插入测试
        /*
        Order order = new Order();
        order.setDate(new Date(System.currentTimeMillis()));
        order.setTravelDate("周二");
        order.setPaied(false);
        order.setClientID(5);
        order.setRoutineID(5);
        order.setCommented(false);
        order.setTraveled(false);
        dao.insertOrder(order);

         */

        //history查询测试
        /*
        History history = dao.searchHistory(55).get(0);
        System.out.println(history.getClientID());
        System.out.println(history.getTime());
        */
        //history新增测试
        /*
        History history = new History();
        history.setClientID(55);
        history.setRoutineID(55);
        history.setTime(new Date(System.currentTimeMillis()));
        dao.insertHistory(history);

         */

        //comment查询测试
        /*
        List<Comment> comments = dao.searchComments("routineID", 1);
        for (Comment comment : comments) {
            System.out.println(comment.toString());
        }
        */
        //comment插入测试
        /*
        Comment comment = new Comment();
        comment.setDate(date);
        comment.setRoutineID(1);
        comment.setContant("这里测试评价的内容");
        comment.setScore(5);
        dao.insertComment(comment);
        */
        //history插入测试
        /*
        History history = new History();
        history.setTime(date);
        history.setClientID(1);
        history.setRoutineID(1);
        dao.updateHistory(history);

         */
        //detail 查询测试
        /*
        List<Detail> details = dao.details(1);
        for (Detail detail : details) {
            System.out.println(detail.toString());
        }

         */
        //含空值更新测试
        /*
        Routine routine = dao.searchRoutine(2);
        routine.setFeatures("空值更新测试");
        dao.updateRoutine(routine);

         */
        //update Order测试
        /*
        Order order = new Order();
        order.setOrderID(22);
        dao.updateOrder(order, "isCommented");

         */
        //删除订单guest测试
        /*
        System.out.println(dao.deleteGuest(5012548174573568l));

         */

        //总条数测试
        /*
        dao.numOfRoutines();

         */
        //deleteRoutine测试
        //System.out.println(dao.deleteRoutine(6));
        //updateDiscount
        /*
        Discount discount = dao.searchDiscount(2);
        discount.setDiscount1("s1");
        discount.setDiscount2("s1");
        discount.setDiscount3("sssssss");
        System.out.println(dao.updateDiscount(discount));

         */
        /*
        List<Category> categoryList = dao.searchCategory();
        for (Category category : categoryList) {
            System.out.println(category.toString());
        }

         */

        List<Routine> routines = dao.srarchRoutineByCategory(1);
        for (Routine routine : routines) {
            System.out.println(routine.toString());
        }
    }
}
