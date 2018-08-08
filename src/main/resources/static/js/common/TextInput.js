import { setBooleanAttribute } from './Common.js';

export default class TextInput extends HTMLElement {
  // wir brauchen: id, label text, required

  constructor() {
    super();
  }

  get id_name() {
    return this.getAttribute('id_name');
  }

  set id_name(id) {
    this.setAttribute('id_name', id);
  }

  get label() {
    return this.getAttribute('label');
  }
  set label(label) {
    this.setAttribute('label', label);
  }

  get class_add() {
    return this.getAttribute('class_add');
  }

  set class_add(classAdd) {
    this.setAttribute('class_add', classAdd);
  }

  get placeholder() {
    return this.getAttribute('placeholder');
  }

  set placeholder(placeholder) {
    this.setAttribute('placeholder', placeholder);
  }

  get required() {
    return this.hasAttribute('required');
  }

  set active(isRequired) {
    setBooleanAttribute(this, isRequired, 'required');
  }

  connectedCallback() {
    this.render();
  }

  render() {
    const placeholder = this.placeholder ? `placeholder="${this.placeholder}"` : '';
    const required = this.hasAttribute('required') ? 'required' : '';
    this.innerHTML = `<div class="form-group">
						<label for="${this.id_name}">${this.label}</label>
						<input type="text" class="form-control form-control-sm ${this.class_add}" id="${this.id_name}" ${placeholder} ${required}>
					</div>`;
  }
}
customElements.define('stock-text-input', TextInput);
