import { expect } from 'chai';

import BandValidator from '../service/bandValidator.js';
import Band from '../model/band.js';
import logger from '../service/logger.js';

const bandValidator = new BandValidator();

describe('Band validator', () => {
  before(() => {
    logger.silent();
  });

  after(() => {
    logger.unsilent();
  });

  describe('validateBand', () => {
    it('expect too low level', () => {
      const band: Partial<Band> = {
        level: -1,
      };
    });
  });
});
