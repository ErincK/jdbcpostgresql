package com.erinc;

import com.erinc.repository.MusteriRepository;
import com.erinc.repository.entity.Musteri;

public class Runner_Repository {
    public static void main(String[] args) {
        MusteriRepository repository = new MusteriRepository();

        //repository.delete(1059l);
        // repository.save(new Musteri(0l,"Erinc","","","","",34,null,null,1));
        // repository.findAll().forEach(System.out::println);
        System.out.println(repository.findById(1000l));
    }


}
