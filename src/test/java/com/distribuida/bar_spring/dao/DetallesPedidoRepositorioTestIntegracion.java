package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.DetallesPedido;
import com.distribuida.bar_spring.model.Pedido;
import com.distribuida.bar_spring.model.Producto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)

public class DetallesPedidoRepositorioTestIntegracion   {

    @Autowired
    private DetallesPedidoRepository detallesPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void findAll(){
        List<DetallesPedido> detallesPedidos = detallesPedidoRepository.findAll();
        for(DetallesPedido item: detallesPedidos){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<DetallesPedido>detallesPedidos = detallesPedidoRepository.findById(2);
        System.out.println(detallesPedidos.toString());
    }

    @Test
    public void save(){
        DetallesPedido detallesPedidosa = new DetallesPedido();
        Optional<Pedido> pedido = pedidoRepository.findById(4);
        Optional<Producto> producto = productoRepository.findById(4);
        detallesPedidosa.setCantidad(1);
        detallesPedidosa.setPrecioUnitario(13.0);
        detallesPedidosa.setSubtotal(13.0);
        detallesPedidosa.setPedido(pedido.orElse(null));
        detallesPedidosa.setProducto(producto.orElse(null));
        detallesPedidoRepository.save(detallesPedidosa);
    }

    @Test
    public void update(){
        Optional<DetallesPedido> detallesPedidosExistentes = detallesPedidoRepository.findById(4);
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(4);
        Optional<Producto> productoExistente = productoRepository.findById(4);

        detallesPedidosExistentes.orElse(null).setCantidad(10);
        detallesPedidosExistentes.orElse(null).setPrecioUnitario(10.0);
        detallesPedidosExistentes.orElse(null).setSubtotal(10.0);
        detallesPedidosExistentes.orElse(null).setPedido(pedidoExistente.orElse(null));
        detallesPedidosExistentes.orElse(null).setProducto(productoExistente.orElse(null));
        detallesPedidoRepository.save(detallesPedidosExistentes.orElse(null));
    }

    @Test
    public void delete(){
        if(detallesPedidoRepository.existsById(2)){
            detallesPedidoRepository.deleteById(2);
        }
    }

}
