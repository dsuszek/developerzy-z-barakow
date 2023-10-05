import { expect } from 'chai';
import Registration from '../model/registration.js';
import logger from '../service/logger.js';
import RegistrationValidator from '../service/registrationValidator.js';

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
                email: 'njidhbir@gmail.com'
            };
            expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal('Email does not have @kainos.com domain')
        });
    })
})