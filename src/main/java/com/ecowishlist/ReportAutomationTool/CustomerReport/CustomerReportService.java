package com.ecowishlist.ReportAutomationTool.CustomerReport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CustomerReportService {

    private final CustomerReportRepository customerReportRepository;
    private final RedisService redisService;

    public CustomerReportService(CustomerReportRepository customerReportRepository, RedisService redisService) {
        this.customerReportRepository = customerReportRepository;
        this.redisService = redisService;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = null;
        try {
            customers = redisService.getValue("customers", List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(customers != null) {
            return customers;
        }
        else {
            customers = customerReportRepository.findAll();
            redisService.setValue("customers", customers, 300L);
            return customers;
        }
    }

    public ResponseEntity<byte[]> downloadAllCustomers() {
        List<Customer> customers = getAllCustomers();

        try (StringWriter sw = new StringWriter()) {
            StatefulBeanToCsv<Customer> beanToCsv =
                    new StatefulBeanToCsvBuilder<Customer>(sw)
                            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER) // corrected method name
                            .build();
            beanToCsv.write(customers);
            byte[] csvBytes = sw.toString().getBytes(StandardCharsets.UTF_8);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"customers.csv\"")
                    .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                    .contentLength(csvBytes.length)
                    .body(csvBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }

}
