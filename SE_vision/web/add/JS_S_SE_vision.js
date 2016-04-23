/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var DirServlet = "/SE_vision/S_SE_vision";
var READY_STATE_COMPLETE = 4;
var peticion_http;

function fnbuscar() {

    peticion_http = inicializar_obj_Ajax();
    var datos = "a";
    peticion_http.open("POST", DirServlet, true);
    peticion_http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    peticion_http.setRequestHeader("Content-length", datos.length);
    peticion_http.setRequestHeader("Connection", "close");

    peticion_http.onreadystatechange = procesarRespuesta;
    peticion_http.send(datos);//envia la cadena de datos
    return;

}



function inicializar_obj_Ajax() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else {
        if (window.ActiveXObject("Microsoft.XMLHTTP")) {
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
}//fin inicializar_obj_Ajax();


function procesarRespuesta() {
    if (peticion_http.readyState === READY_STATE_COMPLETE) {
        if (peticion_http.status === 200) {
            //if (peticion_http.responseText !== "") {

                document.getElementById("cuerpo").innerHTML = peticion_http.responseText;

                return;
           // }
        }
    }
}