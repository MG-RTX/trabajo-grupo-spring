package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.Pedido;
import com.distribuida.bar_spring.model.cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)

public class PedidoRepositorioTestIntegracion {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private clienteRepository clienteRepository;

    @Test
    public void findAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        for(Pedido item: pedidos){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Pedido> pedido = pedidoRepository.findById(3 );
        System.out.println(pedido.orElse(null).toString());
    }

    @Test
    public void save(){
        Pedido pedidosave = new Pedido();
        Optional<cliente> cliente = clienteRepository.findById(4);
        pedidosave.setCliente(cliente.orElse(null));
        pedidosave.setFechaPedido(new Date());
        pedidosave.setTotal(100.0);
        pedidoRepository.save(pedidosave);
    }

    @Test
    public void update(){
        Optional<Pedido> pedidosExistentes = pedidoRepository.findById(3);
        Optional<cliente> clienteExistente = clienteRepository.findById(4);

        pedidosExistentes.orElse(null).setCliente(clienteExistente.orElse(null));
        pedidosExistentes.orElse(null).setFechaPedido(new Date());
        pedidosExistentes.orElse(null).setTotal(666.0);

        pedidoRepository.save(pedidosExistentes.orElse(null));
    }

    @Test
    public void delete(){
        if(pedidoRepository.existsById(3)){
            pedidoRepository.deleteById(3);
        }
    }


}