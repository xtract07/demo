package com.springdemo.demo.resource;
import java.util.List;

import com.springdemo.demo.model.Hotels;
import com.springdemo.demo.model.timeOfBooking;
import com.springdemo.demo.repo.HotelsRepo;
import com.springdemo.demo.services.countingWeekends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelsController {

    @Autowired
    private countingWeekends cW;

    @Autowired
    private HotelsRepo repo;

    @PostMapping("/addHotel")
    public String saveHotel(@RequestBody Hotels Hotel){
        repo.save(Hotel);
        return "Added Hotel successfully!";
    }

    @GetMapping("/listAllHotels")
    public List<Hotels> getHotels(){
        return repo.findAll();
    }

    @DeleteMapping("/deleteHotel/{name}")
    public String deteleHotel(@PathVariable String name){
        Hotels hotel = repo.getHotelsBy(name);
        repo.delete(hotel);
        return "hotel deleted succesfully ! with name :" + name;
    }


    @GetMapping("/listCheapest")
    public Hotels cheapest(@RequestBody timeOfBooking Booking) throws Exception {
        return cW.Cheapest(Booking);
    }

}
