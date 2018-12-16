/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.transaction.repo;

import io.github.evolutionThroughCraft.transaction.models.TransactionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dwin
 */
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    
      public List<TransactionEntity> findAllByCreditorIdOrDebitorId(Long creditorId, Long debitorId);      
    
    @Query(value=TransactionSql.FIND_CURRENT_BALANCE_FOR_ACCOUNT, nativeQuery = true)
    public Long findCurrentBalanceForAccount(@Param(TransactionSql.VAR_ACCOUNT_ID) Long actId);
    
    @Modifying
    @Transactional
    @Query(value=TransactionSql.REMOVE_ACCOUNT_AS_CREDITOR, nativeQuery = true)
    public int removeAccountAsCreditor(
                    @Param(TransactionSql.VAR_ACCOUNT_ID) Long actId,
                    @Param(TransactionSql.VAR_UPDATE_USER) String updateUser);
    
    @Modifying
    @Transactional
    @Query(value=TransactionSql.REMOVE_ACCOUNT_AS_DEBITOR, nativeQuery = true)
    public int removeAccountAsDebitor(
                    @Param(TransactionSql.VAR_ACCOUNT_ID) Long actId,
                    @Param(TransactionSql.VAR_UPDATE_USER) String updateUser);    

    @Modifying
    @Transactional
    @Query(value=TransactionSql.DELETE_ABANDONED_TRANSACTIONS, nativeQuery = true)
    public int removeAbandonedTransactions();    

}
