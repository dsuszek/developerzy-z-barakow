import axios from 'axios';
import Capability from '../model/capability.js';
import CapabilityValidator from './capabilityValidator.js';
import { API } from '../common/constants.js';
import logger from './logger.js';

export default class CapabilityService {
  private capabilityValidator: CapabilityValidator;

  constructor(capabilityValidator: CapabilityValidator) {
    this.capabilityValidator = capabilityValidator;
  }

  async getCapabilities(): Promise<Capability[]> {
    try {
      const response = await axios.get(API.GET_CAPABILITIES);
      return response.data;
    } catch (e) {
      throw new Error('Failed to get capabilities from the database');
    }
  }

  async createCapability(capability: Capability): Promise<number> {
    const validateError = this.capabilityValidator.validateCapability(capability);
    if (validateError) {
      logger.warn(`VALIDATION ERROR: ${validateError}`);
      throw new Error(validateError);
    }
    try {
      const response = await axios.post(API.POST_CAPABILITES, capability);
      return response.data;
    } catch (e) {
      logger.error('Could not create capability');
      throw new Error('Could not create capability');
    }
  }
}
