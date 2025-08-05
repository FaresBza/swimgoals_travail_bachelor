import { getSwimColor } from "./SwimColorLineChart";

export const chartDataBuilder = (swimId: number, labels: string[], data: number[]) => {
    return {
        labels: labels,
        datasets: [
            {
                label: "Temps objectif",
                data: data,
                fill: false,
                borderColor: getSwimColor(swimId),
                tension: 0.2,
                pointRadius: 5,
            },
        ],
    };
}