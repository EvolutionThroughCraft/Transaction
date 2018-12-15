/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.transaction.rest;

import io.github.evolutionThroughCraft.transaction.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dwin
 */
public class DeleteOperation {
    
    @Autowired
    private TransactionRepository transactionRepo;

    public void perform(Long accountId) {
        transactionRepo.removeAccountAsDebitor(accountId);
        transactionRepo.removeAccountAsCreditor(accountId);
        transactionRepo.removeAbandonedTransactions();
    }
}
