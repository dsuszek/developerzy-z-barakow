package org.kainos.ea.service;

import org.kainos.ea.model.BandRequest;

public class BandValidator {

    public String isValidBand(BandRequest band) {

        if (band.getLevel() < 0 || band.getLevel() > 9){
            return "Band level out of boundaries 0-9";
        }

        return null;
    }
}
