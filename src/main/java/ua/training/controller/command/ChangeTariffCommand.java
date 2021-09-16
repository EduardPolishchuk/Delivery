package ua.training.controller.command;

import ua.training.model.dao.TariffDao;
import ua.training.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class ChangeTariffCommand implements Command{

    private TariffService tariffService;

    public ChangeTariffCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "/manager/ChangeTariff.jsp";
    }
}
