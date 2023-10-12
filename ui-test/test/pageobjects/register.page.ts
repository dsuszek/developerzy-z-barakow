import Page from "./page.js";


export default class Registration extends Page {
    async register(emailAddress: string, password: string, roleName: string ) {
        const emailField = await $("input[id='email']");
        await emailField.addValue(emailAddress);

        const passwordField = await $("input[id='password']");
        await passwordField.addValue(password);

        const roleRoller = await $('#roleId');
        await roleRoller.selectByVisibleText(roleName);

        const submitRegistration = await $('//*[@id="form"]/div/form/button');
        submitRegistration.click();
    }

    async open() {
        await browser.url(process.env.WEB_APP_URL + "/auth/register" || 'UNDEFINED')
    }
}