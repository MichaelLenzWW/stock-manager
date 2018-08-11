export default class CurrencyInput extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    this.render();
  }

  set label(label) {
    this.setAttribute('label', label);
  }

  get label() {
    return this.getAttribute('label');
  }

  set input_id(id) {
    this.setAttribute('input_id', id);
  }
  get input_id() {
    return this.getAttribute('input_id');
  }

  set currency(currency) {
    this.setAttribute('currency', currency);
  }
  get currency() {
    return this.getAttribute('currency');
  }

  render() {
    this.innerHTML = `<label for="${this.input_id}" class="col-form-label">${this.label}</label>
    <div class="input-group">
        <input type="number" class="form-control" id="${this.input_id}">
        <div class="input-group-append">
            <span class="input-group-text">${this.currency}</span>
        </div>  
    </div>`;
  }
}
customElements.define('stock-currency-input', CurrencyInput);
