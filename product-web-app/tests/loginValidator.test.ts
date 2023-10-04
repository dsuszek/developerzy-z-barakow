import { expect } from 'chai';
import Login from '../model/login.js';
import LoginValidator from '../service/loginValidator.js';

const loginValidator = new LoginValidator();

describe('Login Validator', () => {
  describe('validateLogin', () => {
    it('when login is valid return null', async () => {
      const validLogin: Login = {
        email: 'random@kainos.com',
        password: 'randomPassword',
      };
      const validationError = loginValidator.validateLogin(validLogin);

      expect(validationError).to.equal(null);
    });
    it('when login is valid return error message', async () => {
      const invalidLogin: Login = {
        email: 'random_131231311212412342342423233414331@kainos.com',
        password: 'randomPassword',
      };
      const validationError = loginValidator.validateLogin(invalidLogin);
      expect(validationError).to.equal("Email can't be longer than 50 characters");
    });
  });
});
