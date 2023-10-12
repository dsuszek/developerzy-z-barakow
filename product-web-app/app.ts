import express, { Application, Request, Response } from 'express';
import * as url from 'url';
import 'dotenv/config';
import path from 'path';
import nunjucks from 'nunjucks';
import axios from 'axios';
import dotenv from 'dotenv';
import cookieParser from 'cookie-parser';
import logger from './service/logger.js';
import RoleController from './controller/roleController.js';
import BandController from './controller/bandController.js';
import AuthController from './controller/authController.js';
import JobRoleController from './controller/jobRoleController.js';
import CapabilityController from './controller/capabilityController.js';
import { API_URL } from './common/constants.js';
import AuthMiddleware from './middleware/authMiddleware.js';

dotenv.config();

const dirname = url.fileURLToPath(new URL('.', import.meta.url));

const app: Application = express();
const authMiddleware: AuthMiddleware = new AuthMiddleware(app);
const appViews = path.join(dirname, '/views');

const nunjucksConfig = {
  autoescape: true,
  noCache: true,
  express: app,
};

nunjucks.configure(appViews, nunjucksConfig);
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
axios.defaults.baseURL = API_URL;
app.set('view engine', 'html');
app.use('/public', express.static(path.join(dirname, 'public')));

app.listen(3000, () => {
  logger.info('Server listening on port 3000');
});

const jobRoleController = new JobRoleController();
const roleController = new RoleController();
const authController = new AuthController();
const capabilityController = new CapabilityController();
const bandController = new BandController();

app.use(cookieParser());
authMiddleware.filter();

// Routing
app.get('/', (req: Request, res: Response) => {
  res.render('home');
});

jobRoleController.appRoutes(app);
roleController.appRoutes(app);
authController.appRoutes(app);
bandController.appRoutes(app);
capabilityController.appRoutes(app);
