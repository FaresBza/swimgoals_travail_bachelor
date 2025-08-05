// hooks/useChartSwimmerLogic.ts
import { useEffect } from "react";
import { ObjectiveGoals } from "./types"; // adapte le chemin si besoin

export const useChartInitialization = (
    swimmerId: number | null,
    roleId: number | null,
    recoverUserId: () => void,
    recoverRoleId: () => void,
    fetchObjectivesBySwimmerId: (params: { swimmerId: number }) => void
) => {
    useEffect(() => {
        recoverRoleId();
        if (roleId) recoverUserId();
        if (swimmerId) fetchObjectivesBySwimmerId({ swimmerId });
    }, [roleId, swimmerId]);
};

export const useExtractObjectiveIds = (
    objectives: ObjectiveGoals[],
    setObjectiveIds: (ids: number[]) => void
) => {
    useEffect(() => {
        if (objectives && objectives.length > 0) {
        const ids = objectives.map((o) => o.id);
        setObjectiveIds(ids);
        }
    }, [objectives]);
};

export const useFetchGoalsByObjectives = (
    objectiveIds: number[],
    fetchGoalsByObjectifId: (params: { objectiveId: number }) => void
) => {
    useEffect(() => {
        if (objectiveIds.length > 0) {
        objectiveIds.forEach((id) => {
            fetchGoalsByObjectifId({ objectiveId: id });
        });
        }
    }, [objectiveIds]);
};