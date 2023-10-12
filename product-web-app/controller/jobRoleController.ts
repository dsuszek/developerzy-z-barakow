import { Application, Request, Response } from 'express';
import sanitize from 'sanitize-html';
import JobRoleService from '../service/jobRoleService.js';
import JobRole from '../model/jobRole.js';
import JobRoleValidator from '../service/jobRoleValidator.js';
import logger from '../service/logger.js';

export default class JobRoleController {
  private jobRoleService = new JobRoleService(new JobRoleValidator());

  appRoutes(app: Application) {
    app.get('/admin/add-job-roles', async (req: Request, res: Response) => {
      res.render('add-job-roles');
    });

    app.get('/delete-job-role/:id', async (req: Request, res: Response) => {
      const { id } = req.params;

      try {
        await this.jobRoleService.deleteJobRole(id);
        res.render('list-roles');
      } catch (e: any) {
        logger.warn(e.message);
        res.locals.errormessage = e.message;
        res.render('list-roles', req.body);
      }
    });

    app.post('/admin/add-job-roles', async (req: Request, res: Response) => {
      const data: JobRole = req.body;
      data.name = sanitize(data.name).trim();
      data.description = sanitize(data.description).trim();
      data.link = sanitize(data.link).trim();

      try {
        const newJobRole = await this.jobRoleService.createJobRole(data);
        res.redirect(`/job-roles/${newJobRole.id}`);
      } catch (e: any) {
        logger.warn(e.message);
        res.locals.errormessage = e.message;
        res.render('add-job-roles', req.body);
      }
    });
  }
}
