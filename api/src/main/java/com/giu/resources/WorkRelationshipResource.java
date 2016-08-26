package com.giu.resources;

import com.giu.representation.WorkRelationshipRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by julian on 05/08/16.
 */
@RestController
public class WorkRelationshipResource {

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
    @RequestMapping(value = "/workrelationship",method = GET)
    public WorkRelationshipRepresentation get() {
        WorkRelationshipRepresentation result = new WorkRelationshipRepresentation();
        result.generateList();
        return result;
    }
}
