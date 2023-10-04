import axios from 'axios';
import Capability from '../model/capability';

export default class CapabilityService {
  async getCapabilities(): Promise<Capability[]> {
    try {
      const response = await axios.get('http://localhost:8080/api/capabilities');
      return response.data;
    } catch (e) {
      throw new Error('Failed to get capabilities from the database');
    }
  }
}
