import JobRole from '../model/jobRole.js'

const LINK_PATTERN = /^(https?:\/\/)?kainossoftwareltd\.sharepoint\.com\/.+$/
export default class JobRoleValidator {

    validateJobRole(jobRole: JobRole) {
        if (jobRole.name.length > 50) {
            return 'Length of name of job role greater than 50 characters!';
        }

        if (jobRole.description.length > 3000) {
            return 'Length of description of job role greater than 3000 characters!';
        }

        if (jobRole.link.length > 1000) {
            return 'Length of link to job role greater than 1000 characters!';
        }

        // checks if input consists only of whitespaces
        if (!jobRole.name.trim() || jobRole.name == null) {
            return "Name of job role cannot be empty!";
        }

        if (!jobRole.description.trim() || jobRole.description == null) {
            return "Description of job role cannot be empty!";
        }

        if (!jobRole.link.trim() || jobRole.link == null) {
            return "Link for job role cannot be empty!";
        }

        // checks if link matches the regex
        if (!LINK_PATTERN.test(jobRole.link)) {
            return "Link does not meet all the criteria!";
        }

        return null;
    }
}