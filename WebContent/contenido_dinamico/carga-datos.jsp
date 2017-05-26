<%@page contentType="text/html; charset=UTF-8" %>

    <style scoped>
        main { overflow: auto; }
        section {
            height: 200px;
            border: 5px dashed black;
            cursor: pointer;
            text-shadow: 0 0 1px #555;
            color: black;
            font-size: 25px;
            padding-top: 88px;
            text-align: center;
            margin-top: 78px;
        }
        section:hover {
            border-color: #888;
            color: #888;
        }
        pre {
            font: bold 16px/26px monospace;
            width: 320px;
            margin: auto;
            color: #00756a;
            letter-spacing: 1px;
            overflow: hidden;
        }
        .boton-verde {
            margin-top: 20px;
            width: 200px; 
        }
        .boton-rojo {
            display: none;
            width: 100%;
            margin-top: 20px;
            height: 60px;
            font-size: 20px;
            line-height: 57px;
            text-shadow: 1px 1px 1px rgba(255, 255, 255, 0.57);
        }
    </style>

    <section id="holder">Arrastre aquí el archivo</section>
    <pre></pre>
    <input type="file" name="selector-archivo" id="selector-archivo" accept=".csv" hidden="true">
    <label for="selector-archivo" class="boton-verde">Seleccionar archivo</label>
    <div class="boton-rojo">Cargar citas en la base de datos</div>

    <script type="text/javascript">
        $('section').click(function(){$('#selector-archivo').click()});
        $('#selector-archivo').on('change', function(){
            var fr = new FileReader();
            fr.onload = function(){
                $('.boton-rojo').show();
                $('pre').text(this.result);
            }
            fr.readAsText(this.files[0]);
        });
        $('section').on('dragover', function() { return false; });
        $('section').on('dragleave', function() { return false; });
        $('section').on('drop', function(e) {
            e.preventDefault();
            var fr = new FileReader();
            fr.onload = function() {
                $('.boton-rojo').show();
                $('pre').text(this.result);
            };
            fr.readAsText(e.originalEvent.dataTransfer.files[0]);
            return false;
        });
        $('.boton-rojo').click(function(){
            var lineas = $('pre').text().split('\n'),
                datos = [], json, columnas;
            $.each(lineas, function(i, linea) {
                columnas = linea.split(',');
                datos[i] = {
                    'dia' : columnas[0],
                    'hora' : columnas[1],
                    'idMedico' : columnas[2],
                    'dniPaciente' : columnas[3],
                }
            });
            json = JSON.stringify(datos);
            alert(json);
            
            $.post("ServletCita", {"opcion" : "subirArchivoCitas", "datos" : json})
            .done(function(){
            	alert("Archivo de citas cargado correctamente.");
            })
            .fail(function(){
            	
            })
            .always(function(){
            	
            });
            
            console.log(json);
        });
    </script>