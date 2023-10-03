import { expect } from 'chai';

import RegistrationValidator from '../service/registrationValidator.js';
import Registration from '../model/registration.js';
import logger from '../service/logger.js';

const registrationValidator = new RegistrationValidator();

describe('Registration validator', () => {
    before(() => {
        logger.silent();
    });

    after(() => {
        logger.unsilent();
    });

    describe('validateRegistration', () => {
        it('expect invalid email address', () => {
            const registration: Partial<Registration> = {
                
            }
        })
    })
})