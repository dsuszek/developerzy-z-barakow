import { Application, Request, Response } from 'express';

import sanitize from 'sanitize-html';
import Registration from '../model/registration.js';
import AuthService from '../service/authService.js';
import RegistrationValidator from '../service/registrationValidator.js';
import Login from '../model/login.js';
import LoginValidator from '../service/loginValidator.js';
import LoginResponse from '../model/loginResponse.js';

export default class AuthController {
  private authService = new AuthService(new RegistrationValidator(), new LoginValidator());

  appRoutes(app: Application) {
    const MILISECONDS_PER_HOUR = 3600000;

    app.get('/auth/register', async (req: Request, res: Response) => {
      res.render('register');
    });

    app.post('/auth/register', async (req: Request, res: Response) => {
      const data: Registration = req.body;
      data.email = sanitize(data.email).trim();
      data.password = sanitize(data.password).trim();

      try {
        await this.authService.register(data);
        res.redirect('/');
      } catch (e: any) {
        res.locals.errormessage = e.message;
        res.render('register', req.body);
      }
    });

    app.get('/auth/login', async (req: Request, res: Response) => {
      res.render('login');
    });

    app.post('/auth/login', async (req: Request, res: Response) => {
      const data: Login = req.body;
      try {
        const loginResponse: LoginResponse = await this.authService.login(data);
        const cookieOptions = {
          httpOnly: true,
          maxAge: MILISECONDS_PER_HOUR,
        };
        res.cookie('token', loginResponse.token, cookieOptions);
        res.cookie('admin', loginResponse.admin, cookieOptions);
        res.redirect('/');
      } catch (e: any) {
        res.locals.errormessage = e.message;
        res.render('login', req.body);
      }
    });
    app.get('/auth/logout', async (req: Request, res: Response) => {
      res.clearCookie('token');
      res.render('login');
    });
  }
}
