package com.cjc.ledger_loan_ms5.serviceimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.ledger_loan_ms5.model.Ledger;
import com.cjc.ledger_loan_ms5.model.LoanApplication;
import com.cjc.ledger_loan_ms5.repoi.LedgerRepoI;
import com.cjc.ledger_loan_ms5.repoi.LoanAppRepoI;
import com.cjc.ledger_loan_ms5.servicei.LedgerServiceI;

@Service
public class LedgerServiceImpl implements LedgerServiceI {

    @Autowired
    private LedgerRepoI ldri;

    @Autowired
    private LoanAppRepoI lri;

    private static final String STATUS_PENDING = "Pending";
    private static final String STATUS_MISSED = "Missed";
    private static final String STATUS_PAID = "Paid";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void updateLedger(int loanid) {
        Optional<LoanApplication> loanOpt = lri.findById(loanid);
        
        if (!loanOpt.isPresent()) {
            throw new IllegalArgumentException("Loan application not found for ID: " + loanid);
        }

        LoanApplication loan = loanOpt.get();
        int tenureInMonths = loan.getSanctionLetter().getLoanTenureInMonth();

        // Initializing loan details
        double loanAmount = loan.getSanctionLetter().getLoanAmtSanctioned();
        double rateOfInterest = loan.getSanctionLetter().getRateOfInterest();
        double monthlyEMI = loan.getSanctionLetter().getMonthlyEmiAmount();
        double totalAmountWithInterest = loanAmount + (loanAmount * rateOfInterest * tenureInMonths / 1200.0);

        // Track the total and remaining balance
        double amountPaidTillDate = 0;
        double remainingAmount = totalAmountWithInterest;

        for (int i = 1; i <= tenureInMonths; i++) {
            Ledger ledger = new Ledger();
            ledger.setLedgerCreatedDate(java.sql.Date.valueOf(LocalDate.now()));
            ledger.setTotalLoanAmount(loanAmount);
            ledger.setTenure(tenureInMonths);
            ledger.setLoanStatus(STATUS_PENDING);

            ledger.setRateOfInterest(rateOfInterest);
            ledger.setMonthlyEMI(monthlyEMI);
            ledger.setPayableAmountWithInterest(totalAmountWithInterest);

            // Calculate the amount paid till the current month
            amountPaidTillDate = monthlyEMI * i;
            ledger.setAmountPaidTillDate(amountPaidTillDate);

            // Calculate the remaining amount
            remainingAmount = totalAmountWithInterest - amountPaidTillDate;
            ledger.setRemainingAmount(remainingAmount);

            // Adjust remaining balance for the final month to ensure it becomes 0
            if (i == tenureInMonths) {
                // In the last month, the EMI may overpay the loan, so we adjust it
                if (remainingAmount > 0) {
                    monthlyEMI = remainingAmount;  // Adjust the EMI for the final month to match the remaining amount
                    amountPaidTillDate = totalAmountWithInterest; // Total payment till date is the full amount with interest
                    remainingAmount = 0; // Final remaining balance should be 0
                }
            }

            // Calculate defaulter count (for simplicity, keeping it as 0)
            int defaulterCount = getDefaulterCount(i, loan);
            ledger.setDefaulterCount(defaulterCount);

            // Get EMI status for previous and current months
            String previousEmiStatus = getEmiStatus(i - 1, loan);
            ledger.setPreviousEmiStatus(previousEmiStatus);

            String currentMonthEmiStatus = getEmiStatus(i, loan);
            ledger.setCurrentMonthEmiStatus(currentMonthEmiStatus);

            // Calculate and set next EMI date start and end
            String nextEmiDateStart = getNextEmiDateStart(i);
            ledger.setNextEmiDateStart(nextEmiDateStart);

            String nextEmiDateEnd = getNextEmiDateEnd(i);
            ledger.setNextEmiDateEnd(nextEmiDateEnd);

            // Set loan end date
            String loanEndDate = calculateLoanEndDate(i, tenureInMonths);
            ledger.setLoanEndDate(loanEndDate);

            // Save the ledger entry
            ldri.save(ledger);
            loan.getLedger().add(ledger);
        }

        // Save the updated loan application
        lri.save(loan);
    }

    private int getDefaulterCount(int monthIndex, LoanApplication loan) {
        int missedPayments = 0;
        for (int i = 0; i <= monthIndex; i++) {
            if (isPaymentMissed(i, loan)) {
                missedPayments++;
            }
        }
        return missedPayments;
    }

    private boolean isPaymentMissed(int monthIndex, LoanApplication loan) {
        return false;  // Simulating that no payment is missed (you can modify this with actual logic)
    }

    private String getEmiStatus(int monthIndex, LoanApplication loan) {
        if (monthIndex < 0) {
            return "N/A";
        }
        
        if (isPaymentMissed(monthIndex, loan)) {
            return STATUS_MISSED;
        } else {
            return STATUS_PAID;
        }
    }

    private String getNextEmiDateStart(int monthIndex) {
        LocalDate nextEmiStartDate = LocalDate.now().plusMonths(monthIndex + 1);
        return nextEmiStartDate.format(DATE_FORMATTER);
    }

    private String getNextEmiDateEnd(int monthIndex) {
        LocalDate nextEmiEndDate = LocalDate.now().plusMonths(monthIndex + 1).plusDays(30);
        return nextEmiEndDate.format(DATE_FORMATTER);
    }

    private String calculateLoanEndDate(int monthIndex, int tenureInMonths) {
        LocalDate loanEndDate = LocalDate.now().plusMonths(tenureInMonths);
        return loanEndDate.format(DATE_FORMATTER);
    }
}
