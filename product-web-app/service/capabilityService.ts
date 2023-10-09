import axios from 'axios';
import Capability from '../model/capability.js';
import { API, API_URL } from '../common/constants.js';

export default class CapabilityService {
  async getCapabilities(): Promise<Capability[]> {
    try {
      const response = await axios.get(API_URL + API.GET_CAPABILITIES);
      return response.data;
    } catch (e) {
      throw new Error('Failed to get capabilities from the database');
    }
  }
}
