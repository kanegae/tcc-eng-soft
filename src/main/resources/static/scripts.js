function setProjeto(codigo) {
	if(codigo != 0) {
		location.href = window.location.origin + window.location.pathname + "?projeto=" + codigo;
	} else {
		location.href = window.location.origin + window.location.pathname;
	}
}