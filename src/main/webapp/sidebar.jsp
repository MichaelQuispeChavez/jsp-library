<div id="layoutSidenav_nav">
	<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
		<div class="sb-sidenav-menu">
			<div class="nav">
				<div class="sb-sidenav-menu-heading">Centro</div>
				<a class="nav-link" href="index.jsp">
					<div class="sb-nav-link-icon">
						<i class="fas fa-tachometer-alt"></i>
					</div> Panel
				</a>
				<div class="sb-sidenav-menu-heading">Bibliotecario</div>
				<!--  -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseUsuarios" aria-expanded="false"
					aria-controls="#collapseUsuarios">
					<div class="sb-nav-link-icon">
						<i class="fa-solid fa-user"></i>
					</div> Usuarios
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseUsuarios"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="usuarios.jsp">Lista</a>
						<a class="nav-link" href="usuariosEliminados.jsp">Eliminados</a>
					</nav>
				</div>
				<!-- Reservas -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseReservas" aria-expanded="false"
					aria-controls="#collapseReservas">
					<div class="sb-nav-link-icon">
						<i class="fa-solid fa-calendar-days"></i>
					</div> Reservas
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseReservas"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="reservasNuevo.jsp">Nuevo</a>
						<a class="nav-link" href="reservasHechos.jsp">Hechos</a>
					</nav>
				</div>
				<!-- Prestamos -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapsePrestamos" aria-expanded="false"
					aria-controls="#collapsePrestamos">
					<div class="sb-nav-link-icon">
						<i class="fa-solid fa-book-open-reader"></i>
					</div> Prestamos
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapsePrestamos"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="prestamos.jsp">Lista</a>
						<a class="nav-link" href="devoluciones.jsp">Devoluciones</a>
					</nav>
				</div>
				<!-- Bibliotecario -->
				<div class="sb-sidenav-menu-heading">Biblioteca</div>
				<!-- Categorias -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseCategorias" aria-expanded="false"
					aria-controls="#collapseCategorias">
					<div class="sb-nav-link-icon">
						<i class="fas fa-align-justify"></i>
					</div> Categorias
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseCategorias"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="categorias.jsp">Lista</a>
						<a class="nav-link" href="categoriasEliminados.jsp">Eliminados</a>
					</nav>
				</div>
				<!-- Autores -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseAutores" aria-expanded="false"
					aria-controls="#collapseAutores">
					<div class="sb-nav-link-icon">
						<i class="fa-solid fa-address-book"></i>
					</div> Autores
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseAutores"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="autores.jsp">Lista</a>
						<a class="nav-link" href="autoresEliminados.jsp">Eliminados</a>
					</nav>
				</div>
				<!-- Libros -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseLibros" aria-expanded="false"
					aria-controls="#collapseLibros">
					<div class="sb-nav-link-icon">
						<i class="fa-solid fa-book"></i>
					</div> Libros
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseLibros"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="libros.jsp">Lista</a>
						<a class="nav-link" href="librosEliminados.jsp">Eliminados</a>
					</nav>
				</div>
			</div>
		</div>
		<div class="sb-sidenav-footer">
			<div class="small">Conectado como:</div>
			Admin
		</div>
	</nav>
</div>