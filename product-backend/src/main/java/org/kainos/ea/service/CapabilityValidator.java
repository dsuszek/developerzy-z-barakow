package org.kainos.ea.service;

import org.kainos.ea.model.CapabilityRequest;

public class CapabilityValidator {
    public String isCapabilityValid(CapabilityRequest capability) {

        if (capability.getCapabilityName().length() > 50) {
            return "Capabilitynamelongerthan50chars";
        }
        if (capability.getLeadName().length() > 50) {
            return "Capabilityleadnamelongerthan50chars";
        }
        if (capability.getCapabilityLeadPicture().length() > Integer.MAX_VALUE - 1) {
            return "Leadpicturetoobig";
        }
        return null;
    }
}

