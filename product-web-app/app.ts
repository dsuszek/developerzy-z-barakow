import express, { Application, Request, Response } from 'express';
import * as url from 'url';
import 'dotenv/config';
import session from 'express-session';
import path from 'path';
import nunjucks from 'nunjucks';
import axios from 'axios';
import Role from './model/role.js';
import RoleController from './controller/roleController.js';
import AuthController from './controller/authController.js';
import logger from './service/logger.js';
import { API_URL } from './common/constants.js';
import CapabilityController from './controller/capabiliyController.js';


const dirname = url.fileURLToPath(new URL('.', import.meta.url));

const app: Application = express();

const appViews = path.join(dirname, '/views');

const nunjucksConfig = {
  autoescape: true,
  noCache: true,
  express: app,
};

nunjucks.configure(appViews, nunjucksConfig);

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(session({ secret: 'NOT_HARDCODED_SECRET', cookie: { maxAge: 60000 } }));

// app.use(cookieParser());

axios.defaults.baseURL = API_URL;

declare module 'express-session' {
  interface SessionData {
    role: Partial<Role>;
  }
}

app.set('view engine', 'html');
app.use('/public', express.static(path.join(dirname, 'public')));

app.listen(3000, () => {
  logger.info('Server listening on port 3000');
});
const roleController = new RoleController();
const authController = new AuthController();
const capabiltyController = new CapabilityController();


// Routing
app.get('/', (eq: Request, res: Response) => {
  res.render('home');
});
roleController.appRoutes(app);
authController.appRoutes(app);
capabiltyController.appRoutes(app);
