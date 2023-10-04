import { Application, Request, Response } from "express";

import Registration from "../model/registration.js";
import AuthService from "../service/authService.js";
import RegistrationValidator from "../service/registrationValidator.js";
import sanitize from "sanitize-html";
import Login from '../model/login.js';
import LoginValidator from '../service/loginValidator.js';

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
        const newUser = await this.authService.register(data);
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
