import axios from 'axios';
import Login from '../model/login.js';
import LoginValidator from './loginValidator.js';
import LoginResponse from '../model/loginResponse.js';

export default class AuthService {
  private loginValidator: LoginValidator;

  constructor(loginValidator: LoginValidator) {
    this.loginValidator = loginValidator;
  }

  async login(login: Login): Promise<LoginResponse> {
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
