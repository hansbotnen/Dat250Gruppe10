const account = require('./account');
const createId = require('../utils/idUtil');

class AccountService {
  constructor() {
    this.accounts = [new account('2721','Olav Thon'), new account(createId(), 'Mikal Fuglestein')];
  }

  getAll() {
    return this.accounts;
  }

  getById(reqId) {
    return this.accounts.find(obj => obj.id == reqId);
  }

  createAccount(name) {
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
    var account = this.deleteById(reqId);
    account = Object.assign({}, account, req);
    this.accounts.push(account);
    return account;
  }
}
module.exports = new AccountService();
