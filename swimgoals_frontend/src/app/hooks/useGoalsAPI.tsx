import { useState } from "react";
import GoalData from "../data/GoalData";

const useGoalsApi = () => {

    const [goals, setGoals] = useState<GoalData[]>([]);

    const fetchGoalsByObjectifId = async ({ objectiveId }: { objectiveId: number }) => {
        try {
            const response = await fetch(`http://localhost:8080/api/goals/${objectiveId}`, {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            if(!response.ok) {
                throw new Error(`Erreur HTTP: ${response.status}`);
            }

            setGoals(await response.json());
            return goals;
        } catch (e) {
            console.error(e);
        }
    }

    const createGoal = async ({ objectiveId, time, date, }: { objectiveId: number; time: string; date: string; }) => {

        const newGoal = {
            objectiveId,
            time: parseInt(time, 10),
            date,
        };

        try {
            const response = await fetch("http://localhost:8080/api/goals", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(newGoal),
            });

            if (!response.ok) {
                const errorText = await response.text();
                console.error("Erreur serveur :", response.status, errorText);
                return;
            }
            
        } catch (error) {
            console.error("Erreur réseau :", error);
        }
    };

    return { goals, fetchGoalsByObjectifId, createGoal }

}

export default useGoalsApi;
