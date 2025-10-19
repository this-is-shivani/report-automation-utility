package com.ecowishlist.ReportAutomationTool.CustomerReport;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReportRepository extends MongoRepository<Customer, String> {
}
