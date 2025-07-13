export const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
        x: {
            title: {
                display: true,
                text: "Date",
            },
        },
        y: {
            title: {
                display: true,
                text: "Temps (en secondes)",
            },
            ticks: {
                callback: function (tickValue: number) {
                    const minutes = Math.floor(tickValue / 60);
                    const seconds = Math.floor(tickValue % 60);
                    return `${minutes}:${seconds < 10 ? "0" + seconds : seconds}`;
                },
            },
        },
    },
};
