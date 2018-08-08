public default class StockHeader{
    constructor(){
        super();
    }

    onConnectecCallback(){
        console.log('Stock header....');
    }
}

customElements.define('stock-header', StockHeader);