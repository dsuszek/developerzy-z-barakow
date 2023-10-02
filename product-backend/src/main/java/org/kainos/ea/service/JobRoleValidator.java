package org.kainos.ea.service;

import org.apache.commons.lang3.StringUtils;
import org.kainos.ea.model.JobRoleRequest;

public class JobRoleValidator {

    public String isValidJobRole(JobRoleRequest jobRole) {

        if (jobRole.getName().length() > 50) {
            return "Length of name of job role greater than 50 characters!";
        }

        if (jobRole.getDescription().length() > 3000) {
            return "Length of description of job role greater than 3000 characters!";
        }

        if (jobRole.getLink().length() > 1000) {
            return "Length of link to job role greater than 1000 characters!";
        }

        // checks if input consists only of whitespaces
        if (StringUtils.isBlank(jobRole.getName())) {
            return "Name of job role cannot be empty!";
        }

        if (StringUtils.isBlank(jobRole.getDescription())) {
            return "Description of job role cannot be empty!";
        }

        if (StringUtils.isBlank(jobRole.getLink())) {
            return "Link for job role cannot be empty!";
        }

        return null;
    }
}
