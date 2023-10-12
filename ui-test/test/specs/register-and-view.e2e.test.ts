import { Guid } from "guid-typescript";
import Registration from "../pageobjects/register.page.js";
import JobRoleListPage from "../pageobjects/job-role-list.page.js";

const jobRoleListPage = new JobRoleListPage();

const registration = new Registration();

const uuid: string = Guid.create().toString();
const registerEmail = '@kainos.com';
const registerPassword = 'Abcde123!';
const adminRole = 'Admin'

describe('Register as an admin test and view job Roles', async () => {
    it('Register as an admin', async () => {
        await registration.open();
        await registration.register(uuid + registerEmail, registerPassword, adminRole);
    });
});