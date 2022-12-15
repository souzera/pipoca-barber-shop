import { jsPDF } from 'jspdf'

const button = document.querySelector('#relatorio-btn')

button.addEventListener('click', () => {
    console.log('olá mundo')
})

function gerarPDF() {

    const doc = new jsPDF({
        orientation: 'landscape',
        format: 'a4'
    });


    doc.text("Hello world!", 10, 10);
    doc.save("a4.pdf");
}

