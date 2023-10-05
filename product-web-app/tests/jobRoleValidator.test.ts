import { expect } from 'chai';

import JobRoleValidator from '../service/jobRoleValidator.js';
import JobRole from '../model/jobRole.js';
import logger from '../service/logger.js';

const jobRoleValidator = new JobRoleValidator();

describe('JobRole validator', () => {
    before(() => {
        logger.silent();
    });

    after(() => {
        logger.unsilent();
    });

    describe('validateJobRole', () => {
        it('expect too long name', () => {
            const jobRole: Partial<JobRole> = {
                name: 'aa aaa a a a a a a a aa aaa a a a a a a a aa aaa a a a a a a a aa aaa a a a a a a a aa aaa a a a a a a a aa aaa a a a a a a a aa aaa a a a a a a a aa aaa a a a a a a a aa aaa a a a a a a a aa aaa a a a a a a a ',
            };

            expect(jobRoleValidator.validateJobRole(jobRole as JobRole)).to.be.equal('Length of name of job role greater than 50 characters!')
        });

        it('expect too long description', () => {
            const jobRole: Partial<JobRole> = {
                name: 'Technology Leader',
                description: 'The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.'
            };

            expect(jobRoleValidator.validateJobRole(jobRole as JobRole)).to.be.equal('Length of description of job role greater than 3000 characters!')
        });

        it('expect too long link', () => {
            const jobRole: Partial<JobRole> = {
                name: 'Technology Leader',
                description: 'A technology leader is key strategic role within the business making executive technology decisions on behalf of the business, based upon the sector and practices’ strategic direction and goals.',
                link: 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineeringhttps://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?ga=1&id=%2Fpeople%2FJob%20Specifications%2FEngineering'
            };

            expect(jobRoleValidator.validateJobRole(jobRole as JobRole)).to.be.equal('Length of link to job role greater than 1000 characters!')
        });

        it('expect empty name', () => {
            const jobRole: Partial<JobRole> = {
                name: '   ',
                description: 'A technology leader is key strategic role within the business making executive technology decisions on behalf of the business, based upon the sector and practices’ strategic direction and goals.',
                link: 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technology%20Leader%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1'
            };

            expect(jobRoleValidator.validateJobRole(jobRole as JobRole)).to.be.equal('Name of job role cannot be empty!')
        });


        it('expect empty description', () => {
            const jobRole: Partial<JobRole> = {
                name: 'Technology Leader',
                description: '          ',
                link: 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technology%20Leader%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1'
            };

            expect(jobRoleValidator.validateJobRole(jobRole as JobRole)).to.be.equal('Description of job role cannot be empty!')
        });


        it('expect empty link', () => {
            const jobRole: Partial<JobRole> = {
                name: 'Technology Leader',
                description: 'A technology leader is key strategic role within the business making executive technology decisions on behalf of the business, based upon the sector and practices’ strategic direction and goals.',
                link: '    '
            };

            expect(jobRoleValidator.validateJobRole(jobRole as JobRole)).to.be.equal('Link for job role cannot be empty!')
        });

        it('expect invalid link', () => {
            const jobRole: Partial<JobRole> = {
                name: 'Technology Leader',
                description: 'A technology leader is key strategic role within the business making executive technology decisions on behalf of the business, based upon the sector and practices’ strategic direction and goals.',
                link: 'ffwemkf23423.com'
            };

            expect(jobRoleValidator.validateJobRole(jobRole as JobRole)).to.be.equal('Link does not meet all the criteria!')
        });

        it('expect no error', () => {
            const jobRole: JobRole = {
                jobRoleId: 1,
                name: 'Technology Leader',
                description: 'A technology leader is key strategic role within the business making executive technology decisions on behalf of the business, based upon the sector and practices’ strategic direction and goals. The core responsibilities of a technology leader in Kainos include setting a Technology direction, a technical advisor to the business and C-level clients, maintaining a commercial edge over other technology services providers, developing and nurturing technical talent across the organisation and representing Kainos as a technology evangelist.',
                link: 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technology%20Leader%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1'
            };

            expect(jobRoleValidator.validateJobRole(jobRole)).to.be.null;
        });
    });
});
