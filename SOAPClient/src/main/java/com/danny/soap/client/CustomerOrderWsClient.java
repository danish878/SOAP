package com.danny.soap.client;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.danny.ws.CreateOrdersRequest;
import com.danny.ws.CreateOrdersResponse;
import com.danny.ws.CustomerOrdersPortType;
import com.danny.ws.GetOrdersRequest;
import com.danny.ws.GetOrdersResponse;
import com.danny.ws.Order;
import com.danny.ws.Product;
import com.danny.ws.soap.CustomerOrderWsImplService;

public class CustomerOrderWsClient {

    private static CustomerOrderWsImplService service;
    private static CustomerOrdersPortType customerOrderWsImplPort;

    public static void main(String[] args) throws MalformedURLException {

        service = new CustomerOrderWsImplService(new URL("http://localhost:8080/wsdl_first_ws/customerOrdersService?wsdl"));
        customerOrderWsImplPort = service.getCustomerOrderWsImplPort();

        createOrder();


        getOrders();

    }

    private static void getOrders() {
        GetOrdersRequest request = new GetOrdersRequest();
        request.setCustomerId(BigInteger.valueOf(1));

        GetOrdersResponse orders = customerOrderWsImplPort.getOrders(request);
        List<Order> ordersList = orders.getOrder();

        for (Order order : ordersList) {
            System.out.println("===>>> Order ID: " + order.getId());
            List<Product> products = order.getProduct();
            for (Product product : products) {
                System.out.println("===>>> PRODUCT INFO:");
                System.out.println(product.getId());
                System.out.println(product.getDescription());
                System.out.println(product.getQuantity());
            }
        }
    }

    private static void createOrder() {
        CreateOrdersRequest createOrdersRequest = new CreateOrdersRequest();
        createOrdersRequest.setCustomerId(BigInteger.valueOf(1));

        Order order = new Order();
        order.setId(BigInteger.valueOf(2));

        Product product = new Product();
        product.setId(BigInteger.valueOf(2));
        product.setDescription("Dildo");
        product.setQuantity(BigInteger.valueOf(6));

        order.getProduct().add(product);

        createOrdersRequest.setOrder(order);


        CreateOrdersResponse createOrdersResponse = customerOrderWsImplPort.createOrders(createOrdersRequest);
        System.out.println(createOrdersResponse.isResult() ? "Order Created" : "Failed");
    }
}
