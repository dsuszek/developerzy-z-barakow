import Page from "./page.js";

export default class JobRoleDetailsPage extends Page {
    async getJobRoleName() {
        const text = (await $("section h1")).getText();
        return text;
    }

    async getRoleDescription() {
        const text = (await $("h7")).getText();
        return text;
    }

    async getLinkHref() {
        return (await $("a[target='_blank']")).getAttribute("href");

    }

}