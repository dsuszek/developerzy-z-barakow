import logger from '../service/logger.js';

describe('Registration validator', () => {
  before(() => {
    logger.silent();
  });

  after(() => {
    logger.unsilent();
  });

  describe('validateRegistration', () => {
    it('expect invalid email address', () => {});
  });
});
