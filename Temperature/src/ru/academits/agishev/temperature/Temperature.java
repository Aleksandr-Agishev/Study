package ru.academits.agishev.temperature;

import javax.swing.*;
import java.awt.*;

public class Temperature {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature application");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width / 4 + 200;
            int height = screenSize.height / 2;
            frame.setSize(width, height);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setTitle("Temperature convert");
            frame.setResizable(true);
            // Создал окно

            JPanel panel = new JPanel(new GridBagLayout());
            // Создал панель

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.NORTH; // Отцентровали
            constraints.fill = GridBagConstraints.BOTH; // Стратегия растяжения
            constraints.gridheight = 1; //Сколько ячеек занимает в высоту
            constraints.gridwidth = 3; //Сколько ячеек занимает в ширину
            constraints.gridx = 2; //Номер столбца позиции
            constraints.gridy = 1; //Номер строки позиции
            constraints.insets = new Insets(0, 40, 0, 40); //Задает отступы для компонента
            constraints.ipadx = 0; //Увеличивает размер на заданное количество пикселов
            constraints.ipady = 0; //Увеличивает размер на заданное количество пикселов
            constraints.weightx = 0.33; //Стратегия изменения размеров компонента в столбцах
            constraints.weighty = 0.16; //Стратегия изменения размеров компонента в строках

            JLabel label1 = new JLabel("Введите температуру");
            panel.add(label1, constraints);

            JTextField textField1 = new JTextField();
            constraints.anchor = GridBagConstraints.WEST; // Отцентровали
            constraints.gridheight = 3; //Сколько ячеек занимает в высоту
            constraints.gridwidth = 2; //Сколько ячеек занимает в ширину
            constraints.gridx = 1; //Номер столбца позиции
            constraints.gridy = 2; //Номер строки позиции
            constraints.weighty = 0.005; //Стратегия изменения размеров компонента в строках
            panel.add(textField1, constraints);


            JRadioButton celsiusUp = new JRadioButton("Цельсий");
            constraints.gridheight = 1; //Сколько ячеек занимает в высоту
            constraints.gridwidth = GridBagConstraints.REMAINDER; //Сколько ячеек занимает в ширину
            constraints.gridx = 3; //Номер столбца позиции
            panel.add(celsiusUp, constraints);

            JRadioButton kelvinUp = new JRadioButton("Кельвин");
            constraints.gridy = 3;
            panel.add(kelvinUp, constraints);

            JRadioButton fahrenheitUp = new JRadioButton("Фаренгейт");
            constraints.gridy = 4;
            panel.add(fahrenheitUp, constraints);

            ButtonGroup buttonGroupUp = new ButtonGroup();
            buttonGroupUp.add(celsiusUp);
            buttonGroupUp.add(kelvinUp);
            buttonGroupUp.add(fahrenheitUp);
            celsiusUp.setSelected(true);

            JLabel labelCenter = new JLabel("В какую шкалу перевезти?");
            constraints.anchor = GridBagConstraints.SOUTH; // Отцентровали
            constraints.gridx = 2; //Номер столбца позиции
            constraints.gridy = 5; //Номер строки позиции
            constraints.weighty = 0.16; //Стратегия изменения размеров компонента в строках
            panel.add(labelCenter, constraints);


            JRadioButton celsiusCenter = new JRadioButton("Цельсий");
            constraints.gridwidth = 1; //Сколько ячеек занимает в ширину
            constraints.gridx = 1; //Номер столбца позиции
            constraints.gridy = 6; //Номер строки позиции
            panel.add(celsiusCenter, constraints);

            JRadioButton kelvinCenter = new JRadioButton("Кельвин");
            constraints.gridx = 2; //Номер столбца позиции
            panel.add(kelvinCenter, constraints);

            JRadioButton fahrenheitCenter = new JRadioButton("Фаренгейт");
            constraints.gridx = 3;
            panel.add(fahrenheitCenter, constraints);

            ButtonGroup buttonGroupCenter = new ButtonGroup();
            buttonGroupCenter.add(celsiusCenter);
            buttonGroupCenter.add(kelvinCenter);
            buttonGroupCenter.add(fahrenheitCenter);
            celsiusCenter.setSelected(true);


            JButton button = new JButton("Перевод градусов");
            constraints.gridwidth = GridBagConstraints.REMAINDER; //Сколько ячеек занимает в ширину
            constraints.gridx = 1; //Номер столбца позиции
            constraints.gridy = 7; //Номер строки позиции
            panel.add(button, constraints);

            JLabel labelResult = new JLabel("Эквивалентная температура в градусах ");
            constraints.gridy = 8; //Номер строки позиции
            constraints.gridwidth = 3; //Сколько ячеек занимает в ширину
            panel.add(labelResult, constraints);
            frame.add(panel);

            // Реализация логики/////////////
            button.addActionListener(e -> {
                double absoluteTemperature = 0;
                double fahrenheitTemperature = 0;
                boolean checkNumber = false;
                try {
                    absoluteTemperature = Double.parseDouble(textField1.getText());
                    fahrenheitTemperature = absoluteTemperature;
                    button.setText("Перевод градусов");
                    checkNumber = true;
                } catch (NumberFormatException t) {
                    button.setText("Введено не число, повторите ввод");
                    labelResult.setText("Введено не число, повторите ввод");
                    //checkNumber = false;
                }

                if (celsiusUp.isSelected() && checkNumber) {
                    absoluteTemperature = absoluteTemperature + 273.15;
                }

                if (fahrenheitUp.isSelected() && checkNumber) {
                    absoluteTemperature = (absoluteTemperature - 32) * 5 / 9 + 273.15;
                }

                if (absoluteTemperature >= 0) {
                    if (celsiusCenter.isSelected() && checkNumber) {
                        labelResult.setText("Эквивалентная температура в градуса Цельсия = " + (absoluteTemperature - 273.15));
                    }
                    if (kelvinCenter.isSelected() && checkNumber) {
                        labelResult.setText("Эквивалентная температура в градуса Кельвина = " + absoluteTemperature);
                    }
                    if (fahrenheitCenter.isSelected() && checkNumber && fahrenheitUp.isSelected()) {
                        labelResult.setText("Эквивалентная температура в градуса Фаренгейта = " + fahrenheitTemperature);
                    } else if (fahrenheitCenter.isSelected() && checkNumber) {
                        labelResult.setText("Эквивалентная температура в градуса Фаренгейта = " + ((absoluteTemperature - 273.15) * 9 / 5 + 32));
                    }
                } else {
                    button.setText("Введена слишком низкая температура, повторите ввод");
                    labelResult.setText("Так холодно не бывает:)))))");
                }
            });
        });
    }
}
