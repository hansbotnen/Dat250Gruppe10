const account = require('./account');
const createId = require('../utils/idUtil');
const dummies = require('../utils/dummyDataUtil');

class AccountService {
  constructor() {
    this.accounts = dummies.accountDummies;
  }

  idExists(id) {
    return this.getById(id) != undefined;
  }

  getAll() {
    return this.accounts;
  }

  getById(reqId) {
    return this.accounts.find(obj => obj.id == reqId);
  }

  createAccount(name) {
    // TODO: validate input
    var newAccount = new account(createId(), name);
    this.accounts.push(newAccount);
    return newAccount;
  }

  deleteById(reqId) {
    var deleted = this.getById(reqId);
    this.accounts = this.accounts.filter(obj => obj.id != reqId);
    return deleted;
  }

  updateById(reqId, req) {
    // TODO: validate input
    var account = this.deleteById(reqId);
    account = Object.assign({}, account, req);
    this.accounts.push(account);
    return account;
  }
}
module.exports = new AccountService();
