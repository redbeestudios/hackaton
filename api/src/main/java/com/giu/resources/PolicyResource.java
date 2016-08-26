package com.giu.resources;

import com.giu.domain.Policy;
import com.giu.representation.PolicyRepresentation;
import com.giu.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by martin on 08/08/16.
 */
@RestController
public class PolicyResource {

    @Autowired
    private PolicyService policyService;

   @RequestMapping(value = "/policies", method = GET)
   public List<PolicyRepresentation> list() {
      return policyService.consultPolicy();
   }
}
