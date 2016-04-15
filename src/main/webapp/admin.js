var myApp = angular.module('myApp', ['ng-admin', 'ui.ace']);


//var customCreate = customCreateTemplate;
myApp.config(['NgAdminConfigurationProvider', function (nga) {
        var admin = nga.application('Administracion de Logica de Negocios').baseApiUrl('/bl-admin/api/');

        var aceHtmlOption = "{mode: 'html'}";
        var aceHtmlTemplate = '<div id="wrap"><div style="height: 300px;" ui-ace="' + aceHtmlOption + '" ng-model="value"/></div>';


        /* condicion */
        var condicion = nga.entity('condicion');
        condicion.listView().fields([
            nga.field('id'),
            nga.field('nombre'),
            nga.field('descripcion')
        ]);

        condicion.creationView().fields([
            nga.field('nombre'),
            nga.field('descripcion'),
            nga.field('implementacion').template(aceHtmlTemplate)
        ]);
        condicion.editionView().fields(condicion.creationView().fields());

        /* requerimientos*/
        var requerimiento = nga.entity('requerimiento').readOnly();
        requerimiento.listView().fields([
            nga.field('id'),
            nga.field('titulo')
        ]).listActions(['show']);
        requerimiento.showView().fields([
            nga.field('id'),
            nga.field('titulo'),
            nga.field('descripcion')
        ]);

        /* tickets */
        var ticket = nga.entity('ticket').readOnly();
        ticket.listView().fields([
            nga.field('id'),
            nga.field('titulo')
        ]).listActions(['show']);
        ticket.showView().fields([
            nga.field('id'),
            nga.field('titulo'),
            nga.field('consulta'),
            nga.field('respuesta'),
        ]);


        /* casos de uso*/
        var caso = nga.entity('casoDeUso');
        caso.listView().fields([
            nga.field('id'),
            nga.field('nombre'),
            nga.field('tema')
        ]).listActions(['show']);

        caso.creationView().fields([
            nga.field('nombre'),
            nga.field('tema'),
            nga.field('preCondiciones').label('Pre-condiciones'),
            nga.field('detalle').template(aceHtmlTemplate),
            nga.field('resultadoExitoso').label('Resultado exitoso'),
            nga.field('resultadoFallido').label('Resultado fallido'),
            nga.field('postCondiciones').label('Post-condiciones')
        ]);
        caso.editionView().fields(caso.creationView().fields());

        caso.showView().fields([
            nga.field('nombre'),
            nga.field('tema'),
            nga.field('preCondiciones', 'referenced_list')
                    .targetEntity(condicion)
                    .targetReferenceField('caso_id')
                    .targetFields(condicion.listView().fields())
                    .sortField('id')
                    .sortDir('DESC')
                    .label('Pre-condiciones'),
            nga.field('detalle').template(aceHtmlTemplate),
            nga.field('resultadoExitoso').label('Resultado exitoso'),
            nga.field('resultadoFallido').label('Resultado fallido'),
            nga.field('postCondiciones').label('Post-condiciones')
        ]);





        admin.addEntity(caso);
        admin.addEntity(condicion);
        admin.addEntity(requerimiento);
        admin.addEntity(ticket);

        nga.configure(admin);
    }]);


