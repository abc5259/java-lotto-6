package lotto.controller;

import java.util.HashMap;
import java.util.Map;
import lotto.controller.user.UserBuyLottoController;
import lotto.controller.user.UserSaveController;
import lotto.repository.UserRepository;
import lotto.service.LottoService;
import lotto.service.UserService;
import lotto.view.View;

public class FrontController {
    private final Map<Command, Controller> controllerMap = new HashMap<>();

    public FrontController() {
        controllerMap.put(Command.INPUT_PURCHASE_AMOUNT, new UserSaveController(new UserService(new UserRepository(),
                new LottoService())));
        controllerMap.put(Command.BUY_LOTTO, new UserBuyLottoController(new UserService(new UserRepository(), new LottoService())));
    }

    public void service(Command command, String input) {

        Controller controller = controllerMap.get(command);
        if(controller == null) {
            return;
        }

        View view = controller.process(input);
        view.render();
    }
}
