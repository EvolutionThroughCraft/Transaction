/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.common.arch.orchestrators;

import org.apache.log4j.Logger;


/**
 *
 * @author dwin
 */
public abstract class SimpleOperation<Arg, Rtn> implements Operation<Arg, Rtn> {
    
    private static final Logger scribe = Logger.getLogger(Operation.class);
    
    @Override
    public Rtn run(Arg arg) {
        debug("perform start");
        Long time = System.currentTimeMillis();
        Rtn output =  perform(arg);
        debug("perform finish time:"+ (System.currentTimeMillis() - time));
        return output;
    }
    
    public abstract Rtn perform(Arg arg);
    
    protected Logger getScribe() {
        return scribe;
    }
    
    protected void debug(String msg) {
        getScribe().debug(getClass()+"::"+msg);
    }
    
    protected void info(String msg) {
        getScribe().info(getClass()+"::"+msg);
    }
}
