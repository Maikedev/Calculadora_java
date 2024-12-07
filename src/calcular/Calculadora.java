package calcular;

public class Calculadora {
    public String num1;
    public String num2;
    public String operador;

    public Calculadora(String num1, String num2, String operador) {
        this.num1 = num1;
        this.num2 = num2;
        this.operador = operador;
    }

    public double transformaString() {
        double num1 = Double.parseDouble(this.num1);
        double num2 = Double.parseDouble(this.num2);

        if (this.operador.equals("+")) {
            return num1 + num2;
        } else if (this.operador.equals("-")) {
            return num1 - num2;
        } else if (this.operador.equals("*")) {
            return num1 * num2;
        } else if (this.operador.equals("/")) {
            if (num2 != 0) {
                return num1 / num2;
            } else {
                throw new ArithmeticException("Divisão por zero");
            }
        } else {
            return 0;
        }
    }
}
