import Registration from "../model/registration.js";

const EMAIL_DOMAIN_PATTERN = /@kainos\.com$/;
const EMAIL_PATTERN = /^(?=.{1,50}$)[A-Za-z0-9+_.-]+@kainos\.com$/;
const PASSWORD_SPECIAL_CHARS_PATTERN = /.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\-].*/;
const PASSWORD_UPPERCASE_LETTER_PATTERN = /(?=.*[A-Z]).*/;
const PASSWORD_LOWERCASE_LETTER_PATTERN = /(?=.*[a-z]).*/;
const PASSWORD_LENGTH_PATTERN = /(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!]).{9,50}/;

export default class RegistrationValidator {

    validateRegistration(registration: Registration) {
        if (!EMAIL_DOMAIN_PATTERN.test(registration.email)) {
            return "Email does not have @kainos.com domain"
        }
        if (!EMAIL_PATTERN.test(registration.email)) {
            return "Email address must have 50 characters at the maximum"
        }
        if (!PASSWORD_SPECIAL_CHARS_PATTERN.test(registration.password)) {
            return "Password must contain at least one special character"
        }
        if (!PASSWORD_UPPERCASE_LETTER_PATTERN.test(registration.password)) {
            return "Password must contain at least one uppercase letter"
        }
        if (!PASSWORD_LOWERCASE_LETTER_PATTERN.test(registration.password)) {
            return "Password must contain at least one lowercase letter"
        }
        if (!PASSWORD_LENGTH_PATTERN.test(registration.password)) {
            return "Password must contain between 9 and 50 characters"
        }
    }
}