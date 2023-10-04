import { expect } from 'chai';
import sinon from 'sinon';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import Login from '../model/login.js';
import AuthService from '../service/authService.js';
import LoginValidator from '../service/loginValidator.js';
import logger from '../service/logger.js';

const mockAxios = new MockAdapter(axios);
const loginValidatorStub = sinon.stub(new LoginValidator());

const loginRequest: Login = {
  email: 'random@kainos.com',
  password: 'randomPassword',
};

const authService = new AuthService(loginValidatorStub);

describe('Login service', () => {
  before(() => {
    logger.silent();
  });
  after(() => {
    logger.unsilent();
  });

  describe('login', () => {
    it('when API is down expect exception to be thrown', async () => {
      const login:Login = loginRequest;
      mockAxios.onPost('/api/auth/login', login).reply(500);

      let exception: any;
      try {
        await authService.login(login);
      } catch (e) {
        exception = e as Error;
      } finally {
        expect(exception.message).to.equal('Could not login');
      }
    });
    it('when login is not found should return error message', async () => {
      const login:Login = loginRequest;
      mockAxios.onPost('/api/auth/login', login).reply(400);

      let exception: any;
      try {
        await authService.login(login);
      } catch (e) {
        exception = e as Error;
      } finally {
        expect(exception.message).to.equal('Could not login');
      }
    });
    it('when API is running expect login to be succesful', async () => {
      const login:Login = loginRequest;
      mockAxios.onPost('/api/auth/login/', login).reply(200, 'randomizedToken2123');
      const responseBody = await authService.login(login);
      expect(responseBody).to.deep.equal('randomizedToken2123');
    });
  });
});
