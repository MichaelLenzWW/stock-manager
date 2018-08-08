import { currencyFormatter, getAmountColor } from '../common/Common.js';

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
              <table class="table table-striped table-hover table-responsive">
                <thead>
                  <tr>
                    <th style="width: 5%" scope="col">#</th>
                    <th style="width: 10%" scope="col">Purchase Date</th>
                    <th style="width: 10%" scope="col">Sell Date</th>
                    <th style="width: 10%" scope="col">Days</th>
                    <th style="width: 10%" scope="col">Ticker</th>
                    <th style="width: 10%" scope="col">Type</th>
                    <th style="width: 10%" scope="col">Status</th>
                    <th style="width: 10%" scope="col">Quantity</th>
                    <th style="width: 15%" scope="col">Price</th>
                    <th style="width: 15%" scope="col">Value</th>
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
        $(`#stock-list-${this.id}`).append(
          `<tr class="stock-order-${order.id}">
                  <th scope="row">${order.id}</th>
                  <td>${order.purchaseDate}</td>
                  <td>${order.sellDate}</td>
                  <td>${this.getDateDifference(new Date(order.purchaseDate), new Date(order.sellDate))}</td>
                  <td>${this.symbol}</td>
                  <td>${this.getOrderType(order)}</td>
                  <td>${order.status}</td>
                  <td>${order.quantity}</td>
                  <td>${currencyFormatter().format(order.price)}</td>
                  <td style="${getAmountColor(this.getOrderValue(order))}">${this.getOrderValueFormatted(order)}</td>
              </tr>`
        );
      });
    });
  }

  getOrderType(order) {
    var orderType = 'Share';
    if (this.isOption(order)) {
      orderType = order.optionType === 'CALL' ? 'Option - Call' : 'Option - Put';
    }
    return orderType;
  }

  getOrderValue(order) {
    var value = order.quantity * order.price;

    // On option contract is 100
    if (this.isOption(order)) {
      value = value * 100;
    }

    // Negative quantity => We sold something, so we received money
    if (order.quantity < 0) {
      value = value * -1;
    }

    return value;
  }

  getDateDifference(dateFrom, dateTo) {
    return Math.floor(
      (Date.UTC(dateTo.getFullYear(), dateTo.getMonth(), dateTo.getDate()) - Date.UTC(dateFrom.getFullYear(), dateFrom.getMonth(), dateFrom.getDate())) / (1000 * 60 * 60 * 24)
    );
  }
  getOrderValueFormatted(order) {
    return currencyFormatter().format(this.getOrderValue(order));
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
