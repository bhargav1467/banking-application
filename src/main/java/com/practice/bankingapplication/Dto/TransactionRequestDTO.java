    package com.practice.bankingapplication.Dto;

    import org.antlr.v4.runtime.misc.NotNull;

    public class TransactionRequestDTO {
//        Validation Suggestion
//        @NotNull(message = "Amount is required")
//        @Positive(message = "Amount must be greater than zero")
        private Double amount;

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }

