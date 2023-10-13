import axios from 'axios';
import Role from '../model/role.js';
import logger from './logger.js';
import { API, API_URL } from '../common/constants.js';
import JobRoleDetails from '../model/jobRoleDetails.js';

export default class RoleService {
  async getRoles(): Promise<Role[]> {
    try {
      const response = await axios.get(API_URL + API.JOBS);
      return response.data;
    } catch (e) {
      throw new Error('Failed to get roles from the database');
    }
  }

  async findRoleById(id: number): Promise<JobRoleDetails> {
    try {
      const response = await axios.get(API_URL + API.GET_ROLE(id));

      return response.data;
    } catch (e) {
      logger.error('Role not found');
      throw new Error('Role not found');
    }
  }
}
