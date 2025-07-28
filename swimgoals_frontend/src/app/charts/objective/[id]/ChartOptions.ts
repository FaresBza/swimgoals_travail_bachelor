import { ChartOptions } from "chart.js";

export const chartOptionsForXAndYAxes: ChartOptions<"line"> = {
    responsive: true,
        maintainAspectRatio: false,
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Date',
                },
            },
            y: {
                title: {
                    display: true,
                    text: 'Temps (en secondes)',
                },
                ticks: {
                    callback: function (value: string | number) {
                        const numericValue = typeof value === "number" ? value : Number(value);
                        const minutes = Math.floor(numericValue / 60);
                        const secondes = Math.floor(numericValue % 60);

                        // Retourne le format "m:ss"
                        return `${minutes < 10 ? "0" + minutes : minutes}:${secondes < 10 ? "0" + secondes : secondes}`;
                    },
                },
            },
        },
};

