package com.han.sega.controller;

import com.google.gson.Gson;
import com.han.sega.entity.GameTransaction;
import com.han.sega.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * validate amount entered - basic validation, positive amount, and 2 decimals
     * @param str
     * @return true=valid amount entered
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * takes json input with post method,
     * first validate the amount
     * then, create a unique transaction_id
     * and save entry in mysql database
     * @param gameTransaction
     * @return success= json including id of the entry
     *          error = throw error information
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody GameTransaction gameTransaction){

        // validate amount entered - basic validation, positive amount, and 2 decimals
        if(!isNumber(String.valueOf(gameTransaction.getAmount()))){
            return new ResponseEntity<>("invalid amount", HttpStatus.BAD_REQUEST);
        };


        try {
            // autogenerate transaction_id
            gameTransaction.setTransactionId(String.valueOf(UUID.randomUUID()));

            int count = apiService.save(gameTransaction);

            if (count == 1 ) {
                String json = new Gson().toJson(gameTransaction);
                return new ResponseEntity<>(json, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("unknown save error", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            return new ResponseEntity<>("unknown save error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * list all records in mysql database
     * @return json of all as a list
     */
    @GetMapping("/list")
    public ResponseEntity<String> listAll(){

        try {
            List<GameTransaction> gameTransactionList = apiService.findAll();

            if (gameTransactionList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            String json = new Gson().toJson(gameTransactionList);

            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
