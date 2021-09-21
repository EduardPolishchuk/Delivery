package ua.training.controller.command.user;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entity.Order;
import ua.training.model.entity.Receipt;
import ua.training.model.entity.User;
import ua.training.model.service.ReceiptService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class PayReceiptCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(PayReceiptCommand.class);
    private ReceiptService receiptService;
    private UserService userService;

    public PayReceiptCommand(ReceiptService receiptService, UserService userService) {
        this.receiptService = receiptService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute("userProfile");
            long price = Long.parseLong(request.getParameter("price"));
            long orderId = Long.parseLong(request.getParameter("orderId"));
            long paidReceipt = Long.parseLong(request.getParameter("paidReceipt"));
            if (receiptService.userPaysReceipt(user, Receipt.builder()
                    .order(Order.builder().id(orderId).build())
                    .id(paidReceipt)
                    .price(BigDecimal.valueOf(price))
                    .build())) {
                request.getSession().setAttribute("userProfile", userService.findById(user.getId()).get());
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return "redirect:/user/userReceipts";
    }
}
