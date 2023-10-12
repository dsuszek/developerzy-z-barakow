import Page from "./page.js";

export default class ViewJobRolesPage extends Page {
    async viewJobRolesList() {
        const jobRoleListName = (await $('/html/body/header/div/div[2]/h1')).getText();
        return jobRoleListName;
    }
}