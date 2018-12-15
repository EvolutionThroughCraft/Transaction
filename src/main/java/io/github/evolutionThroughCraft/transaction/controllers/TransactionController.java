/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.transaction.controllers;

import io.github.evolutionThroughCraft.common.service.main.utils.ResourceUtility;
import io.github.evolutionThroughCraft.transaction.models.TransactionEntity;
import io.github.evolutionThroughCraft.transaction.repo.TransactionRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionRepository transactionRepo;
    
    @GetMapping("/{accountId}")
    public List<TransactionEntity> getTransactions(@PathVariable("accountId") Long id) {
        return transactionRepo.findAllTransactionsForAccount(id);
    }
    
    @GetMapping("/{accountId}/balance")
    public Long getBalance(@PathVariable("accountId") Long id) {
        return transactionRepo.findCurrentBalanceForAccount(id);
    }    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionEntity createTransaction(@Valid @RequestBody TransactionEntity transaction) {
        ResourceUtility.ensureResource(transaction);
        return transactionRepo.save(transaction);
    }
}
