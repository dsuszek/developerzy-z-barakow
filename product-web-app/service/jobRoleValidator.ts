import JobRole from '../model/jobRole.js'


export default class JobRoleValidator {
    validateJobRole(jobRole: JobRole) {
        if (jobRole.name.length > 50) {
            return 'Name greater than 50 characters';
        }

        if (jobRole.description.length > 3000) {
            return 'Description greater than 3000 characters';
        }

        if (jobRole.link.length > 1000) {
            return 'Link greater than 1000 characters';
        }

        return null;
    }
}