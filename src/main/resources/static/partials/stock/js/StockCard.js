import { currencyFormatter, getAmountColor } from '../../../js/common/Common.js';

export default class StockCard extends HTMLElement {
  constructor() {
    super();
    this.profitOrLoss = 0;
    document.addEventListener('calculatedProfitOrLoss', event => this.updateProfitOrLoss(event));
  }

  connectedCallback() {
    this.render();
  }

  disconnectedCallback() {
    document.removeEventListener('calculatedProfitOrLoss', event => this.updateProfitOrLoss(event));
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
    const rate = 87.5;

    // Show next earnings date ==> Color if earnings interferre with open orders
    // Show next dividend date and dividend
    // On hover earnings ==> Show tooltip or box with upcoming earnings
    // On hover dividends ==> Show tooltip or box with upcoming dividends
    // Show date and time for the rate value
    this.innerHTML = `
        <div class="row">
	<div class="col-md-12" style="color:white;">
		<div id="stock-card-${this.id}" class="card col-md-12" style="background: linear-gradient(to bottom right, #669999, #c2d6d6);">
			<div class="card-body">
				<h5 class="card-title">${this.symbol} - ${this.name}
					<span style="float:right;${getAmountColor(this.profitOrLoss)}">P&L ${currencyFormatter().format(this.profitOrLoss)}</span>
				</h5>
				<div id="stock-card-buttons-${this.id}" style="diplay:flex">

					<div class="btn-group btn-group-toggle" data-toggle="buttons">
						<label class="btn btn-sm btn-secondary active">
							<input type="radio" name="options" id="option-button-${this.id}"> Option
						</label>
						<label class="btn btn-sm btn-secondary">
							<input type="radio" name="options" id="share-button-${this.id}"> Share
						</label>
          </div>
          
          <button type="button" class="btn btn-sm btn-success" id="purchase-button-${this.id}" 
          data-toggle="modal" data-target="#orderOptionModal" data-symbol="${this.symbol}" data-name="${this.name}">Purchase</button>

					<button type="button" class="btn btn-sm btn-danger" id="sell-button-${this.id}">Sell</button>
				</div>
				<div>
					<p class="card-text">
						<span style="float:right;">Rate ${currencyFormatter().format(rate)}</span>
					</p>
					<p class="card-text">
						<span class="fa fa-money" style="margin-right: 10px"></span>Earnings
						<span class="fa fa-dollar" style="margin-left:10px; margin-right: 10px"></span>Dividends
					</p>
				</div>
			</div>
		</div>
	</div>
</div>`;
  }

  updateProfitOrLoss(event) {
    // We only are interested in updates for our id
    if (event.detail.id === this.id) {
      this.profitOrLoss = event.detail.profitOrLoss;
      this.render();
    }
  }
}
customElements.define('stock-card', StockCard);
