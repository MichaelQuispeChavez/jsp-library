<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Usuarios - SB Admin</title>
<link href="css/styles.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/PapaParse/5.3.0/papaparse.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/vfs_fonts.js"></script>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="navbar.jsp"></jsp:include>
	<div id="layoutSidenav">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Lista de Usuarios</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">Dashboard</li>
						<li class="breadcrumb-item">Usuarios</li>
						<li class="breadcrumb-item active">Lista</li>
					</ol>
					<div class="card-body">
						<form method="post" action="#">
							<div class="mb-2 row">
								<div class="col-sm d-none">
									<button type="button" class="btn d-none" id="btnActualizar"
										name="btnActualizar">Actualizar</button>
								</div>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="buscador" name="buscador" placeholder="buscar....">
								</div>

								<div class="col-sm-2">
									<button type="button" class="btn btn-primary mb-2"
										id="btnBuscar" name="btnBuscar">Buscar</button>
								</div>


								<div class="col-6">
									<button type="button" class="btn btn-success float-end mb-2"
										id="btnNuevo" name="btnNuevo">Nuevo</button>
								</div>
							</div>



						</form>
					</div>
					<div class="card mb-4" id="divResultado">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> Registros
						</div>
						<div class="card-body">
							<table class="table caption-top">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Nombre</th>
										<th scope="col">Apellido</th>
										<th scope="col">Tipo Documento</th>
										<th scope="col">Nº Documento</th>
										<th scope="col">Correo Electrónico</th>
										<th scope="col">Nº Celular</th>
										<th scope="col">Accion</th>
									</tr>
								</thead>
								<tbody id="detalleTabla">
								</tbody>
							</table>
						</div>
					</div>
					<div class="dropdown">
						<button onclick="exportToExcel()" class="btn btn-success"><i class="fa-solid fa-file-excel"></i> XLSX</button>
						<button onclick="exportToPDF()" class="btn btn-danger"><i class="fa-solid fa-file-pdf"></i> PDF</button>
						<button onclick="exportToCSV()" class="btn btn-warning"><i class="fa-solid fa-file-csv"></i> CSV</button>
					</div>
					<div class="card" id="divRegistro" style="display: none;">
						<div class="card-header">
							<i class="fa-solid fa-list"></i> Formulario
						</div>
						<div class="card-body">
							<form class="row g-3 needs-validation" novalidate>
								<input type="hidden" id="accion" name="accion">
								<div class="col-md-4 d-none">
									<label for="frmIdentifier" class="form-label">ID</label> <input
									type="text" class="form-control" id="frmIdentifier" required>
								</div>
								<div class="col-md-4">
									<label for="frmNames" class="form-label">Nombre</label> <input
										type="text" class="form-control" id="frmNames" value="" required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo válido.</div>
								</div>
								<div class="col-md-4">
									<label for="frmLast_name" class="form-label">Apellido</label>
									<input type="text" class="form-control" id="frmLast_name"
										required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo válido.</div>
								</div>
								<div class="col-md-3">
									<label for="frmDocument_type" class="form-label">Tipo Documento</label> <select
										class="form-select" id="frmDocument_type" required>
										<option selected disabled value="">Elige...</option>
										<option value="DNI">DNI</option>
										<option value="CEX">CEX</option>
									</select>
									<div class="invalid-feedback">Seleccione un tipo de documento.</div>
								</div>
								<div class="col-md-3">
									<label for="frmDocument_number" class="form-label">Nº Documento</label>
									<input type="text" class="form-control" id="frmDocument_number"
										required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo válido.</div>
								</div>
								<div class="col-md-4">
									<label for="frmEmail" class="form-label">Correo
										Electronico</label>
									<div class="input-group has-validation">
										<input type="text" class="form-control" id="frmEmail"
											aria-describedby="inputGroupPrepend" required>
										<div class="valid-feedback">¡Se ve bien!</div>
										<div class="invalid-feedback">Por favor, coloque algo válido.</div>
									</div>
								</div>
								<div class="col-md-4">
									<label for="frmCellphone" class="form-label">Nº Celular</label> <input
										type="text" class="form-control" id="frmCellphone" required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo válido.</div>
								</div>
								<div class="col-12">
									<button class="btn btn-primary" id="btnProcesar" type="submit">Enviar
										formulario</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="js/usuarios.js"></script>
<script>
	
	// Constantes del CRUD
	const ACCION_NUEVO = "NUEVO";
	const ACCION_EDITAR = "EDITAR";
	const ACCION_ELIMINAR = "ELIMINAR";

	// Arreglo de registros
	let arreglo = [];
	
	// Acceder a los controles
	let btnBuscar = document.getElementById("btnBuscar");
	let btnNuevo = document.getElementById("btnNuevo");
	let btnProcesar = document.getElementById("btnProcesar");
	let btnActualizar = document.getElementById("btnActualizar");
	
	// Campos del formulario
	let accion = document.getElementById('accion');
	let frmIdentifier = document.getElementById('frmIdentifier');
	let frmNames = document.getElementById('frmNames');
	let frmLast_name = document.getElementById('frmLast_name');
	let frmDocument_type = document.getElementById('frmDocument_type');
	let frmDocument_number = document.getElementById('frmDocument_number');
	let frmEmail = document.getElementById('frmEmail');
	let frmCellphone = document.getElementById('frmCellphone');

	// Programar los controles
	btnBuscar.addEventListener("click", fnBtnBuscar);
	btnNuevo.addEventListener("click", fnBtnNuevo);
	btnProcesar.addEventListener("click", fnBtnProcesar);
	btnActualizar.addEventListener("click", fnBtnActualizar);

	// Funcion fnEditar
	function fnEditar(identifier) {
		document.getElementById("accion").value = ACCION_EDITAR;
		fnCargarForm(identifier);
		fnEstadoFormulario(ACCION_EDITAR);
		document.getElementById("divResultado").style.display = "none";
		document.getElementById("divRegistro").style.display = "block";
	}

	// Funcion fnEliminar
	function fnEliminar(identifier) {
		Swal.fire({
		  title: 'Are you sure?',
		  text: "You won't be able to revert this!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
		  if (result.isConfirmed) {
		    Swal.fire(
		      'Deleted!',
		      'Your file has been deleted.',
		      'success'
		    )
			document.getElementById("accion").value = ACCION_ELIMINAR;
			fnCargarForm(identifier);
			fnBtnProcesar();
			setTimeout(fnBtnActualizar, 1000);
		  }
		})
	}
	
	// frmIdentifierLoan
	// frmReturn_date
	// frmUser_identifier
	// frmLibrarian
	
	// frmIdentifier
	// frmBook
	// frmAmount
	// frmLoan

	// Funcion fnBtnProcesar
	function fnBtnProcesar() {
		if(!fnValidar()){
			return;
		}
		let datos = "accion=" + document.getElementById("accion").value;
		datos += "&identifier=" + document.getElementById("frmIdentifier").value;
		datos += "&names=" + document.getElementById("frmNames").value;
		datos += "&last_name=" + document.getElementById("frmLast_name").value;
		datos += "&document_type=" + document.getElementById("frmDocument_type").value;
		datos += "&document_number=" + document.getElementById("frmDocument_number").value;
		datos += "&email=" + document.getElementById("frmEmail").value;
		datos += "&cellphone=" + document.getElementById("frmCellphone").value;
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "UsersProcesar", true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				console.log(xhr.responseText);
			}
		};
		xhr.send(datos);
	}

	function fnBtnNuevo() {
		document.getElementById("accion").value = ACCION_NUEVO;
		fnEstadoFormulario(ACCION_NUEVO);
		document.getElementById("divResultado").style.display = "none";
		document.getElementById("divRegistro").style.display = "block";
	}

	function fnBtnBuscar() {
		let buscador = document.getElementById("buscador").value;

		let url = "UsersBuscar?buscador=" + buscador;
		let xhttp = new XMLHttpRequest();
		xhttp.open("GET", url, true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let respuesta = xhttp.responseText;
				arreglo = JSON.parse(respuesta);
				let detalleTabla = "";
				arreglo.forEach(function(item) {
							detalleTabla += "<tr>";
							detalleTabla += "<td>" + item.identifier + "</td>";
							detalleTabla += "<td>" + item.names + "</td>";
							detalleTabla += "<td>" + item.last_name + "</td>";
							detalleTabla += "<td>" + item.document_type + "</td>";
							detalleTabla += "<td>" + item.document_number + "</td>";
							detalleTabla += "<td>" + item.email + "</td>";
							detalleTabla += "<td>" + item.cellphone + "</td>";
							detalleTabla += "<td>";
							detalleTabla += "<a class='btn btn-warning' href='javascript:fnEditar(" + item.identifier + ");'><i class='fa-solid fa-pen'></i></a> ";
							detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.identifier + ");'><i class='fa-solid fa-trash'></i></a>";
							detalleTabla += "</td>";
							detalleTabla += "</tr>";
						});
				document.getElementById("detalleTabla").innerHTML = detalleTabla;
				document.getElementById("divResultado").style.display = "block";
				document.getElementById("divRegistro").style.display = "none";
			}
		};
		xhttp.send();
	}
	
	function fnBtnActualizar() {
		let xhttp = new XMLHttpRequest();
		xhttp.open("GET", "UsersActualizar", true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let respuesta = xhttp.responseText;
				arreglo = JSON.parse(respuesta);
				let detalleTabla = "";
				arreglo.forEach(function(item) {
							detalleTabla += "<tr>";
							detalleTabla += "<td>" + item.identifier + "</td>";
							detalleTabla += "<td>" + item.names + "</td>";
							detalleTabla += "<td>" + item.last_name + "</td>";
							detalleTabla += "<td>" + item.document_type + "</td>";
							detalleTabla += "<td>" + item.document_number + "</td>";
							detalleTabla += "<td>" + item.email + "</td>";
							detalleTabla += "<td>" + item.cellphone + "</td>";
							detalleTabla += "<td>";
							detalleTabla += "<a class='btn btn-warning' href='javascript:fnEditar(" + item.identifier + ");'><i class='fa-solid fa-pen'></i></a> ";
							detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.identifier + ");'><i class='fa-solid fa-trash'></i></a>";
							detalleTabla += "</td>";
							detalleTabla += "</tr>";
						});
				document.getElementById("detalleTabla").innerHTML = detalleTabla;
				document.getElementById("divResultado").style.display = "block";
				document.getElementById("divRegistro").style.display = "none";
			}
		};
		xhttp.send();
	}
	
	fnBtnActualizar();
	
	function fnCargarForm(identifier){
		arreglo.forEach(function(item) {
			if(item.identifier == identifier){
				frmIdentifier.value = item.identifier;
				frmNames.value = item.names;
				frmLast_name.value = item.last_name;
				frmDocument_type.value = item.document_type;
				frmDocument_number.value = item.document_number;
				frmEmail.value = item.email;
				frmCellphone.value = item.cellphone;
				return true;
			}
		});
	}
	
	function fnEstadoFormulario(estado){
		frmNames.disabled = (estado==ACCION_ELIMINAR)
		frmLast_name.disabled = (estado==ACCION_ELIMINAR)
		frmDocument_type.disabled = (estado==ACCION_ELIMINAR)
		frmDocument_number.disabled = (estado==ACCION_ELIMINAR)
		frmEmail.disabled = (estado==ACCION_ELIMINAR)
		frmCellphone.disabled = (estado==ACCION_ELIMINAR)
		if(estado==ACCION_NUEVO){
			frmIdentifier.value = "0";
			frmNames.value = "";
			frmLast_name.value = "";
			frmDocument_type.value = "";
			frmDocument_number.value = "";
			frmEmail.value = "";
			frmCellphone.value = "";
		}
	}
	
	function fnValidar(){
		
		return true;
	}
	
	function exportToExcel() {
        // Obtener los datos de la tabla
        let rows = document.querySelectorAll("#detalleTabla tr");
        // Crear una matriz de datos con las columnas deseadas
        let data = [];
        // Agregar los encabezados de columna
        data.push(["ID", "NOMBRE", "APELLIDO", "TIPO DOCUMENTO", "Nº DOCUMENTO", "CORREO ELECTRONICO", "Nº CELULAR"]);
        rows.forEach(function(row) {
          let rowData = [];
          let columns = row.querySelectorAll("td:nth-child(1), td:nth-child(2), td:nth-child(3), td:nth-child(4), td:nth-child(5), td:nth-child(6), td:nth-child(7)"); // Incluir solo las columnas deseadas
          columns.forEach(function(column) {
            rowData.push(column.innerText);
          });
          data.push(rowData);
        });
        // Crear una hoja de cálculo de Excel
        let worksheet = XLSX.utils.aoa_to_sheet(data);
        // Crear un libro de Excel y agregar la hoja de cálculo
        let workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(workbook, worksheet, "Usuarios");
        // Guardar el archivo de Excel
        XLSX.writeFile(workbook, "reporteUsuarios.xlsx");
  	}
	
	function exportToCSV() {
	  // Obtener los datos de la tabla
	  let table = document.getElementById("detalleTabla");
	  let rows = table.getElementsByTagName("tr");
	  let data = [];
	  for (let i = 0; i < rows.length; i++) {
	    let row = rows[i];
	    let rowData = [];
	    let cells = row.getElementsByTagName("td");
	    for (let j = 0; j < cells.length; j++) {
	      rowData.push(cells[j].innerText);
	    }
	    data.push(rowData);
	  }

	  // Convertir los datos en formato CSV
	  let csv = Papa.unparse(data);

	  // Descargar el archivo CSV
	  let blob = new Blob([csv], { type: "text/csv;charset=utf-8;" });
	  let filename = "reporteUsuarios.csv";
	  if (navigator.msSaveBlob) {
	    // IE 10+
	    navigator.msSaveBlob(blob, filename);
	  } else {
	    // Otros navegadores
	    let link = document.createElement("a");
	    if (link.download !== undefined) {
	      let url = URL.createObjectURL(blob);
	      link.setAttribute("href", url);
	      link.setAttribute("download", filename);
	      link.style.visibility = "hidden";
	      document.body.appendChild(link);
	      link.click();
	      document.body.removeChild(link);
	    }
	  }
	}
	
	function exportToPDF() {
		// Crear un arreglo de encabezados
		const headers = ['ID', 'NOMBRE', 'APELLIDO', 'TIPO DOCUMENTO', 'Nº DOCUMENTO', 'CORREO ELECTRONICO', 'Nº CELULAR'];

		// Crear un arreglo de filas para los registros
		const rows = arreglo.map((item) => [
			item.identifier,
			item.names,
			item.last_name,
			item.document_type,
			item.document_number,
			item.email,
			item.cellphone
		]);

		// Crear el documento PDF
		const docDefinition = {
			content: [
				{ text: 'Reporte PDF - Usuarios', style: 'header' },
				{
					table: {
						headerRows: 1,
						widths: ['auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto'],
						body: [
							headers, // Agregar los encabezados al documento
							...rows // Agregar las filas de registros al documento
						]
					}
				}
			],
			styles: {
				header: {
					fontSize: 18,
					bold: true,
					alignment: 'center'
				}
			}
		};

		// Generar el archivo PDF
		pdfMake.createPdf(docDefinition).download('reporteUsuarios.pdf');
	}

</script>
</body>
</html>