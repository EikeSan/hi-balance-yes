package com.hiBalanceYes.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Card  extends  Finantial{

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
