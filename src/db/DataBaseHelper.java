package db;

import java.sql.*;

public class DataBaseHelper {

    private static volatile DataBaseHelper sDataBaseHelper;
    private Statement mStatement;
    private Connection mConnection;


    public static DataBaseHelper getInstance() {
        if (sDataBaseHelper == null) {
            synchronized (DataBaseHelper.class) {
                if (sDataBaseHelper == null) {
                    sDataBaseHelper = new DataBaseHelper();
                }
            }
        }
        return sDataBaseHelper;
    }

    private DataBaseHelper() {

        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            String driverCommand = "jdbc:mysql://localhost:3306/home";
            String dbAccount = "root";
            String dbPassword = "password";
            mConnection = DriverManager.getConnection(driverCommand, dbAccount, dbPassword);

            if (!mConnection.isClosed()) {
                System.out.println("数据库连接成功");
            }

            mStatement = mConnection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet query(String sql) throws SQLException {
        System.out.println("query"+sql);
        return mStatement.executeQuery(sql);
    }

    public boolean update(String sql) throws SQLException {
        System.out.println("update"+sql);
        return mStatement.execute(sql);
    }

    public boolean insert(String sql) throws SQLException {
        System.out.println("insert"+sql);
        return update(sql);
    }

    public boolean delete(String sql) throws SQLException {
        System.out.println("delete"+sql);
        return update(sql);
    }

}
