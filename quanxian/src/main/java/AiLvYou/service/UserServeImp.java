package AiLvYou.service;

import AiLvYou.dao.MyBatisDao;
import AiLvYou.dao.UserDaoImp;
import AiLvYou.dao.UserDaoInterface;
import AiLvYou.entity.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserServeImp implements UserServeInterface {
    UserDaoInterface dao = new UserDaoImp();
    MyBatisDao myBatisDao = new MyBatisDao();

    @Override
    public Client Login(String name, String pwd) {
        Client user = new Client();
        if (name == null || name.equals("") || pwd == null || pwd.equals("")) {
            return null;
        }
        user.setPwd(pwd);
        user.setName(name);
        List<Client> clients = dao.LoginSearch(user);
        for (Client client : clients) {
            if ((user.getName().equals(client.getName()) || (user.getName().equals(String.valueOf(client.getPhoneNumber()))))
                    && user.getPwd().equals(client.getPwd())) {
                return client;
            }
        }
        return null;
    }

    @Override
    public int registerClient(Client client) {
        if (dao.searchClientByName(client.getName()) != null) {
            return 0; //用户名已注册
        }
        return  dao.insertClient(client);
    }

    @Override //需要锁
    public synchronized int orderRoutine(Client client, String travelDate, long routineID, long orderID, List<Guest> guests, double payment) {
        //System.out.println("UserServImp orderID：" + orderID);
        int amount = guests.size(); //该订单报名人数
        //guest插入
        int k = 0;
        for (Guest guest : guests) {
            int n = dao.insertGuest(guest);
            k += n;
        }
        if (k != amount) { //没有全部插入成功
            System.out.println("UserServImp guests插入err"); //测试
            return -1;
        }
        //order插入
        /*
        while (true) {
            orderID = (int) ((Math.random() * 9 + 1) * 10000);
            List<Order> orders = dao.searchOrders();
            int k = 0; //标识
            for (Order order : orders) {
                if (order.getOrderID() == orderID) {
                    k = 1;
                }
            }
            if (k == 0) {
                break;
            }
        }

         *///生成orderID(旧方法)

        //routine实际人数更新
        Routine routine = dao.searchRoutine((int) routineID);
        routine.setActualClients(routine.getActualClients() + amount);
        if (dao.updateRoutine(routine) != 1) {
            System.out.println("UserServImp dao.updateRoutine err：" + routine.toString()); //测试
            return -1;
        }

        //订单插入
        Order order = new Order();
        order.setOrderID(orderID);
        order.setRoutineID(routine.getId());
        order.setClientID(client.getId());
        order.setIsTraveled(false);
        order.setIsCommented(false);
        order.setIsPaied(false);
        order.setDate(new Date(System.currentTimeMillis()));
        order.setPayment(payment);
        order.setAmount(amount);
        order.setTravelDate(travelDate);
        return dao.insertOrder(order);
    }

    @Override
    public List<Routine> allRoutines() {
        return myBatisDao.allRoutines();
    }

    @Override
    public Routine searchRoutineByID(int routineID) {
        return myBatisDao.searchRoutine(routineID);
    }

    @Override
    public List<History> searchHistory(int clientID) {
        return dao.searchHistory(clientID);
    }

    @Override //全部订单查询
    public List<Order> searchOrders(int clientID) {
        return dao.searchOrders(clientID);
    }

    @Override
    public Order searchOrder(long orderID) {
        return dao.searchOrder(orderID);
    }

    @Override
    public int deleteOrder(long orderID) {
        return dao.deleteOrder(orderID);
    }

    @Override
    public synchronized boolean deleteOrderAndGuest(long orderID) {
        boolean k = false;
        if (deleteOrder(orderID) == 1 && deleteGuests(orderID) > 0) {
            k = true;
        }
        return k;
    }

    @Override
    public List<Order> unPay(int clientID) {
        List<Order> orders = dao.searchOrders(clientID);
        List<Order> unpayOrder = new ArrayList<>();
        for (Order order : orders) {
            if (!order.isPaied()) {
                unpayOrder.add(order);
            }
        }
        return unpayOrder;
    }

    @Override
    public List<Order> unTravel(int clientID) {
        List<Order> orders = dao.searchOrders(clientID);
        List<Order> unpayOrder = new ArrayList<>();
        for (Order order : orders) {
            if (!order.isTraveled()) {
                unpayOrder.add(order);
            }
        }
        return unpayOrder;
    }

    @Override
    public List<Order> unComment(int clientID) {
        /*
        List<Order> orders = dao.searchOrders(clientID);
        List<Order> unpayOrder = new ArrayList<>();
        for (Order order : orders) {
            if (!order.isCommented()) {
                unpayOrder.add(order);
                System.out.println("test:" + order.getRoutineID()); //测试
            }
        }
        return unpayOrder;

         */
        return dao.searchOrdersUncomment(clientID);
    }

    @Override
    public int insertComment(int clientID, int routineID, String content, int score) {
        Comment comment = new Comment();
        comment.setClientID(clientID);
        comment.setRoutineID(routineID);
        comment.setScore(score);
        comment.setContant(content);
        Client client = dao.searchClientByID(clientID);
        String name = client.getName().substring(0, 1) + "***";
        comment.setName(name);
        Date date = new Date(System.currentTimeMillis());
        comment.setDate(date);
        return dao.insertComment(comment);
    }

    @Override
    public List<Comment> searchCommentByClientID(int clientID) {
        return dao.searchComments("clientID", clientID);
    }

    @Override
    public List<Comment> searchCommentByRoutineID(int routineID) {
        return dao.searchComments("routineID", routineID);
    }

    @Override
    public int orderCommentStatusUpdate(long orderID) {
        Order order = dao.searchOrder(orderID);
        order.setIsCommented(true);
        return dao.updateOrder(order, "isCommented");
    }

    @Override
    public int orderPayStatusUpdate(long orderID) {
        Order order = dao.searchOrder(orderID);
        order.setIsPaied(true);
        //addRoutineActualNum(routineID, 1);//路线人数修改
        return dao.updateOrder(order, "isPaied");
    }

    @Override
    public int orderPayStatusUpdate(Order order) {
        return dao.updateOrder(order, "isPaied");
    }

    @Override
    public int orderTravelStatusUpdate(long orderID) {
        Order order = dao.searchOrder(orderID);
        order.setIsTraveled(true);
        return dao.updateOrder(order, "isTraveled");
    }

    @Override
    public int addRoutineActualNum(int routineID, int number) {
        Routine routine = dao.searchRoutine(routineID);
        routine.setActualClients(routine.getActualClients() + number);
        return dao.updateRoutine(routine);
    }

    @Override
    public int insertHistory(int routineID, int clientID) {
        Date date = new Date(System.currentTimeMillis());
        if (existHistory(routineID, clientID) == null) {
            History history = new History();
            history.setRoutineID(routineID);
            history.setClientID(clientID);
            history.setTime(date);
            return dao.insertHistory(history);
        }
        History history = existHistory(routineID, clientID);
        history.setTime(date);
        return dao.updateHistory(history);
    }

    @Override
    public History existHistory(int routineID, int clientID) {
        History res = null;
        List<History> histories = dao.searchHistory(clientID);
        for (History history : histories) {
            if (history.getRoutineID() == routineID) {
                res = history;
                return res;
            }
        }
        return res;
    }

    @Override
    public List<Detail> details(int routineID) {
        return dao.details(routineID);
    }

    @Override
    public Discount discount(int routineID) {
        return dao.searchDiscount(routineID);
    }

    @Override
    public List<RoutineFeatureImg> featureImgs(int routineID) {
        return dao.featureImgs(routineID);
    }

    @Override
    public int insertGuest(int orderID, String name, int identity, int phoneNumber, String status) {

        Guest guest = new Guest((long) orderID, name, (long) identity, (long) phoneNumber, status);
        return dao.insertGuest(guest);
    }

    @Override
    public int deleteGuests(long orderID) {
        return dao.deleteGuest(orderID);
    }

    public static void main(String[] args) {
        //评论插入测试
        UserServeImp ser = new UserServeImp();
        //ser.insertComment(8, 1, "评论内容这里是", 4);

        //guest插入测试
        /*
        Guest guest = new Guest();
        guest.setStatus("成人");
        guest.setPhoneNumber(123123);
        guest.setName("a1");
        guest.setIdentity(123123123);

         */
        /**
         * 使用long时，数字后面要加L，不然会按int处理
         */
        /*
        guest.setOrderID(202064112734L);
        UserDaoImp dao = new UserDaoImp();
        System.out.println(dao.insertGuest(guest));

         */
        //order更新测试
        Order order = new Order();
        order.setOrderID(1);
        order.setTravelDate("更新");
        ser.orderPayStatusUpdate(order);
    }
}

