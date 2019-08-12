package com.danny.ws.soap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.feature.Features;

import com.danny.ws.CreateOrdersRequest;
import com.danny.ws.CreateOrdersResponse;
import com.danny.ws.CustomerOrdersPortType;
import com.danny.ws.DeleteOrderRequest;
import com.danny.ws.DeleteOrderResponse;
import com.danny.ws.GetOrdersRequest;
import com.danny.ws.GetOrdersResponse;
import com.danny.ws.Order;
import com.danny.ws.Product;

@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class CustomerOrderWsImpl implements CustomerOrdersPortType {

    private Map<BigInteger, List<Order>> customerOrders = new HashMap<>();
    private int currentId;

    public CustomerOrderWsImpl() {
        init();
    }

    public void init() {
        List<Order> orders = new ArrayList<Order>();

        Order order = new Order();
        order.setId(BigInteger.valueOf(1));

        Product product = new Product();
        product.setId(BigInteger.valueOf(1));
        product.setDescription("iPhone");
        product.setQuantity(BigInteger.valueOf(7));

        order.getProduct().add(product);

        orders.add(order);

        customerOrders.put(BigInteger.valueOf(++currentId), orders);
    }

    @Override
    public GetOrdersResponse getOrders(GetOrdersRequest request) {
        BigInteger customerId = request.getCustomerId();
        List<Order> orders = customerOrders.get(customerId);

        GetOrdersResponse response = new GetOrdersResponse();

        response.getOrder().addAll(orders);

        return response;
    }

    @Override
    public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
        BigInteger customerId = request.getCustomerId();
        Order order = request.getOrder();

        List<Order> orders = customerOrders.get(customerId);
        orders.add(order);

        CreateOrdersResponse response = new CreateOrdersResponse();
        response.setResult(true);

        return response;
    }

    @Override
    public DeleteOrderResponse deleteOrders(DeleteOrderRequest request) {

        BigInteger customerId = request.getCustomerId();
        BigInteger orderId = request.getOrderId();

        List<Order> orders = customerOrders.get(customerId);
        orders.remove(findOrderById(orderId, orders));

        DeleteOrderResponse response = new DeleteOrderResponse();
        response.setResult(true);

        return response;
    }

    private Order findOrderById(BigInteger orderId, List<Order> orders) {
        for (Order order : orders) {
            if (order.getId().equals(orderId))
                return order;
        }
        return null;
    }


}
