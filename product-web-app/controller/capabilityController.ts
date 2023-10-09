import { Application } from 'express-serve-static-core';
import { Request, Response } from 'express';
import sanitize from 'sanitize-html';
import Capability from '../model/capability.js';
import CapabilityService from '../service/capabilityService.js';
import logger from '../service/logger.js';
import CapabilityValidator from '../service/capabilityValidator.js';

export default class CapabilityController {
  private capabilityService = new CapabilityService(new CapabilityValidator());

  appRoutes(app: Application) {
    app.get('/capabilities', async (req: Request, res: Response) => {
      let data: Capability[] = [];
      try {
        data = await this.capabilityService.getCapabilities();
      } catch (e) {
        logger.error(`Couldn't get capabilities! Error: ${e}`);
      }
      res.render('list-capabilities', { capabilities: data });
    });

    app.get('/admin/add-capabilities', async (req: Request, res: Response) => {
      res.render('add-capabilities');
    });

    app.post('/admin/dd-capabilies', async (req: Request, res: Response) => {
      const data: Capability = req.body;
      data.capabilityName = sanitize(data.capabilityName).trim();
      data.leadName = sanitize(data.leadName).trim();
      data.capabilityLeadPicture = sanitize(data.capabilityLeadPicture).trim();
      data.message = sanitize(data.message).trim();

      try {
        const newCapability = await this.capabilityService.createCapability(data);
        res.redirect(`/capabilites/${newCapability.id}`);
      } catch (e: any) {
        logger.warn(e.message);
        res.locals.errormessage = e.message;
        res.render('add-capability', req.body);
      }
    });
  }
}
