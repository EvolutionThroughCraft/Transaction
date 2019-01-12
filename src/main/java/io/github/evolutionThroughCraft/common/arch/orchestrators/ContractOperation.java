/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.common.arch.orchestrators;

import io.github.evolutionThroughCraft.common.arch.contracts.Contract;

/**
 *
 * @author dwin
 */
public abstract class ContractOperation<Arg, Rtn, Con extends Contract<Arg>> extends SimpleOperation<Arg, Rtn> {
    
    @Override
    public Rtn run(Arg arg) {
        debug("contract start");
        Long time = System.currentTimeMillis();        
        try {
            getContract().validate(arg);            
        } catch (Exception e) {
            debug("contract finish(fail) time:"+(System.currentTimeMillis() - time));
            throw e;
        }
        debug("contract finish(suc) time:"+(System.currentTimeMillis() - time));
        
        return super.run(arg);
    }
    
    public abstract Con getContract();
}
