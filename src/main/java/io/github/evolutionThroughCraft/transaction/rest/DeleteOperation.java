/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.transaction.rest;

import io.github.evolutionThroughCraft.transaction.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dwin
 */
@Component
public class DeleteOperation {
    
    private static final String SYSTEM_ID = "del-op";
    
    @Autowired
    private TransactionRepository transactionRepo;

    public void perform(Long accountId) {
        transactionRepo.removeAccountAsDebitor(accountId, SYSTEM_ID);
        transactionRepo.removeAccountAsCreditor(accountId, SYSTEM_ID);
        transactionRepo.removeAbandonedTransactions();
    }
}
