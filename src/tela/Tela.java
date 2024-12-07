package tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import calcular.Calculadora;

public class Tela extends JFrame {
    private JTextField caixa;
    private String num1;
    private String num2;
    private String operador;
    private boolean novoNumero;

    public Tela() {
        setSize(300, 500);
        setTitle("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(null);

        caixaTexto();
        botoes();

        num1 = "";
        num2 = "";
        operador = "";
        novoNumero = true;

        setVisible(true);
    }

    public void caixaTexto() {
        caixa = new JTextField("0");
        caixa.setBounds(10, 30, 280, 80);
        caixa.setFont(new Font("Arial", Font.BOLD, 40));
        caixa.setHorizontalAlignment(JTextField.LEFT);
        caixa.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(caixa);
    }

    public void botoes() {
        // Definindo a posição dos botões
        int[][] posicoesBotoes = {
                {20, 200}, {80, 200}, {140, 200}, {200, 200},
                {20, 250}, {80, 250}, {140, 250}, {200, 250},
                {20, 300}, {80, 300}, {140, 300}, {200, 300},
                {80, 350}, {20, 350}, {140, 350}, {200, 350},
                {200, 400}
        };

        String[] botoesTexto = {"7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"};

        int indicePosicao = 0;
        for (String texto : botoesTexto) {
            JButton botao = new JButton(texto);
            int[] posicao = posicoesBotoes[indicePosicao];
            botao.setBounds(posicao[0], posicao[1], 50, 40);

            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String textoBotao = ((JButton) e.getSource()).getText();

                    if (textoBotao.matches("\\d|\\.")) {
                        if (novoNumero) {
                            caixa.setText(textoBotao);
                            novoNumero = false;
                        } else {
                            caixa.setText(caixa.getText() + textoBotao);
                        }
                    } else if (textoBotao.matches("[+\\-\\*\\/]")) {
                        if (!num1.isEmpty()) {
                            num1 = caixa.getText();
                            operador = textoBotao;
                            caixa.setText("");
                        } else {
                            num1 = caixa.getText();
                            operador = textoBotao;
                            novoNumero = true;
                        }
                    } else if (textoBotao.equals("=")) {
                        if (!num1.isEmpty() && !caixa.getText().isEmpty()) {
                            num2 = caixa.getText();
                            double resultado = calcular(num1, operador, num2);
                            caixa.setText(String.valueOf(resultado));
                            num1 = "";
                            operador = "";
                            num2 = "";
                            novoNumero = true;
                        }
                    } else if (textoBotao.equals("C")) {
                        caixa.setText("0");
                        num1 = "";
                        operador = "";
                        num2 = "";
                        novoNumero = true;
                    }
                }
            });

            add(botao);
            indicePosicao++;
        }
    }

    private double calcular(String num1, String operador, String num2) {
        Calculadora calc = new Calculadora(num1, num2, operador);
        try {
            return calc.transformaString();
        } catch (ArithmeticException e) {
            caixa.setText("Erro");
            return 0;
        }
    }
}
