package ua.training.controller.command.manager;

import ua.training.controller.command.Command;
import ua.training.model.entity.Tariff;
import ua.training.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class TariffViewCommand implements Command {

    private TariffService tariffService;

    public TariffViewCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("tariff",
                tariffService.getTariff().orElse(new Tariff()));
        return "/manager/managerChangeTariff.jsp";
    }
}
