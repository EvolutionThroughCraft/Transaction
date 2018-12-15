/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.transaction.models;

import io.github.evolutionThroughCraft.common.service.main.api.Transaction;
import io.github.evolutionThroughCraft.common.service.main.models.Stamps;
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
@Table(name = "transactions")
@Getter @Setter
public class TransactionEntity extends Stamps<String> implements Transaction {
    
    @Id @GeneratedValue(generator = "transaction_generator")
    @SequenceGenerator(
            name = "transaction_generator",
            sequenceName = "transaction_sequence",
            initialValue = 1000
    )
    private Long transactionId;
    
    @NotBlank
    private Integer amount; // whole dollars only, no pennies
    
    private Long creditorId; // when null, reflects a user-withdrawl
    
    private Long debitorId; // when null, reflects a user-deposit
}
