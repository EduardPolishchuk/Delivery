package ua.training.controller.command.manager;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.dao.impl.JDBCCityDao;
import ua.training.model.entity.Tariff;
import ua.training.model.service.TariffService;
import ua.training.model.service.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ChangeTariffCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(JDBCCityDao.class);
    private TariffService tariffService;

    public ChangeTariffCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            float widthMulti = Float.parseFloat(request.getParameter("widthMulti"));
            float lengthMulti = Float.parseFloat(request.getParameter("lengthMulti"));
            float heightMulti = Float.parseFloat(request.getParameter("heightMulti"));
            float weightMulti = Float.parseFloat(request.getParameter("weightMulti"));
            float distanceMulti = Float.parseFloat(request.getParameter("distanceMulti"));
            float additional = Float.parseFloat(request.getParameter("additional"));
            Tariff tariff = new Tariff();
            tariff.setUahPerKilometerDistance(distanceMulti);
            tariff.setUahPerKilogramWeight(weightMulti);
            tariff.setUahPerMillimeterHeight(heightMulti);
            tariff.setUahPerMillimeterLength(lengthMulti);
            tariff.setUahPerMillimeterWidth(widthMulti);
            tariff.setAdditional(additional);
            tariff.setId(1);
            tariffService.updateTariff(tariff);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }

        return "redirect:/" + new TariffViewCommand(new TariffServiceImpl()).execute(request);
    }
}
