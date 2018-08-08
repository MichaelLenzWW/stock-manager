import Stock from './models/Stock.js';

export default class StockList extends HTMLElement {
  constructor() {
    super();
  }
  connectedCallback() {
    this.render();
  }
  render() {
    var stockList = [];
    this.fetchTickerSymbols().then(symbols => {
      var html = '';
      if (symbols) {
        symbols.forEach(entry => stockList.push(new Stock(entry.id, entry.tenant, entry.symbol, entry.name, entry.value)));
        stockList.forEach(stock => (html = html + `<stock-entry id="${stock.id}" symbol="${stock.symbol}" name="${stock.name}" value="${stock.value}"></stock-entry>`));
      }
      this.innerHTML = html;
    });
  }

  // Get the ticker symbols from the server via asynchronous REST call.
  async fetchTickerSymbols() {
    try {
      console.log('Fetching ticker symbols.');
      const symbols = await fetch('http://localhost:8081/stock');
      return await symbols.json();
    } catch (error) {
      console.log('Error on loading the ticker symbols from the database: ' + error);
    }
  }
}
customElements.define('stock-stock-list', StockList);
