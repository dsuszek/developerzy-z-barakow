package org.kainos.ea.service;

import org.kainos.ea.db.BandDao;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.exception.InvalidBandException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class BandService {
    private final static Logger logger = LoggerFactory.getLogger(BandService.class);
    private final BandDao bandDao;
    private final BandValidator bandValidator;

    public BandService(BandDao bandDao, BandValidator bandValidator) {
        this.bandDao = bandDao;
        this.bandValidator = bandValidator;
    }

    public Band createBand(BandRequest band) throws FailedToCreateBandException, InvalidBandException {
        try {
            String validation = bandValidator.isValidBand(band);

            if (validation != null) {
                throw new InvalidBandException(validation);
            }

            return bandDao.createBand(band);
        } catch (SQLException e) {
            logger.error("SQL exception! Error: {}", e.getMessage());

            throw new FailedToCreateBandException(e.getMessage());
        }
    }
}