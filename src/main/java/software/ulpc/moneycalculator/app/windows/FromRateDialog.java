package software.ulpc.moneycalculator.app.windows;

import software.ulpc.moneycalculator.arquitecture.view.SelectCurrencyDialog;
import software.ulpc.moneycalculator.arquitecture.view.moneyDialog;

import javax.swing.*;
import java.awt.*;

import static software.ulpc.moneycalculator.app.windows.ToRateDialog.dialogSize;

public class FromRateDialog extends JPanel implements moneyDialog {
    private final JTextField textField;

    public FromRateDialog(SelectCurrencyDialog dialog) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));  // Puedes cambiar a FlowLayout si prefieres
        this.setBackground(MainFrame.backgroundGreen);
        this.add((Component) dialog);
        textField = new JTextField();
        textField.setPreferredSize(dialogSize);  // Tamaño preferido, pero flexible
        textField.setMaximumSize(dialogSize); // Ajuste máximo horizontal
        textField.setBackground(new Color(86, 222, 44));
        textField.setForeground(Color.WHITE);
        this.add(textField);
    }

    @Override
    public String getRate() {
        String text = textField.getText().replace(".", ",");
        try {
            Double.parseDouble(text.replace(",", "."));
            return text;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please introduce a valid quantity.", "Invalid input", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }
}
