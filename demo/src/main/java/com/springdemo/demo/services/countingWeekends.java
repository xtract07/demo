package com.springdemo.demo.services;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.springdemo.demo.model.Hotels;
import com.springdemo.demo.model.timeOfBooking;
import com.springdemo.demo.repo.HotelsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class countingWeekends{

    @Autowired
    private HotelsRepo repo;

    public Hotels Cheapest(timeOfBooking time) throws Exception{
        // getting the hotel list
        List<Hotels> listOfHotels = repo.findAll();

        //converting string into simple date format
        String incoming = time.getFromDate();
        String outgoing = time.getToDate();

        int weekends = 0;
        int weekdays = 0;

        Date incomingDate = new SimpleDateFormat("dd/MM/yyyy").parse(incoming);
        Date outgoingDate = new SimpleDateFormat("dd/MM/yyyy").parse(outgoing);


        //iterating over the dates
        Calendar cal = Calendar.getInstance();

        cal.setTime(outgoingDate);
        cal.add(Calendar.DATE,1);
        outgoingDate = cal.getTime();

        for( Date date=incomingDate;date.before(outgoingDate);){

            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (day >= Calendar.MONDAY && day <= Calendar.FRIDAY){
                weekdays++;
            }else{
                weekends++;
            }
            cal.add(Calendar.DATE,1);
            date = cal.getTime();
        }

        Hotels Cheaper=null;
        int Cost=Integer.MAX_VALUE;

        for(Hotels Hotel : listOfHotels){
            int presentCost = Hotel.getWeekendRates()*weekends + Hotel.getWeekdayRates()*weekdays;

            if(presentCost<Cost){
                Cost = presentCost;
                Cheaper = Hotel;
            }
        }

        return Cheaper;
    }
}
