package com.ecowishlist.ReportAutomationTool.CustomerReport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/customer.report.api")
public class CustomerReportController {

    private final CustomerReportService customerReportService;

    CustomerReportController(CustomerReportService customerReportService)
    {
        this.customerReportService = customerReportService;
    }

    @GetMapping({"","/"})
    HashMap<String,String> getApiEndpoints()
    {
        HashMap<String,String> endpoints = new HashMap<>();
        endpoints.put("/getAllCust","To get list of customers in Json format");
        endpoints.put("/downloadAllCust","To download list of customers in CSV format");
        return endpoints;
    }

    @GetMapping("/getAllCust")
    List<Customer> getAllCustomers()
    {
        try
        {
            return customerReportService.getAllCustomers();
        }
        catch(Exception e) {
            throw new RuntimeException("Failed to fetch customers", e);
        }
    }

    @GetMapping("/downloadAllCust")
    ResponseEntity<byte[]> downloadAllCustomers()
    {
        return customerReportService.downloadAllCustomers();
    }

}
