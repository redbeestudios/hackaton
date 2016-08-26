package com.giu.service;

import com.giu.converter.PolicyConverter;
import com.giu.domain.Policy;
import com.giu.domain.PolicyRepo;
import com.giu.representation.PolicyRepresentation;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by martin on 08/08/16.
 */

@Service
public class PolicyService {

    private PolicyConverter converter;
    private PolicyRepo policyRepo;

    @Autowired
    public PolicyService(PolicyRepo policyRepo, PolicyConverter converter){
        this.policyRepo = policyRepo;
        this.converter = converter;
    }


    public List<PolicyRepresentation> consultPolicy () {
        return converter.convert(policyRepo.findAll());
    }
}

