(function () {
  'use strict';

  // Obtener el formulario a validar
  var form = document.querySelector('.needs-validation');

  // Obtener los campos del formulario
  var amountInput = form.querySelector('#frmAmount');

  // Expresión regular para validar números
  var regexNumbers = /^\d+$/;

  // Validar el campo de stock al cambiar su valor
  amountInput.addEventListener('input', function () {
    validateNumbersLength(amountInput, 1, 5);
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