package software.ulpc.moneycalculator.app.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import software.ulpc.moneycalculator.arquitecture.model.Currency;
import software.ulpc.moneycalculator.arquitecture.view.SelectCurrencyDialog;

import java.util.List;

public class FromSelectorDialog extends JComboBox<String> implements SelectCurrencyDialog {
    private final MainFrame owner;

    public FromSelectorDialog(List<Currency> currencies, MainFrame owner) {
        this.owner = owner;
        for(Currency currency : currencies){
            this.addItem(currency.code());
        }
        this.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                }
            }
        });
        this.setBackground(new Color(34, 139, 34));
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(70, 30));
        this.setMaximumSize(new Dimension(70, 30));
    }

    @Override
    public int getSelecteCurrency() {
        return this.getSelectedIndex();
    }
}
