import { dispatchLoadTicker } from './StockList.js';
import StockService from './StockService.js';

export class StockInputForm extends HTMLElement {
  constructor() {
    super();
    this.service = new StockService();
  }

  connectedCallback() {
    this.render();

    // Attach click listener and dispatch load of the ticker symbols
    const refreshButton = document.querySelector('#refreshButton');
    refreshButton.addEventListener('click', event => dispatchLoadTicker());

    //
    const stockInputForm = document.querySelector('#stockInputForm');
    stockInputForm.addEventListener('submit', event => {
      const symbol = document.querySelector('#symbol').value.toUpperCase();
      const name = document.querySelector('#name').value;
      this.service.addTickerSymbol(symbol, name);
    });
  }

  render() {
    this.innerHTML = `
    <div class="jumbotron">
    	<form id="stockInputForm">
	    	<stock-text-input id_name="symbol" label="Ticker Symbol" class_add="col-sm-3 input-uppercase" required></stock-text-input>
		    <stock-text-input id_name="name" label="Stock Name" placeholder="Enter the name for the ticker symbol..." required></stock-text-input>
		    <div class="form-group">
			    <button id="addButton" type="submit" class="btn btn-primary" style="margin: 10px">
			    	<span class="fa fa-plus-circle " style="margin-right: 10px"></span>Add
			    </button>
			    <button id="refreshButton" type="button" class="btn btn-info" style="margin: 10px">
				    <span class="fa fa-refresh" style="margin-right: 10px"></span>Reload
			    </button>
		    </div>
	    </form>
    </div>`;
  }
}
customElements.define('stock-input-form', StockInputForm);
