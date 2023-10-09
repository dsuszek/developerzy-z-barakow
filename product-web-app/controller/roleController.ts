import { Application } from 'express-serve-static-core';
import { Request, Response } from 'express';
import Role from '../model/role.js';
import RoleService from '../service/roleService.js';
import logger from '../service/logger.js';

export default class RoleController {
  private roleService = new RoleService();

  appRoutes(app: Application) {
    app.get('/job-roles', async (req: Request, res: Response) => {
      let data: Role[] = [];
      try {
        data = await this.roleService.getRoles();
      } catch (e) {
        logger.error(`Could not get roles! Error: ${e}`);
      }

      res.render('list-roles', { roles: data });
    });
    app.get('/job-roles/:id', async (req: Request, res: Response) => {
      let data = null;

      try {
        data = await this.roleService.findRoleById(Number.parseInt(req.params.id, 10));
      } catch (e) {
        logger.error(`Could not get role! Error: ${e}`);
      }
      res.render('view-role', { role: data });
    });
  }
}
