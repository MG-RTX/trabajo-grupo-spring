package com.distribuida.bar_spring.service;

import com.distribuida.bar_spring.model.Factura;

public interface GuestCheckoutService {

    Factura checkoutByToken (String token);
}
