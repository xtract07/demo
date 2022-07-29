package com.springdemo.demo.repo;


import com.springdemo.demo.model.Hotels;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.*;
import java.util.Optional;

public interface HotelsRepo extends MongoRepository<Hotels,String> {

    @Query("{name:?0}")
    Hotels getHotelsBy(String name);

}
