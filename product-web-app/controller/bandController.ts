import { Application, Request, Response } from "express";

import Band from '../model/band.js';
import BandValidator from "../service/bandValidator.js";
import BandService from "../service/bandService.js";
import sanitize from "sanitize-html";

export default class BandController {
    private bandService = new BandService(new BandValidator());

    appRoutes(app: Application) {

        app.get('/admin/band', async (req: Request, res: Response) => {
            res.render('add-band');
        });

        app.post('/admin/band', async (req: Request, res: Response) => {
            const data: Band = req.body;
            data.name = sanitize(data.name).trim();

            try {
                const newBand = await this.bandService.createBand(data);
                res.redirect('/admin/band');
            } catch (e: any) {
                res.locals.errormessage = e.message;
                res.render('add-band', req.body);
            }
        });
    }
}