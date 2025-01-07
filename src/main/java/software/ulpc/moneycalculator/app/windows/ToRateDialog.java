package software.ulpc.moneycalculator.app.windows;

import software.ulpc.moneycalculator.arquitecture.view.SelectCurrencyDialog;

import javax.swing.*;
import java.awt.*;

public class ToRateDialog extends JPanel {
    public static final Dimension dialogSize = new Dimension(320, 30);
    private final JTextField resultLabel;

    public ToRateDialog(SelectCurrencyDialog dialog) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));  // Organiza los componentes horizontalmente
        this.setBackground(MainFrame.backgroundGreen);

        // Agregar el dialogo de selección de moneda
        this.add((Component) dialog);

        // Etiqueta de resultados
        resultLabel = new JTextField("Here will appear the results");
        resultLabel.setPreferredSize(dialogSize);
        resultLabel.setMaximumSize(dialogSize);
        resultLabel.setEditable(false);
        resultLabel.setBackground(new Color(86, 222, 44));
        resultLabel.setForeground(Color.WHITE);
        this.add(resultLabel);
    }

    public JTextField resultLabel(){
        return resultLabel;
    }
}
