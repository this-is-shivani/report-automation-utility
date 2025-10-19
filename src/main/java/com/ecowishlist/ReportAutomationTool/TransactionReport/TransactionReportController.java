package com.ecowishlist.ReportAutomationTool.TransactionReport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/TransactionReportAPI")
public class TransactionReportController {

    private final TransactionReportService transactionReportService;

    TransactionReportController(TransactionReportService transactionReportService)
    {
        this.transactionReportService = transactionReportService;
    }

    @GetMapping("")
    List<String> getApiEndpoints()
    {
        List<String> endpoints = List.of("/getAllTxn", "/downloadAllTxn");
        return endpoints;
    }

    @GetMapping("/getAllTxn")
    List<Transaction> displayAllTransactions()
    {
        return transactionReportService.displayAllTransactions();
    }

    @GetMapping("/downloadAllTxn")
    List<Transaction> downloadAllTransactions()
    {
        return transactionReportService.downloadAllTransactions();
    }

    @GetMapping("/getAllTxnByDate")
    List<Transaction> displayTransactionsByDate()
    {
        return transactionReportService.displayTransactionsByDate();
    }

    @GetMapping("/downloadAllTxnByDate")
    List<Transaction> downloadTransactionsByDate()
    {
        return transactionReportService.downloadTransactionsByDate();
    }
}
