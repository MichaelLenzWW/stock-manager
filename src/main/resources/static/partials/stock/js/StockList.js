import Stock from './models/Stock.js';

export class StockList extends HTMLElement {
  constructor() {
    super();

    // Attach event listener to load the ticker information
    document.addEventListener('loadTicker', event => this.render());
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

/**
 * Dispatch a cutom event, to load the ticker symbols.
 *
 * @since      1.0.0
 * @access     public
 *
 * @param {Event} event     The source Event triggered the dispatch
 */
export function dispatchLoadTicker(event) {
  console.log(`Dispatching loadTicker event.`);
  document.dispatchEvent(
    new CustomEvent('loadTicker', {
      detail: event,
      bubbles: true,
      composed: true,
      cancelable: false
    })
  );
}

customElements.define('stock-stock-list', StockList);
