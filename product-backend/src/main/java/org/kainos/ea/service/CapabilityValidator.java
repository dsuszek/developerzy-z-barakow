package org.kainos.ea.service;

import org.kainos.ea.model.CapabilityRequest;

public class CapabilityValidator {
    public String isCapabilityValid(CapabilityRequest capability) {

        if (capability.getCapabilityName().length() > 50) {
            return "Capability name longer than 50 chars";
        }
        if (capability.getLeadName().length() > 50) {
            return "Capability lead name longer than 50 chars";
        }
        if (capability.getCapabilityLeadPicture().length() > Integer.MAX_VALUE - 1) {
            return "Lead picture too large";
        }
        if (capability.getMessage().length() > 3000) {
            return "Message too long";
        }
        return null;
    }
}

