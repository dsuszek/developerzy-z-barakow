import { expect } from 'chai';
import logger from '../service/logger.js';
import { API } from '../common/constants.js';
import mockAxios from './axios.instance.test.js';
import CapabilityService from '../service/capabilityService.js';
import Capability from '../model/capability.js';

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

const capabilityService = new CapabilityService();

describe('Capability service', () => {
  before(() => {
    logger.silent();
  });

  after(() => {
    logger.unsilent();
  });

  describe('getCapabilities', () => {
    it('when API is online expect capabilities to be returned', async () => {
      mockAxios.onGet(API.GET_CAPABILITIES).reply(200, [capabilityDev, capabilityEng]);

      const responseBody = await capabilityService.getCapabilities();

      expect(responseBody).to.have.lengthOf(2);
      expect(responseBody[0]).to.deep.equal(capabilityDev);
    });

    it('when API is online expect capability to be returned', async () => {
      mockAxios.onGet(API.GET_ROLE(1)).reply(200, capabilityDev);
      const responseBody = await capabilityService.getCapabilities();
      expect(responseBody).to.have.lengthOf(2);
      expect(responseBody[0]).to.deep.equal(capabilityDev);
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
  });
});
