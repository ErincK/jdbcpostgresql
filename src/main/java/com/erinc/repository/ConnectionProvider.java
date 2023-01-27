package com.erinc.repository;

import org.postgresql.Driver;

import javax.security.auth.login.AccountLockedException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class ConnectionProvider {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String url="jdbc:postgresql://localhost:5432/DbSatis";
    private final String username="postgres";
    private final String password="root";

    private boolean openConnection(){
        try{
            Driver.class.forName("org.postgresql.Driver");
            connection =
                    DriverManager.getConnection(url,username,password);
                return true;
        }catch (Exception exception) {
            return false;
        }
    }
    private void closeConnection(){
        try{
            if(!connection.isClosed())
                connection.close(); // ÖNEMLİ!!! - Bağlantı zaten kapalı ise hata verir..
        }catch(Exception exception){

        }
    }

    public boolean executeQuery(String SQL){
        /**
         * Bağlantı açmayı dene, açabilirsen true dön.
         */
        if (openConnection()) {
            try {
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.executeUpdate();
                closeConnection();
                return true;
            } catch (Exception exception) {
                closeConnection();
            }
        }
        return false;
        }

    /**
     * Bu tarz nesne dönen yapılarda her zaman NULL donme ihtimali vardir.
     * Bu nedenle bu method'larda OPTIONAL kullanmak daha sağlıklıdır.
     *
     *
     */

    public Optional<ResultSet> getAllData(String SQL){
            if (openConnection()){
                try{
                    preparedStatement = connection.prepareStatement(SQL);
                    resultSet = preparedStatement.executeQuery();
                    closeConnection();
                    return Optional.of(resultSet);
                }catch (Exception exception){
                    closeConnection();
                }
            }
            return Optional.empty();
    }




}
