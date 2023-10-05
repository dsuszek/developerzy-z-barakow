import Registration from "../model/registration.js";

const EMAIL_PATTERN = /"^[A-Za-z0-9._%+-]+@kainos.com$"/;

export default class RegistrationValidator {

    validateRegistration(registration: Registration) {
        if (!EMAIL_PATTERN.test(registration.email)) {
            return "Email does not have @kainos.com domain"
        }

        return null;
    }
}