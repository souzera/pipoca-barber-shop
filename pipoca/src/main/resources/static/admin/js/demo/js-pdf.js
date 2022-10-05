const { default: jsPDF } = require("jspdf");

function gerar_pdf(table){
    const doc = new jsPDF();

    doc.html(table, {
        callback: function (doc) {
            doc.save();
        },
        x: 10,
        y: 10
    });

    var hoje = new Date();
    doc.save(`relatorio${hoje.valueOf()}.pdf`);
}


function getTabela(id){
    return document.getElementById(id)
}
