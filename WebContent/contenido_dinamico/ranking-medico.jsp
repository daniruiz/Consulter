<%@page contentType="text/html; charset=UTF-8" %>
    <link rel="stylesheet" type='text/css' href="/contenido_dinamico/css/ranking-medico.css" />
    <div id="contenedor-ranking"></div>
    <script src="/contenido_dinamico/js/ranking-medico.js"></script>
    <script>
        var datos = 
            {
                'Ginecología' : [
                    ['Luis Moreno', '20'],
                    ['Sara Ruiz', '19'],
                    ['Alberto Perez', '17'],
                    ['Sofía Garrido', '15'],
                    ['Rigoberto Perez', '15'],
                    ['Laura Ponce', '14'],
                    ['Rebeca Larrañaga', '8'],
                    ['Francisco Gázquez', '7'],
                    ['Carlos Acosta', '6'],
                    ['Fernando Martinez', '5']
                ],
                'Odontología' : [
                    ['Carlos Armas', '21'],
                    ['Nacho Armendáriz', '18'],
                    ['Alberto Perez', '17'],
                    ['Francisco Andreo', '14'],
                    ['Laura Ponce', '15'],
                ],
                'Cardiología' : [
                    ['Rebeca Larrañaga', '40'],
                    ['Sara Ruiz', '39'],
                    ['Alberto Perez', '27'],
                    ['Sofía Garrido', '25'],
                    ['Carlos Armas', '25'],
                    ['Laura Ponce', '24'],
                    ['Rebeca Larrañaga', '18'],
                    ['Francisco Gázquez', '17'],
                    ['Isabel Hernandez', '6'],
                    ['Rebeca Larrañaga', '5']
                ],
                'Psiquiatría' : [
                    ['Luis Moreno', '20'],
                    ['Sara Ruiz', '19'],
                    ['Alberto Perez', '17'],
                    ['Sofía Garrido', '15'],
                    ['Rigoberto Perez', '15'],
                    ['Laura Ponce', '14'],
                    ['Adrian Ruiz', '8']
                ],
                'Neurología' : [
                    ['Luis Moreno', '60'],
                    ['Sara Ruiz', '59'],
                    ['Laura Perez', '47'],
                    ['Sofía Hernandez', '45'],
                    ['Rigoberto Perez', '35'],
                    ['Laura Ponce', '24'],
                    ['Rebeca Larrañaga', '8'],
                    ['Francisco Gázquez', '7'],
                    ['Carlos Acosta', '6'],
                    ['Isabel Hernandez', '1'],
                ],
                'Pediatría' : [
                    ['Luis Moreno', '20'],
                    ['Sara Ruiz', '19'],
                    ['Alberto Perez', '17'],
                    ['Sofía Garrido', '15'],
                    ['Rigoberto Perez', '15'],
                    ['Laura Ponce', '14'],
                    ['Rebeca Larrañaga', '8'],
                    ['Francisco Gázquez', '7'],
                    ['Carlos Acosta', '6']
                ],
            };
        json = JSON.stringify(datos);
        cargarRanking(json);
    </script>