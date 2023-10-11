import axios from 'axios';
import JobRoleValidator from './jobRoleValidator.js';
import JobRole from '../model/jobRole.js';
import logger from './logger.js';
import { API } from '../common/constants.js';

export default class JobRoleService {
  private jobRoleValidator: JobRoleValidator;

  constructor(jobRoleValidator: JobRoleValidator) {
    this.jobRoleValidator = jobRoleValidator;
  }

  async createJobRole(jobRole: JobRole): Promise<JobRole> {
    const validateError = this.jobRoleValidator.validateJobRole(jobRole);
    if (validateError) {
      logger.warn(`VALIDATION ERROR: ${validateError}`);
      throw new Error(validateError);
    }

    try {
      const response = await axios.post(API.JOB_ROLES, jobRole);

      return response.data;
    } catch (e) {
      logger.error('Could not get job roles');
      throw new Error('Could not create job role');
    }
  }

  async deleteJobRole(id: string): Promise<void> {
    try {
      const response = await axios.delete(`${API.JOB_ROLES}/${id}`);

      if (response.status !== 200) {
        throw new Error('Could not delete job role');
      }
    } catch (e) {
      logger.error('Could not delete job role');
      throw new Error('Could not delete job role');
    }
  }
}
