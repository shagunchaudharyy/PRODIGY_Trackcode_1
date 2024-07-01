import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI {
    private JFrame frame;
    private JComboBox<String> scaleComboBox;
    private JTextField temperatureTextField;
    private JButton convertButton;
    private JTextArea resultTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TemperatureConverterGUI().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel scaleLabel = new JLabel("Choose a temperature scale:");
        scaleComboBox = new JComboBox<>(new String[]{"Celsius (C)", "Fahrenheit (F)", "Kelvin (K)"});

        JLabel temperatureLabel = new JLabel("Enter the temperature value:");
        temperatureTextField = new JTextField(10);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());

        resultTextArea = new JTextArea(10, 20);
        resultTextArea.setEditable(false);

        frame.add(scaleLabel);
        frame.add(scaleComboBox);
        frame.add(temperatureLabel);
        frame.add(temperatureTextField);
        frame.add(convertButton);
        frame.add(new JScrollPane(resultTextArea));

        frame.pack();
        frame.setVisible(true);
    }

    private class ConvertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String scale = (String) scaleComboBox.getSelectedItem();
            double temperature = Double.parseDouble(temperatureTextField.getText());

            double fahrenheit = 0, celsius = 0, kelvin = 0;

            switch (scale) {
                case "Celsius (C)":
                    fahrenheit = celsiusToFahrenheit(temperature);
                    kelvin = celsiusToKelvin(temperature);
                    resultTextArea.setText(temperature + " degrees Celsius is equal to:\n" +
                            fahrenheit + " degrees Fahrenheit\n" +
                            kelvin + " Kelvin");
                    break;
                case "Fahrenheit (F)":
                    celsius = fahrenheitToCelsius(temperature);
                    kelvin = fahrenheitToKelvin(temperature);
                    resultTextArea.setText(temperature + " degrees Fahrenheit is equal to:\n" +
                            celsius + " degrees Celsius\n" +
                            kelvin + " Kelvin");
                    break;
                case "Kelvin (K)":
                    celsius = kelvinToCelsius(temperature);
                    fahrenheit = kelvinToFahrenheit(temperature);
                    resultTextArea.setText(temperature + " Kelvin is equal to:\n" +
                            celsius + " degrees Celsius\n" +
                            fahrenheit + " degrees Fahrenheit");
                    break;
            }
        }
    }

    // Conversion functions:

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9 + 273.15;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9 / 5 + 32;
    }
}