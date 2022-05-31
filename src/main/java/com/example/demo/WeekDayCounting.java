package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class WeekDayCounting
{
    private WeekDayCache hashMap;
    private Logger logger = LoggerFactory.getLogger(WeekDayCounting.class);
    @Autowired
    public void setHashMap(WeekDayCache hashMap){
        this.hashMap = hashMap;
    }

    @Autowired
    public Repository repository;

    public String calc(DataClass gr) {
        int rest;
        String weekday = "Monday";
        if(hashMap.isContain(gr)){
            weekday = hashMap.getParam(gr);
            logger.info("Get hashMap");
        }
        else {

            Integer year_int = Integer.valueOf(gr.getYear());
            Integer day_int = Integer.valueOf(gr.getDay());

            Integer days_at_all;
            Integer substract = year_int - 2001;
            Integer vis = 0;

            while(substract!=0)
            {
                if(substract % 4 == 0)
                    vis++;
                substract--;
            }

            if (day_int>366)
            {
                weekday = "Wrong day";
                return weekday;
            }

            days_at_all = year_int * 365 + day_int;
            rest = days_at_all % 7;
            rest = rest + vis;

            while(rest>7)
            {
                rest = rest - 7;
            }

            switch(rest)
            {
                case 0:
                    weekday = "Monday";
                    break;
                case 1:
                    weekday = "Tuesday";
                    break;
                case 2:
                    weekday = "Wednesday";
                    break;
                case 3:
                    weekday = "Thursday";
                    break;
                case 4:
                    weekday = "Friday";
                    break;
                case 5:
                    weekday = "Saturday";
                    break;
                case 6:
                    weekday = "Sunday";
                    break;
            }

            hashMap.addToMap(gr,weekday);
            repository.save(gr);
            logger.info("Add to hashMap");
        }
        return weekday;
    }

    public int calcInt(DataClass gr) {
        int rest = 0;
        String weekday = "Monday";

            Integer year_int = Integer.valueOf(gr.getYear());
            Integer day_int = Integer.valueOf(gr.getDay());

            Integer days_at_all;
            Integer substract = year_int - 2001;
            Integer vis = 0;

            while(substract!=0)
            {
                if(substract % 4 == 0)
                    vis++;
                substract--;
            }

            days_at_all = year_int * 365 + day_int;
            rest = days_at_all % 7;
            rest = rest + vis;

            while(rest>7)
            {
                rest = rest - 7;
            }

            switch(rest)
            {
                case 0:
                    weekday = "Monday";
                    break;
                case 1:
                    weekday = "Tuesday";
                    break;
                case 2:
                    weekday = "Wednesday";
                    break;
                case 3:
                    weekday = "Thursday";
                    break;
                case 4:
                    weekday = "Friday";
                    break;
                case 5:
                    weekday = "Saturday";
                    break;
                case 6:
                    weekday = "Sunday";
                    break;

        }
        return rest;
    }

    public long calcSize(List<String> resList){
        long size = 0;
        if(!resList.isEmpty()){
            size = resList
                    .stream()
                    .count();
        }
        return size;
    }

    public String mostRecurring(List<String> resList){
        int size = 0;
        int max =0;
        String word ="Monday";
        HashSet<String> res = new HashSet<>();
        res.addAll(resList);
        for(String a :res) {
            size = Collections.frequency(resList, a);
            if(size > max){
                max = size;
                word = a;
            }
        }
        return word;
    }

    public int findMax(List<Integer> resList){
        int max = 0;
        if(!resList.isEmpty()){
            max = resList
                    .stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .getAsInt();
        }
        return max;
    }
    public int findMin(List<Integer> resList){
        int min = 0;
        if(!resList.isEmpty()){
            min = resList
                    .stream()
                    .mapToInt(Integer::intValue)
                    .min()
                    .getAsInt();
        }
        return min;
    }

}
