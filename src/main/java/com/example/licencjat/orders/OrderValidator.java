package com.example.licencjat.orders;

import com.example.licencjat.exceptions.IllegalArgumentExceptions.NotEnoughResourceAmount;
import com.example.licencjat.orders.models.Order;
import com.example.licencjat.stuff.models.Stuff;

public class OrderValidator {

    public Stuff checkIfThereIsEnoughAmountAfterUpdateAndUpdateStuff(Order order, int newUpdatedValue) {
        var differenceInAmount = order.getAmount() - newUpdatedValue;
        var stuff = order.getStuff();

        checkIfThereIsEnoughAmount(stuff, -differenceInAmount);
        stuff.setQuantity(stuff.getQuantity() + differenceInAmount);

        return stuff;
    }

    public Stuff checkIfThereIsEnoughAmountWhileCreatingOrderAndUpdateStuff(Stuff stuff, int inputQuantity) {
        checkIfThereIsEnoughAmount(stuff, inputQuantity);
        stuff.setQuantity(stuff.getQuantity() - inputQuantity);

        return stuff;
    }

    private void checkIfThereIsEnoughAmount(Stuff stuff, int orderedAmount) {
        if (stuff.getQuantity() < orderedAmount) {
            throw new NotEnoughResourceAmount("There isn't enough " + stuff.getName() + ".");
        }
    }
}
