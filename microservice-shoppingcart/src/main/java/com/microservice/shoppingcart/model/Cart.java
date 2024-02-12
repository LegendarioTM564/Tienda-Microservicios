package com.microservice.shoppingcart.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Table(name = "carrito")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long id_cart;
    @Column(name = "monto_total")
    private  double total_amount;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @Valid
    //@NotEmpty(message = "lista de productos requerido.")
    private List<CartItem> listItems = new ArrayList<>();


    public void removeItem(CartItem cartItem ) {
        listItems.remove(cartItem);
        cartItem.setCart(null);
    }

}
