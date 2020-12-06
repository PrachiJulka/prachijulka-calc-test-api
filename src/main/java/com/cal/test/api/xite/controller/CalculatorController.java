package com.cal.test.api.xite.controller;

import com.cal.test.api.xite.dto.CalculationResponseDTO;
import com.cal.test.api.xite.dto.ErrorResponseDTO;
import com.cal.test.api.xite.exception.CalculatorException;
import com.cal.test.api.xite.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CalculatorController {


    @Autowired
    CalculatorService calculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<Object> calculate(@RequestParam String expression, HttpSession session) {
        ErrorResponseDTO edto = new ErrorResponseDTO();
        CalculationResponseDTO calculationResponseDTO;
        try {
            calculationResponseDTO = calculatorService.calculateExpression(expression, (CalculationResponseDTO) session.getAttribute("results"));
            if (calculationResponseDTO == null) {
                throw new CalculatorException("Some Internal error occured");
            }
        } catch (Exception err) {
            edto.getMsg().add(err.getMessage());
            return ResponseEntity.badRequest().body(edto);
        }
        session.setAttribute("results", calculationResponseDTO);
        return ResponseEntity.ok().body(calculationResponseDTO);

    }


}
