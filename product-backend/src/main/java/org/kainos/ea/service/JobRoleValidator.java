package org.kainos.ea.service;

import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.model.ProductRequest;

public class JobRoleValidator {

    public String isValidJobRole(JobRoleRequest jobRole) {

        if (jobRole.getName().length() > 50) {
            return "Length of name of job role greater than 50 characters";
        }

        if (jobRole.getDescription().length() > 3000) {
            return "Length of description of job role greater than 3000 characters";
        }

        if (jobRole.getLink().length() > 1000) {
            return "Length of link to job role greater than 3000 characters";
        }

        return null;
    }
}
