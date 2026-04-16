package com.densoft.wsdlfirstwebsvc;

import com.bharath.ws.trainings.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerOrdersWsImpl implements CustomerOrdersPortType {

    Map<BigInteger, List<Order>> customerOrders = new HashMap<>();
    int currentId;

    public CustomerOrdersWsImpl() {
        init();
    }

    public void init() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(BigInteger.valueOf(1).toString());
        Product product = new Product();
        product.setId("1");
        product.setDescription("IPhone");
        product.setQuantity(BigInteger.valueOf(3));
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
        CreateOrdersResponse createOrdersResponse = new CreateOrdersResponse();
        createOrdersResponse.setResult(true);
        return createOrdersResponse;
    }
}
