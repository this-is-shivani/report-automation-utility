package com.ecowishlist.ReportAutomationTool.CustomerReport;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CustomerReportControllerTest {

    @Autowired
    CustomerReportController controller;

    @Test
    void getApiEndpoints() {
        assertNotNull(controller.getApiEndpoints());
    }

    @Test
    void getAllCustomers() throws JsonProcessingException {
        assertNotNull(controller.getAllCustomers());
    }

    @Test
    void downloadAllCustomers() {
        assertNotNull(controller.downloadAllCustomers());
    }
}