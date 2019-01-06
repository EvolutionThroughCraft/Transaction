/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.evolutionThroughCraft.common.service.main.utils;

import io.github.evolutionThroughCraft.common.service.main.api.Account;
import io.github.evolutionThroughCraft.common.service.main.api.pojo.AccountPojo;
import io.github.evolutionThroughCraft.common.service.main.models.BadRequestException;
import io.github.evolutionThroughCraft.common.service.main.models.ResourceNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dwin
 */
public class ResourceUtility {
    
    public static <Resource> Resource ensureResource(Resource resource, String msg) {
        if(null == resource) {
            throw new ResourceNotFoundException(msg);
        }
        return resource;
    }
    
    public static <Resource> Resource ensureResource(Resource resource) {
        if(null == resource) {
            throw new ResourceNotFoundException();
        }
        return resource;
    }
    
    public static <Id> void ensureIdsEqual(Id firstId, Id secondId) {
        if(null == firstId || null == secondId || !firstId.equals(secondId)) {
            throw new BadRequestException();
        }
    }

    public static <T> T optionalGet(T[] maybe){
        if(maybe == null || 0 == maybe.length) {
            return null;
        } else {
            return maybe[0];
        }
    }
    
    public static <T> List<T> asList(T[] result) {
        return new LinkedList<T>(Arrays.asList(result));
    }
}
