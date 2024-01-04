package com.hotelbooking.hotelbooking.modules.EmployeesManagement.controllers;

import com.hotelbooking.hotelbooking.exception.UserNotFoundException;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.User;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.UserDTO;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.services.EmployeeService;
import com.opencsv.CSVWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employees/export/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/Xlsx")
    public void exportEXCEL(HttpServletResponse response) throws IOException {
        List<UserDTO> employees = employeeService.getAll();

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("first Name");
        headerRow.createCell(1).setCellValue("last Name");
        headerRow.createCell(2).setCellValue("email");
        headerRow.createCell(3).setCellValue("role");

        int rowNum = 1;
        for (UserDTO employee : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employee.getFirstName());
            row.createCell(1).setCellValue(employee.getLastName());
            row.createCell(2).setCellValue(employee.getEmail());
            row.createCell(3).setCellValue(employee.getRole().toString());
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
    @GetMapping("/Csv")
    public void exportCSV(HttpServletResponse response) throws IOException {
        List<UserDTO> employees = employeeService.getAll();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=employees.csv");

        CSVWriter csvWriter = new CSVWriter(response.getWriter());

        csvWriter.writeNext(new String[]{"first Name", "last Name", "email", "role"});

        for (UserDTO employee : employees) {
            csvWriter.writeNext(new String[]{
                    String.valueOf(employee.getFirstName()),
                    employee.getLastName(),
                    employee.getEmail(),
                    String.valueOf(employee.getRole().toString())
            });
        }
        csvWriter.flush();
        csvWriter.close();
    }

}
