/// <reference types="cypress" />
import { findUserByEmail } from "../../fixtures/test-users";

const TEST_USER_EMAIL = "johnwick@online.com";
const testUser = findUserByEmail(TEST_USER_EMAIL);

context("Authentication Test", () => {
  before(() => {
    cy.visit("/");
  });

  beforeEach(() => {
    Cypress.Cookies.preserveOnce("authenticated", "session", "fullname");
  });

  it("Login successful", () => {
    cy.get("[data-testid=test-login-user]").type(TEST_USER_EMAIL);
    cy.intercept(
      "POST",
      Cypress.env("api_url") + "/test/login/" + TEST_USER_EMAIL
    ).as("testLogin");
    cy.get("[data-testid=test-login-submit]").click();
    cy.wait(["@testLogin"]).then((interception) => {
      if (interception.responseWaited) {
        cy.wait(4000);
        cy.getCookie("session").should("exist");
        cy.getCookie("authenticated").should("have.property", "value", "true");
        cy.getCookie("fullname").should(
          "have.property",
          "value",
          testUser.name.split(" ").join("_").toLowerCase()
        );

        cy.get("[data-testid=authenticated-user-fullname]").should(
          "be.visible"
        );
        cy.get("[data-testid=authenticated-user-fullname]").should(
          "have.text",
          testUser.name.toLowerCase()
        );
      }
    });
  });

  it("Logout successful", () => {
    cy.get("[data-testid=logout]").click();
    cy.getCookie("session").should("not.exist");
    cy.getCookie("authenticated").should("not.exist");
    cy.get("[data-testid=login-with-google]").should("be.visible");
  });
});
