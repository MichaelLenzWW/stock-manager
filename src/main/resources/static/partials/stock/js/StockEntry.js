import {
  currencyFormatter,
  getAmountColor
} from '../../../js/common/Common.js';

export default class StockEntry extends HTMLElement {
  constructor() {
    super();
  }

  set id(id) {
    this.setAttribute('symbol', id);
  }

  get id() {
    return this.getAttribute('id');
  }

  set symbol(symbol) {
    this.setAttribute('symbol', symbol);
  }

  get symbol() {
    return this.getAttribute('symbol');
  }

  set name(name) {
    this.setAttribute('name', name);
  }

  get name() {
    return this.getAttribute('name');
  }

  set value(value) {
    this.setAttribute('value', value);
  }

  get value() {
    return this.getAttribute('value');
  }

  connectedCallback() {
    this.render();
  }

  render() {
    var html = this.getHtmlForStockCard();
    html = html + this.getHtmlForStockOrderHeader();
    this.innerHTML = html;

    this.fetchAndDisplayOrders();
  }

  getHtmlForStockCard() {
    return `<stock-card id="${this.id}" symbol="${this.symbol}" name="${this.name}"></stock-card>`;
  }

  getHtmlForStockOrderHeader() {
    return `<div class="col-12">
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th style="width: 5%" scope="col">#</th>
                    <th style="width: 6%" scope="col">Purchased</th>
                    <th style="width: 6%" scope="col">Sold</th>
                    <th style="width: 4%" scope="col">Days</th>
                    <th style="width: 9%" scope="col">Ticker</th>
                    <th style="width: 8%" scope="col">Type</th>
                    <th style="width: 8%" scope="col">Status</th>
                    <th style="width: 4%" scope="col">Quantity</th>
                    <th style="width: 5%;text-align: right" scope="col">Price P.</th>
                    <th style="width: 8%;text-align: right" scope="col">Value P.</th>
                    <th style="width: 5%;text-align: right" scope="col">Price S.</th>
                    <th style="width: 8%;text-align: right" scope="col">Value S.</th>
                    <th style="width: 8%;text-align: right" scope="col">P & L</th>
                  </tr>
                </thead>
                <tbody id="stock-list-${this.id}"></tbody>
              </table>
            </div>`;
  }

  fetchAndDisplayOrders() {
    $(`[class^="stock-order-${this.id}"]`).remove();

    this.fetchOrders().then(orders => {
      if (!orders || orders.length === 0) {
        return;
      }
      
      orders.forEach(order => {
        const orderValues = this.getOrderValues(order);
        $(`#stock-list-${this.id}`).append(
          `<tr class="stock-order-${order.id}">
          <th scope="row">${order.id}</th>
          <td>${order.purchaseDate}</td>
          <td>${order.sellDate}</td>
          <td>${this.getDateDifference(new Date(order.purchaseDate), new Date(order.sellDate))}</td>
          <td>${this.getSymbol(order)}</td>
          <td>${this.getOrderType(order)}</td>
          <td>${order.status}</td>
          <td>${order.quantity}</td>
          <td style="text-align: right">${currencyFormatter().format(order.purchasePrice)}</td>
          <td style="text-align: right; ${this.getAmountColor(orderValues.purchaseValue)}">${this.getOrderValueFormatted(orderValues.purchaseValue)}</td>
          <td style="text-align: right">${currencyFormatter().format(order.sellPrice)}</td>
          <td style="text-align: right; ${this.getAmountColor(orderValues.sellValue)}">${this.getOrderValueFormatted(orderValues.sellValue)}</td>
          <td style="text-align: right; ${this.getAmountColor(orderValues.profitOrLoss)}">${this.getOrderValueFormatted(orderValues.profitOrLoss)}</td>
                        </tr>`
        );
      });
    });
  }
  getSymbol(order) {

    var months = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
    const strikeDate = new Date(order.strikeDate);

    if (this.isOption(order)) {
      console.log(strikeDate);
      console.log();
      console.log();

      return this.symbol + ' ' + strikeDate.getDate() + months[strikeDate.getMonth()] + strikeDate.getFullYear() + ' ' + order.strikePrice;
    }

    return this.symbol;
  }
  getOrderType(order) {
    var orderType = 'Share';
    if (this.isOption(order)) {
      orderType = order.optionType === 'CALL' ? 'Option - Call' : 'Option - Put';
    }
    return orderType;
  }

  getOrderValues(order) {
    const purchaseValue = this.calculateOrderValue(order.purchasePrice, order);
    const sellValue = this.calculateOrderValue(order.sellPrice, order);

    return {
      purchaseValue: purchaseValue,
      sellValue: sellValue,
      profitOrLoss: this.calculateProfitOrLoss(purchaseValue, sellValue, order)
    };
  }

  calculateOrderValue(value, order) {

    if (!value) {
      return 0;
    }
    var calculatedValue = order.quantity * value;

    // On option contract is 100
    if (this.isOption(order)) {
      calculatedValue = calculatedValue * 100;
    }

    //  We sold something, so we received money
    if (this.isSell(order)) {
      calculatedValue = calculatedValue * -1;
    }
    return calculatedValue;
  }

  calculateProfitOrLoss(purchaseValue, sellValue, order) {

    var profitOrLoss = this.isSell(order) ? purchaseValue - sellValue : sellValue - purchaseValue;
    profitOrLoss = profitOrLoss - order.purchaseProvision;
    profitOrLoss = profitOrLoss - order.sellProvision;

    return profitOrLoss;
  }
// Set color for the amount (positive/negative), also format the value
getAmountColor(amount) {
  return amount >= 0 ? 'color:green;' : 'color:red;';
}
  getDateDifference(dateFrom, dateTo) {
    return Math.floor(
      (Date.UTC(dateTo.getFullYear(), dateTo.getMonth(), dateTo.getDate()) - Date.UTC(dateFrom.getFullYear(), dateFrom.getMonth(), dateFrom.getDate())) / (1000 * 60 * 60 * 24)
    );
  }
  getOrderValueFormatted(value) {
    return currencyFormatter().format(value);
  }
  isSell(order) {
    return order.quantity < 0;
  }
  isOption(order) {
    return order.type === 'STOCK_OPTION';
  }

  // Get the orders for the ticker symbol
  async fetchOrders() {
    try {
      console.log('Fetching orders.');
      const orders = await fetch(`http://localhost:8081/orders?search=stockId:${this.id}`);
      return await orders.json();
    } catch (error) {
      console.log(`Error on loading the orders for ticker symbol id [${this.id}] from the database:  ${error}`);
    }
  }
}
customElements.define('stock-entry', StockEntry);