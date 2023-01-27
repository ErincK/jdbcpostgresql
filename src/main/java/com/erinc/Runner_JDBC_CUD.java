package com.erinc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Runner_JDBC_CUD {

    public static void main(String[] args) throws Exception{
        /**
         * JDBC, kullanımında ilk yapılması gereken iki sistem arasında
         * köprü görevi görecek olan (Tercüman) Driver ın kullanıma alınması
         *  1- Kullanılan versiyonuna uygun jdbc driver eklenmeli.
         *  2- Eklenen driver devreye alınmalı.
         */
        Driver.class.forName("org.postgresql.Driver");
        /**
         * Driver'a bağlantı yapabilmesi için gerekli olna parametreleri işlemek gereklidir.
         *  -> Bağlantı yapılacak Veritabanı sunucusunun ip adresi.
         *  -> Hangi port üzerinden yayın yaoıyor ise, Port numarası
         *  -> İşlem yapılacak olan veri tabanının adi.
         *  -> Yetkilendirmek için Kullaniciadi ve Sifre.
         *  !! DİKKAT!!
         *      Tüm sistemlerde (Dilllerde) veritabanına bağlantı işlemlerinde
         *      Bağlantı cümlesi (SQLConnection String) kullanılır. Araştırma yaparken bunu kullanırırız.
         *      Eğer kendi pc'nizin postgresql'ine bağlanacaksanız;
         *      - localhost <---> 127.0.0.1
         *
         */
        String baglantiAdresi = "jdbc:postgresql://localhost:5432/DbSatis";
        String kullaniciAdi = "postgres";
        String sifre = "root";
        Connection connection = DriverManager.getConnection(baglantiAdresi,kullaniciAdi,sifre);
        /**
         *  Emir vermeye başlamalıyız.
         *  SQL cümlesi vererek işlenmesini sağlamalıyız
         */
        String SQL = "insert into tbladres(musteriid,il,ilce,mahalle,adres) \n" +
                "values (51,'Istanbul','Kadikoy','merkez','Kosuyolu')";
        String SQL_DELETE = "delete from tbladres where id=44";
        String SQL_UPDATE = "update tbladres set musteriid=455, il='Izmir' where id=33";


        /**
         * Hazırladığınız SQL cümlesini Driver ın taşıyabileceği hale getirmeniz, bir zarfa koymanız gerekli
         * bunun için java da cümle hazırlayıcısı kullanılır.
         */
        PreparedStatement pr = connection.prepareStatement(SQL);
        /**
         * Sürücüye hazırladığımız emri uygulaması gerektiğini belirtmeliyiz.
         * hazırlanan cümlenin execute edilmesini sağlamalıyız.
         */
        pr.executeUpdate();
        /**
         * Tüm bu işlemler sona erdikten sonra mutlaka açılan bağlantının sonlandırılması gerekir.
         * Çünkü bir database connection havuzunda bir PC için 10 bağlantı sağlar, sonraki bağlantılar reddedilir.
         */
        connection.close();
    }
}
