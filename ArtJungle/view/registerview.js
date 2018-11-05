const accountService = require('../account/AccountService')
name = document.forms["registerForm"]["name"].value;
//accountService.createAccount(name);
let as = new accountService;

function register() {
    console.log("Hello world")
    console.log(name)
    as.createAccount(name)
}