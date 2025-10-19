package com.ecowishlist.ReportAutomationTool.TransactionReport;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionReportService {

    List<Transaction> getAllTransactions()
    {
        List<Transaction> temp = new ArrayList<>();
        temp.add(new Transaction("TXN01","210000","Laxmi Devi"));
        return temp;
    }

    public List<Transaction> displayAllTransactions() {
        return this.getAllTransactions();
    }

    public List<Transaction> downloadAllTransactions() {
        return this.getAllTransactions();
    }

    public List<Transaction> displayTransactionsByDate() {
        return this.getAllTransactions();
    }

    public List<Transaction> downloadTransactionsByDate() {
        return this.getAllTransactions();
    }
}
