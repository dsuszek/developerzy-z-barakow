import { expect } from 'chai';
import Role from '../model/role.js';
import RoleService from '../service/roleService.js';
import logger from '../service/logger.js';
import { API, API_URL } from '../common/constants.js';
import mockAxios from './axios.instance.test.js';

const roleDev: Role = {
  id: 1,
  name: 'DEVOps',
  description: 'Decent job',
  link: 'http://ewwew.com',
};
const roleAdmin: Role = {
  id: 2,
  name: 'DEVOps',
  description: 'Decent job',
  link: 'http://ewwew.com',
};

const roleService = new RoleService();

describe('Role service', () => {
  before(() => {
    logger.silent();
  });

  after(() => {
    logger.unsilent();
  });

  describe('getRoles', () => {
    it('when API is online expect roles to be returned', async () => {
      mockAxios.onGet(API_URL + API.JOBS).reply(200, [roleDev, roleAdmin]);

      const responseBody = await roleService.getRoles();

      expect(responseBody).to.have.lengthOf(2);
      expect(responseBody[0]).to.deep.equal(roleDev);
    });

    it('when API is online expect role to be returned', async () => {
      mockAxios.onGet(API_URL + API.GET_ROLE(1)).reply(200, roleDev);

      const responseBody = await roleService.getRoles();

      expect(responseBody).to.have.lengthOf(2);
      expect(responseBody[0]).to.deep.equal(roleDev);
    });

    it('when API is down expect exception to be thrown', async () => {
      mockAxios.onGet(API_URL + API.JOBS).reply(500);

      let exception: any;
      try {
        await roleService.getRoles();
      } catch (e) {
        exception = e as Error;
      } finally {
        expect(exception.message).to.equal('Failed to get roles from the database');
      }
    });
  });
});
