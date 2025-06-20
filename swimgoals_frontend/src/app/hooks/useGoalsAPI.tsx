
const useGoalsApi = () => {

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

            console.log("Le goal a bien été créé");
            
        } catch (error) {
            console.error("Erreur réseau :", error);
        }
    };

    return { createGoal }

}

export default useGoalsApi;
