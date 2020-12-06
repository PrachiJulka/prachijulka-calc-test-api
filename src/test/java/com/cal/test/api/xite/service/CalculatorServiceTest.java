package com.cal.test.api.xite.service;

import com.cal.test.api.xite.dto.CalculationResponseDTO;
import com.cal.test.api.xite.dto.CalculationResultResponseDTO;
import com.cal.test.api.xite.dto.NumericValueDTO;
import com.cal.test.api.xite.exception.CalculationExpressionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @InjectMocks
    CalculatorService calculatorService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void expressionShouldContainOnlyTwoOperands() throws CalculationExpressionException {
        boolean isValid = calculatorService.checkExpression("3*2");
        assertTrue(isValid);
    }

    @Test
    void exceptionShouldBeThrownOnMultipleOperatorsAndMultipleOperands() {
        Exception exception = assertThrows(CalculationExpressionException.class, () -> {
            calculatorService.checkExpression("3*3*2");
        });
        String expectedMessage = "Wrong expression syntax";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void acceptWhitespacesInExpression() throws CalculationExpressionException {
        boolean isValid = calculatorService.checkExpression(" 2 * 2 ");
        assertTrue(isValid);
    }

    @Test
    void acceptNegativeNumberOperands() throws CalculationExpressionException {
        boolean isValid = calculatorService.checkExpression("-2 - 2");
        assertTrue(isValid);
    }

    @Test
    void giveArithematicExceptionOnDividingByZero() {
        NumericValueDTO numericValueDTO = new NumericValueDTO("1", "0", '/', "1/2");

        assertThrows(ArithmeticException.class, () -> {
            calculatorService.calculate(numericValueDTO, new CalculationResultResponseDTO());
        });
    }

    @Test
    void performMultiplicationOnPassingXoperator() {
        NumericValueDTO numericValueDTO = new NumericValueDTO("1", "2", 'X', "1*2");
        CalculationResultResponseDTO calculationResultResponseDTO = new CalculationResultResponseDTO();
        calculatorService.calculate(numericValueDTO, calculationResultResponseDTO);
        assertEquals(calculationResultResponseDTO.getResult(), "2");
    }

    @Test
    void performCalculationOnNegativeOperands() {
        NumericValueDTO numericValueDTO = new NumericValueDTO("-1", "2", '+', "-1+2");
        CalculationResultResponseDTO calculationResultResponseDTO = new CalculationResultResponseDTO();
        calculatorService.calculate(numericValueDTO, calculationResultResponseDTO);
        assertEquals(calculationResultResponseDTO.getResult(), "1");
    }

    @ParameterizedTest
    @MethodSource("setListOfCalculationResultResponseDTO")
    void checkResultIsWholeNumber(CalculationResultResponseDTO calculationResultResponseDTO) {
        calculatorService.checkForWholeAndNaturalNumber(calculationResultResponseDTO);
        assertTrue(calculationResultResponseDTO.isWholeNumber());
    }

    private static Stream setListOfCalculationResultResponseDTO() {
        return Stream.of(
                Arguments.of(new CalculationResultResponseDTO("3456789")),
                Arguments.of(new CalculationResultResponseDTO("0")),
                Arguments.of(new CalculationResultResponseDTO("090909090"))
        );
    }

    @Test
    void checkResultIsNaturalNumber() {
        CalculationResultResponseDTO calculationResultResponseDTO = new CalculationResultResponseDTO("1234");
        calculatorService.checkForWholeAndNaturalNumber(calculationResultResponseDTO);
        assertTrue(calculationResultResponseDTO.isNaturalNumber());
    }

    @Test
    void resultIsWholeNumberButNotNatural() {
        CalculationResultResponseDTO calculationResultResponseDTO = new CalculationResultResponseDTO("000000");
        calculatorService.checkForWholeAndNaturalNumber(calculationResultResponseDTO);
        assertFalse(calculationResultResponseDTO.isNaturalNumber());
        assertTrue(calculationResultResponseDTO.isWholeNumber());
    }

    @ParameterizedTest
    @MethodSource("listToCheckWholeAndNaturalNumber")
    void decimalAndNegativeNumbersAreNotWholeNumbersAndAlsoNotNaturalNumber(CalculationResultResponseDTO calculationResultResponseDTO) {
        calculatorService.checkForWholeAndNaturalNumber(calculationResultResponseDTO);
        assertFalse(calculationResultResponseDTO.isNaturalNumber());
        assertFalse(calculationResultResponseDTO.isWholeNumber());
    }

    private static Stream listToCheckWholeAndNaturalNumber() {
        return Stream.of(
                Arguments.of(new CalculationResultResponseDTO("10.0000")),
                Arguments.of(new CalculationResultResponseDTO("-1.87878")),
                Arguments.of(new CalculationResultResponseDTO("-9898"))
        );
    }
}