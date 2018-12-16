/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.transaction.repo;

import io.github.evolutionThroughCraft.transaction.models.TransactionTableColumns;

/**
 *
 * @author dwin
 */
public interface TransactionSql extends TransactionTableColumns {
    
    public static final String VAR_ACCOUNT_ID = "actId";
    public static final String VAR_UPDATE_USER = "upUsr";
    
    public static final String FIND_CURRENT_BALANCE_FOR_ACCOUNT = 
            "select"
                + " (select coalesce(sum(" +COL_AMOUNT+ "), 0) from " +TBL_TRANSACTIONS+ " where "+COL_FK_CREDITOR_ID+ " = :"+VAR_ACCOUNT_ID+")"
                + " -"
                + " (select coalesce(sum(" +COL_AMOUNT+ "), 0) from " +TBL_TRANSACTIONS+ " where "+COL_FK_DEBITOR_ID+ " = :"+VAR_ACCOUNT_ID+")";
    
    public static final String REMOVE_ACCOUNT_AS_CREDITOR = 
            "update "+TBL_TRANSACTIONS
            + " set "+COL_FK_CREDITOR_ID+"=null, "+COL_UPDATE_USER+"= :"+VAR_UPDATE_USER+", "+COL_UPDATE_TIME+"=now()"
            + " where "+COL_FK_CREDITOR_ID+ " = :"+VAR_ACCOUNT_ID;
    
    public static final String REMOVE_ACCOUNT_AS_DEBITOR = 
        "update "+TBL_TRANSACTIONS
        + " set "+COL_FK_DEBITOR_ID+"=null, "+COL_UPDATE_USER+"= :"+VAR_UPDATE_USER+", "+COL_UPDATE_TIME+"=now()"
        + " where "+COL_FK_DEBITOR_ID+ " = :"+VAR_ACCOUNT_ID;

    public static final String DELETE_ABANDONED_TRANSACTIONS = 
        "delete from "+TBL_TRANSACTIONS
        + " where "+COL_FK_CREDITOR_ID+" is null"
        + " and "+COL_FK_DEBITOR_ID+" is null";
}