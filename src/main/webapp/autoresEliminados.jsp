<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Autores eliminados - SB Admin</title>
<link href="css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="navbar.jsp"></jsp:include>
	<div id="layoutSidenav">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Lista de Autores</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">Dashboard</li>
						<li class="breadcrumb-item">Autores</li>
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
									<input type="text" class="form-control" id="names" name="names"
										placeholder="Ingrese nombre">
								</div>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="last_name"
										name="last_name" placeholder="Ingrese apellido">
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-primary mb-2"
										id="btnBuscar" name="btnBuscar">Buscar</button>
								</div>
								<div class="col-sm-2">
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
										<th scope="col">Nacionalidad</th>
										<th scope="col">Accion</th>
									</tr>
								</thead>
								<tbody id="detalleTabla">
								</tbody>
							</table>
						</div>
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
								<div class="col-md-4">
									<label for="frmNacionality" class="form-label">Nacionalidad</label>
									<input type="text" class="form-control" id="frmNacionality"
										required>
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
<script>
	
	// Constantes del CRUD
	const ACCION_RESTAURAR = "RESTAURAR";
	const ACCION_ELIMINAR = "ELIMINATE";

	// Arreglo de registros
	let arreglo = [];
	
	// Acceder a los controles
	let btnProcesar = document.getElementById("btnProcesar");
	let btnActualizar = document.getElementById("btnActualizar");
	
	// Campos del formulario
	let accion = document.getElementById('accion');
	let frmIdentifier = document.getElementById('frmIdentifier');
	let frmNames = document.getElementById('frmNames');
	let frmLast_name = document.getElementById('frmLast_name');
	let frmNacionality = document.getElementById('frmNacionality');

	// Programar los controles
	btnProcesar.addEventListener("click", fnBtnProcesar);
	btnActualizar.addEventListener("click", fnBtnActualizar);

	// Funcion fnEditar
	function fnRestaurar(identifier) {
		Swal.fire(
		  'Good job!',
		  'You clicked the button!',
		  'success'
		)
		document.getElementById("accion").value = ACCION_RESTAURAR;
		fnCargarForm(identifier);
		fnBtnProcesar();
		setTimeout(fnBtnActualizar, 1000);
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

	// Funcion fnBtnProcesar
	function fnBtnProcesar() {
		if(!fnValidar()){
			return;
		}
		let datos = "accion=" + document.getElementById("accion").value;
		datos += "&identifier=" + document.getElementById("frmIdentifier").value;
		datos += "&names=" + document.getElementById("frmNames").value;
		datos += "&last_name=" + document.getElementById("frmLast_name").value;
		datos += "&nacionality=" + document.getElementById("frmNacionality").value;
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "AuthorProcesar", true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				console.log(xhr.responseText);
			}
		};
		xhr.send(datos);
	}
	
	function fnBtnActualizar() {
		let xhttp = new XMLHttpRequest();
		xhttp.open("GET", "AuthorHistorial", true);
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
							detalleTabla += "<td>" + item.nacionality + "</td>";
							detalleTabla += "<td>";
							detalleTabla += "<a class='btn btn-success' href='javascript:fnRestaurar(" + item.identifier + ");'><i class='fa-solid fa-trash-arrow-up'></i></a> ";
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
				frmNacionality.value = item.nacionality;
				return true;
			}
		});
	}
	
	function fnValidar(){
		
		return true;
	}
</script>
</body>
</html>