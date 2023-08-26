(function () {
  'use strict';

  // Obtener el formulario a validar
  var form = document.querySelector('.needs-validation');

  // Obtener los campos del formulario
  var titleInput = form.querySelector('#frmTitle');
  var stockInput = form.querySelector('#frmStock');
  var isbnInput = form.querySelector('#frmIsbn');

  // Expresión regular para validar números y longitud
  var regexNumbers = /^\d+$/;
  var regexTitle = /^[A-Za-z0-9\s]+$/;
  
  // Función de validación personalizada para el título
  function validateTitle(input) {
    var value = input.value.trim();
    if (!regexTitle.test(value)) {
      input.setCustomValidity('Por favor, ingrese solo letras, espacios y números.');
    } else {
      input.setCustomValidity('');
    }
  }
  
  // Validar el campo de título al cambiar su valor
  titleInput.addEventListener('input', function () {
    validateTitle(titleInput);
  });

  // Función de validación personalizada para números y longitud
  function validateNumbersLength(input, minLength, maxLength) {
    var value = input.value.trim();
    if (!regexNumbers.test(value) || value.length < minLength || value.length > maxLength) {
      input.setCustomValidity(`Por favor, ingrese solo números entre ${minLength} y ${maxLength} caracteres.`);
    } else {
      input.setCustomValidity('');
    }
  }

  // Validar el campo de stock al cambiar su valor
  stockInput.addEventListener('input', function () {
    validateNumbersLength(stockInput, 1, 5);
  });

  // Validar el campo de ISBN al cambiar su valor
  isbnInput.addEventListener('input', function () {
    validateNumbersLength(isbnInput, 13, 13);
  });

  // Validar el formulario al intentar enviarlo
  form.addEventListener('submit', function (event) {
    if (!form.checkValidity()) {
      event.preventDefault();
      event.stopPropagation();
    }

    form.classList.add('was-validated');
  }, false);
})();