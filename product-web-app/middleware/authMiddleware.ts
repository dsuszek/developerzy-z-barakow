import {
  Application, NextFunction, Request, Response, application,
} from 'express';
import axios from 'axios';

export default class AuthMiddleware {
  private app:Application;

  constructor(app:Application) {
    this.app = app;
  }

  filter() {
    this.app.use((req: Request, res: Response, next: NextFunction) => {
      if (req.path === '/auth/login' || req.path === '/auth/register') {
        next();
      } else if (req.cookies.token && req.cookies.token.length > 0) {
        try {
          axios.defaults.headers.common.Authorization = req.cookies.token;
          this.app.locals.admin = 'true';

          // if(req.path.includes('admin')){
          //   if(req.cookies.admin=='true'){
          //     next();
          //   }
          //   else{
          //     res.redirect('/');
          //   }
          // }

          next();
        } catch (error) {
          res.redirect('/auth/login');
        }
      } else {
        res.redirect('/auth/login');
      }
    });
  }
}
