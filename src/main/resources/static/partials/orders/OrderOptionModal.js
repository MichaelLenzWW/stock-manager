import CurrencyInput from '../../js/common/CurrencyInput.js';

export default class OrderOptionModal extends HTMLElement {
  constructor() {
    super();
  }
  connectedCallback() {
    this.render();

    $('#orderOptionModal').on('show.bs.modal', function(event) {
      // Button that triggered the modal
      let button = $(event.relatedTarget);

      // Extract data
      let symbol = button.data('symbol'); // Extract info from data-* attributes
      let name = button.data('name');

      // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
      // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
      var modal = $(this);

      // modal.find('.modal-title').text('New message to ' + recipient);
      console.dir(modal);
      modal.find('#order-name').val(symbol + '-' + name);
      modal.find('#order-date');
      modal.find('#order-quantity').val(1);
      modal.find('#order-price').val(0);
      modal.find('#order-provision').val(0);
    });
  }
  render() {
    this.innerHTML = `<div class="modal fade" id="orderOptionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Purchase or Sell Option</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class='row'>
                        <div class="col-sm-12">
                            <label for="order-name" class="col-form-label">Company</label>
                            <input type="text" class="form-control" id="order-name" disabled>
                        </div>
                    </div>

                    <div class='row'>
                        <div class="col-sm-4">
                            <label for="order-quantity" class="col-form-label">Quantity</label>
                            <input type="number" class="form-control" id="order-quantity">
                        </div>
                        <div class="col-sm-4">
                            <stock-currency-input label="Price" input_id="order-price" currency="$"></stock-currency-input>
                        </div>
                        <div class="col-sm-4">
                            <stock-currency-input label="Provision" input_id="order-provision" currency="$"></stock-currency-input>
                        </div>
                    </div>

                    <div class='row'>
                        <div class="col-sm-8">
                            <label for="option-date" class="col-form-label">Option Date</label>
                            <input type="date" class="form-control" id="option-date">
                        </div>
                        <div class="col-sm-4">
                            <stock-currency-input label="Strike Price" input_id="strike-price" currency="$"></stock-currency-input>
                        </div>
                    </div>

                    <div class='row'>
                        <div class="col-sm-8">
                            <label for="order-date" class="col-form-label">Order Date</label>
                            <input type="date" class="form-control" id="order-date">
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </div>
</div>`;
  }
}
customElements.define('stock-order-option', OrderOptionModal);
