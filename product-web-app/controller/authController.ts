import { Application, Request, Response } from "express";

import Registration from "../model/registration.js";
import AuthService from "../service/authService.js";
import RegistrationValidator from "../service/registrationValidator.js";
import sanitize from "sanitize-html";

export default class AuthController {
    private authService = new AuthService(new RegistrationValidator());

    appRoutes(app: Application) {
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
    }
}