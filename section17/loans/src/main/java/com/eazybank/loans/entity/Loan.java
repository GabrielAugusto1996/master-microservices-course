package com.eazybank.loans.entity;

import com.eazybank.loans.dto.LoanDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "total_loan")
    private BigDecimal totalLoan;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    @Column(name = "outstanding_amount")
    private BigDecimal outstandingAmount;

    public LoanDto toDto() {
        return new LoanDto(loanNumber, mobileNumber, loanType, totalLoan, amountPaid, outstandingAmount);
    }
}
