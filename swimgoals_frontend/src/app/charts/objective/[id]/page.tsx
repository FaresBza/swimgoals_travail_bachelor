// Commit message: feat(chart): add basic chart structure and API loading

"use client";

import { useParams } from "next/navigation";
import useGoalsAPI from "@/app/hooks/useGoalsAPI";
import { useEffect, useState } from "react";

const Chart = () => {
    const { id } = useParams();
    const { goals, fetchGoalsByObjectifId } = useGoalsAPI();
    const [loading, setLoading] = useState(true);

    const [chartData, setChartData] = useState<{ labels: string[]; datasets: { label: string; data: number[]; fill: boolean; borderColor: string; tension: number; pointRadius: number; }[] } | null>(null);

    useEffect(() => {
        const loadGoals = async () => {
            if (id && typeof id === "string") {
                try {
                    await fetchGoalsByObjectifId({ objectiveId: parseInt(id, 10) });
                } catch (err) {
                    console.error(`Erreur lors du chargement des performances: ${err}`);
                } finally {
                    setLoading(false);
                }
            }
        };

        loadGoals();
    }, [id, fetchGoalsByObjectifId]);

    useEffect(() => {
        if (!loading && goals.length > 0) {
            const labels = goals.map(goal => goal.date);
            const data = goals.map(goal => parseInt(goal.time.split(":")[0]) * 3600);

            setChartData({
                labels,
                datasets: [
                    {
                        label: "Temps objectif",
                        data,
                        fill: false,
                        borderColor: "rgba(75,192,192,1)",
                        tension: 0.2,
                        pointRadius: 5,
                    },
                ],
            });
        }
    }, [goals, loading]);

    return (
        <div className="container blur" style={{ height: "500px", padding: "2rem" }}>
            <h1>Performances</h1>
            {loading ? <p>Chargement...</p> : <p>Données chargées</p>}
        </div>
    );
};

export default Chart;
