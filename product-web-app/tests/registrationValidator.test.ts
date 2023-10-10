import { expect } from 'chai';

import Registration from '../model/registration.js';
import logger from '../service/logger.js';
import RegistrationValidator from '../service/registrationValidator.js';

const registrationValidator = new RegistrationValidator();

describe('Registration validator', () => {
  before(() => {
    logger.silent();
  });

  after(() => {
    logger.unsilent();
  });

  describe('validateRegistration', async () => {
    it('expect email address without @kainos.com domain', () => {
      const registration: Registration = {
        email: 'ferngj@mail.com',
        password: 'kjnjkhHGUYr@$@#w@$@',
        roleId: 1,
      };

      expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal(
        'Email does not have @kainos.com domain',
      );
    });

    it('expect too long email address', () => {
      const registration: Registration = {
        email: 'nfjhweiufghwiughiuhwfbjbhebvsivhiweufghewiufhweiufhweuifewjcjkwehiufhweiufhweiufhwfhwfwe@kainos.com',
        password: 'kjnjkhHGUYr@$@#w@$@',
        roleId: 1,
      };

      expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal(
        'Email address should have 50 characters at the maximum',
      );
    });

    it('expect password without special character', () => {
      const registration: Registration = {
        email: 'test@kainos.com',
        password: 'kjGDGEGSdvvwef',
        roleId: 1,
      };

      expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal(
        'Password should contain at least one special character',
      );
    });

    it('expect password without uppercase letter', () => {
      const registration: Registration = {
        email: 'test@kainos.com',
        password: 'kj%$#%%#ff',
        roleId: 1,
      };

      expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal(
        'Password should contain at least one uppercase letter',
      );
    });

    it('expect password without lowercase letter', () => {
      const registration: Registration = {
        email: 'test@kainos.com',
        password: 'NJNIF&YF*Y*FWGEFUYF^&WF',
        roleId: 1,
      };

      expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal(
        'Password should contain at least one lowercase letter',
      );
    });

    it('expect too short password', () => {
      const registration: Registration = {
        email: 'test@kainos.com',
        password: 'kjF#ef',
        roleId: 1,
      };

      expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal(
        'Password should contain at least 9 characters',
      );
    });

    it('expect too long password', () => {
      const registration: Registration = {
        email: 'test@kainos.com',
        password: 'kjfenfiuehwfiuwehuyewgfhewhg&*$@%$@$%@$#%@%#$$@jbjbhbefjbjBHFUHEWIFUHEIUFKHEIUFEKFEF#ef',
        roleId: 1,
      };

      expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal(
        'Password should contain 50 characters at the maximum',
      );
    });
  });
});
