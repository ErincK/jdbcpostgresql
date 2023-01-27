package com.erinc;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Driver.class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/DbSatis?user=postgres&pasword=root");
        PreparedStatement pr = connection.prepareStatement("select * from tblmusteri");
        /**
         *  SQL sorguları neticesinde bir tablo dönüşü olacak ise, bu resultset şeklinde tablo olarak döner.
         *  Burada önemli olan, bu resultset içinde barındırılan dataların, sayır satır okunması gereklidir.
         */

        ResultSet resultSet = pr.executeQuery();

        /**
         * Satır satır okuma işini while ile yaparız.
         * Çünkü, resultset içinde bulunan next() komutu her seferinde bir satıra odaklanır.
         */
        while(resultSet.next()){
            /**
             * resultset içinde bulunan method'lar ile tablo satırı içindeki kolonları seçebilir.
             * Burada dikkat edilecek nokta, okumaya çalıştığınız sütunun tipi (long,int,string vs..) dir.
             * Ayrıca okuyacağınız tablo sütununun ya index numarası yada adını vermeniz gerekir.
             */
            long id = resultSet.getLong("id");
            String ad = resultSet.getString(2);
            System.out.println("id...: "+ id + " - ad....: "+ ad);
        }
        connection.close();




    }
}