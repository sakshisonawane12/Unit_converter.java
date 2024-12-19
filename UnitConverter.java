import javax.swing.*;
import java.awt.event.*;

public class UnitConverter extends JFrame implements ActionListener {
    JButton convertButton;
    JTextField inputField;
    JTextField resultField;
    JComboBox<String> fromUnit;
    JComboBox<String> toUnit;

    public UnitConverter() {
        convertButton = new JButton("Convert");
        convertButton.setBounds(100, 300, 100, 30);
        convertButton.addActionListener(this);
        add(convertButton);

        inputField = new JTextField();
        inputField.setBounds(100, 100, 100, 30);
        add(inputField);

        resultField = new JTextField();
        resultField.setBounds(100, 200, 100, 30);
        resultField.setEditable(false); // Make it read-only
        add(resultField);

        fromUnit = new JComboBox<>(new String[] { "mm", "cm", "dm", "m", "dam", "hm", "km" });
        fromUnit.setBounds(250, 100, 100, 30);
        add(fromUnit);

        toUnit = new JComboBox<>(new String[] { "mm", "cm", "dm", "m", "dam", "hm", "km" });
        toUnit.setBounds(250, 200, 100, 30);
        add(toUnit);

        setLayout(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double value = Double.parseDouble(inputField.getText());
            String from = fromUnit.getSelectedItem().toString();
            String to = toUnit.getSelectedItem().toString();

            // Conversion logic
            double result = convert(value, from, to);

            resultField.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input");
        }
    }

    private double convert(double value, String from, String to) {
        
        double factorFrom = getFactor(from);
        double factorTo = getFactor(to);
    
        
        return value * factorFrom / factorTo;
    }
    
    private double getFactor(String unit) {
        switch (unit) {
            case "mm": return 1;
            case "cm": return 10;
            case "dm": return 100;
            case "m": return 1000;
            case "dam": return 10000;
            case "hm": return 100000;
            case "km": return 1000000;
            default: throw new IllegalArgumentException("Invalid unit: " + unit);
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UnitConverter();
        });
    }
}
