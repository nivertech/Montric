package org.eurekaj.manager.datatypes;

import org.eurekaj.api.datatypes.Account;

/**
 * Created with IntelliJ IDEA.
 * User: joahaa
 * Date: 2/4/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagerAccount implements Account {
    private String accountName;
    private String accountType;

    public ManagerAccount() {
    }

    public ManagerAccount(String accountName, String accountType) {
        this.accountName = accountName;
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}