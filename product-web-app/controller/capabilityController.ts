import multer from 'multer';
import { Application } from 'express-serve-static-core';
import { Request, Response } from 'express';
import sanitize from 'sanitize-html';
import Capability from '../model/capability.js';
import CapabilityService from '../service/capabilityService.js';
import logger from '../service/logger.js';
import CapabilityValidator from '../service/capabilityValidator.js';

export default class CapabilityController {
  private capabilityService = new CapabilityService(new CapabilityValidator());

  private upload = multer();

  appRoutes(app: Application) {
    app.get('/admin/add-capabilities', async (req: Request, res: Response) => {
      res.render('add-capabilities', { page: 'add-capabilities' });
    });
    app.post('/admin/add-capabilities', this.upload.any(), async (req: Request, res: Response) => {
      const data: Capability = req.body;
      const file: any = (req as any).files;
      try {
        data.capabilityLeadPicture = Buffer.from(file[0].buffer).toString('base64');
      } catch (e: any) {
        logger.warn(e.message);
        res.locals.errormessage = e.message;
      }
      data.capabilityName = sanitize(data.capabilityName);
      data.leadName = sanitize(data.leadName);
      data.message = sanitize(data.message);
      try {
        await this.capabilityService.createCapability(data);
        res.redirect('/admin/capabilities');
      } catch (e: any) {
        logger.warn(e.message);
        res.locals.errormessage = e.message;
        res.render('add-capabilities', req.body);
      }
    });
    app.get('/admin/capabilities', async (req: Request, res: Response) => {
      let data: Capability[] = [];
      try {
        data = await this.capabilityService.getCapabilities();
      } catch (e) {
        console.error(e);
      }
      res.render('list-capabilities', {
        capabilities: data,
      });
    });
  }
}
