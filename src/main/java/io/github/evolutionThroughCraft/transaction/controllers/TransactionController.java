/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.transaction.controllers;

import io.github.evolutionThroughCraft.common.service.main.api.Balance;
import io.github.evolutionThroughCraft.common.service.main.api.pojo.BalancePojo;
import io.github.evolutionThroughCraft.common.service.main.routes.TransactionRoutes;
import io.github.evolutionThroughCraft.common.service.main.utils.ResourceUtility;
import io.github.evolutionThroughCraft.transaction.models.TransactionEntity;
import io.github.evolutionThroughCraft.transaction.repo.TransactionRepository;
import io.github.evolutionThroughCraft.transaction.rest.DeleteOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dwin
 */
@RestController
@RequestMapping
public class TransactionController implements TransactionRoutes {
    
    @Autowired
    private TransactionRepository transactionRepo;
    
    @Autowired
    private DeleteOperation deleteOperation;
    
    @GetMapping(GET_TRANSACTIONS_PATH)
    public List<TransactionEntity> getTransactions(@PathVariable(ACCOUNT_ID_VAR) Long id) {
        return transactionRepo.findAllByCreditorIdOrDebitorId(id, id);
    }
    
    @GetMapping(GET_BALANCE_PATH)
    public Balance getBalance(@PathVariable(ACCOUNT_ID_VAR) Long id) {
        BalancePojo pojo = new BalancePojo();
        pojo.setAmount(transactionRepo.findCurrentBalanceForAccount(id));
        return pojo;
    }   
    
    @GetMapping(GET_TEST_PATH)
    public List<TransactionEntity> testFetch() {
        return transactionRepo.findAll();
    }
    
    @PostMapping(POST_TRANSACTIONS_PATH)
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionEntity createTransaction(@Valid @RequestBody TransactionEntity transaction) {
        ResourceUtility.ensureResource(transaction);
        return transactionRepo.save(transaction);
    }
    
    @DeleteMapping(DELETE_TRANSACTIONS_PATH)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable(ACCOUNT_ID_VAR) Long id) {
//        transactionRepo.removeAccountAsDebitor(id);
//        transactionRepo.removeAccountAsCreditor(id);
//        transactionRepo.removeAbandonedTransactions();

        deleteOperation.perform(id);
        
    }
}
