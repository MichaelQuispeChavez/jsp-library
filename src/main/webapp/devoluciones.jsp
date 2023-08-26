<%@page import="pe.edu.vallegrande.app.model.Book"%>
<%@page import="pe.edu.vallegrande.app.service.CrudBookService"%>
<%@page import="pe.edu.vallegrande.app.model.Users"%>
<%@page import="java.util.List"%>
<%@page import="pe.edu.vallegrande.app.service.CrudUsersService"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Usuarios eliminados - SB Admin</title>
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
					<h1 class="mt-4">Usuarios Eliminados</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">Dashboard</li>
						<li class="breadcrumb-item">Usuarios</li>
						<li class="breadcrumb-item active">Eliminados</li>
					</ol>
					<div class="card mb-4" id="divResultado">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> Registros
						</div>
						<div class="card-body">
							<table class="table caption-top">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Usuario</th>
										<th scope="col">Libro</th>
										<th scope="col">Cantidad</th>
										<th scope="col">Fec. Prestamo</th>
										<th scope="col">Fec. Devolucion</th>
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
									<label for="frmUser_identifier" class="form-label">Usuario</label> <select
										class="form-select" id="frmUser_identifier" required>
										<option selected disabled value="">Elige...</option>
										<% CrudUsersService userService = new CrudUsersService(); %>
										<% List<Users> userLista = userService.getActive(); %>
										<% for (Users user : userLista) { %>
										<option value="<%= user.getIdentifier() %>"><%= user.getLast_name().toUpperCase() %>, <%= user.getNames() %></option>
										<% } %>
									</select>
									<div class="invalid-feedback">Seleccione un tipo de documento.</div>
								</div>
								<div class="col-md-4">
									<label for="frmBook_identifier" class="form-label">Libro</label> <select
										class="form-select" id="frmBook_identifier" required>
										<option selected disabled value="">Elige...</option>
										<% CrudBookService bookService = new CrudBookService(); %>
										<% List<Book> bookLista = bookService.getActive(); %>
										<% for (Book book : bookLista) { %>
										<option value="<%= book.getIdentifier() %>"><%= book.getTitle() %></option>
										<% } %>
									</select>
									<div class="invalid-feedback">Seleccione un tipo de documento.</div>
								</div>
								<div class="col-md-1">
									<label for="frmAmount" class="form-label">Cantidad</label>
									<input type="number" class="form-control" id="frmAmount"
										required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo válido.</div>
								</div>
								<div class="col-md-2">
									<label for="frmReturn_date" class="form-label">Fec. Devolucion</label> <input
										type="date" class="form-control" id="frmReturn_date" required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo válido.</div>
								</div>
								<div class="col-12">
									<button class="btn btn-primary" id="btnProcesar" type="submit">Enviar formulario</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</main>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
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
	
	// Campos del formulario
	let accion = document.getElementById('accion');
	let frmIdentifier = document.getElementById('frmIdentifier');
	let frmNames = document.getElementById('frmUser_identifier');
	let frmLast_name = document.getElementById('frmBook_identifier');
	let frmDocument_type = document.getElementById('frmAmount');
	let frmDocument_number = document.getElementById('frmReturn_date');

	// Programar los controles
	btnProcesar.addEventListener("click", fnBtnProcesar);

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
		datos += "&user_identifier=" + document.getElementById("frmUser_identifier").value;
		datos += "&book_identifier=" + document.getElementById("frmBook_identifier").value;
		datos += "&amount=" + document.getElementById("frmAmount").value;
		datos += "&return_date=" + document.getElementById("frmReturn_date").value;
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "LoanProcesar", true);
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
		xhttp.open("GET", "LoanHistorial", true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let respuesta = xhttp.responseText;
				arreglo = JSON.parse(respuesta);
				let detalleTabla = "";
				arreglo.forEach(function(item) {
							detalleTabla += "<tr>";
							detalleTabla += "<td>" + item.identifier + "</td>";
							detalleTabla += "<td>" + item.user_identifier + "</td>";
							detalleTabla += "<td>" + item.book_identifier + "</td>";
							detalleTabla += "<td>" + item.amount + "</td>";
							detalleTabla += "<td>" + item.start_dates + "</td>";
							detalleTabla += "<td>" + item.return_date + "</td>";
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
				frmUser_identifier.value = item.user_identifier;
				frmBook_identifier.value = item.book_identifier;
				frmAmount.value = item.amount;
				frmReturn_date.value = item.return_date;
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