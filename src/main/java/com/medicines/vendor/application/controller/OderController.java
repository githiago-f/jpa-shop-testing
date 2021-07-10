package com.medicines.vendor.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/orders")
public class OderController {
    @GetMapping(value="")
    public void page(@RequestParam String param) {}
}
