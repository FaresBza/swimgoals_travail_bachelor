export const chartDataBuilder = (labels: string[], data: number[]) => {
    return {
        labels: labels,
        datasets: [
            {
                label: "Temps objectif",
                data: data,
                fill: false,
                borderColor: "rgba(75,192,192,1)",
                tension: 0.2,
                pointRadius: 5,
            },
        ],
    };
}