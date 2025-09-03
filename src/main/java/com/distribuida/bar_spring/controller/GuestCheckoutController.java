package com.distribuida.bar_spring.controller;

import com.distribuida.bar_spring.model.Factura;
import com.distribuida.bar_spring.service.GuestCheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guest/checkout")
public class GuestCheckoutController {

    private final GuestCheckoutService guestCheckoutService;

    public GuestCheckoutController(GuestCheckoutService service) {
        this.guestCheckoutService = service;

    }

    @PostMapping
    public ResponseEntity<Factura> cheackout(@RequestParam String token) {
        return ResponseEntity.ok(guestCheckoutService.checkoutByToken(token));
    }
}