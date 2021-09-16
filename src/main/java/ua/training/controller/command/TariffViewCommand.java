package ua.training.controller.command;

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
        return "/manager/ChangeTariff.jsp";
    }
}
