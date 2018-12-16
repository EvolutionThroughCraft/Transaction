/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.transaction.models;

import io.github.evolutionThroughCraft.common.service.main.models.StampsTableColumns;

/**
 *
 * @author dwin
 */
public interface TransactionTableColumns extends StampsTableColumns {
    
    public static final String TBL_TRANSACTIONS = "transactions";
    public static final String COL_PK_TRANSACTION_ID = "transaction_id";
    public static final String COL_AMOUNT = "amount";
    public static final String COL_FK_CREDITOR_ID = "creditor_id";
    public static final String COL_FK_DEBITOR_ID = "debitor_id";
}
