package software.ulpc.moneycalculator.arquitecture.control;

import software.ulpc.moneycalculator.arquitecture.model.ExchangeRate;
import software.ulpc.moneycalculator.arquitecture.model.Money;
import software.ulpc.moneycalculator.arquitecture.view.moneyDialog;
import software.ulpc.moneycalculator.app.windows.ToRateDialog;

import javax.swing.*;
import java.io.IOException;

public class GetMoneyCommand implements Command{
    private final moneyDialog from;
    private final GetExchangeRateCommand exchangeCommand;
    private final JTextField result;
    private Money money;

    public GetMoneyCommand(moneyDialog from, ToRateDialog to, GetExchangeRateCommand exchangeRate) {
        this.from = from;
        this.exchangeCommand = exchangeRate;
        this.result = to.resultLabel();
    }

    @Override
    public void execute() {
        try {
            calculateMoney();
            setResultText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void calculateMoney() throws IOException {
        if(from.getRate().isEmpty()) return;
        ExchangeRate exchangeRate = exchangeCommand.getExchangeRate();
        double amount = Double.parseDouble(normalizeRateField());
        amount = exchangeRate.rate() * amount;
        money = new Money(amount, exchangeCommand.getFromCurrency());
    }

    private String normalizeRateField() {
        return from.getRate().trim().replace(",", ".");
    }

    private void setResultText() {
        if ((money == null || money.amount() <= 0)) {
            setWrongResult();
        } else {
            result.setText(String.format("%.2f", money.amount()));
        }
    }

    private void setWrongResult() {
        result.setText("Amount introduced is invalid, reintroduce another amount");
    }


}
