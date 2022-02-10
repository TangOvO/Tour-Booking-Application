package AiLvYou.dao;

import AiLvYou.entity.Client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Properties;

public class Utils {

    public static void main(String[] args) {
        Connection connection = getMysqlCon();
        System.out.println(connection);
        UserDaoImp dao = new UserDaoImp();

        //getMethod测试
        Client client = dao.searchClientByName("Pig");
        System.out.println(getMethod("name", client));

    }

    public static Connection getConnection() {
        Context context = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
            return ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection con) {
        if (con != null) {
            try {
                //con = null ;
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object getMethod(String fieldName, Object source) {
        Method[] methods = source.getClass().getDeclaredMethods();
        String s = "get" + fieldName.toLowerCase();
        for (int i = 0; i < methods.length; i++) {
            String m = methods[i].getName().toLowerCase();
            if (s.equals(m)) {
                try {
                    return methods[i].invoke(source);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Object newObject(Class<?> c) throws IllegalAccessException, InstantiationException {
        return c.newInstance();
    }

    /**
     *
     * @param fieldName
     * @param source 目标类
     * @param val 要给的值
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void setMethod(String fieldName, Object source, Object val) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = source.getClass().getDeclaredMethods();
        String s = "set" + fieldName.toLowerCase();
        for (int i = 0; i < methods.length; i++) {
            String m = methods[i].getName().toLowerCase();
            if (s.equals(m)) {
                if (val != null) {
                    //System.out.println("Util:" + methods[i].getName()); //测试
                    methods[i].invoke(source, val);
                    break;
                }
            }
        }
    }

    public static Connection getMysqlCon() {
        try {


            //Class.forName("com.mysql.jdbc.Driver") ;
            Class.forName("com.mysql.cj.jdbc.Driver") ;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanxian?serverTimezone=GMT",
                    "root", "root");
            return con ;
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        }
    }
}
