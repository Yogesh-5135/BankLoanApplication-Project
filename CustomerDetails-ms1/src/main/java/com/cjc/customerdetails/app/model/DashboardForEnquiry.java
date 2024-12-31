package com.cjc.customerdetails.app.model;

import lombok.Data;

@Data
public class DashboardForEnquiry {

    private int approvedCount;
    private int rejectedCount;
    private int pendingCount;
}
