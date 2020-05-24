// Very simple JS for updating the text when a radio button is clicked
const INPUTS = document.querySelectorAll('#smileys input');

function updateValue(e) {
  document.querySelector('#result').innerHTML = e.target.value;
}

INPUTS.forEach(el => el.addEventListener('click', e => updateValue(e)));