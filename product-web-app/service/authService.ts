import axios from "axios";
import Registration from "../model/registration.js";
import RegistrationValidator from "./registrationValidator.js";
import Login from '../model/login.js';
import LoginValidator from './loginValidator.js';
import logger from './logger.js';
import { API } from '../common/constants.js';

export default class AuthService {
  private registrationValidator: RegistrationValidator;
  private loginValidator: LoginValidator;

  constructor(registrationValidator: RegistrationValidator, loginValidator: LoginValidator) {
    this.registrationValidator = registrationValidator;
    this.loginValidator = loginValidator;
  }

  async register(registration: Registration): Promise<string> {
    const validateError = this.registrationValidator.validateRegistration(registration);

    if (validateError) {
      logger.warn(`VALIDATION ERROR: ${validateError}`);
      throw new Error(validateError);
    }

    try {

      const response = await axios.post(API.REGISTRATION, registration);

      return response.data;
    } catch (e) {
      logger.error('Could not register new user')
      throw new Error('Could not register new user');
    }
  }

  async login(login: Login): Promise<string> {
    try {
      const validateError = this.loginValidator.validateLogin(login);
      if (validateError) {
        throw new Error(validateError);
      }

      const response = await axios.post('/api/auth/login/', login);
      return response.data;
    } catch (e) {
      throw new Error('Could not login');
    }
  }
}