import axios from 'axios';
import Login from '../model/auth.js';
import LoginValidator from './loginValidator.js';

export default class AuthService {
  private loginValidator: LoginValidator;

  constructor(productValidator: LoginValidator) {
    this.loginValidator = productValidator;
  }

  async login(login: Login): Promise<string> {
    try {
      const validateError = this.loginValidator.validateLogin(login);
      if (validateError) {
        throw new Error(validateError);
      }

      const response = await axios.post('http://localhost:8080/api/login/', login);
      return response.data;
    } catch (e) {
      throw new Error('Could not login');
    }
  }
}
