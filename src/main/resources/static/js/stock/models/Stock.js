export default class Stock {
  constructor(id, tenant, symbol, name, value) {
    this.id = id;
    this.tenant = tenant;
    this.symbol = symbol;
    this.name = name;
    this.value = value;
  }
}
