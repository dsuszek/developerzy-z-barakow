import { Application, Request, Response } from 'express';
import { serialize } from 'cookie';
import jwt from 'jsonwebtoken';

import Login from '../model/auth.js';
import AuthService from '../service/authService.js';
import LoginValidator from '../service/loginValidator.js';

export default class ProductController {
  private authService = new AuthService(new LoginValidator());

  appRoutes(app: Application) {
    app.get('/login', async (req: Request, res: Response) => {
      res.render('login');
    });

    app.post('/login', async (req: Request, res: Response) => {
      const data: Login = req.body;
      try {
        const tokenFromApi: string = await this.authService.login(data);
        const userData = {
          email: data.email,
          token: tokenFromApi,
        };
        const token = jwt.sign(userData, 'MYVERYSECRETSERCRET', { expiresIn: '1h' });
        const cookieOptions = {
          httpOnly: true, // Make the cookie accessible only via HTTP(S)
          maxAge: 3600000, // 1 hour
        };
        res.setHeader('Set-Cookie', serialize('token', token, cookieOptions));
        res.redirect('/');
        console.log(jwt.verify(token, 'MYVERYSECRETSERCRET'));
      } catch (e:any) {
        res.locals.errormessage = e.message;
        res.render('login', req.body);
      }
    });
  }
}
