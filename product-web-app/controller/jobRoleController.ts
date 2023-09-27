import { Application, Request, Response } from "express";
import JobRoleService from '../service/jobRoleService.js';
import JobRole from '../model/jobRole.js';
import JobRoleValidator from "../service/jobRoleValidator.js";
import logger from "../service/logger.js";

export default class JobRoleController {
    private jobRoleService = new JobRoleService(new JobRoleValidator());

    appRoutes(app: Application) {
        app.get('/add-job-role', async (req: Request, res: Response) => {
            res.render('add-job-role');
        });

        app.post('/add-job-role', async (req: Request, res: Response) => {
            const data: JobRole = req.body;

            try {
                const newJobRole = await this.jobRoleService.createJobRole(data);
                res.redirect(`/job-roles/${newJobRole.jobRoleId}`);
            } catch (e: any) {
                logger.warn(e.message);
                res.locals.errormessage = e.message;
                res.render('add-job-role', req.body);
            }
        })
    }
}