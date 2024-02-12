package com.microservice.shoppingcart.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "items_carrito")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long id_item;
    @Column(name = "cantidad")
    //@NotBlank(message = "cantidad de productos requerido.")
    private int quantity;
    @Column(name = "monto_total")
    private double total_amount;
    @Column(name = "id_producto")
    //@NotEmpty(message = "id de los productos requerido.")
    private Long id_product;
    @ManyToOne
    @JoinColumn(name = "id_carrito")
    private Cart cart;
}
