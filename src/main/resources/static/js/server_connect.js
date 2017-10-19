var table;


$(document).ready(function(){
    table = $('#searchResultTable').DataTable({
        stateSave: true,
        searching: false,
        columnDefs: [
            { width: 200, targets: 1 }
        ]
    });
});


$(function() {
    //  searchボタンが押された時
    $('body').on('click', 'button[data-btn-type=search]', function(e) {
        console.log('click btn search');
        //  リクエストの下準備

        var formData = new FormData();
        var $file = $('#file');
        var $birthmark = $('#birthmark');
        var $threshold = $('#threshold');
        console.log($file.attr('name'));
        formData.append($file.attr('name'), $file.prop("files")[0]);
        formData.append($birthmark.attr('name'), $birthmark.val());
        formData.append($threshold.attr('name'), $threshold.val());
        console.log($file);
        console.log($birthmark);
        console.log($threshold);
        console.log(formData.get("file"));
        $.ajax({
            //  リクエストの内容
            type: "POST",
            url: "file",
            data: formData,
            enctype: 'json',
            contentType: 'application/json',
            processData: false,
            contentType: false,
        }).done(function( res ){
            console.log( 'SUCCESS', res);
            console.log( res.toString());
            console.log(JSON.parse(res.toString()));
            $('#searchResult').val(res.toString());
            console.log($('#searchResult').val());

            document.getElementById("searchResultZone").style.display="block";

            table.destroy();

            table = $('#searchResultTable').removeAttr('width').DataTable({
                data: JSON.parse(res.toString()),
                columns: [
                    { data: 'postedClassFile' },
                    { data: 'resultClassFile' },
                    { data: 'sim' },
                    { data: 'jar' },
                    { data: 'groupId' },
                    { data: 'artifactId' },
                    { data: 'version' }
                ],
                searching: false,
                scrollY:        "300px",
                scrollX:        true,
                scrollCollapse: true,
                paging:         false,
                columnDefs: [
                    { width: 200, targets: 0 }
                ],
                fixedColumns: true
                // "scrollY":        "400px",
                // "scrollCollapse": true,
                // "paging":         false
                //
                // autoWidth: false,
                // columnDefs: [
                //     { targets: 1, width: '20%' }
                // ]
            });
            return true;

            // var tableHeaderRowCount = 1;
            // var table = document.getElementById('searchResultTable');
            // var rowCount = table.rows.length;
            // for (var i = tableHeaderRowCount; i < rowCount; i++) {
            //     table.deleteRow(tableHeaderRowCount);
            // }
            // var items = [];
            // $.each(JSON.parse(res.toString()), function (key, val) {
            //     items.push("<tr>");
            //     items.push("<td id=''"+key+"''>"+val.postedClassFile+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.resultClassFile+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.jar+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.groupId+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.artifactId+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.version+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.sim+"</td>");
            //     items.push("</tr>");
            // });
            // $("<tbody/>", {html: items.join("")}).appendTo("table");
            // localStorage.setItem('searchResult', res.toString());
            // document.getElementById('searchResult').value = res.toString();
        }).fail(function(jqXHR, textStatus, errorThrown){
            console.log( 'ERROR', jqXHR, textStatus, errorThrown);
        });
        return false;
    });


});

$(function() {
    //  searchボタンが押された時
    $('body').on('click', 'button[data-btn-type=compare]', function(e) {
        console.log('click btn compare');
        //  リクエストの下準備
        //
        var $searchResult = $('#searchResult');
        var $birthmark = $('#birthmark');
        var formData = new FormData();
        formData.append($searchResult.attr('name'), $searchResult.val());
        formData.append($birthmark.attr('name'), $birthmark.val());
        console.log($searchResult.val());
        console.log('compare');
        $.ajax({
            //  リクエストの内容
            type: "POST",
            url: "compare",
            data: formData,
            enctype: 'json',
            processData: false,
            contentType: false,
        }).done(function( res ){
            console.log( 'SUCCESS', res);
            console.log( res.toString());
            console.log(JSON.parse(res.toString()));
            $('#searchResult').val(res.toString());
            console.log($('#searchResult').val());

            document.getElementById("searchResultZone").style.display="block";

            table.destroy();

            table = $('#searchResultTable').removeAttr('width').DataTable({
                data: JSON.parse(res.toString()),
                columns: [
                    { data: 'postedClassFile' },
                    { data: 'resultClassFile' },
                    { data: 'jar' },
                    { data: 'groupId' },
                    { data: 'artifactId' },
                    { data: 'version' },
                    { data: 'sim' }
                ],
                searching: false,
                scrollY:        "300px",
                scrollX:        true,
                scrollCollapse: true,
                paging:         false,
                columnDefs: [
                    { width: 200, targets: 0 }
                ],
                fixedColumns: true
                // "scrollY":        "400px",
                // "scrollCollapse": true,
                // "paging":         false
                //
                // autoWidth: false,
                // columnDefs: [
                //     { targets: 1, width: '20%' }
                // ]
            });
            return true;

            // var tableHeaderRowCount = 1;
            // var table = document.getElementById('searchResultTable');
            // var rowCount = table.rows.length;
            // for (var i = tableHeaderRowCount; i < rowCount; i++) {
            //     table.deleteRow(tableHeaderRowCount);
            // }
            // var items = [];
            // $.each(JSON.parse(res.toString()), function (key, val) {
            //     items.push("<tr>");
            //     items.push("<td id=''"+key+"''>"+val.postedClassFile+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.resultClassFile+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.jar+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.groupId+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.artifactId+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.version+"</td>");
            //     items.push("<td id=''"+key+"''>"+val.sim+"</td>");
            //     items.push("</tr>");
            // });
            // $("<tbody/>", {html: items.join("")}).appendTo("table");
            // localStorage.setItem('searchResult', res.toString());
            // document.getElementById('searchResult').value = res.toString();
        }).fail(function(jqXHR, textStatus, errorThrown){
            console.log( 'ERROR', jqXHR, textStatus, errorThrown);
        });
        return false;
    });


});
