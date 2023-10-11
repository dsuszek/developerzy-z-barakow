import { Guid } from "guid-typescript";
import AddJobRolePage from "../pageobjects/add-job-roles.js";
import JobRoleDetailsPage from "../pageobjects/job-role-details.page.js";
import JobRoleListPage from "../pageobjects/job-role-list.js";
import LoginPage from "../pageobjects/login.page.js";

const jobRoleListPage = new JobRoleListPage();

const jobRoleDetailsPage = new JobRoleDetailsPage();
const addJobRolePage = new AddJobRolePage();
const loginPage = new LoginPage();

const adminEmail = "admin@kainos.com";
const adminPassword = "admin";

const uuid: string = Guid.create().toString();
const jobRoleDescription = 'bla bla alicja bla bla';
const sharepointLink = "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Innovation%20Lead%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1";

describe('Add Job Role Test', async () => {

    it('Login As Admin', async () => {
        await loginPage.open();
        await loginPage.loginAdmin(adminEmail, adminPassword);
    })

    it('Go to add a new job rol, fill a form and submit', async () => {
        await addJobRolePage.open();
        await addJobRolePage.addJobRoles(uuid, jobRoleDescription, sharepointLink);
    });

    it('Verify job role view details', async () => {
        const roleName = await jobRoleDetailsPage.getJobRoleName();
        const roleDescription = await jobRoleDetailsPage.getRoleDescription();

        expect(roleName).toEqual(uuid);
        expect(roleDescription).toEqual(jobRoleDescription);
    });
});
