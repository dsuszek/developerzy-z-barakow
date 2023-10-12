import { expect } from 'chai';
import logger from '../service/logger.js';
import { API } from '../common/constants.js';
import mockAxios from './axios.instance.test.js';
import CapabilityService from '../service/capabilityService.js';
import Capability from '../model/capability.js';
import CapabilityValidator from '../service/capabilityValidator.js';

const capabilityTest: Capability = {
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
      mockAxios.onGet(API.GET_CAPABILITIES).reply(200, []);
      const responseBody = await capabilityService.getCapabilities();
      expect(responseBody).to.have.lengthOf(0);
    });
    it('when API is down expect exception to be thrown', async () => {
      mockAxios.onGet(API.GET_CAPABILITIES).reply(500);
      let exception: any;
      try {
        await capabilityService.getCapabilities();
      } catch (e) {
        exception = e as Error;
      } finally {
        expect(exception.message).to.equal('Failed to get capabilities from the database');
      }
    });
    it('when Capability is created, expect an id to be returned', async () => {
      const id = 12;
      mockAxios.onPost(API.POST_CAPABILITES).reply(200, id);
      const res = await capabilityService.createCapability(capabilityTest);
      expect(res).to.equal(12);
    });
  });
});
