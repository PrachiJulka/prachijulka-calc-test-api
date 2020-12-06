package com.cal.test.api.xite.dto;

public class CalculationResultResponseDTO {

    String expression;
    String result;
    boolean naturalNumber;
    boolean wholeNumber;
    boolean positiveNumber;
    boolean negativeNumber;
    boolean primeNumber;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isNaturalNumber() {
        return naturalNumber;
    }

    public void setNaturalNumber(boolean naturalNumber) {
        this.naturalNumber = naturalNumber;
    }

    public boolean isWholeNumber() {
        return wholeNumber;
    }

    public void setWholeNumber(boolean wholeNumber) {
        this.wholeNumber = wholeNumber;
    }

    public boolean isPositiveNumber() {
        return positiveNumber;
    }

    public void setPositiveNumber(boolean positiveNumber) {
        this.positiveNumber = positiveNumber;
    }

    public boolean isNegativeNumber() {
        return negativeNumber;
    }

    public void setNegativeNumber(boolean negativeNumber) {
        this.negativeNumber = negativeNumber;
    }

    public boolean isPrimeNumber() {
        return primeNumber;
    }

    public void setPrimeNumber(boolean primeNumber) {
        this.primeNumber = primeNumber;
    }

    public CalculationResultResponseDTO() {
    }

    public CalculationResultResponseDTO(String result) {
        this.result = result;
    }

    public CalculationResultResponseDTO(String expression, String result, boolean naturalNumber, boolean wholeNumber, boolean positiveNumber, boolean negativeNumber, boolean primeNumber) {
        this.expression = expression;
        this.result = result;
        this.naturalNumber = naturalNumber;
        this.wholeNumber = wholeNumber;
        this.positiveNumber = positiveNumber;
        this.negativeNumber = negativeNumber;
        this.primeNumber = primeNumber;
    }
}
