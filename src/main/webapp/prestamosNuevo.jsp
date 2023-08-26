<%@page import="pe.edu.vallegrande.app.model.Book"%>
<%@page import="pe.edu.vallegrande.app.service.CrudBookService"%>
<%@page import="java.util.List"%>
<%@page import="pe.edu.vallegrande.app.model.Users"%>
<%@page import="pe.edu.vallegrande.app.service.CrudUsersService"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Usuarios - SB Admin</title>
<link href="css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/PapaParse/5.3.0/papaparse.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/pdfmake.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/vfs_fonts.js"></script>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="navbar.jsp"></jsp:include>
	<div id="layoutSidenav">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div id="layoutSidenav_content">
			<main>
				<div class="card-body">
					<form method="post" action="#">
						<div class="mb-2 row">
							<div class="col-sm d-none">
								<button type="button" class="btn" id="btnActualizar"
									name="btnActualizar">Actualizar</button>
							</div>
						</div>
					</form>
				</div>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Nuevo Prestamo</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item">Dashboard</li>
						<li class="breadcrumb-item">Prestamos</li>
						<li class="breadcrumb-item active">Nuevo</li>
					</ol>
					<div class="card" id="divRegistroLoan">
						<div class="card-header">
							<i class="fa-solid fa-list"></i> Crear prestamo
						</div>
						<div class="card-body">
							<form class="row g-3 needs-validation" novalidate>
								<input type="hidden" id="accion" name="accion">
								<div class="col-md-4 d-none">
									<label for="frmIdentifierLoan" class="form-label">ID</label> <input
										type="text" class="form-control" id="frmIdentifierLoan" required>
								</div>
								<div class="col-md-4">
									<label for="frmReturn_date" class="form-label">Fec.
										Devolucion</label> <input type="date" class="form-control"
										id="frmReturn_date" required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo
										válido.</div>
								</div>
								<div class="col-md-4">
									<label for="frmUser_identifier" class="form-label">Usuario</label>
									<select class="form-select" id="frmUser_identifier" required>
										<option selected disabled value="">Elige...</option>
										<%
										CrudUsersService usersService = new CrudUsersService();
										%>
										<%
										List<Users> listaUsers = usersService.getActive();
										%>
										<%
										for (Users users : listaUsers) {
										%>
										<option value="<%=users.getIdentifier()%>"><%=users.getLast_name().toUpperCase()%>,
											<%=users.getNames()%></option>
										<%
										}
										%>
									</select>
									<div class="invalid-feedback">Seleccione la categoria.</div>
								</div>
								<div class="col-md-4 d-none">
									<label for="frmLibrarian" class="form-label">Bibliotecario</label>
									<select class="form-select" id="frmLibrarian" required>
										<option selected value="1">Bibliotecario</option>
									</select>
									<div class="invalid-feedback">Seleccione la categoria.</div>
								</div>
								<div class="col-12">
									<button class="btn btn-primary" id="btnPrestamo" type="button">Seleccionar
										Usuario</button>
								</div>
							</form>
						</div>
					</div>
					<br>
					<div class="card" id="divRegistroLoanDetalle" style="display: none;">
						<div class="card-header">
							<i class="fa-solid fa-list"></i> Crear prestamo
						</div>
						<div class="card-body">
							<form class="row g-3 needs-validation" novalidate>
								<input type="hidden" id="accion" name="accion">
								<div class="col-md-4 d-none">
									<label for="frmIdentifier" class="form-label">ID</label> <input
										type="text" class="form-control" id="frmIdentifier" required>
								</div>
								<div class="col-md-5">
									<label for="frmBook" class="form-label">Libros</label> <select
										class="form-select" id="frmBook" required>
										<option selected disabled value="">Elige...</option>
										<%
										CrudBookService bookService = new CrudBookService();
										%>
										<%
										List<Book> listBook = bookService.getActive();
										%>
										<%
										for (Book book : listBook) {
										%>
										<option value="<%=book.getIdentifier()%>"><%=book.getTitle()%>
											->
											<%=book.getCategory_identifier().toUpperCase()%></option>
										<%
										}
										%>
									</select>
									<div class="invalid-feedback">Seleccione un tipo de
										documento.</div>
								</div>
								<div class="col-md-1">
									<label for="frmAmount" class="form-label">Cantidad</label> <input
										type="number" class="form-control" id="frmAmount" required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo
										válido.</div>
								</div>
								<div class="col-md-4 d-none">
									<label for="frmLoan" class="form-label">Error</label> <input
										type="text" class="form-control" id="frmLoan" required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo
										válido.</div>
								</div>
								<div class="col-12">
									<button class="btn btn-primary" id="btnDetalle" type="submit">Agregar
										Libro</button>
								</div>
							</form>
						</div>
						<div class="mb-4" id="divResultado">
							<div class="card-body">
								<table class="table caption-top">
									<thead>
										<tr>
											<th scope="col">Libros</th>
											<th scope="col">Cantidad</th>
											<th scope="col">Accion</th>
										</tr>
									</thead>
									<tbody id="detalleTabla">
									</tbody>
								</table>
							</div>
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
		const LOAN_NUEVO = "NUEVO";
		const LOAN_DELETE = "ELIMINAR";
		const LOAN_ELIMINATE = "ELIMINATE";

		let arreglo = [];

		let btnProcesar = document.getElementById("btnProcesar");
		let btnDetalle = document.getElementById("btnDetalle");
		let btnPrestamo = document.getElementById("btnPrestamo");
		let btnActualizar = document.getElementById("btnActualizar");

		let frmIdentifierLoan = document.getElementById("frmIdentifierLoan");
		let frmReturn_date = document.getElementById("frmReturn_date");
		let frmUser_identifier = document.getElementById("frmUser_identifier");
		let frmLibrarian = document.getElementById("frmLibrarian");
		
		let frmIdentifier = document.getElementById("frmIdentifier");
		let frmBook = document.getElementById("frmBook");
		let frmAmount = document.getElementById("frmAmount");
		let frmLoan = document.getElementById("frmLoan");
		
		btnDetalle.addEventListener("click", fnBtnDetalle);
		btnPrestamo.addEventListener("click", fnBtnPrestamo);
		//btnProcesar.addEventListener("click", fnBtnProcesar);
		btnActualizar.addEventListener("click", fnBtnActualizar);
		
		function fnBtnDetalle() {
			
		}
		
		function fnBtnPrestamo() {
			fnEstadoFormulario(LOAN_NUEVO);
			if(frmUser_identifier.value != '' && frmReturn_date.value != ''){
				fnBtnActualizar();
				document.getElementById("divRegistroLoan").style.display = "none";
				document.getElementById("divRegistroLoanDetalle").style.display = "block";
				fnBtnProcesar();
			}
		}
		
		function fnBtnProcesar() {
			if(!fnValidar()){
				return;
			}
			let datos = "accion=" + document.getElementById("accion").value;
			datos += "&identifier=" + document.getElementById("frmIdentifier").value;
			datos += "&amount=" + document.getElementById("frmBook").value;
			datos += "&loan=" + document.getElementById("frmAmount").value;
			datos += "&book=" + document.getElementById("frmLoan").value;
			let xhr = new XMLHttpRequest();
			xhr.open("POST", "LoanDetailProcesar", true);
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
			xhttp.open("GET", "LoanDetailID", true);
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					let respuesta = xhttp.responseText;
					arreglo = JSON.parse(respuesta);
					let detalleTabla = "";
					arreglo.forEach(function(item) {
								detalleTabla += "<tr>";
								detalleTabla += "<td>" + item.book_identifier + "</td>";
								detalleTabla += "<td>" + item.amount + "</td>";
								detalleTabla += "<td>";
								detalleTabla += "<a class='btn btn-warning' href='javascript:fnEditar(" + item.identifier + ");'><i class='fa-solid fa-pen'></i></a> ";
								detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.identifier + ");'><i class='fa-solid fa-trash'></i></a>";
								detalleTabla += "</td>";
								detalleTabla += "</tr>";
							});
					document.getElementById("detalleTabla").innerHTML = detalleTabla;
				}
			};
			xhttp.send();
		}
		
		function fnEstadoFormulario(estado){
			frmBook.disabled = (estado==LOAN_DELETE)
			frmAmount.disabled = (estado==LOAN_DELETE)
			frmLoan.disabled = (estado==LOAN_DELETE)
			if(estado==LOAN_NUEVO){
				frmIdentifier.value = "0";
				frmBook.value = "";
				frmAmount.value = "";
				frmLoan.value = "";
			}
		}
		
		function fnValidar(){
			
			return true;
		}
	</script>
</body>
</html>