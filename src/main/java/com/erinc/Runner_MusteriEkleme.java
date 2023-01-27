package com.erinc;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Runner_MusteriEkleme {
    public static void main(String[] args) {
        System.out.println("*******************************");
        System.out.println("****   Musteri Ekleme   *******");
        System.out.println("*******************************");

        String ad,soyad,email,telefon,cinsiyet;
        int yas=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Musteri adi...........: " );
        ad= sc.nextLine();
        System.out.println("Musteri Soyadi...........: " );
        soyad= sc.nextLine();
        System.out.println("Musteri email...........: " );
        email= sc.nextLine();
        System.out.println("Musteri telefon...........: " );
        telefon= sc.nextLine();
        System.out.println("Musteri cinsiyet...........: " );
        cinsiyet= sc.nextLine();
        System.out.println("Musteri yas...........: " );
        yas= sc.nextInt();
        save(ad,soyad,email,telefon,cinsiyet,yas);

    }

    private static void save(String ad, String soyad, String email, String telefon, String cinsiyet, int yas){
        try{
            Driver.class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/DbSatis?user=postgres&pasword=root");
            String SQL_INSERT = "insert into tblmusteri(ad,soyad,email,telefon,cinsiyet,yas)" +
                    "values('"+ad+"','"+soyad+"','"+email+"','"+telefon+"','"+cinsiyet+"',"+yas+")";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.executeUpdate();
            connection.close();
        }catch(Exception e){
            System.out.println("Bir hata oldu....:  " + e.getLocalizedMessage());
        }


    }





}
