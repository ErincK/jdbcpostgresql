package com.erinc.repository;

import com.erinc.repository.entity.Musteri;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.erinc.repository.entity.Musteri;

public class MusteriRepository implements ICrud<Musteri>{
    private String SQL;
    private ConnectionProvider provider;


    @Override
    public void save(Musteri musteri) {
        SQL="insert into tblmusteri(ad,soyad,email,telefon,cinsiyet,yas)"+
                "values('"
                +musteri.getAd()+"','"
                +musteri.getSoyad()+"','"
                +musteri.getEmail()+"','"
                +musteri.getTelefon()+"','"
                +musteri.getCinsiyet()+"',"
                +musteri.getYas()+")";
        provider.executeQuery(SQL);
    }

    @Override
    public void update(Musteri musteri) {
        SQL="update tblmusteri set "+
                "ad='"+musteri.getAd()+"',"+
                "soyad='"+musteri.getSoyad()+"',"+
                "email='"+musteri.getEmail()+"',"+
                "telefon='"+musteri.getTelefon()+"',"+
                "cinsiyet='"+musteri.getCinsiyet()+"',"+
                "yas=" +musteri.getYas()+
                " where id="+musteri.getId();
        provider.executeQuery(SQL);
    }

    @Override
    public void delete(Long id) {
        SQL= "delete from tblmusteri where id="+id;
        provider.executeQuery(SQL);
    }

    @Override
    public List<Musteri> findAll() {
        SQL = "select * from tblmusteri";
        Optional<ResultSet> resultSet = provider.getAllData(SQL);
        if(resultSet.isEmpty()) return new ArrayList<>();
        List<Musteri> musteriListesi = new ArrayList<>();
        try{
            while (resultSet.get().next()){ // gelen tablo içindeki verileri satır satır okumamaız lazım.
                Long id = resultSet.get().getLong("id");
                String ad = resultSet.get().getString("ad");
                String soyad = resultSet.get().getString("soyad");
                String email = resultSet.get().getString("email");
                String telefon = resultSet.get().getString("telefon");
                String cinsiyet = resultSet.get().getString("cinsiyet");
                Integer yas = resultSet.get().getInt("yas");
                Long createdate = resultSet.get().getLong("createdate");
                Long updatedate = resultSet.get().getLong("createdate");
                Integer state = resultSet.get().getInt("state");
                musteriListesi.add(new Musteri(id,ad,soyad,email,telefon,cinsiyet,yas,createdate,updatedate,state));
            }
            return musteriListesi;
        }catch (Exception exception){

        }
        return new ArrayList<>();
    }

    @Override
    public Musteri findById(Long id) {
        SQL = "select * from tblmusteri where id="+id;
        Optional<ResultSet> resultSet = provider.getAllData(SQL);
        if(resultSet.isEmpty()) return new Musteri();
        try{
            while (resultSet.get().next()){ // gelen tablo içindeki verileri satır satır okumamaız lazım.
                String ad = resultSet.get().getString("ad");
                String soyad = resultSet.get().getString("soyad");
                String email = resultSet.get().getString("email");
                String telefon = resultSet.get().getString("telefon");
                String cinsiyet = resultSet.get().getString("cinsiyet");
                Integer yas = resultSet.get().getInt("yas");
                Long createdate = resultSet.get().getLong("createdate");
                Long updatedate = resultSet.get().getLong("createdate");
                Integer state = resultSet.get().getInt("state");
                return new Musteri(id,ad,soyad,email,telefon,cinsiyet,yas,createdate,updatedate,state);
            }
        }catch (Exception exception){
        }
        return new Musteri();
    }

    @Override
    public List findByAny(String findKey) {
        return null;
    }
}
