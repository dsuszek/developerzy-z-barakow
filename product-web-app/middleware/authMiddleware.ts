import { NextFunction, Request, Response } from 'express';
import axios from 'axios';

export default function (req: Request, res: Response, next: NextFunction) {
  if (req.path === '/auth/login' || req.path === '/auth/register') {
    next();
  } else if (req.cookies.token && req.cookies.token.length > 0) {
    try {
      axios.defaults.headers.common.Authorization = `Bearer ${req.cookies.token}`;
      next();
    } catch (error) {
      res.redirect('/auth/login');
    }
  } else {
    res.redirect('/auth/login');
  }
}
