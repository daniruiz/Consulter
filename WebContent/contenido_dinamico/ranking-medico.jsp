<%@page contentType="text/html; charset=UTF-8" %>


<jsp:useBean id="Pagina" class="es.consulter.ranking.PaginaRanking">
		<jsp:setProperty name="Pagina" property="pageContext" value="<%=pageContext%>" />
</jsp:useBean>

<%@page contentType="text/html; charset=UTF-8" %>
    <link rel="stylesheet" type='text/css' href="/contenido_dinamico/css/ranking-medico.css" />
    <div id="contenedor-ranking"></div>
    <script src="/contenido_dinamico/js/ranking-medico.js"></script>
    <script>
        var datos = 
            [
                {
                    'nombreEspecialidad' : 'Ginecología',
                    'lista' : [
                        {
                            'nombreMedico' : 'Luis Moreno',
                            'totalCitas' : 20
                        },
                        {
                            'nombreMedico' : 'Sara Ruiz',
                            'totalCitas' : 19
                        },
                        {
                            'nombreMedico' : 'Alberto Perez',
                            'totalCitas' : 18
                        },
                        {
                            'nombreMedico' : 'Sofía Garrido',
                            'totalCitas' : 16
                        },
                        {
                            'nombreMedico' : 'Rigoberto Perez',
                            'totalCitas' : 14
                        },
                        {
                            'nombreMedico' : 'Laura Ponce',
                            'totalCitas' : 13
                        },
                        {
                            'nombreMedico' : 'Rebeca Larrañaga',
                            'totalCitas' : 13
                        },
                        {
                            'nombreMedico' : 'Francisco Gázquez',
                            'totalCitas' : 7
                        },
                        {
                            'nombreMedico' : 'Carlos Acosta',
                            'totalCitas' : 6
                        },
                        {
                            'nombreMedico' : 'Fernando Martinez',
                            'totalCitas' : 5
                        },
                    ]
                },
                {
                    'nombreEspecialidad' : 'Odontología',
                    'lista' : [
                        {
                            'nombreMedico' : 'Carlos Armas',
                            'totalCitas' : 21
                        },
                        {
                            'nombreMedico' : 'Nacho Armendáriz',
                            'totalCitas' : 18
                        },
                        {
                            'nombreMedico' : 'Alberto Perez',
                            'totalCitas' : 17
                        },
                        {
                            'nombreMedico' : 'Francisco Andreo',
                            'totalCitas' : 14
                        },
                        {
                            'nombreMedico' : 'Laura Ponce',
                            'totalCitas' : 15
                        }
                    ]
                },
                {
                    'nombreEspecialidad' : 'Cardiología',
                    'lista' : [
                        {
                            'nombreMedico' : 'Rebeca Larrañaga',
                            'totalCitas' :  40
                        },
                        {
                            'nombreMedico' : 'Sara Ruiz',
                            'totalCitas' :  39
                        },
                        {
                            'nombreMedico' : 'Alberto Perez',
                            'totalCitas' :  27
                        },
                        {
                            'nombreMedico' : 'Sofía Garrido',
                            'totalCitas' :  25
                        },
                        {
                            'nombreMedico' : 'Carlos Armas',
                            'totalCitas' :  25
                        },
                        {
                            'nombreMedico' : 'Laura Ponce',
                            'totalCitas' :  24
                        },
                        {
                            'nombreMedico' : 'Rebeca Larrañaga',
                            'totalCitas' :  18
                        },
                        {
                            'nombreMedico' : 'Francisco Gázquez',
                            'totalCitas' :  17
                        },
                        {
                            'nombreMedico' : 'Isabel Hernandez',
                            'totalCitas' :  6
                        },
                        {
                            'nombreMedico' : 'Rebeca Larrañaga',
                            'totalCitas' :  5
                        }
                    ]
                },
                {
                    'nombreEspecialidad' : 'Psiquiatría',
                    'lista' : [
                        {
                            'nombreMedico' : 'Luis Moreno',
                            'totalCitas' :   20
                        },
                        {
                            'nombreMedico' : 'Sara Ruiz',                            
                            'totalCitas' :  19
                        },
                        {
                            'nombreMedico' : 'Alberto Perez',
                            'totalCitas' :   17
                        },
                        {
                            'nombreMedico' : 'Sofía Garrido',
                            'totalCitas' :   15
                        },
                        {
                            'nombreMedico' : 'Rigoberto Perez',
                            'totalCitas' :   15
                        },
                        {
                            'nombreMedico' : 'Laura Ponce',
                            'totalCitas' :   14
                        },
                        {
                            'nombreMedico' : 'Adrian Ruiz',
                            'totalCitas' :   8
                        }
                    ]
                },
                {
                    'nombreEspecialidad' : 'Neurología',
                    'lista' : [
                        {
                            'nombreMedico' : 'Luis Moreno',
                            'totalCitas' :    60
                        },
                        {
                            'nombreMedico' : 'Sara Ruiz',
                            'totalCitas' :    59
                        },
                        {
                            'nombreMedico' : 'Laura Perez',
                            'totalCitas' :    47
                        },
                        {
                            'nombreMedico' : 'Sofía Hernandez',
                            'totalCitas' :    45
                        },
                        {
                            'nombreMedico' : 'Rigoberto Perez',
                            'totalCitas' :    35
                        },
                        {
                            'nombreMedico' : 'Laura Ponce',
                            'totalCitas' :    24
                        },
                        {
                            'nombreMedico' : 'Rebeca Larrañaga',
                            'totalCitas' :    8
                        },
                        {
                            'nombreMedico' : 'Francisco Gázquez',
                            'totalCitas' :    7
                        },
                        {
                            'nombreMedico' : 'Carlos Acosta',
                            'totalCitas' :    6
                        },
                        {
                            'nombreMedico' : 'Isabel Hernandez',
                            'totalCitas' :    1
                        }
                    ]
                },
                {
                    'nombreEspecialidad' : 'Pediatría',
                    'lista' : [
                        {
                            'nombreMedico' : 'Luis Moreno',
                            'totalCitas' : 20
                        },
                        {
                            'nombreMedico' : 'Sara Ruiz',
                            'totalCitas' : 19
                        },
                        {
                            'nombreMedico' : 'Alberto Perez',
                            'totalCitas' :  17
                        },
                        {
                            'nombreMedico' : 'Sofía Garrido',
                            'totalCitas' :  15
                        },
                        {
                            'nombreMedico' : 'Rigoberto Perez',
                            'totalCitas' :  15
                        },
                        {
                            'nombreMedico' : 'Laura Ponce',
                            'totalCitas' :  14
                        },
                        {
                            'nombreMedico' : 'Rebeca Larrañaga',
                            'totalCitas' :  8
                        },
                        {
                            'nombreMedico' : 'Francisco Gázquez',
                            'totalCitas' :  7
                        },
                        {
                            'nombreMedico' : 'Carlos Acosta',
                            'totalCitas' :  6
                        }
                    ]
                }
            ];
        json = JSON.stringify(datos);
        
        json = <%=Pagina.getJsonRanking()%>;
        
        cargarRanking(json);
    </script>