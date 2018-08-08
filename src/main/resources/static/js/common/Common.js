export function setBooleanAttribute(object, booleanValue, attributeName) {
  if (booleanValue) {
    object.setAttribute(attributeName, '');
  } else {
    this.removeAttribute(attributeName);
  }
}

/**
 * Constructs a NumberFormat used to format currencies.
 *
 * @since      1.0.0
 * @access     public
 *
 * @param {String} numberFormat     The number format, to format the currency value.
 *                                  If nothing given, we use 'de-de' as default
 *
 * @param {String} currency         The currency, to format the currency value.
 *                                  If nothing given, we use 'USD' as default
 *
 * @return {NumberFormat} the created Number
 */
export function currencyFormatter(numberFormat, currency) {
  // Fallback for the currency number format
  if (!numberFormat) {
    numberFormat = 'de-de';
  }

  // Fallback for the currency
  if (!currency) {
    currency = 'USD';
  }

  return new Intl.NumberFormat(numberFormat, {
    style: 'currency',
    currency: currency,
    minimumFractionDigits: 2
  });
}

// Set color for the amount (positive/negative), also format the value
export function getAmountColor(amount) {
  return amount < 0 ? 'color:red;' : 'color:green;';
}
