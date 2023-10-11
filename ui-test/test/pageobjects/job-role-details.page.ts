import Page from "./page.js";

export default class JobRoleDetailsPage extends Page {
    async getJobRoleName() {
        const jobRoleNameText = (await $("section h1")).getText();
        return jobRoleNameText;
    }

    async getRoleDescription() {
        const roleDescriptionText = (await $("h7")).getText();
        return roleDescriptionText;
    }

    async getLinkHref() {
        return (await $("a[target='_blank']")).getAttribute("href");

    }

}