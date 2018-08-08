{

    /*
     * Class for the stock.
     */
    class Stock {
        constructor(tenant, symbol, name) {
            this.tenant = tenant;
            this.symbol = symbol;
            this.name = name;
        }
    }

    // Get the ticker symbols from the server via asynchronous REST call.
    async function fetchTickerSymbols() {
        try {
            console.log("Fetching ticker symbols.");
            const symbols = await fetch("http://localhost:8081/stock");
            return await symbols.json();
        } catch (error) {
            console.log("Error on loading the ticker symbols from the database: " + error);
        }
    };

    // Get the ticker symbols and display as list
    function fetchAndDisplayTickerSymbols() {

        const stocks = document.querySelector('#stocks');


        // Remove all elements from the list
        $('[class^="stock-entry"]').remove();

        // Fetch the ticker symbols and create the HTML
        fetchTickerSymbols()
            .then(symbols => {

                // Add configurations to the list
                symbols.forEach(entry => {
                	
                	// stocks.append(´<stock-header></stock-header´);
                	
                    $("#stock-list").append(
                        `<tr class="stock-entry-${entry.id}" onClick="onTickerRowClicked(${entry.id}, '${entry.symbol}')">
					<th scope="row">${entry.id}</th>
					<td>${entry.symbol}</td>
					<td>${entry.name}</td>
					<td class="action-buttons">
					
						<button type="button" id="buy-stock-${entry.id}" class="btn btn-default btn-sm" onClick="buy(${entry.id})">
							<span class="fa fa-plus-circle" style="margin-right:10px"></span>Buy
						</button>
						<button type="button" id="sell-stock-${entry.id}" class="btn btn-default btn-sm" onClick="sell(${entry.id})">
	                    	<span class="fa fa-minus-circle" style="margin-right:10px"></span>Sell
						</button>
						<button type="button" id="delete-stock-${entry.id}" class="btn btn-default btn-sm" onClick="deleteTickerSymbol(${entry.id})">
							<span class="fa fa-times-circle" style="margin-right:10px"></span>Delete
						</button>
					
					</td>
				</tr>`);
                });
            });
    }

    function onTickerRowClicked(id, symbol) {
        console.log(`Clicked on row with ID "${id}" and ticker symbol "${symbol}".`);

        document.querySelector("#companyName").value = "";
        document.querySelector("#exchange").value = "";
        document.querySelector("#industry").value = "";
        document.querySelector("#sector").value = "";


        getCompanyInformation(symbol)
            .then(response => {
                if (response) {
                    console.log(response);
                    document.querySelector("#companyName").value = response.companyName;
                    document.querySelector("#exchange").value = response.exchange;
                    document.querySelector("#industry").value = response.industry;
                    document.querySelector("#sector").value = response.sector;
                }
            });
    }
    /*
     * Get company information via iextrading API for the given ticker symbol.
     */
    async function getCompanyInformation(symbol) {
        try {
            console.log(`"Fetching company information for ticker symbol "${symbol}"`);
            const companyInformation = await fetch(`https://api.iextrading.com/1.0/stock/${symbol}/company`);
            return await companyInformation.json();
        } catch (error) {
            console.log("Hier ist was schief gelaufen bei der company info: " + error);
        }
    }

    function buy(id) {
        window.alert("Buy the id : " + id);
    }

    function sell(id) {
        window.alert("Sell the id : " + id);
    }
    /*
     * Removes a ticker symbol from the database.
     */
    function deleteTickerSymbol(id) {

        console.log(`Removing ticker symbol ${id} from the database.`);

        const url = `http://localhost:8081/stock/${id}`;

        // Perform deleting the ticker symbol
        fetch(url, {
                method: 'DELETE',
            })
            .catch(error => window.alert('Fehler beim Löschen des Ticker Symbols.\n' + error))
            .then(response => {
                if (response.status != 200) {
                    window.alert('Fehler beim Löschen des Ticker Symbols.');
                    reject('Fehler beim Löschen des Ticker Symbols.')
                }
                return response;
            })
            .then(response => {
                // Remove entry from UI
                $(`.stock-entry-${id}`).remove();
            });
    }

    /*
     * Create the ticker symbol in the database.
     */
    function addTickerSymbol() {

        console.log("Adding Ticker Symbol...");

        const url = 'http://localhost:8081/stock';

        // Get data from the UI input
        const symbol = document.querySelector('#symbol').value.toUpperCase();;
        const name = document.querySelector('#name').value;
        const stock = new Stock('1', symbol, name);

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
                    reject('Fehler beim Anlegen des Ticker Symbols.')
                }
                return response;
            })
            .then(response => {
                response.json();
                fetchAndDisplayTickerSymbols()
            });
    }
}