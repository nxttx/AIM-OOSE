package src.main.java.oose.ooad.quebble.adapter;

import src.main.java.oose.ooad.quebble.service.OnlineBetaalService;

public class OnlineBetaalServiceAdapter implements IBetaalServiceAdapter {
    @Override
    public boolean processTransaction(float amount) {
        OnlineBetaalService onlineBetaalService = new OnlineBetaalService();
        boolean status = onlineBetaalService.processTransaction(amount);

        return status;
    }
}
