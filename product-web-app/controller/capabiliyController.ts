import { Application } from 'express-serve-static-core';
import { Request, Response } from 'express';
import Capability from '../model/capability';
import CapabilityService from '../service/capabilityService';
import logger from '../service/logger.js';

export default class CapabilityController {
  private capabilityService = new CapabilityService();

  appRoutes(app: Application) {
    app.get('/roles', async (req: Request, res: Response) => {
      let data: Capability[] = [];
      try {
        data = await this.capabilityService.getCapabilities();
      } catch (e) {
        logger.error(`Couldn't get capabilities! Error: ${e}`);
      }
      res.render('list-capabilities', { roles: data });
    });
  }
}
