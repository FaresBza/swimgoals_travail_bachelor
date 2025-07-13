// Commit message: feat(chart): add basic chart structure and API loading

"use client";

import { useParams } from "next/navigation";
import useGoalsAPI from "@/app/hooks/useGoalsAPI";
import { useEffect, useState } from "react";

const Chart = () => {
    const { id } = useParams();
    const { fetchGoalsByObjectifId } = useGoalsAPI();
    const [loading, setLoading] = useState(true);

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

    return (
        <div className="container blur" style={{ height: "500px", padding: "2rem" }}>
            <h1>Performances</h1>
            {loading ? <p>Chargement...</p> : <p>Données chargées</p>}
        </div>
    );
};

export default Chart;
