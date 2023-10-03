import axios from "axios";
import Registration from "../model/registration.js";
import RegistrationValidator from "./registrationValidator.js";
import logger from './logger.js';
import { API } from '../common/constants.js';

export default class AuthService {
    private registrationValidator: RegistrationValidator;

    constructor(registrationValidator: RegistrationValidator) {
        this.registrationValidator = registrationValidator;
    }

    async register(registration: Registration): Promise<string> {
        const validateError = this.registrationValidator.validateRegistration(registration);

        if (validateError) {
            logger.warn(`VALIDATION ERROR: ${validateError}`);
            throw new Error(validateError);
        }

        try{
            const response = await axios.post(API.REGISTRATION, registration);

            return response.data;
        } catch (e) {
            logger.error('Could not register new user')
            throw new Error('Could not register new user');
        }
    }
}