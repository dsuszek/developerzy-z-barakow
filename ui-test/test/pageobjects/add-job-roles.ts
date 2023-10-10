import Page from "./page.js";

export default class AddJobRolePage extends Page {

    async addJobRoles(roleName: string, roleDescription: string, sharepointLink: string) {
        const nameField = await $("input[id='name']");

        await nameField.addValue(roleName);

        const descriptionField = await $("textarea[id='description']");
        await descriptionField.addValue(roleDescription);

        const linkField = await $("input[id=link]");
        await linkField.addValue(sharepointLink);

        const submitButton = $('button[class="btn btn-primary"]');

        await submitButton.click();
    }

    public open() {
        return browser.url(process.env.WEB_APP_URL + "/admin/add-job-roles" || 'UNDEFINED');
    }

}