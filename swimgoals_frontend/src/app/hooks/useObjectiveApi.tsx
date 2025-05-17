import { useState } from "react";
import ObjectiveData from "../data/ObjectiveData";
import SwimMapping from "../mapping/SwimMapping";
import { useRouter } from "next/navigation";


const useObjectiveApi = () => {
    const route = useRouter();

    const [objectives, setObjectives] = useState<ObjectiveData[]>([]);

    const fetchObjectivesBySwimmerId = async ({ swimmerId }: { swimmerId: number }) => {
        try {
            const response = await fetch(`http://localhost:8080/api/objectives/swimmer/${swimmerId}`, {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            if(!response.ok) {
                throw new Error(`Erreur HTTP: ${response.status}`);
            }

            setObjectives(await response.json());
            return objectives;

        } catch (e) {
            console.error(e);
        }
    }

    const createObjective = async ({ swim, swimmerId, distance, time }: ObjectiveData) => {
        const swimId = SwimMapping[swim];

        const newObjective = { swimId, swimmerId, distance, time };

        try {
            const response = await fetch("http://localhost:8080/api/objectives", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newObjective),
            });

            if (response.ok) {
                console.log("l'objectif a bien été créer")
                route.push("/objectives");
            }

        } catch (error) {
            console.error("Erreur réseau : ", error);
        }
    }

    return {
        objectives,
        fetchObjectivesBySwimmerId,
        createObjective,
    }

}

export default useObjectiveApi;