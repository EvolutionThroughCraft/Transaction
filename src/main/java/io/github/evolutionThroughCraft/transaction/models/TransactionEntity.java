/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.transaction.models;

import io.github.evolutionThroughCraft.common.service.main.api.Transaction;
import io.github.evolutionThroughCraft.common.service.main.models.Stamps;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author dwin
 */
@Entity
@Table(name = TransactionTableColumns.TBL_TRANSACTIONS)
@Getter @Setter
public class TransactionEntity extends Stamps<String> implements Transaction {
    
    @Id @GeneratedValue(generator = "transaction_generator")
    @SequenceGenerator(
            name = "transaction_generator",
            sequenceName = "transaction_sequence",
            initialValue = 1000
    )
    @Column(name = TransactionTableColumns.COL_PK_TRANSACTION_ID)
    private Long transactionId;
    
    @Column(name = TransactionTableColumns.COL_AMOUNT, nullable = false)
    private Integer amount; // whole dollars only, no pennies
    
    @Column(name = TransactionTableColumns.COL_FK_CREDITOR_ID)
    private Long creditorId; // when null, reflects a user-withdrawl (or deleted counterparty act)

    @Column(name = TransactionTableColumns.COL_FK_DEBITOR_ID)
    private Long debitorId; // when null, reflects a user-deposit (or deleted counterparty act)
}
