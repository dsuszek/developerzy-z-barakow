import { expect } from 'chai';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import Role from '../model/role.js';
import RoleService from '../service/roleService.js';
import logger from '../service/logger.js';

const mockAxios = new MockAdapter(axios);

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
      mockAxios.onGet('http://localhost:8080/api/job-roles').reply(200, [roleDev, roleAdmin]);

      const responseBody = await roleService.getRoles();

      expect(responseBody).to.have.lengthOf(2);
      expect(responseBody[0]).to.deep.equal(roleDev);
    });

    it('when API is online expect role to be returned', async () => {
      mockAxios.onGet('http://localhost:8080/api/job-roles/1').reply(200, roleDev);

      const responseBody = await roleService.getRoles();

      expect(responseBody).to.have.lengthOf(2);
      expect(responseBody[0]).to.deep.equal(roleDev);
    });

    it('when API is down expect exception to be thrown', async () => {
      mockAxios.onGet('http://localhost:8080/api/job-roles').reply(500);

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
