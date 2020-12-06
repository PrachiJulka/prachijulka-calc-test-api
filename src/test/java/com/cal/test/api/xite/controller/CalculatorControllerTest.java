
package com.cal.test.api.xite.controller;

import com.cal.test.api.xite.dto.CalculationResponseDTO;
import com.cal.test.api.xite.dto.CalculationResultResponseDTO;
import com.cal.test.api.xite.dto.ErrorResponseDTO;
import com.cal.test.api.xite.exception.CalculatorException;
import com.cal.test.api.xite.service.CalculatorService;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class CalculatorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    CalculatorController calculatorController;

    @Mock
    ErrorResponseDTO errorResponseDTO;

    @Mock
    CalculationResponseDTO calculationResponseDTO;

    @Mock
    CalculatorService calculatorService;


    @Test
    void calculateExpressionAndGiveResult() throws Exception {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        CalculationResponseDTO calculationResponseDTO = new CalculationResponseDTO();
        CalculationResultResponseDTO calculationResultResponseDTO = new CalculationResultResponseDTO("3*3", "9", true, true, true, false, false);
        calculationResponseDTO.getUsersListResponseDtos().add(calculationResultResponseDTO);
        Mockito.when(calculatorService.calculateExpression("3*3", calculationResponseDTO)).thenReturn(calculationResponseDTO);
        calculatorController.calculate("3*3", mockHttpServletRequest.getSession());
        Assert.assertEquals(calculationResponseDTO.getUsersListResponseDtos().get(0).getResult(), "9");

    }

}