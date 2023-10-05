import { Application, Request, Response } from 'express';

import Login from '../model/login.js';
import AuthService from '../service/authService.js';
import LoginValidator from '../service/loginValidator.js';

const MILISECONDS_PER_HOUR = 3600000;

export default class AuthController {
  private authService = new AuthService(new LoginValidator());

  appRoutes(app: Application) {
    app.get('/auth/login', async (req: Request, res: Response) => {
      res.render('login');
    });

    app.post('/auth/login', async (req: Request, res: Response) => {
      const data: Login = req.body;
      try {
        const tokenFromApi: string = await this.authService.login(data);
        const cookieOptions = {
          httpOnly: true,
          maxAge: MILISECONDS_PER_HOUR,
        };
        res.cookie('token', tokenFromApi, cookieOptions);
        res.redirect('/');
      } catch (e: any) {
        res.locals.errormessage = e.message;
        res.render('login', req.body);
      }
    });
  }
}
