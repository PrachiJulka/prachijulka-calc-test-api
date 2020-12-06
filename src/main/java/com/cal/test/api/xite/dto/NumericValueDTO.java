package com.cal.test.api.xite.dto;

public class NumericValueDTO {
    private String firstNumber;
    private String secondNumber;
    private char operand;
    private String expression;

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }

    public char getOperand() {
        return operand;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setOperand(char operand) {
        this.operand = operand;
    }

    public NumericValueDTO() {
    }

    public NumericValueDTO(String firstNumber, String secondNumber, char operand, String expression) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operand = operand;
        this.expression = expression;
    }


    public static NumericValueDTO setNumericValueFromExpression(String expression) {
        expression = expression.replaceAll(" ", "");
        int i = expression.charAt(0) == '-' ? 1 : 0;
        while (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
            i++;
        }
        return new NumericValueDTO(expression.substring(0, i), expression.substring(++i), expression.charAt(--i), expression);
    }
}
