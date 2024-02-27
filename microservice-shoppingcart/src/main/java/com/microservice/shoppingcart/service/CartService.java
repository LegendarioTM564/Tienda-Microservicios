package com.microservice.shoppingcart.service;

import com.microservice.shoppingcart.client.ProductClient;
import com.microservice.shoppingcart.dto.CartDTO;
import com.microservice.shoppingcart.dto.CartItemDTO;
import com.microservice.shoppingcart.dto.ProductDTO;
import com.microservice.shoppingcart.model.Cart;
import com.microservice.shoppingcart.model.CartItem;
import com.microservice.shoppingcart.repository.ICartItemRepository;
import com.microservice.shoppingcart.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {

    @Autowired
    ICartRepository cartRepository;

    @Autowired
    ProductClient productClient;


    @Autowired
    ICartItemRepository cartItemRepository;

    @Override
    public List<Cart> getAllCart() {

        List<Cart> listCart = cartRepository.findAll();
        return listCart;
    }

    @Override
    public Cart getCartById(Long id_cart) {

        Cart cart = cartRepository.findById(id_cart).orElse(null);
        return cart;
    }

    @Override
    public Long saveCart(CartDTO cartDTO) {
        Cart carrito = new Cart();
        List<CartItem> listItems = new ArrayList<>();

        double montoTotalCarrito = 0.0;

        for (CartItemDTO itemDTO : cartDTO.getListItems()) {
            CartItem itemCarrito = new CartItem();
            itemCarrito.setQuantity(itemDTO.getQuantity());

            // Obtener el producto del servicio de productos por su ID
            ProductDTO product = productClient.getProductsById(itemDTO.getId_product());

            // Establecer los detalles del producto en el item del carrito
            itemCarrito.setId_product(product.getId_product());
            itemCarrito.setTotal_amount(product.getPrice() * itemDTO.getQuantity());

            // Agregar el monto total del item al monto total del carrito
            montoTotalCarrito += itemCarrito.getTotal_amount();

            itemCarrito.setCart(carrito);
            listItems.add(itemCarrito);
        }

        carrito.setListItems(listItems);
        carrito.setTotal_amount(montoTotalCarrito);

        cartRepository.save(carrito);

        return carrito.getId_cart();
    }


    @Override
    public CartDTO getCartDtoById(Long id_cart) {
        //Tre el carrito por ID.
        Cart cart = this.getCartById(id_cart);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId_cart(cart.getId_cart());


        double total_amount = cart.getListItems().stream()
                .mapToDouble(item -> item.getTotal_amount()).sum();
        cartDTO.setTotal_amount(total_amount);


        List<CartItemDTO> listCartItemsDTO = cart.getListItems().stream()
                        .map(item ->{
                            ProductDTO product = productClient.getProductsById(item.getId_product());

                        CartItemDTO itemDTO = new CartItemDTO();
                        itemDTO.setId_product(product.getId_product());
                        itemDTO.setName_product(product.getName());
                        itemDTO.setPrice_product(product.getPrice());
                        itemDTO.setQuantity(item.getQuantity());

                        double total_amount_item = product.getPrice() * item.getQuantity();
                        itemDTO.setTotal_amount(total_amount_item);

                        return itemDTO;
                        })
                                .collect(Collectors.toList());

        cartDTO.setListItems(listCartItemsDTO);

        return cartDTO;
    }



    @Override
    public void deletedItemByID(Long id_cart, Long id_item) {
        Optional<Cart> optionalCart = cartRepository.findById(id_cart);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Optional<CartItem> optionalCartItem = cart.getListItems().stream()
                    .filter(item -> item.getId_product().equals(id_item))
                    .findFirst();
            if (optionalCartItem.isPresent()){
                    CartItem cartItem = optionalCartItem.get();
                    cart.getListItems().remove(cartItem);
                    actualizarMontoTotalCarrito(cart);
                    cartItemRepository.delete(cartItem);
                    cartRepository.save(cart);

            }else {
                throw new RuntimeException("No se encontró el ítem del carrito con ID: " + id_item);
            }

        } else {
            throw new RuntimeException("No se encontró el carrito con ID: " + id_cart);
        }
    }

    private void actualizarMontoTotalCarrito(Cart cart) {
        double total_amount = cart.getListItems().stream()
                .mapToDouble(CartItem::getTotal_amount).sum();
        cart.setTotal_amount(total_amount);
    }

}
