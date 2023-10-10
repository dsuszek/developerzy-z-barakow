import { Guid } from "guid-typescript";
import Page from "./page.js";

export default class LoginPage extends Page {

    async loginAdmin(email: string, pasword: string) {
        const emailField = await $("input[id='email']");
        await emailField.addValue(email);

        const descriptionField = await $("input[id='password']");
        await descriptionField.addValue(pasword);

        const buttonField = await $("button[type='submit']");

        await buttonField.click();
    }

    public open() {
        return browser.url(process.env.WEB_APP_URL + "/api/auth/login" || 'UNDEFINED');
    }
}