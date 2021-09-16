package ua.training.controller.command;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.entity.Order;
import ua.training.model.entity.Receipt;
import ua.training.model.service.ReceiptService;
import ua.training.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class CreateReceiptCommand implements Command {

    private static final Logger logger = LogManager.getLogger(MakeOrderCommand.class);
    private ReceiptService receiptService;
    private OrderServiceImpl orderService;

    public CreateReceiptCommand(ReceiptService receiptService, OrderServiceImpl orderService) {
        this.receiptService = receiptService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
       try {
           long orderId = Long.parseLong(request.getParameter("order"));
           Order order = orderService.findById(orderId).orElseThrow(IndexOutOfBoundsException::new);
           receiptService.create(Receipt.builder()
                   .price(orderService.calculateOrderPrice(order))
                   .paid(false)
                   .order(order)
                   .build());
       }catch (NumberFormatException | IndexOutOfBoundsException e){
           logger.log(Level.ERROR, e.getMessage());
       }

        return new OrderListCommand(new OrderServiceImpl()).execute(request);
    }
}
