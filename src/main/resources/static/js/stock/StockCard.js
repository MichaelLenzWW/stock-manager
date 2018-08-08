import { currencyFormatter, getAmountColor } from '../common/Common.js';

export default class StockCard extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    this.render();
  }

  get id() {
    return this.getAttribute('id');
  }

  set id(id) {
    this.setAttribute('id', id);
  }

  get symbol() {
    return this.getAttribute('symbol');
  }

  set symbol(symbol) {
    this.setAttribute('symbol', symbol);
  }

  get name() {
    return this.getAttribute('name');
  }

  set name(name) {
    this.setAttribute('name', name);
  }
  render() {
    const profitOrLoss = 1250.5;
    const rate = 87.5;

    // Show Order sum P & L
    // Show next earnings date ==> Color if earnings interferre with open orders
    // Show next dividend date and dividend
    // On hover earnings ==> Show tooltip or box with upcoming earnings
    // On hover dividends ==> Show tooltip or box with upcoming dividends
    // Show date and time for the rate value
    this.innerHTML = `
    <div class="row">
    <div  class="col-md-12" style="color:white;">
		  <div id="stock-card-${this.id}" class="card col-md-12" style="background: linear-gradient(to bottom right, #0099cc, #b3ecff);">
		    <div class="card-body">
          <h5 class="card-title">${this.symbol} - ${this.name}
            <span style="float:right;${getAmountColor(profitOrLoss)}">P&L  ${currencyFormatter().format(profitOrLoss)}</span>
          </h5>
          <p class="card-text">What should we show here?<span style="float:right;">Rate ${currencyFormatter().format(rate)}</span></p>
          <p class="card-text">
            <span class="fa fa-money" style="margin-right: 10px"></span>Earnings
            <span class="fa fa-dollar" style="margin-left:10px; margin-right: 10px"></span>Dividends
          </p>
         </div>
         </div>
	      </div>
      </div>`;
  }
}
customElements.define('stock-card', StockCard);
