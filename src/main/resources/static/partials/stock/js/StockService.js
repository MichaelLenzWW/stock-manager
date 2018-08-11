import Stock from './models/Stock.js';

export default class StockService {
  addTickerSymbol(symbol, name) {
    console.log(`Adding Ticker Symbol [symbol] - [name].`);

    const url = 'http://localhost:8081/stock';
    const stock = new Stock(null, '1', symbol, name);

    // Perform creating the ticker symbol
    fetch(url, {
      method: 'POST',
      body: JSON.stringify(stock),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .catch(error => window.alert('Fehler beim Anlegen des Ticker Symbols.\n' + error))
      .then(response => {
        if (response.status != 200) {
          window.alert('Fehler beim Anlegen des Ticker Symbols.');
          reject('Fehler beim Anlegen des Ticker Symbols.');
        }
        return response;
      });
  }
}
