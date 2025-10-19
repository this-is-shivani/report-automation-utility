package com.ecowishlist.ReportAutomationTool.CustomerReport;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Customer(String customerId, String customerPhone, String customerName) {
}
