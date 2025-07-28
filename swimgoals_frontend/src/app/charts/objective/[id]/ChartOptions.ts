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
                        const minutes = Math.floor(numericValue / 3600); // Récupère les minutes
                        const secondes = Math.floor((numericValue % 3600) / 60); // Récupère les secondes

                        // Retourne le format "m:ss"
                        return `${minutes > 0 ? minutes + ":" : ""}${secondes < 10 ? "0" + secondes : secondes}`;
                    },
                },
            },
        },
};

