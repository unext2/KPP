package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.counter.RequestCounterThread;
import com.example.demo.exception.DataRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class WeekDayController {
    private WeekDayCounting states;
    private RequestCounterThread counterThread;
    private Logger logger = LoggerFactory.getLogger(WeekDayController.class);

    @Autowired
    public void setter(WeekDayCounting newService){
        this.states = newService;
    }

    private static final String template = "Day \" %s \" of \"%s\" year is %s";
    private static final String template1 = "Day %s of %s year is %s\n";


    @GetMapping("/weekday")
    public String get(@RequestParam(value = "year") String year,
                      @RequestParam(value = "day") String day) throws DataRequestException{
        counterThread = new RequestCounterThread();
        DataClass gr = new DataClass(year, day);
        return String.format(template,gr.getDay(),gr.getYear(),states.calc(gr));
    }

    @PostMapping("/weekday")
    public ResponseEntity<?> post1(@RequestBody List<DataClass> list){
        List<String> res = new ArrayList<>();
        List<Integer> resInt = new ArrayList<>();
        List<String> res_output = new ArrayList<>();
        list.forEach((element)->{
            try {
            res_output.add(String.format(template1,element.getYear(),element.getDay(),states.calc(element)));
            resInt.add(states.calcInt(element));
            res.add(states.calc(element));
            } catch (Throwable e) {
                logger.info("Argument is null (not fact)");
            }
        });

        long sizeOfRequest = states.calcSize(res);
        String offten = states.mostRecurring(res);
        int MAX = states.findMax(resInt);
        int MIN = states.findMin(resInt);
        return new ResponseEntity<>(res_output + "\nSize = " + sizeOfRequest + "\nMax = "+MAX + "\nMin = "+ MIN+"\nOfften = "+ offten, HttpStatus.OK);
    }
}

