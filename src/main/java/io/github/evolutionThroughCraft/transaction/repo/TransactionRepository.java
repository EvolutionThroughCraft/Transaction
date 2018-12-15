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

/**
 *
 * @author dwin
 */
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    
    @Query(
      value="SELECT * FROM transactions T WHERE T.creditor_id = :actId OR T.debitor_id = :actId")
    public List<TransactionEntity> findAllTransactionsForAccount(@Param("actId")Long actId);
    
    @Query(
      value="SELECT credits - debits FROM"
            + " (SELECT SUM(amount) credits FROM transactions WHERE creditor_id = :actId),"
            + " (SELECT SUM(amount) debits FROM transactions WHERE debitor_id = :actId)")
    public Long findCurrentBalanceForAccount(@Param("actId")Long actId);
    
    @Modifying
    @Query(value="UPDATE transactions T set T.creditor_id = NULL where creditor_id = :actId")
    public int removeAccountAsCreditor(@Param("actId")Long actId);
    
    @Modifying
    @Query(value="UPDATE transactions T set T.debitor_id = NULL where debitor_id = :actId")
    public int removeAccountAsDebitor(@Param("actId")Long actId);
    
    @Modifying
    @Query(value="DELETE FROM transactions T WHERE T.debitor_id = NULL AND T.creditor_id = NULL")
    public int removeAbandonedTransactions();
}
