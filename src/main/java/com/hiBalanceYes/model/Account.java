package com.hiBalanceYes.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Account extends Finantial {

    private String type;
    private String Bank;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }
}
