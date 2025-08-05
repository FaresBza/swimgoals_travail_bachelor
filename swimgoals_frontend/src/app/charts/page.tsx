"use client";

import { useState } from "react";
import useLocalStorage from "../hooks/useLocalStorage";
import useObjectiveApi from "../hooks/useObjectiveApi";
import useGoalsApi from "../hooks/useGoalsAPI";
import { useChartInitialization, useExtractObjectiveIds, useFetchGoalsByObjectives } from "../utils/useChartInitialization";

const ChartSwimmer = () => {
    const { swimmerId, roleId, recoverUserId, recoverRoleId } = useLocalStorage();
    const { objectives, fetchObjectivesBySwimmerId } = useObjectiveApi();
    const { goals, fetchGoalsByObjectifId } = useGoalsApi();

    const [objectiveIds, setObjectiveIds] = useState<number[]>([]);

    // Appels des hooks personnalisés
    useChartInitialization(swimmerId, roleId, recoverUserId, recoverRoleId, fetchObjectivesBySwimmerId);
    useExtractObjectiveIds(objectives, setObjectiveIds);
    useFetchGoalsByObjectives(objectiveIds, fetchGoalsByObjectifId);

    return (
        <>
            <h1>Swimmer ID: {swimmerId}</h1>
            <p>Objective IDs: {objectiveIds.join(", ")}</p>

            {objectives.map(o => (
                <div key={o.id}>
                    <h3>{o.distance}m - {o.time}min</h3>
                    {goals.map(g => (
                        <div key={g.id}>
                            <p>{g.date} - {g.time}</p>
                        </div>
                    ))}
                </div>
            ))}
        </>
    );
};

export default ChartSwimmer;