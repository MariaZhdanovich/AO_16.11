package com.Maria.zakupy.application;

public class OrderApplicationService implements DeliveryCostCalculator {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final Clock clock;

    public OrderApplicationService(OrderService orderservice, OrderRepository orderRepository, Clock clock){
        this.orderservice = orderService;
        this.orderRepository = orderRepository;
        this.clock = clock;
    }

    public Order placeOrder(String customerName, List<OrderItem> items, DeliveryMethod deliveryMethod {
        Order order = Order.place(customername, items, deliveryMethod, clock.instant());
        Order orderWithDelivery = order.withdeliveryCost(calculateDeliveryCost(order.items(), order.deliveryMethod()));
        return orderRepository.save(orderWithDelivery);
    }

    public Optional<Order> getOrder(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public BigDecimal calculateDeliveryCost(List<OrderItem> items, DeliveryMethod deliveryMethod) {
        return orderService.calculateDeliveryCost(items, deliveryMethod);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
