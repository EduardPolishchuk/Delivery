package ua.training.controller.command.user;

import ua.training.controller.command.Command;
import ua.training.model.entity.Receipt;
import ua.training.model.entity.User;
import ua.training.model.service.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserReceiptsCommand implements Command {

    private final ReceiptService receiptService;

    public UserReceiptsCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        boolean paid = "1".equals(request.getParameter("paid"));
        User user = (User) request.getSession().getAttribute("userProfile");
        List<Receipt> list = receiptService.findUserReceipts(user,paid);
        request.getSession().setAttribute("userReceipts", list);
        return "/user/userReceipts.jsp";
    }
}
