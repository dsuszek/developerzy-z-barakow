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

  describe('when band level is too low should return error message', async () => {
    it('expect too low level', () => {
      const band: Band = {
        id: 1,
        name: 'Trainee',
        level: -1,
      };

      expect(bandValidator.validateBand(band as Band)).to.be.equal('Level of band should be greater than 0!');
    });
  });
});
