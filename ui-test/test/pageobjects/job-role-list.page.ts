import Page from "./page.js";

export default class JobRoleListPage extends Page {
  async goToJobRole(index: number) {
    const viewDetailButton = await $$("td a");
    await viewDetailButton[index].click();
  }
  async goBackToList(){
    const viewBackButton = await $('//*[@id="role"]/a');
    await viewBackButton.click();
  }

  public open() {
    return browser.url(process.env.WEB_APP_URL + "/job-roles" || 'UNDEFINED');
  }
}