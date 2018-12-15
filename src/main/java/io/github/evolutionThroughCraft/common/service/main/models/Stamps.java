/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.service.common.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author dwin
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createTime", "updateTime"},
        allowGetters = true
)
@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class Stamps<U> {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    private Date createTime;
    
//    @CreatedBy // turn on when spring-security, see build note 2018.12.09:dwin357
    @Column(name = "create_user", nullable = false, updatable = false)
    private U createUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false)
    @LastModifiedDate
    private Date updateTime;    
    
//    @LastModifiedBy // turn on when spring-security, see build note 2018.12.09:dwin357
    @Column(name = "update_user", nullable = false)    
    private U updateUser;
}
