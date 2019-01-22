package lk.ijse.fx.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static DBConnection dbConnection;
    private static Connection connection;

    private DBConnection() throws IOException, SQLException {

        File file = new File("resource/Application.properties");
        FileReader fileReader = new FileReader(file);
        Properties dbProp = new Properties();
        dbProp.load(fileReader);
        String ip = dbProp.getProperty("ip");
        String port = dbProp.getProperty("port");
        String database = dbProp.getProperty("database");
        String username = dbProp.getProperty("username");
        String password = dbProp.getProperty("password");

        String url = "jdbc:mysql://"+ip+":"+port+"/"+database;
        connection = DriverManager.getConnection(url,username,password);

    }

    public static DBConnection getInstance() throws IOException, SQLException {
        if(dbConnection == null){
            return dbConnection = new DBConnection();

        }
        return dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }

}
