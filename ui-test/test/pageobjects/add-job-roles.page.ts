import Page from "./page.js";

export default class AddJobRolePage extends Page {

    async addJobRoles(roleName: string, roleDescription: string, sharepointLink: string) {
        const nameField = await $("input[id='name']");
        await nameField.addValue(roleName);

        const descriptionField = await $("textarea[id='description']");
        await descriptionField.addValue(roleDescription);

        const linkField = await $("input[id=link]");
        await linkField.addValue(sharepointLink);

        const submitButton = $("button[class='btn-green']");
        await submitButton.click();

    }

    async open() {
        await browser.url("/admin/add-job-roles" || 'UNDEFINED')
    }

}