import Navigation from './Navigation.js';
import TextInput from './common/TextInput.js';
import { StockList, dispatchLoadTicker } from '../partials/stock/js/StockList.js';
import StockEntry from '../partials/stock/js/StockEntry.js';
import StockCard from '../partials/stock/js/StockCard.js';

// Setup the dispatcher
window.dispatchLoadTicker = dispatchLoadTicker;
