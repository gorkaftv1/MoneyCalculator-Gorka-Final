package software.ulpc.moneycalculator.app.windows;

import software.ulpc.moneycalculator.arquitecture.control.Command;
import software.ulpc.moneycalculator.arquitecture.model.Currency;
import software.ulpc.moneycalculator.arquitecture.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    public static final Color backgroundGreen = new Color(133, 175, 0);
    private final Map<String, Command> commands;
    private moneyDialog fromRateDialog;
    private ToRateDialog toRateDialog;
    private Component result;
    private final List<Currency> currencies;
    private ToSelectorDialog toSelectDialog;
    private FromSelectorDialog fromSelectDialog;

    public MainFrame(List<Currency> currencies){
        this.currencies = currencies;
        setTitle("Money Calculator");
        setIconImage(new ImageIcon("src/main/resources/icon-money-calculator.png").getImage());
        setSize(470, 170);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(backgroundGreen);
        fromRateDialog = createFromRateDialog();
        toRateDialog = createToRateDialog();
        add(contentPanel());
        add(resultbar(), BorderLayout.SOUTH);
        commands = new HashMap<>();
    }

    public MainFrame add(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    private Component resultbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(acceptButton());
        panel.add(result = resultLabel());
        panel.setBackground(backgroundGreen);
        return panel;
    }

    private Component resultLabel() {
        JLabel jLabel = new JLabel();
        return jLabel;
    }

    private Component acceptButton() {
        JButton jButton = new JButton("accept");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("getExchangeRate").execute();
                commands.get("getExchangedAmount").execute();
            }
        });
        jButton.setBackground(new Color(34, 139, 34));
        jButton.setForeground(Color.WHITE);
        return jButton;
    }

    private Component contentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add((Component) fromRateDialog);
        panel.add(toRateDialog);
        return panel;
    }

    private FromSelectorDialog createFromCombo(List<Currency> currencies) {
        return new FromSelectorDialog(currencies, this);
    }

    private ToSelectorDialog createToCombo(List<Currency> currencies) {
        return new ToSelectorDialog(currencies, this);
    }

    private FromRateDialog createFromRateDialog() {
        return new FromRateDialog(this.fromSelectDialog = createFromCombo(currencies));
    }

    private ToRateDialog createToRateDialog() {
        return new ToRateDialog(this.toSelectDialog = createToCombo(currencies));
    }

    public FromRateDialog fromRateDialog(){return (FromRateDialog) fromRateDialog;}
    public ToRateDialog ToRateDialog(){return toRateDialog;}

    public FromSelectorDialog fromSelectDialog() {
        return fromSelectDialog;
    }

    public ToSelectorDialog toSelectDialog() {
        return toSelectDialog;
    }
}
