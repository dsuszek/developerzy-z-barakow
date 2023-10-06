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
        it('expect email address with invalid domain', () => {
            const registration: Partial<Registration> = {
                email: 'njidhbir@gmail.com'
            };
            expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal('Email does not have @kainos.com domain')
        });

        it('expect too long email address', () => {
            const registration: Partial<Registration> = {
                email: 'njifwefkengkergergerrgrengjk34ghiggergergergregdhbir@kainos.com'
            };
            expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal('Email address must have 50 characters at the maximum')
        });

        it('expect password without special character', () => {
            const registration: Partial<Registration> = {
                email: 'joeD@kainos.com',
                password: 'fFFESJFIFEfefefefeffe'
            };
            expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal('Password must contain at least one special character')
        });

        it('expect password without uppercase letter', () => {
            const registration: Partial<Registration> = {
                email: 'joeD@kainos.com',
                password: 'fgfewg&*&bdfbdfgg'
            };
            expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal('Password must contain at least one uppercase letter')
        });

        it('expect password without lowercase letter', () => {
            const registration: Partial<Registration> = {
                email: 'joeD@kainos.com',
                password: 'FNJN(*(*FE&(FDBFE'
            };
            expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal('Password must contain at least one lowercase letter')
        });

        it('expect too short password', () => {
            const registration: Partial<Registration> = {
                email: 'joeD@kainos.com',
                password: 'f$Fg'
            };
            expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal('Password must contain at least 9 characters')
        });

        it('expect too long password', () => {
            const registration: Partial<Registration> = {
                email: 'joeD@kainos.com',
                password: 'fFFNEHFiuhewiufhewiufhewfy&Y&EFHfefewfefuywegfuywegfeufgeufererg$Fg'
            };
            expect(registrationValidator.validateRegistration(registration as Registration)).to.be.equal('Password must contain 50 characters at the maximum')
        });
    })
})