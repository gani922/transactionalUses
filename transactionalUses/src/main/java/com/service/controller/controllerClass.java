package com.service.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.service.service.serviceClass;
@RestController
@RequestMapping("/accounts")

public class controllerClass {

    @Autowired
    private serviceClass serv;

    @PostMapping("/init")
    public String init() {
        serv.createAccounts();
        return "Accounts created!";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long from,
                           @RequestParam Long to,
                           @RequestParam double amount) {
        try {
            serv.transfer(from, to, amount);
            return "Transfer successful!";
        } catch (Exception e) {
            return "Transfer failed: " + e.getMessage();
        }
    }

    @GetMapping
    public Object all() {
        return serv.getAllAccounts();
    }
}

