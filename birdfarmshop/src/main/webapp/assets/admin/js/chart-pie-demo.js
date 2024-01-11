// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
function pie(nodePie, label, color, hoverColor, data) {
    if (nodePie && nodePie.chart) {
        nodePie.chart.destroy();
    }
    var myPieChart = new Chart(nodePie, {
        type: "doughnut",
        data: {
            labels: label,
            datasets: [
                {
                    data: data,
                    backgroundColor: color,
                    hoverBackgroundColor: hoverColor,
                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                },
            ],
        },
        options: {
            maintainAspectRatio: false,
            tooltips: {
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                borderColor: "#dddfeb",
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                caretPadding: 10,
            },
            legend: {
                display: false,
            },
            cutoutPercentage: 60,
        },
    });
    nodePie.chart = myPieChart;
}
