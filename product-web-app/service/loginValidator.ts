import Login from '../model/auth.js';

export default class LoginValidator {
  validateLogin(login: Login) {
    if (login.email.length > 50) {
      return "Email can 't be longer than 50 characters";
    }
    return null;
  }
}
