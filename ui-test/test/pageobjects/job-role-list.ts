import Page from "./page.js";

export default class JobRoleListPage extends Page {
    async goToJobRole(index: number) {

        const viewDetailLinks = await $$("td a");
     
        await viewDetailLinks[index].click();
     
     }
     public open() {
        return browser.url(process.env.WEB_APP_URL + "/job-roles" || 'UNDEFINED');
      }
}