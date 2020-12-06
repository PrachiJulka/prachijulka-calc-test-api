package com.cal.test.api.xite.service;

import com.cal.test.api.xite.dto.CalculationResponseDTO;
import com.cal.test.api.xite.dto.CalculationResultResponseDTO;
import com.cal.test.api.xite.dto.NumericValueDTO;
import com.cal.test.api.xite.exception.CalculationExpressionException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CalculatorService {


    public CalculationResponseDTO calculateExpression(String expression, CalculationResponseDTO calculationResponseDTO) throws CalculationExpressionException {
        if (checkExpression(expression)) {
            CalculationResponseDTO calculationResponseNewDTO;
            if (calculationResponseDTO == null) {
                calculationResponseNewDTO = new CalculationResponseDTO();
            } else {
                calculationResponseNewDTO = calculationResponseDTO;
            }
            NumericValueDTO numericValueDTO = NumericValueDTO.setNumericValueFromExpression(expression);
            calculateExpressionAndSetResult(calculationResponseNewDTO, numericValueDTO);
            return calculationResponseNewDTO;
        } else {
            throw new CalculationExpressionException("Wrong expression");
        }
    }


    boolean checkExpression(String expression) throws CalculationExpressionException {
        Pattern pattern = Pattern.compile("^[-]{0,1}[0-9]+[*+xX/-][-]{0,1}[0-9]+$");
        Matcher matcher = pattern.matcher(expression.replaceAll(" ", ""));
        if (matcher.find()) {
            return true;
        } else {
            throw new CalculationExpressionException("Wrong expression syntax");
        }
    }

    void calculate(NumericValueDTO numericValueDTO, CalculationResultResponseDTO calculationResultResponseDTO) {
        String result = "";
        BigDecimal firstNo = new BigDecimal(numericValueDTO.getFirstNumber());
        BigDecimal secondNo = new BigDecimal(numericValueDTO.getSecondNumber());
        switch (numericValueDTO.getOperand()) {
            case '*':
            case 'x':
            case 'X':
                result = firstNo.multiply(secondNo).toString();
                break;
            case '/':
                result = firstNo.divide(secondNo, MathContext.DECIMAL128).toString();
                break;
            case '+':
                result = firstNo.add(secondNo).toString();
                break;
            case '-':
                result = firstNo.subtract(secondNo).toString();
                break;
        }
        calculationResultResponseDTO.setResult(result);
    }

    void checkTypeOfNumber(CalculationResultResponseDTO calculationResultResponseDTO) {
        checkForWholeAndNaturalNumber(calculationResultResponseDTO);
        if (calculationResultResponseDTO.isWholeNumber()) {
            calculationResultResponseDTO.setPositiveNumber(true);
        }
        if (calculationResultResponseDTO.isNaturalNumber() && new BigInteger(calculationResultResponseDTO.getResult()).isProbablePrime(1)) {
            calculationResultResponseDTO.setPrimeNumber(true);
        }
    }

    void checkForWholeAndNaturalNumber(CalculationResultResponseDTO calculationResultResponseDTO) {
        Pattern pattern = Pattern.compile("\\.");
        Matcher matcher = pattern.matcher(calculationResultResponseDTO.getResult());
        boolean matchFound = matcher.find();
        if (matchFound) {
            calculationResultResponseDTO.setWholeNumber(false);
            calculationResultResponseDTO.setNaturalNumber(false);
        } else {
            if (!(calculationResultResponseDTO.getResult().charAt(0) == '-')) {
                calculationResultResponseDTO.setWholeNumber(true);
                if ((new BigDecimal(calculationResultResponseDTO.getResult()).compareTo(new BigDecimal("1")) == -1)) {
                    calculationResultResponseDTO.setNaturalNumber(false);
                } else {
                    calculationResultResponseDTO.setNaturalNumber(true);
                }
            } else {
                calculationResultResponseDTO.setWholeNumber(false);
                calculationResultResponseDTO.setNaturalNumber(false);
                calculationResultResponseDTO.setNegativeNumber(true);
            }
        }
    }

    void calculateExpressionAndSetResult(CalculationResponseDTO calculationResponseNewDTO, NumericValueDTO numericValueDTO) {
        CalculationResultResponseDTO calculationResultResponseDTO = new CalculationResultResponseDTO();
        calculate(numericValueDTO, calculationResultResponseDTO);
        calculationResultResponseDTO.setExpression(numericValueDTO.getExpression());
        checkTypeOfNumber(calculationResultResponseDTO);
        if (calculationResponseNewDTO.getUsersListResponseDtos().size() > 0) {
            calculationResponseNewDTO.getUsersListResponseDtos().add(calculationResultResponseDTO);
        } else {
            List<CalculationResultResponseDTO> calculationResultResponseDTOS = new ArrayList<>();
            calculationResultResponseDTOS.add(calculationResultResponseDTO);
            calculationResponseNewDTO.setUsersListResponseDtos(calculationResultResponseDTOS);
        }
    }

}
