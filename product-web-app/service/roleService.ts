import axios from 'axios';
import Role from '../model/role.js';
import logger from './logger.js';
import { API_URL } from '../common/constants.js';

export default class RoleService {
  async getRoles(): Promise<Role[]> {
    try {
      const response = await axios.get();
      return response.data;
    } catch (e) {
      throw new Error('Failed to get roles from the database');
    }
  }

  async findRoleById(id: number): Promise<Role> {
    try {
      const response = await axios.get(`http://localhost:8080/api/job-roles/${id}`);

      return response.data;
    } catch (e) {
      logger.error('Role not found');
      throw new Error('Role not found');
    }
  }
}
