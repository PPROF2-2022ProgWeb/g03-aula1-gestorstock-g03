const MESES = ['Enero', 'Febrero', 'Marzo', 'Abirl', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
const DIAS = ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'];

function generarDataSemanal(cantidad, inicio) {
    var labels = [], data1 = [], data2 = [], data3 = [];
    for (let i = 0; i < cantidad; i++) {
        labels.push(i + inicio || 0);
        data1.push(Math.random() * 10000);
        data2.push(Math.random() * 10000);
        data3.push(Math.random() * 10000);
    }

    return {
        labels: labels,
        dataEfectivo: data1,
        dataDebito: data2,
        dataCredito: data3
    };
}

function generarDataMensual(mes) {
    var labels = [], dataEfectivo = [], dataDebito = [], dataCredito = [];
    for (let i = 0; i < getDiasMes(mes); i++) {
        labels.push(i + 1);
        dataEfectivo.push(Math.random() * 200000);
        dataDebito.push(Math.random() * 200000);
        dataCredito.push(Math.random() * 200000);
    }
    return {
        labels: labels,
        dataEfectivo: dataEfectivo,
        dataDebito: dataDebito,
        dataCredito: dataCredito,
    }
}


function getDiasSemana(numDias) {
    var res = [];
    for (let i = 0; i < numDias.length; i++) {
        res.push(DIAS[numDias[i] % DIAS.length]);
    }
    return res
}

function getDiasMes(mes) {
    switch (mes) {
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;

        case 2:
            return 28;

        default:
            return 31;
    }
}

let rawMensual = generarDataMensual(2);
let rawSemanal = {
    labels: rawMensual.labels.slice(-7),
    dataEfectivo: rawMensual.dataEfectivo.slice(-7),
    dataDebito: rawMensual.dataDebito.slice(-7),
    dataCredito: rawMensual.dataCredito.slice(-7)
};
let dataSemanal = {
    labels: getDiasSemana(rawSemanal.labels),
    datasets: [
        {
            label: "Efectivo",
            backgroundColor: "#00324a",
            borderColor: "#00324a",
            borderWidth: 2,
            data: rawSemanal.dataEfectivo,
        },
        {
            label: "Debito",
            backgroundColor: "#ff7530",
            borderColor: "#ff7530",
            borderWidth: 2,
            data: rawSemanal.dataDebito,
        },
        {
            label: "Credito",
            backgroundColor: "#4a3200",
            borderColor: "#4a3200",
            borderWidth: 2,
            data: rawSemanal.dataCredito,
        }
    ]
};
var opcionesSemanal = {
    maintainAspectRatio: false,
    scales: {
        y: {
            grid: {
                display: true,
                color: "rgba(50,50,50,0.2)"
            },
            title: {
                display: true,
                text: 'Ingresos ($)',
                color: '#00324a',
                font: {
                    family: 'Varela Round',
                    size: 20,
                    lineHeight: 1.2,
                },
                padding: { top: 20, left: 0, right: 0, bottom: 0 }
            }
        },
        x: {
            grid: {
                display: false
            }
        }
    },
    plugins: {
        title: {
            display: true,
            text: 'Ingresos de los ultimos 7 dias'
        }
    },
    interaction: {
        intersect: false
    },
    animation: true,
    tension: 0
};

let dataMensual = {
    labels: rawMensual.labels,
    datasets: [
        {
            label: 'Efectivo',
            backgroundColor: "#00324a",
            borderColor: "#00324a",
            borderWidth: 2,
            data: rawMensual.dataEfectivo,
        },
        {
            label: 'Debito',
            backgroundColor: "#ff7530",
            borderColor: "#ff7530",
            borderWidth: 2,
            data: rawMensual.dataDebito,
        },
        {
            label: 'Credito',
            backgroundColor: "#4a3200",
            borderColor: "#4a3200",
            borderWidth: 2,
            data: rawMensual.dataCredito,
        }
    ]
};
var opcionesMensual = {
    maintainAspectRatio: false,
    scales: {
        y: {
            grid: {
                display: true,
                color: "rgba(50,50,50,0.2)"
            },
            title: {
                display: true,
                text: 'Ingresos ($)',
                color: '#00324a',
                font: {
                    family: 'Varela Round',
                    size: 20,
                    lineHeight: 1.2,
                },
                padding: { top: 20, left: 0, right: 0, bottom: 0 }
            }
        },
        x: {
            grid: {
                display: true,
                color: "rgba(50,50,50,0.2)"
            }
        }
    },
    plugins: {
        title: {
            display: true,
            text: 'Ingresos del mes anterior',
        }
    },
    interaction: {
        intersect: false
    },
    animation: true,
    tension: 0
};


// Inicio graficos dona
var opcionesDona = {
    responsive: true,
    cutout: '80%',
    tooltips: {
        // Disable the on-canvas tooltip
        enabled: false
    }
};


const formatter = new Intl.NumberFormat('es-AR', {
        style: 'currency', currency: 'ARS',
        minimumFractionDigits: 2
    });

let periodoSemanal = true; //true = Semanal,  false = Mensual
const ingEfectivo = () => periodoSemanal ? rawSemanal.dataEfectivo.reduce((a, b) => a + b, 0) : rawMensual.dataEfectivo.reduce((a, b) => a + b, 0);
const ingDebito = () => periodoSemanal ? rawSemanal.dataDebito.reduce((a, b) => a + b, 0) : rawMensual.dataDebito.reduce((a, b) => a + b, 0);
const ingCredito = () => periodoSemanal ? rawSemanal.dataCredito.reduce((a, b) => a + b, 0) : rawMensual.dataCredito.reduce((a, b) => a + b, 0);
const ingTotal = () => ingCredito() + ingDebito() + ingEfectivo();
// Fin graficos dona

// Empieza control de periodos
function cambiarGrafico(selectorDestino, idGrafico, tipo, data, opciones) {
    console.log(selectorDestino);
    console.log(data);
    document.querySelector(selectorDestino).innerHTML = `<canvas id="${idGrafico}"></canvas>`
    graficoPeriodo = new Chart(idGrafico, {
        type: tipo,
        options: opciones,
        data: data
    });
}
const semanal = document.querySelector('#semanal');
const mensual = document.querySelector('#mensual');

semanal.onclick = () => {
    if (!semanal.classList.contains('active')) {
        periodoSemanal = true;
        cambiarGrafico('#graf-ingresos', 'chart', 'line', dataSemanal, opcionesSemanal);
        cambiarGrafico('#efectivo > .grafico', 'chart-efectivo', 'doughnut', {
            datasets: [{
                label: 'My First Dataset',
                data: [ingEfectivo(), ingTotal() - ingEfectivo()],
                backgroundColor: [
                    '#00324a',
                    '#ddd'
                ]
            }]
        }, opcionesDona);
        cambiarGrafico('#debito > .grafico', 'chart-debito', 'doughnut', {
            datasets: [{
                label: 'My First Dataset',
                data: [ingDebito(), ingTotal() - ingDebito()],
                backgroundColor: [
                    '#ff7530',
                    '#ddd'
                ]
            }]
        }, opcionesDona);

        cambiarGrafico('#credito > .grafico', 'chart-cerdito', 'doughnut', {
            datasets: [{
                label: 'My First Dataset',
                data: [ingCredito(), ingTotal() - ingCredito()],
                backgroundColor: [
                    '#4a3200',
                    '#ddd'
                ]
            }]
        }, opcionesDona);

        document.querySelector('#semanal').classList.toggle('active');
        document.querySelector('#mensual').classList.toggle('active');

        document.querySelector('#efectivo>.info>.valor').textContent = formatter.format(ingEfectivo());
        document.querySelector('#debito>.info>.valor').textContent = formatter.format(ingDebito());
        document.querySelector('#credito>.info>.valor').textContent = formatter.format(ingCredito());
    }
}

mensual.onclick = () => {
    if (!mensual.classList.contains('active')) {
        periodoSemanal = false;
        cambiarGrafico('#graf-ingresos', 'chart', 'line', dataMensual, opcionesMensual);

        cambiarGrafico('#efectivo > .grafico', 'chart-efectivo', 'doughnut', {
            datasets: [{
                label: 'My First Dataset',
                data: [ingEfectivo(), ingTotal() - ingEfectivo()],
                backgroundColor: [
                    '#00324a',
                    '#ddd'
                ]
            }]
        }, opcionesDona);

        cambiarGrafico('#debito > .grafico', 'chart-debito', 'doughnut', {
            datasets: [{
                label: 'My First Dataset',
                data: [ingDebito(), ingTotal() - ingDebito()],
                backgroundColor: [
                    '#ff7530',
                    '#ddd'
                ]
            }]
        }, opcionesDona);

        cambiarGrafico('#credito > .grafico', 'chart-credito', 'doughnut', {
            datasets: [{
                label: 'My First Dataset',
                data: [ingCredito(), ingTotal() - ingCredito()],
                backgroundColor: [
                    '#4a3200',
                    '#ddd'
                ]
            }]
        }, opcionesDona);

        document.querySelector('#semanal').classList.toggle('active');
        document.querySelector('#mensual').classList.toggle('active');

        document.querySelector('#efectivo>.info>.valor').textContent = formatter.format(ingEfectivo());
        document.querySelector('#debito>.info>.valor').textContent = formatter.format(ingDebito());
        document.querySelector('#credito>.info>.valor').textContent = formatter.format(ingCredito());
    }
}
// Termina control de periodos

document.querySelector('#semanal').classList.add('active');
let graficoPeriodo = new Chart('chart', {
    type: 'line',
    options: opcionesSemanal,
    data: dataSemanal
});

document.querySelector('#efectivo>.info>.valor').textContent = formatter.format(ingEfectivo());
document.querySelector('#debito>.info>.valor').textContent = formatter.format(ingDebito());
document.querySelector('#credito>.info>.valor').textContent = formatter.format(ingCredito());
let grafEfectivo = new Chart('chart-efectivo', {
    type: 'doughnut',
    options: opcionesDona,
    data: {
        datasets: [{
            label: 'My First Dataset',
            data: [ingEfectivo(), ingTotal() - ingEfectivo()],
            backgroundColor: [
                '#00324a',
                '#ddd'
            ]
        }]
    }
})
let grafDebito = new Chart('chart-debito', {
    type: 'doughnut',
    options: opcionesDona,
    data: {
        datasets: [{
            label: 'My First Dataset',
            data: [ingDebito(), ingTotal() - ingDebito()],
            backgroundColor: [
                '#ff7530',
                '#ddd'
            ]
        }]
    }
})

let grafCredito = new Chart('chart-credito', {
    type: 'doughnut',
    options: opcionesDona,
    data: {
        datasets: [{
            label: 'My First Dataset',
            data: [ingCredito(), ingTotal() - ingCredito()],
            backgroundColor: [
                '#4a3200',
                '#ddd'
            ]
        }]
    }
})

dataMensual

