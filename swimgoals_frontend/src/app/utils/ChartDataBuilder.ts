import { getSwimColor } from "./SwimColorLineChart";

export const chartDataBuilder = (swimId: number, labels: string[], data: number[], objectiveTime: number) => {
    return {
        labels: labels,
        datasets: [
            {
                label: "Temps réalisés",
                data: data,
                fill: false,
                borderColor: getSwimColor(swimId),
                tension: 0.2,
                pointRadius: 5,
            },
            {
                label: "Temps initial",
                data: Array(labels.length).fill(objectiveTime),
                fill: false,
                borderColor: "red",
                borderDash: [5, 5],
                pointRadius: 0,
                tension: 0
            }
        ],
    };
}