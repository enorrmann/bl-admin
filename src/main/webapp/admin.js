var myApp = angular.module('myApp', ['ng-admin', 'ui.ace']);


//var customCreate = customCreateTemplate;
myApp.config(['NgAdminConfigurationProvider', function (nga) {
        var admin = nga.application('Administracion de Logica de Negocios').baseApiUrl('/bl-admin/api/');

        var aceHtmlOption = "{mode: 'html'}";
        var aceHtmlTemplate = '<div id="wrap"><div style="height: 300px;" ui-ace="' + aceHtmlOption + '" ng-model="value"/></div>';

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
            nga.field('tema'),
            nga.field('nombre')
        ]);

        caso.creationView().fields([
            nga.field('tema'),
            nga.field('nombre'),
            nga.field('detalle').template(aceHtmlTemplate)
        ]);
        caso.editionView().fields(caso.creationView().fields());

        /* reglas */
        var regla = nga.entity('regla');
        regla.listView().fields([
            nga.field('id'),
            nga.field('tema'),
            nga.field('aplicadaA'),
            nga.field('casoDeUso')
        ]);

        regla.creationView().fields([
            nga.field('tema'),
            nga.field('aplicadaA'),
            nga.field('casoDeUso'),
            nga.field('detalle').template(aceHtmlTemplate)
        ]);
        regla.editionView().fields(regla.creationView().fields());

        admin.addEntity(caso);
        admin.addEntity(regla);
        admin.addEntity(requerimiento);
        admin.addEntity(ticket);

        nga.configure(admin);
    }]);


