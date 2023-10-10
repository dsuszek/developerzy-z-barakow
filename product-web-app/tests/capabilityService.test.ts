import { expect } from 'chai';
import logger from '../service/logger.js';
import { API, API_URL } from '../common/constants.js';
import mockAxios from './axios.instance.test.js';
import CapabilityService from '../service/capabilityService.js';
import Capability from '../model/capability.js';
import CapabilityValidator from '../service/capabilityValidator.js';
import { response } from 'express';


const capabilityEng: Capability = {
  id: 1,
  capabilityName: '',
  leadName: '',
  capabilityLeadPicture: '',
  message: '',
};
const capabilityDev: Capability = {
  id: 2,
  capabilityName: '',
  leadName: '',
  capabilityLeadPicture: '',
  message: '',
};

const capabilityService = new CapabilityService(new CapabilityValidator());

describe('Capability service', () => {
  before(() => {
    logger.silent();
  });

  after(() => {
    logger.unsilent();
  });

  describe('getCapabilities', () => {
    it('when API is online expect capabilities to be returned', async () => {
      mockAxios.onGet(API_URL + API.GET_CAPABILITIES).reply(200, [capabilityDev, capabilityEng]);

      const responseBody = await capabilityService.getCapabilities();

      expect(responseBody).to.have.lengthOf(2);
      expect(responseBody[0]).to.deep.equal(capabilityDev);
    });
    it('when API is down expect exception to be thrown', async () => {
      mockAxios.onGet(API_URL + API.GET_CAPABILITIES).reply(500);
      try {
        await capabilityService.getCapabilities();
      } catch (e: any) {
        if (e instanceof Error) {
          return {
            message: `Failed(${e.message})`,
          };
        }
      }
      return null;
    });
    it('when Capability is created, expect an id to be returned', async () => {
      const id = 12;
      mockAxios.onPost(API_URL + API.POST_CAPABILITES).reply(200, id);
      const res = await capabilityService.createCapability(capabilityEng);
      chai.assert.equal(res, id);
    });
  });
});
